package assignment3;
/*
 * Braeden Bodily
 * Julian Whitteron
 */
import java.util.ArrayList;
import java.util.Comparator;

public class SearchUtil 
{

	
	/**
	 * 
	 * 
	 * @param <T>	The type of elements contained in the list
	 * @param list	An ArrayList to search through. 
	 * 				This ArrayList is assumed to be sorted according 
	 * 				to the supplied comparator. This method has
	 * 				undefined behavior if the list is not sorted. 
	 * @param item	The item to search for
	 * @param cmp	Comparator for comparing Ts or a super class of T
	 * @return		true if "item" exists in "list", false otherwise
	 */
	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp)
	{	
		
		int start = 0;
		int end = list.size()-1;
		//Go as long as the end and start haven't passed each other.
		while (end>=start)
		{
			int startSearch = (end+start)/2;
			if (item.equals(list.get(startSearch)))
				return true;
			
			if (cmp.compare(item,list.get(startSearch)) > 0)
				{
					start = startSearch + 1;
				}
			if (cmp.compare(item,list.get(startSearch)) < 0)
				{
					end = startSearch - 1;
				}
			
		}
		
		return false;
		
	
		}
}
