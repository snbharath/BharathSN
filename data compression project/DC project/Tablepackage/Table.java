package Tablepackage;

import java.io.*;

public class Table implements Serializable
{
	private String FileName;
	private int fileSize,arr[],size=0,front=0;
	public Table(int fileSize,String FileName)
	{
		arr=new int[256];
		this.FileName=FileName;
		this.fileSize=fileSize;
	}
	public void push(int c)
	{
		if(size>256)
			System.out.println("Error in record");
		arr[size]=c;
		size++;
	}
	public int originalSize()
	{
		return fileSize;
	}
	public int pop()
	{
		if(size<1)
			System.out.println("Error in record");
		int rt=arr[front++];
		size--;
		return rt;
	}

	public String fileName()
	{
		return FileName;
	}
	public int recSize()
	{
		return size;
	}
}
