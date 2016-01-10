package decoderhuffpackage;

import java.io.*;
import javax.swing.*;
import HuffmanNodepackage.*;
import PriorityQueuepackage.*;
import Tablepackage.*;

public class decoderhuff
{
	private int totalBytes=0,mycount=0;
	private int freq[],arr=0;
	private String summary="";
	private File inputFile;
	private Table table;
	private FileInputStream in1;
	private ObjectInputStream inF;
	private BufferedInputStream in;		
	private File outputFile ;
	private FileOutputStream outf;
	String dest="";

	public decoderhuff(File file,String dest)
	{
		this.dest=dest;
		inputFile=file;
	}
	public void decode()//throws Exception
	{
		freq=new int[256];
		for(int i=0;i<256;i++)
		{
			freq[i]=0;
		}	
    	  //  File inputFile = new File(JOptionPane.showInputDialog("Enter the input File name"));
		
		try
		{
        		in1 = new FileInputStream(inputFile);
			inF=new ObjectInputStream(in1);
			in=new 	BufferedInputStream(in1);

//			int arr=0;
			table=(Table)(inF.readObject());

			//System.out.println(dest+table.fileName());

			outputFile = new File(dest+table.fileName());
			outf=new FileOutputStream(outputFile);
			
			summary+="File name : "+ table.fileName();
			summary+="\n";	
		}
		catch(Exception exc)
		{
			System.out.println("Error creating file");
			JOptionPane.showMessageDialog(null,"Error"+"\nNot a valid < .comp > format file. please input the valid one.","Summary",JOptionPane.INFORMATION_MESSAGE);
		}

		HuffmanNode tree=new HuffmanNode(),one,two;
		PriorityQueue q=new PriorityQueue();
		
		try
		{
		
		//creating priority queue.................
			
			for(int j=0;j<256;j++)
			{
				int r =table.pop();
			//	System.out.println("Size of table "+r+" "+j);			
				if (r>0)
				{
					HuffmanNode t=new HuffmanNode("dipu",r,j,null,null,null);
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
		catch(Exception exc)
		{
			System.out.println("Priority queue exception");
		}
		String s="";
		try
		{
			mycount=in.available();
			while (totalBytes<mycount)
			{
				arr=in.read();
				s+=toBinary(arr);
				while (s.length()>32)
				{	
					for(int a=0;a<32;a++)
					{
						int wr=getCode(tree,s.substring(0,a+1));
						if(wr==-1)continue;
						else
						{
							outf.write(wr);
							s=s.substring(a+1);
							break;
						}							
					}
				}
				totalBytes++;	
			}
			s=s.substring(0,(s.length()-8));
			s=s.substring(0,(s.length()-8+arr));
			int counter;
			while (s.length()>0)
			{
				if(s.length()>16)counter=16;
				else counter=s.length();
				for(int a=0;a<counter;a++)
				{
					int wr=getCode(tree,s.substring(0,a+1));
					if(wr==-1)continue;
					else
					{
						outf.write(wr);
						s=s.substring(a+1);
						break;
					}
				}
			}
			outf.close();
		}
		catch(IOException eofexc)
		{
			System.out.println("IO error");
		}
		summary+="Compressed size : "+ mycount+" bytes.";
		summary+="\n";	
		summary+="Size after decompressed : "+table.originalSize()+" bytes.";
		summary+="\n";
	}

	private int getCode(HuffmanNode node,String decode)
    	{
		while (true)
		{
			if (decode.charAt(0)=='0')
			{
				node=node.lchild;
			}
			else
			{
				node=node.rchild;
			}
			if (node.lchild==null&&node.rchild==null)
			{
				return node.getValue();
			}
			if(decode.length()==1)break;
			decode=decode.substring(1);	
		}
		return -1;
    	}

	public  String toBinary(int b)
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

	public int toInt(String b)
    	{
    		int output=0,wg=128;
	    	for(int i=0;i<8;i++)
    		{
	   		output+=wg*Integer.parseInt(""+b.charAt(i));
			wg/=2;
	     	}	
    		return output;
	}
	public int getCurrent()
	{
		return totalBytes;
	}

	public int lengthOftask()
	{
		return mycount;
	}
	public String getSummary()
	{
		return summary;
	}
}


