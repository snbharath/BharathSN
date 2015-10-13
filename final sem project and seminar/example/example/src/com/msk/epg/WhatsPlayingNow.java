package com.msk.epg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WhatsPlayingNow extends Activity implements OnItemClickListener
{
	int progs_id[];
	JSONArray jArray;
	private Show selectedShow;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nowplaying);
		Calendar ci = Calendar.getInstance();
		ArrayList<String> programList = new ArrayList<String>();
		String time=ci.get(Calendar.HOUR_OF_DAY) + ":" + ci.get(Calendar.MINUTE)+ ":00";
		String Date=ci.get(Calendar.YEAR) + "-" + (ci.get(Calendar.MONTH) + 1) + "-" +ci.get(Calendar.DAY_OF_MONTH);

		final ListView pl=(ListView)findViewById(R.id.listView12);
		String result="";
		InputStream isr=null;
		
		try
		{
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httppost=new HttpPost("http://mobileepg.net63.net/NowPlaying.php?progdate="+Date+"&progtime="+time);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			isr=entity.getContent();
		}
		catch(Exception e)
    	{
			Log.e("Exception in Whats Playing Now 1", e.toString());
    	}
		
		try
		{
	    	BufferedReader reader=new BufferedReader(new InputStreamReader(isr));
	    	StringBuilder sb= new StringBuilder();
	    	String line=null;
	    	while((line=reader.readLine())!=null)
	    	{
	    		sb.append(line+"\n");
	    	}
	    	isr.close();
	    	result=sb.toString();
	    }
	    catch(Exception e)
	    {
	    	Log.e("Exception in Whats Playing Now 2", e.toString());
	    }
	   
	    try
	    {
	    	String s="";
	    	jArray=new JSONArray(result);
	    	progs_id=new int [jArray.length()+1];
	    	for(int ii=0;ii<jArray.length();ii++)
	    	{
	    		JSONObject json= jArray.getJSONObject(ii);
	    		progs_id[ii]=Integer.parseInt(json.getString("pid"));
	    		s="Name:"+json.getString("pname")+"\nChannel name:"+json.getString("cname")+"\nDetails:"+json.getString("pdetails")+"\nDate:"+json.getString("pdate")+"\nStart Time:"+json.getString("pstime")+"\nEnd Time:"+json.getString("petime")+"\n";
	    		programList.add(s);
	    	}
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, programList);
	    	pl.setAdapter(adapter);
	    	pl.setOnItemClickListener(this);
	    }
	    catch(Exception e)
	    {
	    	Log.e("Exception in Whats Playing Now 3", e.toString());
	    }
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
	{
		// TODO Auto-generated method stub
		try
		{
			// getting values from selected ListItem
			JSONObject json = jArray.getJSONObject(position);
			int prog_selec=progs_id[position];
			selectedShow = new Show(Integer.toString(prog_selec),json.getString("pname"),json.getString("cname"),json.getString("pdate"),json.getString("pdetails"),json.getString("pstime"),json.getString("petime"));
			Dialog dlg = Utility.favoriteOnly(this, selectedShow);
			dlg.show();
		}
		catch(Exception e)
		{
			Log.e("Exception in Whats Playing Now 4", e.toString());
		}
	}
}