package aa;
import java.io.*;
import java.net.*;
import java.util.*;


/** Node of a doubly linked list of strings */
class DNode 
{
	protected String element;	// String element stored by a node
	protected DNode next, prev;	// Pointers to next and previous nodes

	/** Constructor that creates a node with given fields */
	public DNode(String e, DNode p, DNode n) 
	{
		element = e;
		prev = p;
		next = n;
	}

	/** Returns the element of this node */
	public String getElement()
	{ 
		return element;
	}

	/** Returns the previous node of this node */
	public DNode getPrev() 
	{ return prev; }

	/** Returns the next node of this node */
	public DNode getNext() 
	{ return next; }

	/** Sets the element of this node */
	public void setElement(String newElem) 
	{ element = newElem; }

	/** Sets the previous node of this node */
	public void setPrev(DNode newPrev) 
	{ prev = newPrev; }

	/** Sets the next node of this node */
	public void setNext(DNode newNext) 
	{ next = newNext; }

}

public class MyDlist 
{
	public DNode header;
	public DNode trailer;
	int size;

	public MyDlist()
	{
		header=new DNode(null,null,null);
		trailer=new DNode(null,null,null);
		header.setNext(trailer);
		trailer.setPrev(header);
		size=0;
	}

	public MyDlist(String f) throws Exception
	{
		header=new DNode(null,null,null);
		trailer=new DNode(null,null,null);
		header.setNext(trailer);
		trailer.setPrev(header);
		size=0;

		//   DNode last=trailer.getPrev();
		if(f.equals("stdin"))
		{
			Scanner input = new Scanner(System.in);

			String line="As";
			while (!(line=input.nextLine()).isEmpty())
			{
				DNode current = new DNode(line,trailer.getPrev(),trailer);
				trailer.getPrev().setNext(current);
				trailer.setPrev(current);
				size++;
				//System.out.println(current.getElement() + "  "+size);
			} 
			input.close();
		}
		else
		{

			String line=null;  
//			FileReader fileReader=null;  
//			BufferedReader br=null;  

			try 
			{  
				URL u = MyDlist.class.getResource(f);
			//	System.out.println(f+" "+u);
			    Scanner in = new Scanner(new File(u.toURI()));
//				fileReader=new FileReader("/Users/sunjianqiang/Desktop/myfile");  
//				br=new BufferedReader(fileReader);   
				while(in.hasNextLine())
				{  
					line = in.nextLine();
					DNode current = new DNode(line,trailer.getPrev(),trailer);
					trailer.getPrev().setNext(current);
					trailer.setPrev(current);
					size++;
				}  					
			} 
			catch (IOException e) //throws FileNotFoundException 
			{
				e.printStackTrace();
			}
		}  

	}
	public void printList()
	{
		DNode node = header.getNext();
		while( (node) != trailer)
		{
			System.out.println(node.element);
			node=node.getNext();
		}

	}

	public static MyDlist cloneList(MyDlist u)
	{
		DNode head2 = new DNode(null,null,null);
		DNode trailer2 = new DNode(null,null,null);
		head2.setNext(trailer2);
		trailer2.setPrev(head2);
		
		DNode point =u.header;
		
		while( (point.getNext()) != (u.trailer)  )
		{
			DNode node1 = point.getNext();// 先找到原始节点的下一个节点地址
			
			String aa=node1.getElement();//得到这个节点的内容
			
			DNode lastone=trailer2.getPrev(); //得到 复制链表的 最后一个节点地址
			
			DNode node2=new DNode(aa,lastone,trailer2); //新建一个节点连上去
			
			lastone.setNext(node2);
			trailer2.setPrev(node2);			
		}
		return head2; //为什么要返回一个MyDlist类型的对象？？？
	}

	 /** Inserts the given node z after the given node v. An error occurs
	    * if v is the trailer */
	  public void addAfter(DNode v, DNode z) 
	  {
	    DNode w = v.getNext();	// may throw an IllegalArgumentException
	    z.setPrev(v);
	    z.setNext(w);
	    w.setPrev(z);
	    v.setNext(z);
	    size++;
	  }
	  /** Inserts the given node at the head of the list */
	  public void addFirst(DNode v) {
	    addAfter(header, v);
	  }
	
	public static void main(String[] args) throws Exception 
	{	 
		System.out.println("please type some strings, one string each line and an empty line for the end of input:");
		/** Create the first doubly linked list
		    by reading all the strings from the standard input. */
		MyDlist firstList = new MyDlist("stdin");

		/** Print all elememts in firstList */
		firstList.printList();

		/** Create the second doubly linked list                         
		    by reading all the strings from the file myfile that contains some strings. */
		MyDlist secondList=new MyDlist("myfile");

		/** Print all elememts in secondList */                     
		secondList.printList();

		/** Innsert "data" into firstList */
		firstList.addFirst(new DNode("data", null, null));

		/** insert "structures" into firstList */
		firstList.addFirst(new DNode("structures", null, null));

		/** Print all elements in firstList. */
		firstList.printList();

		/** Innsert "data" into secondtList */
		secondList.addFirst(new DNode("data", null, null));

		/** insert "structures" into secondList */
		secondList.addFirst(new DNode("structures", null, null)); 

		/** Print all elements in secondList. */                 
		secondList.printList();

		//		   /** Concatenate firstList and secondList into thirdList */
		//		    MyDlist thirdList = concatenateList(firstList, secondList);
		//
		//		   /** Print all elements in thirdList. */ 
		//		    thirdList.printList(); 
		//
		//		   /** Remove all the nodes in thirdList that contains "data". */
		//		    firstList.removeNode("data");
		//
		//		   /** Print thirdList. */
		//		    thirdList.printList();
		//		 
		//		   /** Remove all the nodes in thirdList that contains "structures". */
		//		    thirdList.removeNode("structures");
		//		     
		//		   /** Print thirdList. */
		//		    thirdList.printList();   
		//		  
		//		   /** Clone thirdList */
		//		    MyDlist fourthList = cloneList(thirdList);
		//
		//		   /** Print all elements in fourthList. */
		//		     fourthList.printList();
	}

}
















