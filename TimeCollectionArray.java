package assignment3;
/*
 * Braeden Bodily
 * Julian Whitteron
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import assignment3.CollectionArray;

public class TimeCollectionArray 
{
	private static Random rand;
	
	public static void main(String[] args)
	{
		IntegerComparator cmp = new IntegerComparator();
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int arraySize = 1000;
		int timesToLoop = 10000;
		long timeStart,timeEnd;
		ArrayList<Long> timeToSort = new ArrayList<Long>();
		
		System.out.println("Sorting Method-----------------");

		while (arraySize <= 20000)
		{
			for (int i = 0; i < timesToLoop;i++ )
			{
				CollectionArray<Integer> test =  new CollectionArray<Integer>();
				while (test.size < arraySize)
					test.add(rand.nextInt());
				timeStart =System.nanoTime();
				test.toSortedList(cmp);
				timeEnd = System.nanoTime();
				timeToSort.add(timeEnd-timeStart);
			}
			long sum = 0 ;
			for (int i = 0; i < timeToSort.size();i++)
			{
				sum+=timeToSort.get(i);
			}
			System.out.println(sum/arraySize);

			arraySize+=1000;
		}
		
		System.out.println("Contains Method-----------------");
		arraySize = 1000;
		ArrayList<Long> timeToContain = new ArrayList<Long>();
		while (arraySize <= 20000)
		{
			// Create an array and search for integers timeToLoop times.
			CollectionArray<Integer> test =  new CollectionArray<Integer>();
			while (test.size < arraySize)
		{
				test.add(rand.nextInt());
			}
	
	
			for (int i = 0; i < timesToLoop;i++ )
			{
				int search = rand.nextInt();
			
				// Start Timing
				timeStart =System.nanoTime();
				test.contains(search);
				timeEnd = System.nanoTime();
				
				// Add time to array
				timeToContain.add(timeEnd-timeStart);
			}
			long sum = 0 ;
			for (int i = 0; i < timeToContain.size();i++)
			{
				sum+=timeToContain.get(i);
			}
//			System.out.println(timeToContain.size());
			System.out.println(sum/timeToContain.size());

			arraySize+=1000;
		}
	
	
		
		
		System.out.println("Binary Search-----------------");
		

		
		for (arraySize = 1000; arraySize <= 20000; arraySize = arraySize + 1000)
		{

			
			// Create an array and search for integers timeToLoop times.
			CollectionArray<Integer> test =  new CollectionArray<Integer>();
			
			// fill the new CollectionArray with integers.
			while (test.size < arraySize)
			{
				test.add(rand.nextInt());
			}
			
			// Sort the array for binary
			ArrayList<Integer> binaryTest = test.toSortedList(cmp);
			
			// Create array to add times to
			ArrayList<Long> timeToBinary = new ArrayList<Long>();

			// Search timesToLoop times.
			for (int i = 0; i < timesToLoop; i++)
			{
				int search = rand.nextInt();
							
				// Start Timing
				System.nanoTime();
				timeStart = System.nanoTime();
				SearchUtil.binarySearch(binaryTest, search, cmp);
				timeEnd = System.nanoTime();
				
				// Add time to array
				timeToBinary.add(timeEnd - timeStart);
			}
			
			long sum = 0 ;
			for (int i = 0; i < timeToBinary.size(); i++)
				
			{
				sum = sum + timeToBinary.get(i);				
			}

			System.out.println(sum / timeToBinary.size());
		}
//		arraySize = 1000;
//		ArrayList<Long> timeToBinary = new ArrayList<Long>();
//		while (arraySize <= 20000)
//		{
//			// Create an array and search for integers timeToLoop times.
//			CollectionArray<Integer> test =  new CollectionArray<Integer>();
//			while (test.size < arraySize)
//				test.add(rand.nextInt());
//			test.toSortedList(cmp);
//			for (int i = 0; i < timesToLoop; i++)
//			{
//				ArrayList<Integer> binarytest =  new ArrayList<Integer>();
//				while (binarytest.size() < arraySize)
//					binarytest.add(rand.nextInt());
//				int search = rand.nextInt();
//				timeStart =System.nanoTime();
//				SearchUtil.binarySearch(binarytest, search, cmp);
//				timeEnd = System.nanoTime();
//				timeToBinary.add(timeEnd-timeStart);
//			}
//			sum = 0 ;
//			for (int i = 0; i < timeToBinary.size();i++)
//			{
//				sum = sum + timeToBinary.get(i);
//			}
//			System.out.println("N " + timeToBinary.size() + " Time " + sum/timeToBinary.size());
//
//			arraySize = arraySize + 1000;
//		}
		
		


	
		//TODO: Write code to time your toSortedList, contains, and SearchUtil.binarySearch methods so you can plot the results.

		// Consult lab 2 experiment 8 for timing code ideas
		
		System.out.println("done");
	}

	public static Integer randomInt()
	{
		return new Integer(rand.nextInt());
	}
}
