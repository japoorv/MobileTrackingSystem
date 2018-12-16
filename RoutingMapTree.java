import java.util.*;
import java.io.*;
import java .lang.*;
public class RoutingMapTree
{
	Exchange front=null;
		RoutingMapTree()
		{
			front=new Exchange(0);
		}
RoutingMapTree(Exchange x)
{
	front=x;
}
public  void switchoff(int a)
	{
		switchoffs(this,a);
		
	}

static void switchoffs(RoutingMapTree t,int a)
	{
		if (t.front.all_resident_phonenumbers.x.front!=null&&t.front.all_resident_phonenumbers.x.IsMember(a,(MobilePhone)null))
		{
			Node temp=t.front.all_resident_phonenumbers.x.front;
			while(temp!=null)
			{	if (((MobilePhone)temp.data).id==a){((MobilePhone)temp.data).switchOff();break;}
			temp=temp.next;}

		for (int c=1;c<=t.front.child_size;c++)
			switchoffs(t.front.subtree(c),a);	
		return ;
		}
		else return;
		
				

	}
	public  void deletephone(int a)
	{
		RoutingMapTree t=this;
		if (t.front.all_resident_phonenumbers.x.front!=null&&t.front.all_resident_phonenumbers.x.IsMember(a,(MobilePhone)null))
		{
			t.front.all_resident_phonenumbers.x.Delete(a);
			

		for (int c=1;c<=t.front.child_size;c++)
			this.front.subtree(c).deletephone(a);	
		return ;
		}
		else return;
		
				

	}
	public MobilePhone getMobile(int a)
	{
		Node temp=this.front.all_resident_phonenumbers.x.front;
		while(temp!=null)
			if (((MobilePhone)temp.data).id==a)return (MobilePhone)temp.data;
		else temp=temp.next;
		return null;
	}
public Exchange location(int a)
{

	if (front.all_resident_phonenumbers.x.IsMember(a,(MobilePhone)null))	
	{
	
		if (front.child_size==0)return front;
		Exchange retval=null;
		for (int c=1;c<=front.child_size;c++)
			if (front.subtree(c).location(a)!=null)retval=front.subtree(c).location(a);
	return retval;
	}
	else return null;
}
		public Exchange uidtoExch(int uid)
		{
			if (front.uid==uid)return front;
			Node temp=front.childs.front;
			int c=1;
			Exchange istrue=null;
			Exchange retval=null;
			while(temp!=null)
			{
				istrue=front.subtree(c).uidtoExch(uid);
				if (istrue!=null)retval=istrue;
				c++;
				temp=temp.next;
			}
			return retval;
		}

		public Exchange findphone(int a)
		{
			MobilePhone m=getMobile(a);
			if (m==null||m.stat==false)return null;
			else return m.location();
		}
		public Boolean containsNode(Exchange a)
		{

			if (front.uid==a.uid)return true;
			Node temp=front.childs.front;
			Boolean istrue=false;
			int c=1;
			while(temp!=null&&istrue==false)
			{
				istrue=front.subtree(c).containsNode(a);
				c++;
				temp=temp.next;
			}
			return istrue;
		}
		public void add(int a,int b)
		{

			Exchange newnode=new Exchange(b);
			newnode.pr=uidtoExch(a);
			newnode.pr.childs.Insert(newnode);newnode.pr.child_size++;
		}
		public Exchange lowestRouter(int a,int b)
		{
			Exchange a1=uidtoExch(a);
			Exchange b1=uidtoExch(b);
			if (a==b)return a1;
			while(a1!=null)
			{
				if ((a1.child_size!=0&&(new RoutingMapTree(a1)).containsNode(new Exchange(b)))||a1.uid==b1.uid){return a1;}
				else a1=a1.pr;
			}
			return null;
		}

		public ExchangeList routeCall(MobilePhone a,MobilePhone b)
		{
			Exchange a1=a.location();
			Exchange b1=b.location();
		//	System.out.println("aa "+a1.uid+" " +b1.uid);
			Exchange lr=lowestRouter(a1.uid,b1.uid);
		//	System.out.println(lr);
			ExchangeList retval=new ExchangeList();
			while(true)
			{
				
				retval.x.Insert(b1.uid);
				if (b1.uid==lr.uid)break;
				b1=b1.pr;
			//	System.out.println(b1.uid);

			}
			LList t=new LList();
			while(a1.uid!=lr.uid)

			{
				t.Insert(a1.uid);
				a1=a1.pr;
			}
			Node temp=t.front;
			while(temp!=null)
			{
				retval.x.Insert(temp.data);
				temp=temp.next;
			}
			return retval;
		}
		public String performAction(String actionMessage)
		{
					
					Scanner s=new Scanner(actionMessage);
					String x=s.next();
					String retval="";
			if (x.equals("addExchange")){
			try{
				int a,b;
				a=s.nextInt(); // Add b to a Exchange 
				b=s.nextInt();

				if (!this.containsNode(new Exchange(a))) // if this doesn't have a
					throw new nomem();
				else 
				{
					this.add(a,b);
				}	
			}
			catch (nomem en)	
			{
				retval="Error ! Exchange a not in Routing Tree";
			}
			catch(Exception en)
			{
				retval="Error ! Something went wrong.";
			}
			
			}
			else if (x.equals("switchOnMobile"))
			{
				try{
				int a,b;
				a=s.nextInt();
				b=s.nextInt();
				if (!this.containsNode(new Exchange(b)))
					throw new nomem();
				else
				{
					
					Exchange temp=this.uidtoExch(b); 	// Getting the Exchange corrosponding to b
					Node ff=temp.all_resident_phonenumbers.x.front; // Getting the head of the linked list containing the mobile phone numbers under Exchange b
					if ((new MobilePhone(a,this)).location()!=null&&(new MobilePhone(a,this)).location()!=this.uidtoExch(b))
						{if (this.getMobile(a).stat==true)throw new phone(); else this.deletephone(a);}
					int fl=-1;
					while(ff!=null)
					{
						
						if (((MobilePhone)ff.data).id==a){
							((MobilePhone)ff.data).switchOn();fl=1;
							if (temp.pr!=null){ff=temp.pr.all_resident_phonenumbers.x.front;continue;}
							else break;
						}

							else ff=ff.next;}
					if (fl==-1){MobilePhone x1=new MobilePhone(a,this);for (;temp!=null;temp=temp.pr){temp.all_resident_phonenumbers.x.Insert(x1);}}
				}
			
			}
			catch(nomem en)
			{
				retval="Error ! Exchange b not in Routing Tree";
			}
			catch (phone en)
			{
				retval="Phone is already switched on in another Exchange";
			}
			catch(Exception en)
			{
				retval="Error ! Something went wrong.";
			}


			}
else if (x.equals("switchOffMobile"))
	{
		try{
		int a=s.nextInt();MobilePhone xx=new MobilePhone(-1,this);
	 	Node ddd=this.front.all_resident_phonenumbers.x.front;
		if (!this.front.all_resident_phonenumbers.x.IsMember(a,xx))throw new nomem();
		else
		{
			switchoff(a);
		}
	}

	
	catch(nomem en)
	{

		retval="Error ! Mobile Phone not found .";
	}

		}
		else if (x.equals("queryNthChild"))
		{
			try 
			{
				int a,b;
				a=s.nextInt();
				b=s.nextInt();
				b++;
				b=this.uidtoExch(a).child_size-b+1;
					if (!this.containsNode(new Exchange(a))||this.uidtoExch(a).child_size<b||b<=0)throw new nomem();
				else  retval=""+this.uidtoExch(a).child(b).uid;
			}
			catch(nomem en)
			{
				retval="Error ! Exchange not found.";
			}
		}

		else if (x.equals("queryMobilePhoneSet"))
		{
			
			try
			{
				int a=s.nextInt();
				if (!this.containsNode(new Exchange(a)))throw new nomem();
				Node dd=this.uidtoExch(a).all_resident_phonenumbers.x.front;
				LList vals=new LList();
				
				while(dd!=null)
				{
					//System.out.println(((MobilePhone)dd.data).id+" " +((MobilePhone)dd.data).stat);
					if (((MobilePhone)dd.data).stat)vals.Insert(""+((MobilePhone)dd.data).id+"");
					dd=dd.next;
				}
				dd=vals.front;
				while(dd!=null)
				{
					if (dd.next!=null)
					retval+=""+dd.data+", ";
				else retval+=dd.data;
					dd=dd.next;
				}
				
			}
			catch(nomem en)
			{
				retval="Error ! Exchange not found.";
			}
		}
		
		else if (x.equals("findPhone"))
		{
			int a=s.nextInt();
			try{
			//	System.out.println("yy");
			Exchange xd=findphone(a);
			//System.out.println("ye "+xd);
			if (xd==null){if (getMobile(a)==null)retval="Error - No mobile phone with identifier "+a+" found in the network"; 
							else throw new phone();}
							else retval=""+xd.uid;
}
catch (phone en)
			{
				retval="Phone is switched off.";
			}
			actionMessage="queryFindPhone "+a;
		}

		else if (x.equals("lowestRouter"))
		{
			int a=s.nextInt();
			int b=s.nextInt();
			try{
			if (uidtoExch(a)==null||uidtoExch(b)==null)throw new nomem();
			Exchange a1=uidtoExch(a);
			Exchange b1=uidtoExch(b);

			if (a==b)retval=""+a;
			while(a1!=null)
			{
				//System.out.println("yes " + a1.uid+ " "+a1.childs.IsMember(b));
				if ((a1.child_size!=0&&(new RoutingMapTree(a1)).containsNode(new Exchange(b)))||a1.uid==b1.uid){retval=""+a1.uid;break;}
				else a1=a1.pr;
			}
		}
			catch(nomem en)
			{
				retval="Exchange not found.";
			}
			actionMessage="queryLowestRouter "+a+" "+b;
		}

		else if (x.equals("findCallPath"))
			{
				int a,b;
			a=s.nextInt();
			b=s.nextInt();
			try{
			if (getMobile(a)==null||getMobile(b)==null)throw new phone();
			else if (getMobile(a).stat==false)retval="Error - Mobile phone with identifier "+a+" is currently switched off";
			else if (getMobile(b).stat==false)retval="Error - Mobile phone with identifier "+b+" is currently switched off";
			else
			{
				ExchangeList re=routeCall(getMobile(a),getMobile(b));
				Node temp=re.x.front;
				
				while(temp!=null)
				{	if (temp.next!=null)retval+=temp.data+", ";
					else retval+=temp.data;
			temp=temp.next;
				}

			}
		}
		catch(phone en)
		{
			retval="Mobile Phone not present";
		}
actionMessage="queryFindCallPath "+a+" "+b;
		}

		else if (x.equals("movePhone"))
		{
			int a=s.nextInt();
			int b=s.nextInt();
			if (getMobile(a)==null)retval="Phone not present";
			else if (uidtoExch(b)==null)retval="Exchange not present";
			else 
			{
				Boolean stat=getMobile(a).stat;
				deletephone(a);
				Exchange temp=this.uidtoExch(b); 	// Getting the Exchange corrosponding to b
					
					Node ff=temp.all_resident_phonenumbers.x.front; // Getting the head of the linked list containing the mobile phone numbers under Exchange b
		
					int fl=-1;
					while(ff!=null)
					{
						
						if (((MobilePhone)ff.data).id==a){
							((MobilePhone)ff.data).switchOn();fl=1;
							if (temp.pr!=null){ff=temp.pr.all_resident_phonenumbers.x.front;continue;}
							else break;
						}

							else ff=ff.next;}
					if (fl==-1){MobilePhone x1=new MobilePhone(a,this);for (;temp!=null;temp=temp.pr){temp.all_resident_phonenumbers.x.Insert(x1);}}
			if (stat==false)switchoffs(this,a);
		//	System.out.println("y"+getMobile(a).location().uid);
			}
		}

			if (retval!="")retval=actionMessage+": "+retval;

			return retval;
		}

}