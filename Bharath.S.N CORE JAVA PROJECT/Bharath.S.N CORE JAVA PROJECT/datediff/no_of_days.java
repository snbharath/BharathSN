//Bharath.S.N

package datediff;

import java.util.*;

public class no_of_days
{
	public int getNumberOfDays(String date1,String date2)
	{
		Calendar cal1=new GregorianCalendar();
		Calendar cal2=new GregorianCalendar();
		String temp1[]=date1.split("/");
		String temp2[]=date2.split("/");
		cal1.set(Integer.parseInt(temp1[2]),Integer.parseInt(temp1[1]),Integer.parseInt(temp1[0]));
		cal2.set(Integer.parseInt(temp2[2]),Integer.parseInt(temp2[1]),Integer.parseInt(temp2[0]));
		return (daysBetween(cal1.getTime(),cal2.getTime()));
	}
	public int daysBetween(Date d1,Date d2)
	{
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}