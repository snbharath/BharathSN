//Bharath.S.N

package sorting;


import java.util.*;
import java.io.*;

class Rent_Vehicle
{
	private String cartype,regno,sdate,edate,DLno,dname;
	
	public Rent_Vehicle(String cartype, String regno, String sdate , String edate, String DLno, String dname )
	{
		this.cartype=cartype;
		this.regno=regno;
		this.sdate=sdate;
		this.edate=edate;
		this.DLno=DLno;
		this.dname=dname;
	}
	
	public String getcartype()
	{
		return cartype;
	}
	public String getregno()
	{
		return regno;
	}
	public String getsdate()
	{
		return sdate;
	}
	public String getedate()
	{
		return edate;
	}
	public String getDLno()
	{
		return DLno;
	}
	public String getdname()
	{
		return dname;
	}
	public String getfinal_sdate()
	{
		String strrev_sdate[]=sdate.split("/");
		String srev=strrev_sdate[2]+"/"+strrev_sdate[1]+"/"+strrev_sdate[0];
		return srev;
	}
	public String getfinal_edate()
	{
		String strrev_edate[]=edate.split("/");
		String erev=strrev_edate[2]+"/"+strrev_edate[1]+"/"+strrev_edate[0];
		return erev;
	}
}

class sort_cartype implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getcartype().compareTo(o2.getcartype());
    	}
}

class sort_regno implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getregno().compareTo(o2.getregno());
    	}
}

class sort_sdate implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getsdate().compareTo(o2.getsdate());
    	}
}

class sort_edate implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getedate().compareTo(o2.getedate());
    	}
}

class sort_DLno implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getDLno().compareTo(o2.getDLno());
    	}
}

class sort_dname implements Comparator<Rent_Vehicle>
{
    	public int compare(Rent_Vehicle o1, Rent_Vehicle o2)
	{
        	return o1.getdname().compareTo(o2.getdname());
    	}
}

public class arrange
{
	public String sort(int i)
	{
		try
		{
		List lst=new ArrayList();
		BufferedReader reader=new BufferedReader(new FileReader("rented_vehicle_db.txt"));
		String temp;
		while( (temp=reader.readLine())!=null )
		{
			String str[]=temp.split(",");
			String strrev_sdate[]=str[2].split("/"),strrev_edate[]=str[3].split("/");	
			String srev=strrev_sdate[2]+"/"+strrev_sdate[1]+"/"+strrev_sdate[0],erev=strrev_edate[2]+"/"+strrev_edate[1]+"/"+strrev_edate[0];
			lst.add(new Rent_Vehicle(str[0],str[1],srev,erev,str[4],str[6]));
		}
		reader.close();
		if(i==0)
		{
			Collections.sort(lst, new sort_cartype());
			return( printList(lst) );
		}
		else if(i==1)
		{
			Collections.sort(lst, new sort_regno());
			return( printList(lst) );
		}
		else if(i==2)
		{
			Collections.sort(lst, new sort_sdate());
			return( printList(lst) );
		}
		else if(i==3)
		{
			Collections.sort(lst, new sort_edate());
			return( printList(lst) );
		}
		else if(i==4)
		{
			Collections.sort(lst, new sort_DLno());
			return( printList(lst) );
		}
		else if(i==5)
		{
			Collections.sort(lst, new sort_dname());
			return( printList(lst) );
		}
		}
		catch(Exception ex){ return(ex.toString());}
		return "kkkkk";
	}

	public String printList(List<Rent_Vehicle> list)
	{
		String content="";
		for (Rent_Vehicle e: list)
		{
			content=content + e.getcartype() + "\t" + e.getregno() + "\t" + e.getfinal_sdate() + "\t" + e.getfinal_edate() + "\t" + e.getDLno() + "\t" + e.getdname() + "\n";
		}
		return content;
	}

}