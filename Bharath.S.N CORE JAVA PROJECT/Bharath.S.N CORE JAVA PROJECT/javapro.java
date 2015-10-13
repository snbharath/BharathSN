//Bharath.S.N


import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import RentalManagerPackage.*;
import check_date.*;


public class javapro extends JApplet
{
	public void init()
	{
		JTabbedPane tab=new JTabbedPane();
		tab.add("Vehicles",new Vehicles());
		tab.add("Driver Details",new DriverDetails());
		tab.add("Booking",new Booking());
		tab.add("Return",new Return());
		tab.add("Rent Details",new RentDetails());
		tab.add("Add Driver and Car",new AddDC());

		Container con=getContentPane();
		JScrollPane jsp=new JScrollPane(tab,20,30);
		con.add(jsp);
	}
}

class Vehicles extends JPanel
{
	ArrayList La=new ArrayList(),Lr=new ArrayList();
	String header[]={ "Car Make","Registration No.","Car Type" };
	JTable tabr,taba;

	Vehicles()
	{
		JLabel l1=new JLabel("Vehicles Available:"),l2=new JLabel("Vehicles Rented:");
		
		setLayout(null);
		l1.setBounds(0,-80,200,200);
		l2.setBounds(550,-80,200,200);
		add(l1);
		add(l2);

		String dat1,dat2;
		try
		{
		BufferedReader reader_a=new BufferedReader(new FileReader("avail_vehicles.txt")),reader_r=new BufferedReader(new FileReader("rent_vehicles.txt"));;
		
		File f1=new File("avail_vehicles.txt"),f2=new File("rent_vehicles.txt");

		if(f1.exists())
		{
			while( ( dat1 = reader_a.readLine() ) != null)
			La.add( dat1 );
			String dataa[ ][ ]=new String[La.size()][3];
			for(int i=0;i<La.size();i++)
			{
				String record=(String)La.get(i);
				String items[]=record.split(",");
				dataa[i][0]=items[0];
				dataa[i][1]=items[1];
				dataa[i][2]=items[2];
			}
			
			taba=new JTable(dataa,header);
			JScrollPane jsp = new JScrollPane( taba , 20 , 30 );
			jsp.setBounds(0,30,500,500);
			add(jsp);
		}

		if(f2.exists())
		{
			while( (dat2=reader_r.readLine())!=null)
			Lr.add(dat2);
			String datar[ ][ ]=new String[Lr.size()][3];
			for(int i=0;i<Lr.size();i++)
			{
				String record=(String)Lr.get(i);
				String items[]=record.split(",");
				datar[i][0]=items[0];
				datar[i][1]=items[1];
				datar[i][2]=items[2];
			}
			
			tabr=new JTable(datar,header);
			JScrollPane jsp = new JScrollPane( tabr , 20 , 30 );
			jsp.setBounds(550,30,500,500);
			add(jsp);
		}
		}

	catch(Exception ex){}
	}
}

class DriverDetails extends JPanel
{
	ArrayList La=new ArrayList(),Lr=new ArrayList();
	String header[]={ "Driver Name","Date Of Birth","DL number","DL Type" };
	JTable tabr,taba;

	DriverDetails()
	{
		JLabel l1=new JLabel("Drivers Available:"),l2=new JLabel("Drivers on Duty:");
		
		setLayout(null);
		l1.setBounds(0,-80,200,200);
		l2.setBounds(550,-80,200,200);
		add(l1);
		add(l2);

		String dat1,dat2;
		try
		{
		BufferedReader reader_a=new BufferedReader(new FileReader("avail_drivers.txt")),reader_r=new BufferedReader(new FileReader("rent_drivers.txt"));;
		
		File f1=new File("avail_drivers.txt"),f2=new File("rent_drivers.txt");

		if(f1.exists())
		{
			while( ( dat1 = reader_a.readLine() ) != null)
			La.add( dat1 );
			String dataa[ ][ ]=new String[La.size()][4];
			for(int i=0;i<La.size();i++)
			{
				String record=(String)La.get(i);
				String items[]=record.split(",");
				dataa[i][0]=items[0];
				dataa[i][1]=items[1];
				dataa[i][2]=items[2];
				dataa[i][3]=items[3];
			}
			taba=new JTable(dataa,header);
			JScrollPane jsp = new JScrollPane( taba , 20 , 30 );
			jsp.setBounds(0,30,500,500);
			add(jsp);
		}

		if(f2.exists())
		{
			while( (dat2=reader_r.readLine())!=null)
			Lr.add(dat2);
			String datar[ ][ ]=new String[Lr.size()][4];
			for(int i=0;i<Lr.size();i++)
			{
				String record=(String)Lr.get(i);
				String items[]=record.split(",");
				datar[i][0]=items[0];
				datar[i][1]=items[1];
				datar[i][2]=items[2];
				datar[i][3]=items[3];
			}
			
			tabr=new JTable(datar,header);
			JScrollPane jsp = new JScrollPane( tabr , 20 , 30 );
			jsp.setBounds(550,30,500,500);
			add(jsp);
		}
		}
		catch(Exception ex){ System.out.println(ex.toString()); }
	}
}

class Booking extends JPanel implements ItemListener,ActionListener
{
	Hashtable table=new Hashtable(),hash_month_rev=new Hashtable(),hash_sort_type=new Hashtable();
	Choice DLno=new Choice(),vtype=new Choice(),sort_type=new Choice();
	JButton chk_button=new JButton("Check"),get_selected=new JButton("Book Selected"),book=new JButton("BOOK");
	JTextField dname=new JTextField(15),vehicle_type=new JTextField(20),DL_text=new JTextField(20),vehicle_rrtype=new JTextField(15);
	CheckboxGroup cg=new CheckboxGroup();
	Checkbox ac=new Checkbox("AC",cg,true),no_ac=new Checkbox("no",cg,false);
	Hashtable hash_month=new Hashtable();
	Choice date=new Choice(),month=new Choice(),year=new Choice(),date_s=new Choice(),month_s=new Choice(),year_s=new Choice();
	JTextField errors=new JTextField(50);
	RentalManagerPackage.RentalManager obj=new RentalManagerPackage.RentalManager();
	check_date.valid_date chk_obj1=new check_date.valid_date(),chk_obj2=new check_date.valid_date();
	JTextArea display_v=new JTextArea(50,100);

	Booking()
	{
		JLabel check_avail=new JLabel("Check for driver's eligibility:-"),driver_name=new JLabel("2.Driver Name:"),DL_number=new JLabel("1.DL number:"),Vehicle_type=new JLabel("3.vehicle type:"),errorsl=new JLabel("Errors:"),sort_detail=new JLabel("*Vehicles out on rent:");
		JLabel vehicle_req=new JLabel("Request the selected vehicle :-"),vehicle_rtype=new JLabel("*Vehicle type:"),start_date=new JLabel("Start date:"),end_date=new JLabel("End date:"),DL_rnum=new JLabel("DL number"),air_c=new JLabel("AC required ? :"),sort_label=new JLabel("Sort By:");
		setLayout(null);

		try
		{
			BufferedReader reader=new BufferedReader(new FileReader("avail_drivers.txt"));
			String temp;
			while( (temp=reader.readLine())!=null )
			{
				String str[]=temp.split(",");
				DLno.add(str[2]);
				table.put(str[2],str[0]);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		vtype.add("SmallCar");
		vtype.add("FamilyCar");
		vtype.add("LuxuryCar");
		vtype.add("SmallVan");
		vtype.add("LargeVan");

		DLno.addItemListener(this);
		vtype.addItemListener(this);
		chk_button.addActionListener(this);
		dname.setEditable(false);

		JPanel p1=new JPanel(),p2=new JPanel(),p3=new JPanel();
		check_avail.setBounds(0,-80,200,200);
		add(check_avail);

		p1.add(DL_number);p1.add(DLno);
		p1.setBounds(0,40,200,40);

		p2.add(driver_name);p2.add(dname);
		p2.setBounds(-50,80,400,50);

		p3.add(Vehicle_type);p3.add(vtype);
		p3.setBounds(0,120,200,50);
		chk_button.setEnabled(false);
		chk_button.setBounds(50,160,100,30);
		add(chk_button);
		add(p1);add(p2);add(p3);

		get_selected.addActionListener(this);
		get_selected.setBounds(400,100,150,30);
		add(get_selected);
		ac.addItemListener(this);no_ac.addItemListener(this);

		vehicle_req.setBounds(600,-80,200,200);
		add(vehicle_req);

		JPanel p4=new JPanel(),p5=new JPanel(),p6=new JPanel(),p7=new JPanel(),p8=new JPanel(),p9=new JPanel();
		vehicle_rrtype.setEditable(false);
		p4.add(vehicle_rtype);p4.add(vehicle_rrtype);
		p4.setBounds(600,40,400,40);
		add(p4);

		get_selected.setEnabled(false);

		hash_month.put("January","01");
		hash_month.put("February","02");
		hash_month.put("March","03");
		hash_month.put("April","04");
		hash_month.put("May","05");
		hash_month.put("June","06");
		hash_month.put("July","07");
		hash_month.put("August","08");
		hash_month.put("September","09");
		hash_month.put("October","10");
		hash_month.put("November","11");
		hash_month.put("December","12");

		hash_month_rev.put("1","January");
		hash_month_rev.put("2","February");
		hash_month_rev.put("3","March");
		hash_month_rev.put("4","April");
		hash_month_rev.put("5","May");
		hash_month_rev.put("6","June");
		hash_month_rev.put("7","July");
		hash_month_rev.put("8","August");
		hash_month_rev.put("9","September");
		hash_month_rev.put("10","October");
		hash_month_rev.put("11","November");
		hash_month_rev.put("12","December");

		hash_sort_type.put("Car Type","0");
		hash_sort_type.put("Registration number","1");
		hash_sort_type.put("Start Date","2");
		hash_sort_type.put("End Date","3");
		hash_sort_type.put("Drivers DL number","4");
		hash_sort_type.put("Drivers name","5");

		for(int i=1;i<=12;i++)
		{
			month.add( (String)hash_month_rev.get(String.valueOf(i)) );
			month_s.add( (String)hash_month_rev.get(String.valueOf(i)) );
		}

		for(int i=1;i<=31;i++)
		{
			date.add( String.valueOf(i) );
			date_s.add( String.valueOf(i) );
		}

		p5.add(start_date);p5.add(year_s);p5.add(month_s);p5.add(date_s);
		year_s.addItemListener(this);month_s.addItemListener(this);date_s.addItemListener(this);
		p5.setBounds(600,80,400,40);
		add(p5);

		errors.setEditable(false);
		month.addItemListener(this);date.addItemListener(this);year.addItemListener(this);

		Date dat=new Date();
		int yy1=dat.getYear()+1900,yy2=dat.getYear()+1900;

		year_s.add(String.valueOf(yy1));year_s.add(String.valueOf(yy1+1));year_s.add(String.valueOf(yy1+2));
		year.add(String.valueOf(yy2));year.add(String.valueOf(yy2+1));year.add(String.valueOf(yy2+2));

		year.addItemListener(this);
		p6.add(end_date);p6.add(year);p6.add(month);p6.add(date);
		p6.setBounds(600,120,400,40);
		add(p6);

		DL_text.setEditable(false);
		p7.add(DL_rnum);p7.add(DL_text);
		p7.setBounds(600,160,400,40);
		add(p7);

		p8.add(air_c);p8.add(ac);p8.add(no_ac);
		p8.setBounds(600,200,400,40);
		add(p8);

		p9.add(errorsl);p9.add(errors);
		p9.setBounds(100,240,800,40);
		add(p9);

		book.addActionListener(this);
		book.setBounds(1000,200,150,30);
		add(book);
		book.setEnabled(false);

		sort_detail.setBounds(20,420,200,40);
		add(sort_detail);
		display_v.setEditable(false);
		display_v.setBounds(20,450,1200,400);
		add(display_v);

		sort_type.add("Car Type");
		sort_type.add("Registration number");
		sort_type.add("Start Date");
		sort_type.add("End Date");
		sort_type.add("Drivers DL number");
		sort_type.add("Drivers name");

		JPanel p10=new JPanel();
		p10.add(sort_label);p10.add(sort_type);
		p10.setBounds(400,420,400,40);
		add(p10);
		sort_type.addItemListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==chk_button)
		{
			boolean condition=obj.canRent(dname.getText(),DLno.getSelectedItem(),vtype.getSelectedItem());
			if(condition)
			{
				get_selected.setEnabled(true);
				errors.setText("Can be selected.. to book the car click on the button \"Book Selected\" ...");
			}
			else
			{
				get_selected.setEnabled(false);
				errors.setText("you cannot select the driver,may be age dependency not satisfied...");
			}
		}

		if(e.getSource()==get_selected)
		{
			vehicle_rrtype.setText(vtype.getSelectedItem());
			DL_text.setText(DLno.getSelectedItem());
		}

		if(e.getSource()==book)
		{
			try
			{
			String tempp2=date.getSelectedItem()+"/"+(String)hash_month.get(month.getSelectedItem())+"/"+year.getSelectedItem(),tempp1=date_s.getSelectedItem()+"/"+(String)hash_month.get(month_s.getSelectedItem())+"/"+year_s.getSelectedItem();
			String state="",temp="";
			if(ac.getState())
			{
				state="AC";
			}
			else if(no_ac.getState())
			{
				state="no";
			}
			String vehicle_regno=obj.rentVehicle(vehicle_rrtype.getText(),tempp1,tempp2,DL_text.getText(),state);
			String find_length[]=vehicle_regno.split(" ");
			if(find_length.length>1)
			{
				temp=vehicle_regno+" , add the drivers and vehicles and restart the application.";
			}
			else
			{
				DLno.remove(DL_text.getText());
				temp="Vehicle with register no. "+vehicle_regno+" has been rented";
			}
			errors.setText(temp);
			}
			catch( IllegalArgumentException exp)
			{
				book.setEnabled(false);
			}
			catch(Exception ex){ System.out.println(ex.toString());}
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		if(!table.isEmpty())
		{
			dname.setText((String)table.get(DLno.getSelectedItem()));
			String str1=DLno.getSelectedItem(),str2=dname.getText(),str3=vtype.getSelectedItem();
			if(str1.length()!=0 && str2.length()!=0 && str3.length()!=0)
			{
				chk_button.setEnabled(true);
			}
		}

		String s1=vehicle_rrtype.getText(),s2=year_s.getSelectedItem(),s3=month_s.getSelectedItem(),s4=date_s.getSelectedItem(),s5=year.getSelectedItem(),s6=month.getSelectedItem(),s7=date.getSelectedItem(),s8=DL_text.getText();
		if((s1.length()!=0)&&(s2.length()!=0)&&(s3.length()!=0)&&(s4.length()!=0)&&(s5.length()!=0)&&(s6.length()!=0)&&(s7.length()!=0)&&(s8.length()!=0) )
		{
			String date_c1=s4+"/"+(String)hash_month.get(s3)+"/"+s2 , date_c2=s7+"/"+(String)hash_month.get(s6)+"/"+s5;
			boolean state1=chk_obj1.checkForValidDate(date_c1),state2=chk_obj2.checkForValidDate(date_c2);
			if(state1 && state2)
			book.setEnabled(true);
			else
			book.setEnabled(false);
		}

		if( hash_sort_type.containsKey(sort_type.getSelectedItem()) )
		{
			try
			{
				String str=obj.rentedVehicles( Integer.parseInt((String)hash_sort_type.get(sort_type.getSelectedItem())) );
				display_v.setText(str);
			}
			catch(Exception ex){ System.out.println(ex.toString());}
		}
	}
}

class Return extends JPanel implements ItemListener,ActionListener
{
	Choice ch_reg=new Choice(),date=new Choice(),month=new Choice(),year=new Choice();
	JTextField s_date=new JTextField(10),v_type=new JTextField(15),dri_DLno=new JTextField(15),air_c=new JTextField(5),d_name=new JTextField(15),amount=new JTextField(10),actual_edate=new JTextField(10);
	Hashtable hash_month=new Hashtable(),hash_month_rev=new Hashtable();
	JButton ok=new JButton("OK");
	check_date.valid_date chk_obj=new check_date.valid_date();
	RentalManagerPackage.RentalManager obj=new RentalManagerPackage.RentalManager();

	Return()
	{
		JLabel title=new JLabel("Return Vehicle"),regno_label=new JLabel("*Vehicle Registration number:"),sdate=new JLabel("Start Date:"),edate=new JLabel("End Date:"),vtype=new JLabel("Vehicle type:"),driDLno=new JLabel("Driver's DL number:"),airc=new JLabel("AC:"),dname=new JLabel("Driver's name:"),amount_label=new JLabel("Total Amount £ :"),actualedate=new JLabel("Actual End Date:");
		setLayout(null);

		try
		{
		BufferedReader reader=new BufferedReader(new FileReader("rented_vehicle_db.txt"));
		String temp;
		while( (temp=reader.readLine())!=null)
		{
			String str[]=temp.split(",");
			ch_reg.add(str[1]);
		}
		reader.close();
		}
		catch(Exception ex){ System.out.println(ex.toString()) ; }

		ch_reg.addItemListener(this);

		title.setBounds(0,-80,200,200);
		add(title);

		JPanel p1=new JPanel(),p2=new JPanel(),p3=new JPanel(),p4=new JPanel(),p5=new JPanel(),p6=new JPanel(),p7=new JPanel(),p8=new JPanel(),p9=new JPanel();

		p1.add(regno_label);p1.add(ch_reg);
		p1.setBounds(50,40,400,40);
		add(p1);

		p2.add(sdate);p2.add(s_date);
		p2.setBounds(50,80,300,40);
		s_date.setEditable(false);
		add(p2);

		p9.add(actualedate);p9.add(actual_edate);
		actual_edate.setEditable(false);
		p9.setBounds(500,80,300,40);
		add(p9);

		hash_month.put("January","01");
		hash_month.put("February","02");
		hash_month.put("March","03");
		hash_month.put("April","04");
		hash_month.put("May","05");
		hash_month.put("June","06");
		hash_month.put("July","07");
		hash_month.put("August","08");
		hash_month.put("September","09");
		hash_month.put("October","10");
		hash_month.put("November","11");
		hash_month.put("December","12");

		hash_month_rev.put("1","January");
		hash_month_rev.put("2","February");
		hash_month_rev.put("3","March");
		hash_month_rev.put("4","April");
		hash_month_rev.put("5","May");
		hash_month_rev.put("6","June");
		hash_month_rev.put("7","July");
		hash_month_rev.put("8","August");
		hash_month_rev.put("9","September");
		hash_month_rev.put("10","October");
		hash_month_rev.put("11","November");
		hash_month_rev.put("12","December");

		for(int i=1;i<=12;i++)
		{
			month.add( (String)hash_month_rev.get(String.valueOf(i)) );
		}

		for(int i=1;i<=31;i++)
		{
			date.add( String.valueOf(i) );
		}

		Date dat=new Date();
		int yy=dat.getYear()+1900;
		year.add(String.valueOf(yy));year.add(String.valueOf(yy+1));year.add(String.valueOf(yy+2));
		p3.add(edate);p3.add(year);p3.add(month);p3.add(date);
		year.addItemListener(this);date.addItemListener(this);month.addItemListener(this);
		p3.setBounds(50,120,400,40);
		add(p3);

		p4.add(vtype);p4.add(v_type);
		v_type.setEditable(false);
		p4.setBounds(50,160,400,40);
		add(p4);

		p5.add(driDLno);p5.add(dri_DLno);
		dri_DLno.setEditable(false);
		p5.setBounds(50,200,400,40);
		add(p5);

		p6.add(airc);p6.add(air_c);
		air_c.setEditable(false);
		p6.setBounds(50,240,400,40);
		add(p6);

		p7.add(dname);p7.add(d_name);
		d_name.setEditable(false);
		p7.setBounds(50,280,400,40);
		add(p7);

		p8.add(amount_label);p8.add(amount);
		amount.setEditable(false);
		p8.setBounds(50,320,400,40);
		add(p8);

		ok.setBounds(500,320,100,30);
		add(ok);
		ok.setEnabled(false);
		ok.addActionListener(this);
	}
	public void itemStateChanged(ItemEvent e)
	{
		String s1=ch_reg.getSelectedItem();
		if(s1.length()!=0)
		{
			try
			{
			BufferedReader reader=new BufferedReader(new FileReader("rented_vehicle_db.txt"));
			String str1;
			while( (str1=reader.readLine())!=null)
			{
				String temp[]=str1.split(",");
				if(temp[1].equalsIgnoreCase(s1))
				{
					s_date.setText(temp[2]);
					v_type.setText(temp[0]);
					dri_DLno.setText(temp[4]);
					air_c.setText(temp[5]);
					d_name.setText(temp[6]);
					actual_edate.setText(temp[3]);
					break;
				}
			}
			reader.close();
			}
			catch(Exception ex){ System.out.println(ex.toString());}
		}

		String s2=s_date.getText(),s3=v_type.getText(),s4=dri_DLno.getText(),s5=air_c.getText(),s6=d_name.getText(),endstr_d=date.getSelectedItem(),endstr_m=month.getSelectedItem(),endstr_y=year.getSelectedItem();
		if((s1.length()!=0)&&(s2.length()!=0)&&(s3.length()!=0)&&(s4.length()!=0)&&(s5.length()!=0)&&(s6.length()!=0)&&(endstr_d.length()!=0)&&(endstr_m.length()!=0)&&(endstr_y.length()!=0))
		{
			String date_str_chk=endstr_d+"/"+(String)hash_month.get(endstr_m)+"/"+endstr_y;
			boolean state=chk_obj.checkForValidDate(date_str_chk);
			if(state)
			{
				ok.setEnabled(true);
				
			}
			else
			{
				ok.setEnabled(false);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==ok)
			{
				String temp_reg=ch_reg.getSelectedItem();
				String temp_date=date.getSelectedItem()+"/"+(String)hash_month.get( month.getSelectedItem() )+"/"+year.getSelectedItem();
				double charge=obj.returnVehicle(ch_reg.getSelectedItem(),temp_date);
				amount.setText( (String.valueOf(charge))+" /-" );
				ch_reg.remove(temp_reg);
			}
			ok.setEnabled(false);
		}
		catch(Exception ex){ System.out.println(ex.toString()) ; }
	}
}

class RentDetails extends JPanel implements ItemListener
{
	Choice c=new Choice();
	JTextArea info=new JTextArea(20,100);
	RentDetails()
	{
		JLabel l1=new JLabel("rent Details");
		add(l1);

		c.add("Small Car");
		c.add("Family Car");
		c.add("Luxury Car");
		c.add("Small Van");
		c.add("Large Van");
		c.addItemListener(this);

		JPanel p=new JPanel(),p1=new JPanel();
		p.add(c);
		p1.add(info);

		info.setEditable(false);
		setLayout(new BorderLayout());
		add(p,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		JScrollPane jsp=new JScrollPane(p1,20,30);
		add(jsp);
	}

	public void itemStateChanged(ItemEvent e)
	{
		if(c.getSelectedItem()=="Small Car");
		{
			String det="For a Small Car Rent=£ 30/day,if AC 10% extra charges,\n If a vehicle is returned early (before the booked end date),\n then there is a reduction in charge of 100% of the daily rate for each unused whole day.\n If a vehicle is returned late then there is a penalty of 20% per day.\n Driver must be 17 or over and should have a full licence .";
			info.setText(det);
		}
		if(c.getSelectedItem()=="Family Car")
		{
			String det="For a family car Rent=£ 40/day,if AC 10% extra charges,\n If a vehicle is returned early (before the booked end date),\n then there is a reduction in charge of 50% of the daily rate for each unused whole day.\n If a vehicle is returned late then there is a penalty of 20% per day.\n Driver must be 21 or over and sholud have a full licence .";
			info.setText(det);
		}
		if(c.getSelectedItem()=="Luxury Car")
		{
			String det="For a luxury car £ 150 per day, no AC charges ,\n If a vehicle is returned early (before the booked end date),\n then there is a reduction in charge of 30% of the daily rate for each unused whole day.\n If a vehicle is returned late then there is a penalty of 30% per day.\n Driver must be 25 or over and sholud have a full licence .";
			info.setText(det);
		}
		if(c.getSelectedItem()=="Small Van")
		{
			String det="For a Small Van £ 50 per day, if AC 10% extra charges ,\n If a vehicle is returned early (before the booked end date),\n then there is a reduction in charge of 50% of the daily rate for each unused whole day.\n If a vehicle is returned late then there is a penalty of 20% per day.\n Driver must be 21 or over and sholud have a full licence .";
			info.setText(det);
		}
		if(c.getSelectedItem()=="Large Van")
		{
			String det="For a Large Van £ 50 per day, if AC 10% extra charges ,\n If a vehicle is returned early (before the booked end date),\n then there is a reduction in charge of 50% of the daily rate for each unused whole day.\n If a vehicle is returned late then there is a penalty of 20% per day.\n Driver must be 25 or over and sholud have a full licence that permits drivers to drive heavy goods vehicles";
			info.setText(det);
		}
	}
}

class AddDC extends JPanel implements ItemListener,ActionListener
{
	Choice c=new Choice();
	JButton save=new JButton("Save Record");
	JPanel p1=new JPanel(),p2=new JPanel(),p3=new JPanel();
	JTextField tMake=new JTextField(40),tReg=new JTextField(20),errors=new JTextField(60);
	Checkbox c1,c2;
	JTextField name=new JTextField(30),DLno=new JTextField(20),year=new JTextField(4);
	Hashtable ht=new Hashtable(),hash_month=new Hashtable();
	Choice date=new Choice(),month=new Choice();

	AddDC()
	{	
		JLabel l1=new JLabel("Car Make:"),l2=new JLabel("Registration Nunber:"),l3=new JLabel("Car type:");
		c.add("SmallCar");
		c.add("FamilyCar");
		c.add("LuxuryCar");
		c.add("SmallVan");
		c.add("LargeVan");
		c.addItemListener(this);
		c.setBounds(50,70,20,10);
		
		setLayout(new BorderLayout());
		p1.add(l1);p1.add(tMake);p1.add(l2);p1.add(tReg);p1.add(l3);p1.add(c);add(p1,BorderLayout.NORTH);

		try{ BufferedReader reader1=new BufferedReader(new FileReader("cardb.txt"));
		     BufferedReader reader2=new BufferedReader(new FileReader("driverdb.txt"));

		File checking1=new File("cardb.txt"),checking2=new File("driverdb.txt");
		if(checking1.exists()&&checking2.exists())
		{
			String stg1,stg2;
			while(  ((stg1=reader1.readLine())!=null)  &&  ((stg2=reader2.readLine())!=null)   )
			{
				String dat1[]=stg1.split(","),dat2[]=stg2.split(",");
				ht.put(dat1[1].toLowerCase(),dat2[2].toLowerCase());
			}
		}
		}
		catch(Exception ex) { errors.setText(ex.toString());}

		hash_month.put("January","01");
		hash_month.put("February","02");
		hash_month.put("March","03");
		hash_month.put("April","04");
		hash_month.put("May","05");
		hash_month.put("June","06");
		hash_month.put("July","07");
		hash_month.put("August","08");
		hash_month.put("September","09");
		hash_month.put("October","10");
		hash_month.put("November","11");
		hash_month.put("December","12");

		month.add("January");
		month.add("February");
		month.add("March");
		month.add("April");
		month.add("May");
		month.add("June");
		month.add("July");
		month.add("August");
		month.add("September");
		month.add("October");
		month.add("November");
		month.add("December");

		for(int i=1;i<=31;i++)
		date.add( (String.valueOf(i) ));

		JLabel dName=new JLabel("Driver Name:"),dDOB=new JLabel("Driver's date of birth:dd/mm/yyyy"),dDL=new JLabel("DL Type:"),dDLno=new JLabel("Unique DL number");
		CheckboxGroup cg=new CheckboxGroup();
		c1=new Checkbox("Full Licence",cg,true);
		c2=new Checkbox("Provisional Licence",cg,false);
		p2.add(dName);p2.add(name);p2.add(dDOB);p2.add(year);p2.add(month);p2.add(date);p2.add(dDLno);p2.add(DLno);
		add(p2,BorderLayout.CENTER);

		errors.setEditable(false);
		errors.setText("once filled all the fields once again select the \"Car Type\" to enable the save button,dont leave empty fields");
		p3.add(dDL);p3.add(c1);p3.add(c2);p3.add(save);p3.add(errors);
		add(p3,BorderLayout.SOUTH);
		save.setEnabled(false);
		c1.addItemListener(this);
		c2.addItemListener(this);
		save.addActionListener(this);
		date.addItemListener(this);
		month.addItemListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		String comp1=tReg.getText(),comp2=DLno.getText();
		comp1=comp1.toLowerCase();comp2=comp2.toLowerCase();

		String s1=tMake.getText(),s2=tReg.getText(),s3=c.getSelectedItem(),s4=name.getText(),s5=year.getText(),s6=DLno.getText(),s7=month.getSelectedItem(),s8=date.getSelectedItem();
		if(c1.getState() && !(ht.containsKey(comp1)) && !(ht.containsValue(comp2)) && (s1.length()!=0)&&(s2.length()!=0)&&(s3.length()!=0)&&(s4.length()!=0)&&(s5.length()!=0)&&(s6.length()!=0)&&(s7.length()!=0)&&(s8.length()!=0))
		{
			try{
			String contentc="",contentd="";
			contentc= tMake.getText().trim() + "," + tReg.getText().trim() + "," + c.getSelectedItem(); 
			contentd=name.getText().trim() + "," + date.getSelectedItem() +"/"+(String)hash_month.get(month.getSelectedItem()) +"/"+year.getText() + "," + DLno.getText().trim() + "," + "Full";
			save.setEnabled(false);
			PrintWriter cardet=new PrintWriter(new FileWriter("cardb.txt",true)),driverdet=new PrintWriter(new FileWriter("driverdb.txt",true));
			PrintWriter cardet1=new PrintWriter(new FileWriter("avail_vehicles.txt",true)),driverdet1=new PrintWriter(new FileWriter("avail_drivers.txt",true));
			cardet.println(contentc);
			driverdet.println(contentd);

			cardet1.println(contentc);
			driverdet1.println(contentd);

			errors.setText(contentc+" "+contentd);
			cardet.close();driverdet.close();
			cardet1.close();driverdet1.close();

			errors.setText("In order to apply the changes in the Tables, u must restart the application... :-)");
			ht.put(tReg.getText(),DLno.getText());
			}
			catch(Exception ex){ errors.setText(ex.toString());}
		}
		else
		{
			errors.setText("Registration number and Drivers DL number must be unique or all entries must be filled.");
		}

		if(c2.getState())
		{
			errors.setText("Not possible to assign a vehicle for Learning Drivers");
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		String s1=tMake.getText(),s2=tReg.getText(),s3=c.getSelectedItem(),s4=name.getText(),s5=year.getText(),s6=DLno.getText(),s7=date.getSelectedItem(),s8=month.getSelectedItem();
		if((s1.length()!=0)&&(s2.length()!=0)&&(s3.length()!=0)&&(s4.length()!=0)&&(s5.length()!=0)&&(s6.length()!=0)&&(s7.length()!=0)&&(s8.length()!=0))
		{
			if( Integer.parseInt( (String)hash_month.get(s8) )==2 )
			{
				if( Integer.parseInt(s5)%4==0)
				{
					if(Integer.parseInt(s7) <=29 )
					{
						save.setEnabled(true);
					}
					else
					{
						save.setEnabled(false);
					}
				}
				else
				{
					if( Integer.parseInt(s7) <=28 )
					{
						save.setEnabled(true);
					}
					else
					{
						save.setEnabled(false);
					}
				}
			}
			else if( (Integer.parseInt( (String)hash_month.get(s8) )==4) || (Integer.parseInt( (String)hash_month.get(s8) )==6) || (Integer.parseInt( (String)hash_month.get(s8) )==9) || (Integer.parseInt( (String)hash_month.get(s8) )==11) )
			{
				if( Integer.parseInt(s7) <= 30)
				{
					save.setEnabled(true);
				}
				else
				{
					save.setEnabled(false);
				}
			}
			else if( (Integer.parseInt( (String)hash_month.get(s8) )==1) || (Integer.parseInt( (String)hash_month.get(s8) )==3) || (Integer.parseInt( (String)hash_month.get(s8) )==5) || (Integer.parseInt( (String)hash_month.get(s8) )==7) || (Integer.parseInt( (String)hash_month.get(s8) )==8) || (Integer.parseInt( (String)hash_month.get(s8) )==10) || (Integer.parseInt( (String)hash_month.get(s8) )==12) )
			{
				if(Integer.parseInt(s7) <= 31 )
				{
					save.setEnabled(true);
				}
			}
			else
			{
				save.setEnabled(false);
			}
		}
		else
		{
			save.setEnabled(false);
		}
	}
}

//<applet code="javapro" height=1000 width=1000></applet>