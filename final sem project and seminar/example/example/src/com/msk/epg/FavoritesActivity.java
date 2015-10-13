package com.msk.epg;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class FavoritesActivity extends Activity implements OnItemClickListener
{
	private ListView l1 ;
	static private ArrayList <String> list = null;
	private Cursor c;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite);
		l1 = (ListView)findViewById(R.id.fav_list);
		list = new ArrayList<String>();
		l1.setOnItemClickListener(this);
		this.refresh();
    }
	
	public void refresh()
	{
		list.clear();
		Utility.DBWrapper wrapper = new Utility.DBWrapper(this);
		c = wrapper.getFavorite();
		if(c.getCount()!=0)
		{
			int showidClm;
			c.moveToFirst();
			do 
			{
				showidClm = c.getColumnIndex(DBHelper.PROG_ID);
				
				showidClm = c.getColumnIndex(DBHelper.PROG_NAME);
				String temp= "Name: "+c.getString(showidClm)+"\n";
				
				showidClm = c.getColumnIndex(DBHelper.CHANNEL_NAME);
				temp+="Channel name: "+c.getString(showidClm)+"\n";
				
				showidClm = c.getColumnIndex(DBHelper.PROG_DATE);
				temp+="Date: "+c.getString(showidClm)+"\n";
				
				showidClm = c.getColumnIndex(DBHelper.PROG_DETAILS);
				temp+="Details:"+c.getString(showidClm)+"\n";
				
				showidClm = c.getColumnIndex(DBHelper.PROG_STIME);
				temp+="Start time: "+c.getString(showidClm)+"\n";
				
				showidClm = c.getColumnIndex(DBHelper.PROG_ETIME);
				temp+="End Time: "+c.getString(showidClm)+"\n";
				
				list.add(temp);
			} while (c.moveToNext());
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("no favorites found");
			builder.show();
		}
		ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		l1.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view,final int position, long id) 
	{
		// TODO Auto-generated method stub
		Dialog dlg = deleteRecord(this, position, this);
		dlg.show();
	}
	
	public static Dialog deleteRecord(final Context context, final int position, final Object tis)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Select Option");
		String[] options = { "Delete Favorite", "Cancel" };
		builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				// TODO Auto-generated method stub
				switch (which) 
				{
					case 0: Utility.DBWrapper wrapper = new Utility.DBWrapper(context);
							Cursor c = wrapper.getIds();
							c.moveToFirst();
							for(int i=0;i<position;i++)
							{
								c.moveToNext();
							}
							int temp= c.getColumnIndex(DBHelper.PROG_ID);
							String selectedID=c.getString(temp);
							temp= wrapper.deleteFavorite(selectedID);
							if(temp!=-1)
							{
								Toast.makeText(context,"Favorite deleted!!",Toast.LENGTH_LONG).show();
							}
							Log.e("NUMBER OF ROWS DELETED", Integer.toString(temp));
							dialog.cancel();
							((FavoritesActivity) tis).refresh();
							break;
					case 1: dialog.cancel();
							break;
				}
			}
		});
		return builder.create();
	}
}