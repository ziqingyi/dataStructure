package aa;
import java.io.*;
import java.util.*;


public class MyDlist extends DList
{
	public MyDlist()
	{
		super();
	}

	public MyDlist(String f) throws Exception
	{
		super();

		if(f.equals("stdin")) //if the input is stdin
		{
			Scanner input = new Scanner(System.in);

			String line="As";
			while (!(line=input.nextLine()).isEmpty())  //read the input from the standard input
			{
				DNode current = new DNode(line,trailer.getPrev(),trailer);// create a node and put it in the list
				trailer.getPrev().setNext(current); //let the last one point to the node
				trailer.setPrev(current);   // make the trailer point to the node
				size++;
			} 
			input.close();
		}
		else
		{
			try 
			{  						
				Scanner in = new Scanner(new File(f)); //get file 
				while(in.hasNext()) // if there is words in the file
				{
					this.addLast(new DNode(in.next(),null,null));//create a node and add into the list
				}
				in.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}  

	}
	public void printList()
	{
		DNode node = header.getNext(); // get the first one of the list
		while( (node) != trailer)  // if the node is not trailer
		{
			System.out.println(node.getElement());
			node=node.getNext();     // get the next node
		}

	}

	public static MyDlist cloneList(MyDlist u)
	{
		MyDlist dd = new MyDlist();

		DNode aa=u.getFirst();    // get the first node of u
		while(aa!=u.trailer)   
		{
			String pp = aa.getElement();
			DNode now = new DNode(pp,null,null);
			dd.addLast(now);
			aa=u.getNext(aa);    // get the next node of aa and let aa point to it.
		}

		return dd; 
	}

	public static MyDlist concatenateList(MyDlist u,MyDlist v)
	{
		MyDlist uv =new MyDlist();
		
		DNode aa=u.getFirst();
		while(aa!=u.trailer)  // put the u into uv
		{
			String pp = aa.getElement();    // get the element of u
			DNode now = new DNode(pp,null,null); //store the element in a new node
			uv.addBefore(uv.trailer, now);  // add the node into uv
			aa=u.getNext(aa);   // get the next node
		}
		
		DNode bb=v.getFirst();
		while(bb!=v.trailer)  // put v to the end of uv
		{
			String qq = bb.getElement();  
			DNode now2 = new DNode(qq,null,null);
			uv.addBefore(uv.trailer, now2);
			bb=v.getNext(bb);
		}
		
		return uv;
	}
	
	public void removeNode(String e)
	{
		
		DNode aa=this.getNext(header); //get the first node of MyDlist object
		DNode kk=null;                  
		while( (this.size())!=0 && aa!=this.trailer ) //traverse the MyDlist object
		{
			String word=aa.getElement(); // get the element of each node of MyDlist object
			if(word==e)           
				{
				   kk=this.getNext(aa);  //get the reference of aa's next node
				   this.remove(aa);// delete the node aa from MyDlist object
				   aa=kk;
				}
			else
			    aa=this.getNext(aa);//get the reference of aa's next node
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
















