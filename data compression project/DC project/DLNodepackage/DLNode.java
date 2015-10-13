package DLNodepackage;

import HuffmanNodepackage.*;

public class DLNode
{
	private DLNode next,prev;
	private HuffmanNode elem;
	
	public DLNode()
	{
		next=null;
		prev=null;
		elem=null;
	}
	public DLNode(DLNode next,DLNode prev,HuffmanNode elem)
	{
		this.next=next;
		this.prev=prev;
		this.elem=elem;
	}
	
	public DLNode getNext()
	{
		return next;
	}
	public DLNode getPrev()
	{
		return prev;
	}
	public void setNext(DLNode n)
	{
		next=n;
	}
	public void setPrev(DLNode n)
	{
		prev=n;
	}
	public void setElement(HuffmanNode o)
	{
		elem=o;
	}
	public HuffmanNode getElement()
	{
		return elem;
	}
	
}
