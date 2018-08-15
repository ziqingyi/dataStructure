package aa;
import java.io.*;
import java.net.*;
import java.util.*;


public class MyDlist extends DList
{
	//private static MyDlist MyDlist;
	//	public DNode header;
	//	public DNode trailer;
	//	int size;
	//
	//	public MyDlist()
	//	{
	//		header=new DNode(null,null,null);
	//		trailer=new DNode(null,null,null);
	//		header.setNext(trailer);
	//		trailer.setPrev(header);
	//		size=0;
	//	}
	public MyDlist()
	{
		super();
	}

	public MyDlist(String f) throws Exception
	{
//		header=new DNode(null,null,null);
//		trailer=new DNode(null,null,null);
//		header.setNext(trailer);
//		trailer.setPrev(header);
//		size=0;
		super();

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

//			String line=null;   
			try 
			{  
				URL u = MyDlist.class.getResource(f);

				Scanner in = new Scanner(new File(u.toURI()));
			
//				while(in.hasNextLine())
//				{  
//					line = in.nextLine();
//					DNode current = new DNode(line,trailer.getPrev(),trailer);
//					trailer.getPrev().setNext(current);
//					trailer.setPrev(current);
//					size++;
//				}  
				while(in.hasNext())
				{
					this.addLast(new DNode(in.next(),null,null));
				}
				in.close();
			} 
//			catch (FileNotFoundException e)
//			{
//				e.printStackTrace();
//			}
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
		MyDlist dd = new MyDlist();

		//		DNode head2 = new DNode(null,null,null);  //新建一个空链表
		//		DNode trailer2 = new DNode(null,head2,null);
		//		head2.setNext(trailer2);
		//	//	trailer2.setPrev(head2);
		//		
		//		DNode point =u.header;
		//		
		//		while( (point.getNext()) != (u.trailer)  )
		//		{
		//			DNode node1 = point.getNext();// 先找到原始节点的下一个节点地址
		//			
		//			String aa=node1.getElement();//得到这个节点的内容
		//			
		//			DNode lastone=trailer2.getPrev(); //得到 复制链表的 最后一个节点地址，就是trailer2的前一个节点。
		//			
		//			DNode node2=new DNode(aa,lastone,trailer2); //新建一个节点，设置其 内容 和 前后节点。
		//			
		//			lastone.setNext(node2);
		//			trailer2.setPrev(node2);		
		//		}
		//		dd.header=head2;
		//		dd.trailer=trailer2;
		//		dd.size=u.size;

		DNode aa=u.getFirst();
		while(aa!=u.trailer)
		{
			String pp = aa.element;
			DNode now = new DNode(pp,null,null);
			dd.addLast(now);
			aa=u.getNext(aa);
		}

		return dd; 
	}

	public static MyDlist concatenateList(MyDlist u,MyDlist v)
	{
		MyDlist uv =new MyDlist();
		
		DNode aa=u.getFirst();
		while(aa!=u.trailer)
		{
			String pp = aa.element;  
			DNode now = new DNode(pp,null,null);
			uv.addBefore(uv.trailer, now);
			aa=u.getNext(aa);
		}
		
		DNode bb=v.getFirst();
		while(bb!=v.trailer)
		{
			String qq = bb.element;  
			DNode now2 = new DNode(qq,null,null);
			uv.addBefore(uv.trailer, now2);
			bb=v.getNext(bb);
		}
		
		return uv;
	}
	
	public void removeNode(String e)
	{
		
		DNode aa=this.getNext(header);
		DNode kk=null;
		while( (this.size())!=0 && aa!=this.trailer )
		{
			String word=aa.getElement();
			if(word==e)
				{
				   kk=this.getNext(aa);
				   this.remove(aa);
				   aa=kk;
				}
			else
			    aa=this.getNext(aa);
		}
	}

	/** Inserts the given node z before the given node v. An error
	 * occurs if v is the header */
	public void addBefore(DNode v, DNode z) throws IllegalArgumentException {
		DNode u = getPrev(v);	// may throw an IllegalArgumentException
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size++;
	}
	/** Inserts the given node z after the given node v. An error occurs
	 * if v is the trailer */
	public void addAfter(DNode v, DNode z) {
		DNode w = getNext(v);	// may throw an IllegalArgumentException
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
	/** Inserts the given node at the tail of the list */
	public void addLast(DNode v) {
		addBefore(trailer, v);
	}
	/** Removes the given node v from the list. An error occurs if v is
	 * the header or trailer */
	public void remove(DNode v) {
		DNode u = getPrev(v);	// may throw an IllegalArgumentException
		DNode w = getNext(v);	// may throw an IllegalArgumentException
		// unlink the node from the list 
		w.setPrev(u);
		u.setNext(w);
		v.setPrev(null);
		v.setNext(null);
		size--;
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


		/** Concatenate firstList and secondList into thirdList */
		MyDlist thirdList = concatenateList(firstList, secondList);

		/** Print all elements in thirdList. */ 
		thirdList.printList(); 
		

		/** Remove all the nodes in thirdList that contains "data". */
        thirdList.removeNode("data");
        
		/** Print thirdList. */
		thirdList.printList();
		/** Remove all the nodes in thirdList that contains "structures". */
		thirdList.removeNode("structures");

		/** Print thirdList. */
		thirdList.printList();   

		/** Clone thirdList */
		MyDlist fourthList = cloneList(thirdList);

		/** Print all elements in fourthList. */
		fourthList.printList();

	}

}
















