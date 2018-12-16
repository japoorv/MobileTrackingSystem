import java.util.*;
import java.io.*;
import java.lang.*;
public class Node
{
	Object data;
	Node next;
	Node(Object o)
	{
		this.data=o;
		this.next=null;
	}
	Node (Node a)
	{
		if (a==null)return;
		this.data=a.data;
		this.next=a.next;
	}
}