import java.io.*;
import java.util.*;
import java.lang.*;
public class Exchange
{
	int uid;// Unique identification number 
	Exchange pr; // parent of the current Exchange
	int child_size; // number of children
	LList childs=new LList();
	MobilePhoneSet all_resident_phonenumbers=new MobilePhoneSet(); // contains all the mobile phone number under the tree rooted at this exchange 
	Exchange(int number)
	{
		this.uid=number; // Creating the uid of the new Exchange
		this.child_size=0;
		this.pr=null;
		childs=new LList();
	}
	public Exchange parent()
	{
		return this.pr;
	}
	public int numChildren()
	{
		return this.child_size;
	}

	public Exchange child(int i) // returns the ith exchange child. That is the ith member of linked list childs

	{
		int c=1;
		Node temp=childs.front;
		while(temp!=null)
		{
			if (c==i)return (Exchange)temp.data; else {temp=temp.next;c++;}
		}
		System.out.println(""+i+"th child not present.");
		return null;

	}
	public Boolean isRoot()
	{
		if (this.pr==null)return true; else return false;
	}
	public RoutingMapTree subtree(int i)
	{
if (this.child(i)==null)return null;
		RoutingMapTree x=new RoutingMapTree(this.child(i));
		return x;
	}
	public MobilePhoneSet residentSet()
	{
		return all_resident_phonenumbers;
	}
}