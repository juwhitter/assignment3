package assignment3;
/*
 * Braeden Bodily
 * Julian Whitteron
 */
import java.util.ArrayList;
import java.util.Comparator;

import junit.framework.TestCase;

public class TestCollectionArray extends TestCase 
{
	CollectionArray<Integer> test =  new CollectionArray<Integer>();
	
	protected void setUp() throws Exception 
	{
		
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	//Tests putting in a single value.
	public void testSingleInsert()
	{
			assertEquals(true,test.add(1));
	}
	//Tests the grow() method by adding more values than the current capacity
	public void testGrow()
	{
		for (int i = 0; i < 10;i++)
			test.add(i);
		assertEquals(true,test.add(20));
		assertEquals(11,test.size);
	}
	//Adds 4 to the collection, then tries to add 4 again, testing the duplicate prevention.
	public void testAdd()
	{
		test.add(4);
		assertEquals(false,test.add(4));
		assertEquals(true,test.add(5));
	}
	//Testing adding a whole collection to the current collection, then verifying the size variable.
	public void testAddAll()
	{
		for (int i = 0; i < 5; i++)
			test.add(i);
		CollectionArray<Integer> addition =  new CollectionArray<Integer>();
		for (int i = 3; i < 8;i++)
			addition.add(i);
		assertEquals(true,test.addAll(addition));
		assertEquals(8,test.size);
	}
	//Testing clear() by checking the size after an input and then after the clear.
	public void testClear()
	{
		for (int i = 0; i < 20; i++)
			test.add(i);
		assertEquals(20,test.size);
		test.clear();
		assertEquals(0,test.size);
		
	}
	//Testing contains() by checking an expected value and a not present value.
	public void testContains()
	{
		for (int i = 0; i < 20; i++)
			test.add(i);
		assertEquals(true,test.contains(7));
		assertEquals(false,test.contains(21));
	}
	//Dual test of remove() and contains()
	public void testRemoveContains()
	{
		for (int i = 0; i < 20; i++)
			test.add(i);
		test.remove(8);
		assertEquals(false,test.contains(8));
	}
	//Tests removing the last item on the list to make sure we don't end up with double entries at the end.
	public void testRemoveLast()
	{
		for(int i = 0; i < 5; i++)
			test.add(i);
		
		test.remove(4);
		assertEquals(4,test.size);
		assertEquals(false,test.contains(4));
	}
	/*
	 * Testing the containsAll() method by adding 1 value to a new array that is out of the bounds of the first array, then adding
	 * values that are contained in the first array, removing extra value, then comparing the two.
	 */
	public void testContainsAll()
	{
		for (int i = 0; i < 20; i++)
			test.add(i);
		
		CollectionArray<Integer> empty =  new CollectionArray<Integer>();
		empty.add(45);
		assertEquals(false,test.containsAll(empty));
		empty.add(1);
		empty.add(2);
		assertEquals(false,test.containsAll(empty));
		empty.remove(45);
		assertEquals(true,test.containsAll(empty));
	}
	//testing isEmpty(), adding an item, verifying the false value works, then removing it and ensuring true works.
	public void testIsEmpty()
	{
		CollectionArray<Integer> empty =  new CollectionArray<Integer>();
		assertEquals(true,empty.isEmpty());
		empty.add(2);
		assertEquals(false,empty.isEmpty());
		empty.remove(2);
		assertEquals(true, empty.isEmpty());
	}
	//Testing removeAll(), populates an array, populates an array with a smaller scope, then removes the contents of the second array from the first
	public void testRemoveAll()
	{
		for (int i = 0; i < 20; i++)
			test.add(i);
		
		CollectionArray<Integer> toBeRemoved =  new CollectionArray<Integer>();
		for (int i = 0; i < 5;i++)
			toBeRemoved.add(i);
		
		assertEquals(true,test.removeAll(toBeRemoved));
		assertEquals(false,test.containsAll(toBeRemoved));
	}
	//Testing retainAll(), testing what was removed, what wasn't removed and verifying the remainder was correct.
	public void testRetainAll()
	{
		CollectionArray<Integer> retainTest =  new CollectionArray<Integer>();

		for (int i = 0; i < 11;i++)
			test.add(i);
		for (int i = 0; i < 11; i+=3)
			retainTest.add(i);
		
		assertEquals(true,test.retainAll(retainTest));
		assertEquals(true, test.containsAll(retainTest));
		retainTest.add(30);
		assertEquals(false,test.containsAll(retainTest));
		assertEquals(true,test.contains(0));
		assertEquals(false,test.contains(1));
	}
	//Testing retainAll(), testing what was removed, what wasn't removed and verifying the remainder was correct.
	public void testRetainAll2()
	{		
		CollectionArray<Integer> retainTest2 =  new CollectionArray<Integer>();
		
		for (int i = 0; i < 5;i++)
			test.add(i);
		
		for (int i = 0;i <5 ;i++)
			retainTest2.add(i);
		assertEquals(false,test.retainAll(retainTest2));	
	}
	//Testing toSort(), creating a new comparator and sorting a list.
	public void testSort()
	{
		IntegerComparator cmp = new IntegerComparator();
		
		test.add(5);
		test.add(2);
		test.add(1);
		test.add(3);
		test.add(4);
		ArrayList<Integer> output = test.toSortedList(cmp);
		
		assertEquals((int)1,(int)output.get(0));
		assertEquals((int)3,(int)output.get(2));

	}
	//Testing toSort(), creating a new comparator and sorting a list.
	public void testSort2()
	{
		IntegerComparator cmp = new IntegerComparator();

			test.add(1);
			test.add(8);
			test.add(17);
			test.add(12);
			test.add(8);
		ArrayList<Integer> output = test.toSortedList(cmp);
		System.out.println(output);

	}
	//Testing searchUtil
	public void testUtil()
	{
		IntegerComparator cmp = new IntegerComparator();
		test.add(1);
		test.add(8);
		test.add(17);
		test.add(12);
		test.add(8);
		test.add(230);
		test.add(460);
		test.add(-4);
		test.add(-2000);
		ArrayList<Integer> output = test.toSortedList(cmp);
		
		assertEquals(true,SearchUtil.binarySearch(output, 17, cmp));
		assertEquals(false,SearchUtil.binarySearch(output, 100, cmp));
		assertEquals(true,SearchUtil.binarySearch(output, -2000, cmp));

		
	}
}
