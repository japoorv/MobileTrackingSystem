import java.util.*;
import java.io.*;
import java.lang.*;

public class LList
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

	public void Insert(Object o)
	{
		 
		 	Node a=new Node(o);
		 	a.next=front;
		 	this.front=a;
		 	size++;
		 	
	}
	public Boolean IsMember (int o,MobilePhone xx)
	{
		Node temp=new Node(this.front);
		while(temp!=null)
		{
			if (((MobilePhone)temp.data).id==o)return true;
			temp=temp.next;

		}
		return false;
	}
	public Boolean IsMember (int o,Exchange xx)
	{
		Node temp=new Node(this.front);
		while(temp!=null)
		{
			if (((Exchange)temp.data).uid==o)return true;
			temp=temp.next;

		}
		return false;
	}

	public void Delete(Object o)
	{
		Node temp=front;
		if (size==0){System.out.println("Error ! Object not in list");return;}
		if (temp.data==o){front=front.next;size--;}
		else 
		while(temp!=null)
		{
			try
			{
				if (temp.next.data==o){temp.next=temp.next.next;size--;break;}
				else {temp=temp.next;}
			}
			catch (Exception en){System.out.println("Error ! Object not in list");break;}
		}
		

	}

	

}


