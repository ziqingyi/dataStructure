package net.datastructures;
import java.io.*;
import java.util.Scanner;

public class TaskScheduler
{
	static void scheduler(String file1, String file2, Integer m) throws IOException 
	{
		/**
		 * @param file1
		 * @param file2
		 * @param m
		 * @param hee1
		 * @throws IOException
		 * fist step: put all the tasks of file1 into the object of Task, key is release time. 
		 *            and then put all the objects into one of the HeapPriorityQueue--hee1
		 */
		HeapPriorityQueue<Integer, Task<String,Integer,Integer>> hee1 = new HeapPriorityQueue<Integer, Task<String,Integer,Integer>>();	
		try 
		{
			Scanner in = new Scanner(new File(file1)); //get file 
			while(in.hasNext()) // if there is words in the file
		   {
			 String ss1 =in.next();//only read String
			 Integer ss2=in.nextInt();
			 Integer ss3=in.nextInt();
			 
			 Task<String,Integer,Integer> tt = new Task<String,Integer,Integer>();
			 tt.set(ss1, ss2, ss3);
			 
			 hee1.insert(tt.release,tt);
			 
		   }
		}
		catch(FileNotFoundException e)
		{
			System.out.print(file1+" does not exist\n");
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.print("format errors!");
			System.exit(0);
		}	
		/**
		 * @param hee1
		 * create a new file2 
		 * 
		 * with the time increases, try to put all the released tasks into hee2 ,key is deadline
		 * at the same time ,the core process the tasks form hee2.
		 * 
		 */
        File writer = new File(file2);
        if(writer.exists())
        {
        	System.out.print("already exists"+"\n");
		    return;
		}
        FileWriter resultFile = new FileWriter(writer);
        PrintWriter newfile = new PrintWriter(resultFile);
        
		HeapPriorityQueue<Integer, Task<String,Integer,Integer>> hee2 = new HeapPriorityQueue<Integer, Task<String,Integer,Integer>>();
		int time=0;
		int M=m.intValue();
		
		while (!hee1.isEmpty() || !hee2.isEmpty())
		{			
			while (!hee1.isEmpty() && hee1.min().getKey() == time)
			{
				Entry<Integer, Task<String, Integer, Integer>> current = hee1.removeMin();
				hee2.insert(current.getValue().getD(), current.getValue());
			}         
			for(int pop=0;pop < M;pop++)
			{
				if(!hee2.isEmpty())
				{
					if(hee2.min().getValue().getD()<=time)
					{
						System.out.println(file1+" No feasible schedule exists\n");
						new File(file2).delete();
						return;
					}
					Task<String, Integer, Integer> oo=hee2.removeMin().getValue();				    
				    newfile.print(oo.getN()+" "+time+" ");			   
				}
			}	
			time++;
		}	
		resultFile.close();
	}
	
	public static void main(String[] args) throws Exception
	{

		TaskScheduler.scheduler("samplefile1.txt", "feasibleschedule1", 4);
		/** There is a feasible schedule on 4 cores */
		TaskScheduler.scheduler("samplefile1.txt", "feasibleschedule2", 3);
		/** There is no feasible schedule on 3 cores */
		TaskScheduler.scheduler("samplefile2.txt", "feasibleschedule3", 5);
		/** There is a feasible scheduler on 5 cores */
		TaskScheduler.scheduler("samplefile2.txt", "feasibleschedule4", 4);
		/** There is no feasible schedule on 4 cores */
	}
}
//create a class to store the information of each task 
class Task<N, R, D> 
{
	public N name;
	public R release;
	public D deadline1;

	public void set(N n, R r, D d)
	{
		this.name = n;
		this.release = r;
		this.deadline1 = d;
	}
	public N getN()
	{
		return (N)name;
	}
	public R getR()
	{
		return (R)release;
	}
	public D getD()
	{
		return (D)deadline1;
	}
	public  String toString()
	{
		return (String)this.name;
	}
}
/*
 * Algorithm Complexity Analysis
 * fist step: read tasks form the file1 and put them into a HeapPriorityQueue, the method of insert() will 
 * process all the tasks and sort them based on the keys. input one task will leads to the up-heap bubbling which
 * will take log(n) time. if the file1 has n tasks, the time complexity is n*log(n). 
 * 
 * for remove, if we remove one task, the down-heap bubbling will take log(n) time. n tasks will cost n*log(n)
 * 
 * finally, I insert two times and remove two times ,the total time complexity is 4n*log(n) 
 * O(4nlog(n) )=O(n*log(n))
 * 
 */
























