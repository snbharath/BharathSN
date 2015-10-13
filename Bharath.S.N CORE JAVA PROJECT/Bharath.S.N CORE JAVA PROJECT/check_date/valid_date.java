//Bharath.S.N


package check_date;

import java.util.*;

public class valid_date
{
	public boolean checkForValidDate(String str)
	{
		String datestr[]=str.split("/");
		Date date=new Date();

		int year_s=Integer.parseInt(datestr[2]),month_s=Integer.parseInt(datestr[1]),date_s=Integer.parseInt(datestr[0]);

		if( (year_s==(date.getYear()+1900)) && ((month_s<(date.getMonth()+1)) || (date_s<date.getDate()) ) )
		{
			return false;
		}
		else if(month_s==2)
		{
			if(year_s%4==0)
			{
				if(date_s>29)
				{
					return false;
				}
			}
			else
			{
				if(date_s>28)
				{
					return false;
				}
			}
		}
		else if(month_s==4 || month_s==6 || month_s==9 || month_s==11)
		{
			if(date_s>30)
			{
				return false;
			}
		}
		return true;
	}
}