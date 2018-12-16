import java.util.*;
import java.io.*;
import java.lang.*;
public class MobilePhone
{
	int id;
	Boolean stat;
	RoutingMapTree tree;
	MobilePhone(int a,RoutingMapTree r)
	{
		id=a;
		stat=true;
		tree=r;

	}
	public int number()
	{
		return this.id;
	}
	public Boolean status()
	{
		return stat;
	}
	public void switchOn()
	{
		stat=true;
	}
	public void switchOff()
	{
		stat=false;
	}
	public Exchange location() 
	{
		return tree.location(this.id);
	}

}