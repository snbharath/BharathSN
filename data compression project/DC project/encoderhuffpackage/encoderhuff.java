package encoderhuffpackage;

import java.io.*;
import javax.swing.*;
import HuffmanNodepackage.*;
import PriorityQueuepackage.*;
import Tablepackage.*;

public class encoderhuff
{
	private static	String code[],summary="";
	private	int totalBytes=0;
	private int count=0;
	private File inputFile;
	private File outputFile ;
	private FileOutputStream C;
	private ObjectOutputStream outF;
	private BufferedOutputStream outf;
	private FileInputStream in1;
	private BufferedInputStream in;	
	private boolean done=false;
	String dest="";

	public  encoderhuff(File inputFile,String dest)
	{
		this.dest=dest;
		this.inputFile=inputFile;
	}
    	public void encode()
	{
		int freq[]=new int[256];
		for(int i=0;i<256;i++)
		{
			freq[i]=0;
		}		
//        	File inputFile = new File(JOptionPane.showInputDialog("Enter the input file name"));
		try
		{
        		in1 = new FileInputStream(inputFile);
			in=new 	BufferedInputStream(in1);	
		}
		catch(Exception eee)
		{
		
		}
  		try
		{
			System.out.println(" "+in.available());
			totalBytes=in.available();
			int mycount=0;
			in.mark(totalBytes);
			while (mycount<totalBytes)
			{		
				int a=in.read();			
				mycount++;
				freq[a]++;
			}
			in.reset();			
		}
		catch(IOException eofexc)
		{
			System.out.println("error");
		}
		HuffmanNode tree=new HuffmanNode(),one,two;
		PriorityQueue q=new PriorityQueue();

		try
		{
			for(int j=0;j<256;j++)
			{
//				System.out.println("\n"+byteval[j]+" "+freq[j]+"   prob  "+probablity[j]+"int value"+toInt(byteval[j]));
				if (freq[j]>0)
				{
					HuffmanNode t=new HuffmanNode("dipu",freq[j],j,null,null,null);
					q.insertM(t);				
				}
	
			}
			//create tree....................................
			while (q.sizeQ()>1)
			{
				one=q.removeFirst();
				two=q.removeFirst();
				int f1=one.getFreq();
				int f2=two.getFreq();
				if (f1>f2)
				{
					HuffmanNode t=new HuffmanNode(null,(f1+f2),0,two,one,null);			
					one.up=t;
					two.up=t;
					q.insertM(t);	
				}
				else
				{
					HuffmanNode t=new HuffmanNode(null,(f1+f2),0,one,two,null);			
					one.up=t;
					two.up=t;
					q.insertM(t);			
				}	
			}	
			tree =q.removeFirst();
		}
		catch(Exception e)
		{
			System.out.println("Priority Queue error");
		}
		code=new String[256];
		for(int i=0;i<256;i++)
		code[i]="";

		traverse(tree);

		Table rec=new Table(totalBytes,inputFile.getName());	
		for(int i=0;i<256;i++)
		{
			rec.push(freq[i]);
			if(freq[i]==0)
			continue;
//			System.out.println(""+i+" "+code[i]+" ");
		}
//		System.out.println("size of table"+rec.recSize());
		//create tree ends...........................

//		System.out.println("\n total= "+totalBytes+"\n probablity="+d);
		int wrote=0,csize=0;
		int  recordLast=0;
		try
		{
			outputFile = new File(dest+inputFile.getName()+".comp");
			C=new FileOutputStream(outputFile);
			outF=new ObjectOutputStream(C);
			outf=new BufferedOutputStream(C);
			outF.writeObject(rec);
			String outbyte="";

			while (count<totalBytes)
			{
				outbyte+=code[in.read()];
				count++;	
				if (outbyte.length()>=8)
				{
					int k=toInt(outbyte.substring(0,8));
					csize++;
					outf.write(k);
					outbyte=outbyte.substring(8);
				}
			}
			while(outbyte.length()>8)
			{
				csize++;
				int k=toInt(outbyte.substring(0,8));
				outf.write(k);
				outbyte=outbyte.substring(8);
			}
			if((recordLast=outbyte.length())>0)
			{
				while(outbyte.length()<8)
					outbyte+=0;
				outf.write(toInt(outbyte));		
				csize++;
			}
			outf.write(recordLast);
			outf.close();
		}
		catch(Exception re)
		{
			System.out.println("Error in writng.... "+re.toString());
		}

		float ff=(float)csize/((float)totalBytes);
		System.out.println("Compression "+recordLast+" ratio"+csize+" "+(ff*100)+" %");		

		
		summary+="File name : "+ inputFile.getName();
		summary+="\n";	

		summary+="File size : "+totalBytes+" bytes.";
		summary+="\n";	
		
		summary+="Compressed size : "+ csize+" bytes.";
		summary+="\n";	
	
		summary+="Compression ratio: "+(ff*100)+" %";
		summary+="\n";	

		done=true;
	}

	public void traverse(HuffmanNode n)
	{
		if (n.lchild==null&&n.rchild==null)
		{
			HuffmanNode m=n;
			int arr[]=new int[20],p=0;
			while (true)
			{
				if (m.up.lchild==m)
				{
					arr[p]=0;
				}
				else
				{
					arr[p]=1;
				}
				p++;
				m=m.up;
				if(m.up==null)
				break;
			}
			for(int j=p-1;j>=0;j--)
			code[n.getValue()]+=arr[j];			
		}
//		System.out.println("Debug3");		
		if(n.lchild!=null)
		traverse(n.lchild);
		if(n.rchild!=null)
		traverse(n.rchild);
	}

	private  String toBinary(int b)
	{
		int arr[]=new int[8];
		String s="";
    		for(int i=0;i<8;i++)
    		{
    			arr[i]=b%2;
    			b=b/2;			
    	
    		}
    		for(int i=7;i>=0;i--)
    		{
    			s+=arr[i];
    		}
    		return s;
    	}
	
    	private int toInt(String b)
    	{
    		int output=0,wg=128;
    		for(int i=0;i<8;i++)
    		{
	   		output+=wg*Integer.parseInt(""+b.charAt(i));
			wg/=2;
     		}
    		return output;
    	}
	
	public int lengthOftask()
	{
		return totalBytes;
	}
	public int getCurrent()
	{
		return count;
	}
	public String getSummary()
	{
		String temp=summary;
		summary="";
		return temp;
	}
	public boolean isDone()
	{
		return done;	
	}

}
