package assignment3;
/*
 * Braeden Bodily
 * Julian Whitteron
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Daniel Kopta and Paymon Saebi
 * Implements the Collection interface using an array as storage.
 * The array must grow as needed.
 * All methods should be implemented as defined by the Java API, unless otherwise specified in the assignment.
 * 
 * It is your job to fill in the missing implementations and to comment this class. Every method should have
 * comments (Javadoc-style prefered, but not necessary). The comments should at least indicate what the method
 * does, what the arguments mean, and what the returned value is. It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be commented.
 *
 * @param <E> - generic type placeholder
 */
public class CollectionArray<E> implements Collection<E> 
{
	E data[]; // Storage for the items in the collection
	int size; // Keep track of how many items we hold

	// There is no clean way to convert between E and Object, the text book discusses this.
	@SuppressWarnings("unchecked")  
	public CollectionArray()
	{
		size = 0;
		// We can't instantiate an array of unknwon type E, so we must create an Object array, and typecast
		data = (E[]) new Object[10]; // Start with an initial capacity of 10
	}
	
	/**
	 * This is a helper function specific to ArrayCollection
	 * Doubles the size of the data storage array, retaining its current contents.
	 */
	@SuppressWarnings("unchecked")
	private void grow()
	{
		// You will need to use something similar to the code in the constructor above to create a new array.
		
		//If the current length is 0, it will just produce a new array 2 in size.
		if(data.length == 0)
			data =(E[]) new Object[2];

		// Create new array with double the size.
		E doubleData[] =(E[]) new Object[data.length * 2];
		
		// Fill new array with old array.
		for (int i = 0; i < data.length; i++)
		{
			doubleData[i] = data[i];
		}
		
		data = doubleData;
	}	
	
	public boolean add(E arg0) 
	{
		// Search the array to see if item is already in there.
		for (int i = 0; i < data.length; i++)
		{
			if (arg0.equals(data[i]))
				return false;
		}
		//If the current value for size is the same as the length of data, it will call grow.
		if (size==data.length)
			grow();
		//Afterwards it assigns arg0 to the slot at size on data, increments size and then returns true.
		data[size] = arg0;
		size++;
		return true;
	}
	
	public boolean addAll(Collection<? extends E> arg0) 
	{
		int sizeAtFirst = size;
		
		// Turn arg0 into an array
		E convertArray[] = (E[]) arg0.toArray();
			
		
		// Add new array to old one
		for(int i = 0; i < convertArray.length;i++)
			add(convertArray[i]);
			
		// If nothing was added return false.
		if (data.length == sizeAtFirst)
			return false;
		
		return true;
	}


	@SuppressWarnings("unchecked")
	public void clear() 
	{
		//Turns data into a new array collection with 0 size.
		data = (E[]) new Object[10];
		size = 0;
	}

	public boolean contains(Object arg0) 
	{
		//Runs through the array of data and compares each element against arg0, returns true is there is an equal.
		for (int i = 0; i < size;i++)
			if(arg0.equals(data[i]))
				return true;
		
		return false;
	}

	public boolean containsAll(Collection<?> arg0) 
	{
		//Turns arg0 into an array for analysis.
		E convertArray[] = (E[]) arg0.toArray();
		//Goes through data and checks to make sure each item in arg0 is in data, if any return false it returns false.
		for (int i = 0; i < arg0.size(); i++)
			if (!contains(convertArray[i]))
				return false;
		
		return true;
	}

	public boolean isEmpty() 
	{
		//If the size of the collection is 0, returns true.
		if (this.size()==0)
			return true;
		
		return false;
	}

	public Iterator<E> iterator() 
	{
		return new CollectionArrayIterator();
	}

	public boolean remove(Object arg0) 
	{
		// If it does not contain arg0, return false.
		// if (!contains(arg0))
		//  	return false;
		
		// Look through the array until you find an object equal to arg0.
		for (int i = 0; i < data.length; i++)
		{
			// Now that we've found an item equal to arg0, shift everything to the right of it to the left one spot.
			if (arg0.equals(data[i]))
			{
				// Put the object to the right into the left.
				for (int j = i + 1; j < data.length; i++, j++)
				{
					data[i] = data[j];
				}
				data[data.length-1] = null;
				// Drop the size by one.
				size--;
				return true;
			}
		}
		// No items matching arg0 were in the array so false will be returned.
		return false;
	}

	public boolean removeAll(Collection<?> arg0) 
	{
		int sizeAtFirst = size;
		
		// Turn arg0 into an array
		@SuppressWarnings("unchecked")
		E convertArray[] = (E[]) arg0.toArray();
		
		// Go through each item in the array and remove it.
		for (int i = 0; i < convertArray.length; i++)
			remove(convertArray[i]);
		
		// Determine if anything was removed by comparing size of new array.
		if (sizeAtFirst == size)
			return false;
		
		return true;
	}

	public boolean retainAll(Collection<?> arg0) 
	{
		//Boolean keeping track if anything was removed
		boolean somethingRemoved = false;
		//Creates a new iterator object
		CollectionArrayIterator iterator = new CollectionArrayIterator();
		//While the iterator has a next entry in data, it will compare the next object against the arg0 collection, and if it
		//doesn't exist in both, removes it.
		while(iterator.hasNext())
		{
			E thing = iterator.next();
			if (!arg0.contains(thing))
			{
				iterator.remove();
				somethingRemoved = true;
			}
		}
		
		return somethingRemoved;
	}

	public int size() 
	{
		//Returns size
		return size;
	}

	public Object[] toArray() 
	{
		//Creates a new array based off the size of the current number of entries (int size), eliminating empty spots.
		E outPutArray[] = (E[]) new Object[size];
		for (int i = 0; i < outPutArray.length;i++)
		{
			outPutArray[i] = data[i];
		}
		return outPutArray;
	}

  /* 
   * Don't implement this method (unless you want to).
   * It must be here to complete the Collection interface.
   * We will not test this method.
   */
  public <T> T[] toArray(T[] arg0) 
  {
	return null;
  }  
  
  /*
   Sorting method specific to ArrayCollection - not part of the Collection interface
   Must implement an insertion sort (see lecture 6 for code ideas).
   */
  public ArrayList<E> toSortedList(Comparator<? super E> cmp)
  {
	for(int i = 1; i < size; i++)
	{
		E val = data[i];
		int j;
		for(j = i - 1; (j >= 0) && (cmp.compare(data[j], val)) > 0; j--)
		{
			data[j + 1] = data[j];
		}
		data[j+1] = val;
	}
	ArrayList<E> output = new ArrayList<E>();
		for (int i = 0; i < size;i++)
			output.add(data[i]);
	return output;
  }  
  
  private class CollectionArrayIterator implements Iterator<E>
  {
		int iterator;
		boolean canRemove;
		
	  	public CollectionArrayIterator()
		{
		  iterator = 0;
		  canRemove = false;
		}	
		
		public boolean hasNext() 
		{
		  // If there is no next, return false.	
		  if ((iterator + 1) > size)
			  return false;
		  
		  return true;
		}
		
		public E next() 
		{
		  // Check to see if the iterator is in bounds.  If not it will throw an exception.
		  if ((iterator+1) > size)
			 throw new NoSuchElementException();
		  
		  // Increment iterator by one, and return the data at previous iterator.
		  iterator++;
		  // Change canRemove so that the remove method can be called.
		  canRemove = true;
		  return data[iterator - 1];
		}
		
		public void remove() 
		{
			//If the canRemove boolean is true, it will remove the element last indicated by next and then sets canRemove to false.
			if (canRemove)
			{
				E itemToRemove = (E) data[iterator - 1];
				CollectionArray.this.remove(itemToRemove);
				canRemove = false;
				iterator--;
			}
			else
				throw new IllegalStateException();	
			
		}	
  }
}
