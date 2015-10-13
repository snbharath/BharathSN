import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import decoderhuffpackage.*;
import encoderhuffpackage.*;

public class huffman extends JApplet
{
	public void init()
	{
		//huffman.this.setTitle("HUFFMAN TEXT COMPRESSION ALGORITHM");
		JTabbedPane tab=new JTabbedPane();
		tab.add("Compress",new Compresshuff());
		tab.add("Decompress",new Decompresshuff());

		Container con=getContentPane();
		JScrollPane jsp=new JScrollPane(tab,20,30);
		con.add(jsp);
	}
}


class Compresshuff extends JPanel implements ItemListener,ActionListener
{
	float part1=-1,part2=-1,part3=-1,part4=-1;
	int file_size;
	File inputfile;
	JTextArea info=new JTextArea(20,100),frag_info1=new JTextArea(10,20),frag_info2=new JTextArea(10,20),frag_info3=new JTextArea(10,20),frag_info4=new JTextArea(10,20);
	JTextField fname=new JTextField(30),fname_d=new JTextField(30);
	JButton but_compress = new JButton("Compress"),comp_frag=new JButton("Compress fragments"),browse1=new JButton("Browse..."),browse2=new JButton("Browse...");
	Choice compress_part=new Choice();
	BufferedInputStream bin;
	FileInputStream fin;
	String file_content="";

	Compresshuff()
	{
		JPanel p1=new JPanel(),p2=new JPanel(),p3=new JPanel(),p4=new JPanel();
		JLabel l1=new JLabel("enter the entire path of the filename to be compressed :- "),l2=new JLabel("select how many parts to be made:");
		JLabel pa1=new JLabel("file 1:"),pa2=new JLabel("file 2:"),pa3=new JLabel("file 3:"),pa4=new JLabel("file 4:"),l3=new JLabel("enter the destination path:");
		setLayout(null);
		fname.setEditable(true);
		fname_d.setEditable(true);
		p1.add(l1);
		p1.add(fname);
		add(p1);
		p1.setBounds(100,70,400,60);

		p4.add(l3);p4.add(fname_d);
		p4.setBounds(600,70,400,60);
		add(p4);

		compress_part.add("select");compress_part.add("2");compress_part.add("3");compress_part.add("4");
		compress_part.addItemListener(this);

		p2.add(l2);p2.add(compress_part);
		info.setBounds(200,200,500,100);
		info.setEditable(false);
		add(info);

		p2.setBounds(300,300,400,60);
		p2.add(comp_frag);comp_frag.addActionListener(this);comp_frag.setEnabled(true);
		add(p2);

		p3.add(frag_info1);p3.add(frag_info2);p3.add(frag_info3);p3.add(frag_info4);
		frag_info1.setEditable(false);frag_info2.setEditable(false);frag_info3.setEditable(false);frag_info4.setEditable(false);
		p3.setBounds(30,450,1200,200);
		add(p3);

		pa1.setBounds(200,430,50,20);pa2.setBounds(420,430,50,20);pa3.setBounds(640,430,50,20);pa4.setBounds(860,430,50,20);
		add(pa1);add(pa2);add(pa3);add(pa4);

		browse1.addActionListener(this);
		browse1.setBounds(500,90,100,30);
		add(browse1);
		browse1.setEnabled(true);

		browse2.addActionListener(this);
		browse2.setBounds(1000,90,100,30);
		add(browse2);
		browse2.setEnabled(true);

		but_compress.addActionListener(this);
		but_compress.setBounds(400,150,100,30);
		add(but_compress);
		but_compress.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==browse2)
		{
			JFileChooser choose=new JFileChooser();
			int f=choose.showOpenDialog(Compresshuff.this);
			choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			choose.setAcceptAllFileFilterUsed(false);
			if(f==JFileChooser.APPROVE_OPTION)
			{
				inputfile=choose.getSelectedFile();
				fname_d.setText(((choose.getCurrentDirectory()).getPath())+"\\");
			}
		}
		if(e.getSource()==browse1)
		{
			JFileChooser choose=new JFileChooser();
			int f=choose.showOpenDialog(Compresshuff.this);
			fname_d.setText(((choose.getCurrentDirectory()).getPath())+"\\");
			if(f==JFileChooser.APPROVE_OPTION)
			{
				inputfile=choose.getSelectedFile();
				fname.setText(inputfile.getPath());
			}
		}
		if(e.getSource()==but_compress)
		{
			try
			{
				inputfile=new File(fname.getText());
				if(inputfile.exists()==false)
				{
					JOptionPane.showMessageDialog(null,"File not Exists...","ERROR",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					encoderhuffpackage.encoderhuff encode_obj=new encoderhuffpackage.encoderhuff(inputfile,fname_d.getText());
					//System.out.println("file size: "+file_size);
					encode_obj.encode();
					info.setText(encode_obj.getSummary());
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		if(e.getSource()==comp_frag)
		{
			frag_info1.setText("");
			frag_info2.setText("");
			frag_info3.setText("");
			frag_info4.setText("");
			if(compress_part.getSelectedItem()=="select")
			{
				JOptionPane.showMessageDialog(null,"Select atleast one option in the dropdown menu","Fatal error",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				File f=new File(".");
				String fnames[]=f.list();
				for(int i=0;i<fnames.length;i++)
				{
					if(fnames[i].equals("1.txt.comp")||fnames[i].equals("2.txt.comp")||fnames[i].equals("3.txt.comp")||fnames[i].equals("4.txt.comp"))
					{
						File del=new File(fnames[i]);
						del.delete();
					}
				}

				try
				{
				if((part1!=-1)&&(part2!=-1)&&(part3!=-1)&&(part4!=-1))
				{
					PrintWriter p=null;
					BufferedReader reader= new BufferedReader( new FileReader( fname.getText()));
					String data,con1,con2,con3,con4;
					while((data=reader.readLine())!=null)
					{
						file_content+=data+"\n";
					}
					con1=file_content.substring(0,(int)(part1));
					con2=file_content.substring((int)(part1+1),(int)(part1+part2));
					con3=file_content.substring((int)(part1+part2+1),(int)(part1+part2+part3));
					con4=file_content.substring((int)(part1+part2+part3+1),(int)(part1+part2+part3+part4));

					p=new PrintWriter(new FileWriter("1.txt"));
					p.println(con1);
					p.close();

					p=new PrintWriter(new FileWriter("2.txt"));
					p.println(con2);
					p.close();

					p=new PrintWriter(new FileWriter("3.txt"));
					p.println(con3);
					p.close();

					p=new PrintWriter(new FileWriter("4.txt"));
					p.println(con4);
					p.close();

					encoderhuffpackage.encoderhuff encode_obj1=new encoderhuffpackage.encoderhuff( (new File("1.txt")) ,"");
					encode_obj1.encode();
					frag_info1.setText(encode_obj1.getSummary());

					encoderhuffpackage.encoderhuff encode_obj2=new encoderhuffpackage.encoderhuff( (new File("2.txt")) , "");
					encode_obj2.encode();
					frag_info2.setText(encode_obj2.getSummary());

					encoderhuffpackage.encoderhuff encode_obj3=new encoderhuffpackage.encoderhuff( (new File("3.txt")) , "");
					encode_obj3.encode();
					frag_info3.setText(encode_obj3.getSummary());

					encoderhuffpackage.encoderhuff encode_obj4=new encoderhuffpackage.encoderhuff( (new File("4.txt")) ,"");
					encode_obj4.encode();
					frag_info4.setText(encode_obj4.getSummary());

				}
				else if((part1!=-1)&&(part2!=-1)&&(part3!=-1))
				{
					PrintWriter p=null;
					BufferedReader reader= new BufferedReader( new FileReader( fname.getText()));
					String data,con1,con2,con3;
					while((data=reader.readLine())!=null)
					{
						file_content+=data+"\n";
					}
					con1=file_content.substring(0,(int)(part1));
					con2=file_content.substring((int)(part1+1),(int)(part1+part2));
					con3=file_content.substring((int)(part1+part2+1),(int)(part1+part2+part3));

					p=new PrintWriter(new FileWriter("1.txt"));
					p.println(con1);
					p.close();

					p=new PrintWriter(new FileWriter("2.txt"));
					p.println(con2);
					p.close();

					p=new PrintWriter(new FileWriter("3.txt"));
					p.println(con3);
					p.close();

					encoderhuffpackage.encoderhuff encode_obj1=new encoderhuffpackage.encoderhuff( (new File("1.txt")) , "");
					encode_obj1.encode();
					frag_info1.setText(encode_obj1.getSummary());

					encoderhuffpackage.encoderhuff encode_obj2=new encoderhuffpackage.encoderhuff( (new File("2.txt")) , "");
					encode_obj2.encode();
					frag_info2.setText(encode_obj2.getSummary());

					encoderhuffpackage.encoderhuff encode_obj3=new encoderhuffpackage.encoderhuff( (new File("3.txt")) , "");
					encode_obj3.encode();
					frag_info3.setText(encode_obj3.getSummary());
				}
				else if((part1!=-1)&&(part2!=-1))
				{
					PrintWriter p=null;
					BufferedReader reader= new BufferedReader( new FileReader( fname.getText()));
					String data,con1,con2;
					while((data=reader.readLine())!=null)
					{
						file_content+=data+"\n";
					}
					con1=file_content.substring(0,(int)(part1));
					con2=file_content.substring((int)(part1+1),(int)(part1+part2));

					p=new PrintWriter(new FileWriter("1.txt"));
					p.println(con1);
					p.close();

					p=new PrintWriter(new FileWriter("2.txt"));
					p.println(con2);
					p.close();

					encoderhuffpackage.encoderhuff encode_obj1=new encoderhuffpackage.encoderhuff( (new File("1.txt")) , "");
					encode_obj1.encode();
					frag_info1.setText(encode_obj1.getSummary());

					encoderhuffpackage.encoderhuff encode_obj2=new encoderhuffpackage.encoderhuff( (new File("2.txt","")) , "");
					encode_obj2.encode();
					frag_info2.setText(encode_obj2.getSummary());
				}
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}

				f=new File(".");
				String fnames1[]=f.list();
				for(int i=0;i<fnames.length;i++)
				{
					if(fnames[i].equals("1.txt")||fnames[i].equals("2.txt")||fnames[i].equals("3.txt")||fnames[i].equals("4.txt"))
					{
						File del=new File(fnames[i]);
						del.delete();
					}
				}
			}
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		part1=-1;part2=-1;part3=-1;part4=-1;
		if(fname.getText()!=null)
		{
			try
			{
			fin=new FileInputStream(fname.getText());
			bin=new BufferedInputStream(fin);
			file_size=bin.available();
			String s1=compress_part.getSelectedItem();
			if(s1=="2")
			{
				int div=Integer.parseInt(s1),rem;
				rem=file_size%div;
				file_size=file_size-rem;
				part1=file_size/div;
				part2=part1+rem;
			}
			if(s1=="3")
			{
				int div=Integer.parseInt(s1),rem;
				rem=file_size%div;
				file_size=file_size-rem;
				part1=file_size/div;
				part2=part1;
				part3=part1+rem;
			}
			if(s1=="4")
			{
				int div=Integer.parseInt(s1),rem;
				rem=file_size%div;
				file_size=file_size-rem;
				part1=file_size/div;
				part2=part1;
				part3=part1;
				part4=part1+rem;
			}
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Select atleast one file to divide:","Fatal error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

class Decompresshuff extends JPanel implements ActionListener
{
	File inputfile;
	JTextArea info=new JTextArea(20,100),frag_info1=new JTextArea(10,20),frag_info2=new JTextArea(10,20),frag_info3=new JTextArea(10,20),frag_info4=new JTextArea(10,20);
	JTextField fname=new JTextField(30),fname_d=new JTextField(30);
	JButton but_decompress = new JButton("De-compress"),browse1=new JButton("Browse..."),browse2=new JButton("Browse..."),decomp_frag=new JButton("Decompress fragments");

	Decompresshuff()
	{
		JPanel p1=new JPanel(),p2=new JPanel(),p3=new JPanel(),p4=new JPanel();
		JLabel l1=new JLabel("  Enter the entire path of the filename to be compressed (.comp format) :- "),l2=new JLabel("enter the destination path:");;
		JLabel pa1=new JLabel("file 1:"),pa2=new JLabel("file 2:"),pa3=new JLabel("file 3:"),pa4=new JLabel("file 4:");
		setLayout(null);
		fname.setEditable(true);
		fname_d.setEditable(true);
		p1.add(l1);
		p1.add(fname);
		add(p1);
		p1.setBounds(100,70,400,60);

		p2.setBounds(400,350,400,40);
		p2.add(decomp_frag);decomp_frag.addActionListener(this);decomp_frag.setEnabled(true);

		p4.add(l2);p4.add(fname_d);
		p4.setBounds(600,70,400,60);
		add(p4);

		p3.add(frag_info1);p3.add(frag_info2);p3.add(frag_info3);p3.add(frag_info4);
		frag_info1.setEditable(false);frag_info2.setEditable(false);frag_info3.setEditable(false);frag_info4.setEditable(false);
		p3.setBounds(30,450,1200,300);
		add(p3);

		info.setBounds(250,200,500,100);
		info.setEditable(false);
		add(info);
		add(p2);
		but_decompress.addActionListener(this);
		but_decompress.setBounds(380,150,150,30);
		add(but_decompress);
		but_decompress.setEnabled(true);

		pa1.setBounds(200,430,50,20);pa2.setBounds(420,430,50,20);pa3.setBounds(640,430,50,20);pa4.setBounds(860,430,50,20);
		add(pa1);add(pa2);add(pa3);add(pa4);

		browse1.addActionListener(this);
		browse1.setBounds(500,90,100,30);
		add(browse1);
		browse1.setEnabled(true);

		browse2.addActionListener(this);
		browse2.setBounds(1000,90,100,30);
		add(browse2);
		browse2.setEnabled(true);

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==browse2)
		{
			JFileChooser choose=new JFileChooser();
			int f=choose.showOpenDialog(Decompresshuff.this);
			choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			choose.setAcceptAllFileFilterUsed(false);
			if(f==JFileChooser.APPROVE_OPTION)
			{
				inputfile=choose.getSelectedFile();
				fname_d.setText(((choose.getCurrentDirectory()).getPath())+"\\");
			}
		}

		if(e.getSource()==browse1)
		{
			JFileChooser choose=new JFileChooser();
			int f=choose.showOpenDialog(Decompresshuff.this);
			fname_d.setText(((choose.getCurrentDirectory()).getPath())+"\\");
			if(f==JFileChooser.APPROVE_OPTION)
			{
				inputfile=choose.getSelectedFile();
				fname.setText(inputfile.getPath());
			}
		}

		if(e.getSource()==but_decompress)
		{
			try
			{
				inputfile=new File(fname.getText());
				if(inputfile.exists()==false)
				{
					JOptionPane.showMessageDialog(null,"File not Exists...","ERROR",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					decoderhuffpackage.decoderhuff decode_obj=new decoderhuffpackage.decoderhuff(inputfile,fname_d.getText());
					decode_obj.decode();
					info.setText(decode_obj.getSummary());
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}

		if(e.getSource()==decomp_frag)
		{
			frag_info1.setText("");
			frag_info2.setText("");
			frag_info3.setText("");
			frag_info4.setText("");
			File f=new File(".");
			String fnames[]=f.list();
			int j=0;

			for(int i=0;i<fnames.length;i++)
			{
				if(fnames[i].equals("1.txt.comp")||fnames[i].equals("2.txt.comp")||fnames[i].equals("3.txt.comp")||fnames[i].equals("4.txt.comp"))
				{
					j++;
				}
			}

			String avail_files[]=new String[j];
			j=0;
			for(int i=0;i<fnames.length;i++)
			{
				if(fnames[i].equals("1.txt.comp")||fnames[i].equals("2.txt.comp")||fnames[i].equals("3.txt.comp")||fnames[i].equals("4.txt.comp"))
				{
					avail_files[j]=fnames[i];
					j++;
				}
			}

			if(avail_files.length==0)
			{
				JOptionPane.showMessageDialog(null,"There are no part .comp files present!!!","Fatal error!!!",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				f=new File(".");
				String fnames1[]=f.list();
				for(int i=0;i<fnames.length;i++)
				{
					if(fnames[i].equals("1.txt")||fnames[i].equals("2.txt")||fnames[i].equals("3.txt")||fnames[i].equals("4.txt"))
					{
						File del=new File(fnames[i]);
						del.delete();
					}
				}

				try
				{
					if(avail_files.length==4)
					{
						for(int i=0;i<4;i++)
						{
							File inf=new File(avail_files[i]);
							decoderhuffpackage.decoderhuff decode_obj=new decoderhuffpackage.decoderhuff(inf,"");
							decode_obj.decode();
							if(i==0)
							frag_info1.setText(decode_obj.getSummary());
							else if(i==1)
							frag_info2.setText(decode_obj.getSummary());
							else if(i==2)
							frag_info3.setText(decode_obj.getSummary());
							else if(i==3)
							frag_info4.setText(decode_obj.getSummary());
						}
					}
					else if(avail_files.length==3)
					{
						for(int i=0;i<3;i++)
						{
							File inf=new File(avail_files[i]);
							decoderhuffpackage.decoderhuff decode_obj=new decoderhuffpackage.decoderhuff(inf,"");
							decode_obj.decode();
							if(i==0)
							frag_info1.setText(decode_obj.getSummary());
							else if(i==1)
							frag_info2.setText(decode_obj.getSummary());
							else if(i==2)
							frag_info3.setText(decode_obj.getSummary());
						}
					}
					else if(avail_files.length==2)
					{
						for(int i=0;i<2;i++)
						{
							File inf=new File(avail_files[i]);
							decoderhuffpackage.decoderhuff decode_obj=new decoderhuffpackage.decoderhuff(inf,"");
							decode_obj.decode();
							if(i==0)
							frag_info1.setText(decode_obj.getSummary());
							else if(i==1)
							frag_info2.setText(decode_obj.getSummary());
						}
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
			}
		}
	}
}


//<applet code="huffman" height=1000 width=1000></applet>