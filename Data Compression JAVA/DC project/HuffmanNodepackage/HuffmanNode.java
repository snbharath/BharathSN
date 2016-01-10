package HuffmanNodepackage;

import java.io.*;

public class HuffmanNode implements Serializable
{

	public HuffmanNode rchild,lchild,up;
	private String code;
	private int freq;
	private int value;
	public HuffmanNode(String bstring,int freq,int value,HuffmanNode lchild,HuffmanNode rchild,HuffmanNode up)
	{
		code=bstring;
		this.freq=freq;
		this.value=value;
		this.lchild=lchild;
		this.rchild=rchild;
		this.up=up;		
	}
	public HuffmanNode()
	{
		code="";
		freq=0;
		value=0;
		lchild=null;
		rchild=null;
	}
	public int getFreq()
	{
		return freq;
	}
	public int getValue()
	{
		return value;
	}
	public String getCode()
	{
		return code;
	}
	
}
