//Bharath.S.N

package RentalManagerPackage;

import java.util.*;
import java.io.*;
import datediff.*;
import sorting.*;

public class RentalManager
{
	File fad=new File("avail_drivers.txt"),frd=new File("rent_drivers.txt");
	File fav=new File("avail_vehicles.txt"),frv=new File("rent_drivers.txt");

	public boolean canRent(String Dname,String DLno,String vehicle_type)
	{
		try
		{
		if(fad.exists()&&fav.exists())
		{
			BufferedReader readDrivers=new BufferedReader(new FileReader("avail_drivers.txt"));

			String temp;
			Hashtable ht=new Hashtable();

			while((temp=readDrivers.readLine())!=null)
			{
				String data[]=temp.split(",");
				ht.put(data[2],data[1]);
			}
			String dob1=(String)ht.get(DLno);
			Date d=new Date();
			String dob2[ ]=dob1.split("/");
			int age=(d.getYear()+1900)-Integer.parseInt(dob2[2]);
			if( vehicle_type.equalsIgnoreCase("SmallCar") && age>=17)
			{
				return true;
			}
			else if( ( vehicle_type.equalsIgnoreCase("FamilyCar") || vehicle_type.equalsIgnoreCase("SmallVan") ) && age>=21)
			{
				return true;
			}
			else if( ( vehicle_type.equalsIgnoreCase("LuxuryCar") || vehicle_type.equalsIgnoreCase("LargeVan") ) && age>=25)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		}
		catch(Exception ex){ System.out.println(ex.toString());}
		return false;
	}

	public String rentVehicle(String vehicle_type,String start_date,String end_date,String DLno,String air_c)
	{
		try
		{
		if(fad.exists()&&fav.exists())
		{
			int j=0;
			Hashtable ht=new Hashtable();
			BufferedReader readCars=new BufferedReader(new FileReader("avail_vehicles.txt"));
			BufferedReader readDrivers=new BufferedReader(new FileReader("avail_drivers.txt"));

			PrintWriter temp_file_d=new PrintWriter(new FileWriter("temp_drivers.txt"));
			PrintWriter temp_file_v=new PrintWriter(new FileWriter("temp_vehicles.txt"));

			String temp;
			String regno[]=new String [100];
			while( (temp=readCars.readLine())!=null )
			{
				String avail[]=temp.split(",");
				ht.put(avail[1],avail[2]);
				regno[j]=avail[1];j++;
				temp_file_v.println(temp);
			}

			while( (temp=readDrivers.readLine())!=null )
			{
				temp_file_d.println(temp);
			}

			readCars.close();readDrivers.close();
			temp_file_v.close();
			temp_file_d.close();

			BufferedReader temp_reader_v=new BufferedReader(new FileReader("temp_vehicles.txt"));
			BufferedReader temp_reader_d=new BufferedReader(new FileReader("temp_drivers.txt"));

			PrintWriter rent_save=new PrintWriter(new FileWriter("rented_vehicle_db.txt",true));

			String rent_reg="";

			System.out.println(String.valueOf(ht.size()));

			for(int i=0;i<ht.size();i++)
			{
				if(ht.containsKey(regno[i]) && vehicle_type.equalsIgnoreCase((String)ht.get(regno[i])))
				{
					PrintWriter avail_v=new PrintWriter(new FileWriter("avail_vehicles.txt"));
					PrintWriter rent_d=new PrintWriter(new FileWriter("rent_drivers.txt",true));
					PrintWriter avail_d=new PrintWriter(new FileWriter("avail_drivers.txt"));
					PrintWriter rent_v=new PrintWriter(new FileWriter("rent_vehicles.txt",true));

					rent_reg=regno[i];
					ht.remove(regno[i]);

					String dri_name="";
					String str1;
					while( (str1=temp_reader_d.readLine())!=null )
					{
						String temp1[]=str1.split(",");

						if(temp1[2].equalsIgnoreCase(DLno))
						{
							dri_name=temp1[0];
							rent_d.println(str1);
							rent_d.close();
						}
						else
						{
							avail_d.println(str1);
						}
					}
					String str=vehicle_type+","+rent_reg+","+start_date+","+end_date+","+DLno+","+air_c+","+dri_name;
					rent_save.println(str);
					rent_save.close();

					String str2;
					while( (str2=temp_reader_v.readLine())!=null )
					{
						String temp1[]=str2.split(",");

						if(temp1[1].equalsIgnoreCase(rent_reg))
						{
							rent_v.println(str2);
							rent_v.close();
						}
						else
						{
							avail_v.println(str2);
						}
					}
					avail_v.close();
					avail_d.close();
					break;
				}
				if( !ht.containsValue(vehicle_type))
				{
					rent_save.close();
					temp_reader_d.close();
					temp_reader_v.close();
					return "requested vehicle not found";
				}
			}

			temp_reader_d.close();
			temp_reader_v.close();

			return rent_reg;
		}
		}catch(Exception ex){ System.out.println(ex.toString());}
		return "cannot open files";
	}

	public double returnVehicle(String regno,String returned_date)throws IOException
	{
		BufferedReader readCars=new BufferedReader(new FileReader("rent_vehicles.txt"));
		BufferedReader readDrivers=new BufferedReader(new FileReader("rent_drivers.txt"));
		BufferedReader rent_det=new BufferedReader(new FileReader("rented_vehicle_db.txt"));

		PrintWriter temp_file_d=new PrintWriter(new FileWriter("temp_drivers.txt"));
		PrintWriter temp_file_v=new PrintWriter(new FileWriter("temp_vehicles.txt"));
		PrintWriter temp_file_rented=new PrintWriter(new FileWriter("temp_rented_vehicle_db.txt"));

		String temp;
		while( (temp=readCars.readLine())!=null )
		{
			temp_file_v.println(temp);
		}

		while( (temp=readDrivers.readLine())!=null )
		{
			temp_file_d.println(temp);
		}

		while( (temp=rent_det.readLine())!=null)
		{
			temp_file_rented.println(temp);
		}

		readCars.close();
		readDrivers.close();
		rent_det.close();
		temp_file_v.close();
		temp_file_d.close();
		temp_file_rented.close();

		Hashtable cartypetable=new Hashtable();
		cartypetable.put("SmallCar","1");
		cartypetable.put("FamilyCar","2");
		cartypetable.put("LuxuryCar","3");
		cartypetable.put("SmallVan","4");
		cartypetable.put("LargeVan","5");

		double charge=0;
		String delete_driver="",actual_end_date="",air_c="",car_type="",start_date="";

		rent_det=new BufferedReader(new FileReader("temp_rented_vehicle_db.txt"));
		readCars=new BufferedReader(new FileReader("temp_vehicles.txt"));
		readDrivers=new BufferedReader(new FileReader("temp_drivers.txt"));

		PrintWriter rent_save=new PrintWriter(new FileWriter("rented_vehicle_db.txt"));
		while( (temp=rent_det.readLine())!=null )
		{
			String str1[]=temp.split(",");
			if(str1[1].equalsIgnoreCase(regno))
			{
				delete_driver=str1[4];
				actual_end_date=str1[3];
				air_c=str1[5];
				car_type=str1[0];
				start_date=str1[2];
			}
			else
			{
				rent_save.println(temp);
			}
		}
		rent_save.close();

		PrintWriter rent_v=new PrintWriter(new FileWriter("rent_vehicles.txt"));
		while( (temp=readCars.readLine())!=null )
		{
			String str1[]=temp.split(",");
			if( str1[1].equalsIgnoreCase(regno) )
			{
				PrintWriter avail_v=new PrintWriter(new FileWriter("avail_vehicles.txt",true));
				avail_v.println(temp);
				avail_v.close();
			}
			else
			{
				rent_v.println(temp);
			}
		}
		rent_v.close();

		PrintWriter rent_d=new PrintWriter(new FileWriter("rent_drivers.txt"));
		while( (temp=readDrivers.readLine())!=null )
		{
			String str1[]=temp.split(",");
			if( str1[2].equalsIgnoreCase(delete_driver) )
			{
				PrintWriter avail_d=new PrintWriter(new FileWriter("avail_drivers.txt",true));
				avail_d.println(temp);
				avail_d.close();
			}
			else
			{
				rent_d.println(temp);
			}
		}
		rent_d.close();

		readCars.close();
		readDrivers.close();
		rent_det.close();

		datediff.no_of_days n=new datediff.no_of_days();

		int check=n.getNumberOfDays(actual_end_date,returned_date);
		//System.out.println(String.valueOf(check));System.out.println(String.valueOf(n.getNumberOfDays(start_date,actual_end_date)));

		switch(Integer.parseInt((String)cartypetable.get(car_type)))
		{
			case 1 : 	if(check>0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
					  	{
							charge=33*days+39.6*check;
						}
						else
						{
							charge=30*days+36*check;
						}
					  }
					  else if(check<0)
					  {
						int days=n.getNumberOfDays(start_date,returned_date);
						if(days>0)
						{
							if(air_c.equalsIgnoreCase("AC"))
							{
					 			charge=33*days;
							}
							else
							{
								charge=30*days;
							}
						}
					  }
					  else if(check==0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
						{
					 		charge=33*days;
						}
						else
						{
							charge=30*days;
						}
					  }
					  break;

			case 2 :	if(check>0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
					  	{
							charge=44*days+52.8*check;
						}
						else
						{
							charge=40*days+48*check;
						}
					  }
					  else if(check<0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(days>0)
						{
							if(air_c.equalsIgnoreCase("AC"))
							{
					 			charge=44*days-22*Math.abs(check);
							}
							else
							{
								charge=40*days-20*Math.abs(check);
							}
						}
					  }
					  else if(check==0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
						{
					 		charge=44*days;
						}
						else
						{
							charge=40*days;
						}
					  }
					  break;

			case 3 :	if(check>0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						charge=150*days+195*check;
					  }
					  else if(check<0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(days>0)
						{
					 		charge=150*days-45*Math.abs(check);
						}
					  }
					  else if(check==0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
					 	charge=150*days;
					  }
					  break;

			case 4 : 	if(check>0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
					  	{
							charge=55*days+66*check;
						}
						else
						{
							charge=50*days+60*check;
						}
					  }
					  else if(check<0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(days>0)
						{
							if(air_c.equalsIgnoreCase("AC"))
							{
					 			charge=55*days-27.5*Math.abs(check);
							}
							else
							{
								charge=50*days-25*Math.abs(check);
							}
						}
					  }
					  else if(check==0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
						{
					 		charge=55*days;
						}
						else
						{
							charge=50*days;
						}
					  }
					  break;

			case 5 : 	if(check>0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
					  	{
							charge=77*days+92.4*check;
						}
						else
						{
							charge=70*days+84*check;
						}
					  }
					  else if(check<0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(days>0)
						{
							if(air_c.equalsIgnoreCase("AC"))
							{
					 			charge=77*days-38.5*Math.abs(check);
							}
							else
							{
								charge=70*days-35*Math.abs(check);
							}
						}
					  }
					  else if(check==0)
					  {
						int days=n.getNumberOfDays(start_date,actual_end_date);
						if(air_c.equalsIgnoreCase("AC"))
						{
					 		charge=77*days;
						}
						else
						{
							charge=70*days;
						}
					  }
					  break;
		}
		return charge;
	}

	public String rentedVehicles(int i)throws IOException
	{
		sorting.arrange obj=new sorting.arrange();
		return(obj.sort(i));
	}
}