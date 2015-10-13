package PriorityQueuepackage;

import DLNodepackage.*;
import HuffmanNodepackage.*;
public class PriorityQueue
{
	private DLNode head,tail;
	private int size=0;
	private int capacity;
	private HuffmanNode obj[];
	public PriorityQueue(int cap)
	{
		head=new DLNode();
		tail=new DLNode();
		head.setNext(tail);
		tail.setPrev(head);
		capacity=cap;
		obj=new HuffmanNode[capacity];
	}
	public PriorityQueue()
	{
		head=new DLNode();
		tail=new DLNode();
		head.setNext(tail);
		tail.setPrev(head);
		capacity=1000;
		obj=new HuffmanNode[capacity];
	}
	public void insertM(HuffmanNode o)throws Exception
	{
		if (size==capacity)
		throw new Exception("Queue is full");
		if (head.getNext()==tail)
		{
			DLNode d=new DLNode(tail,head,o);
			head.setNext(d);
			tail.setPrev(d);
		}
		else
		{
			DLNode n=head.getNext();
			HuffmanNode CurrenMax=null;
			int key=o.getFreq();
			while (true)
			{
				if (n.getElement().getFreq()>key)
				{
					DLNode second=n.getPrev();						
					DLNode huf=new DLNode(n,second,o);
					second.setNext(huf);
					n.setPrev(huf);
					break;
				}
				if (n.getNext()==tail)
				{
					DLNode huf=new DLNode(tail,n,o);
					n.setNext(huf);
					tail.setPrev(huf);
					break;
				}
				n=n.getNext();
			}
		}
		size++;
	}

	public HuffmanNode removeFirst() throws Exception
	{
		if(isEmpty())
		throw new Exception("Queue is empty");
		HuffmanNode o=head.getNext().getElement();
		DLNode sec=head.getNext().getNext();
		head.setNext(sec);
		sec.setPrev(head);
		size--;
		return o;
	}

//public HuffmanNode removeLast() throws Exception
//{
//	if(isEmpty())	
//	  throw new Exception("Queue is empty");
//	DLNode d=tail.getPrev();
//	HuffmanNode o=tail.getPrev().getElement();
//	tail.setPrev(d.getPrev());
//	d.getPrev().setNext(tail);
//	size--;
//	return o;
//}

	public boolean isEmpty()
	{
		if(size==0)return true;
		return false;
	}
	public int sizeQ()
	{
		return size;
	}

//public HuffmanNode first()throws Exception
//{
//	if(isEmpty())
//	  throw new Exception("Stack is empty");
//	return head.getNext().getElement();
//}
//
//public HuffmanNode Last()throws Exception
//{
//	if(isEmpty())
//	  throw new Exception("Stack is empty");
//	return tail.getPrev().getElement();
//}
}