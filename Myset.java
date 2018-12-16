import java.util.*;
import java.io.*;
import java.lang.*;

public class Myset // Helper Class for implementing the Set data structure not the real deal.
{
	Node front=null;int size=0;
	public Boolean IsEmpty()
	{
	
		if (size!=0)return false; else return true;
	}

	public Boolean IsMember (Object o)
	{
		Node temp=new Node(this.front);
		while(temp!=null)
		{
			if (temp.data.equals(o))return true;
			temp=temp.next;

		}
		return false;
	}

	public Boolean IsMember (int o,MobilePhone xx)
	{
		Node temp=front;
		while(temp!=null)
		{
			if (((MobilePhone)temp.data).id==o)return true;
			temp=temp.next;

		}
		return false;
	}

	public void Insert(Object o)
	{
		 
		 	Node a=new Node(o);
		 	a.next=front;
		 	this.front=new Node(a);
		 	size++;
		 	
	}
	public void Delete(int o)
	{
		Node temp=front;
		if (size==0){System.out.println("Error ! Object not in list");return;}
		if (((MobilePhone)temp.data).id==o){front=front.next;size--;}
		else 
		while(temp!=null)
		{
			try
			{
				if (((MobilePhone)temp.next.data).id==o){temp.next=temp.next.next;size--;break;}
				else {temp=temp.next;}
			}
			catch (Exception en){System.out.println("Error ! Object not in list");break;}
		}
		

	}

	public Myset Union(Myset a)
	{
		Myset x=new Myset();
		Node temp=this.front;
		if (this.IsEmpty())return a;
		else if (a.IsEmpty())return this;
		while(temp!=null)
		{
			if (!x.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}
		temp=a.front;
		while(temp!=null)
		{
			if (!x.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}
		return x;
	}

	public Myset Intersection(Myset a)
	{
		Myset x=new Myset();
		Node temp=a.front;
		
		while(temp!=null)
		{
			if (this.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}


return x;
	}


}