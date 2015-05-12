/* 
 *  ArraySearch.java
 *  
 *  test Code implementation for interview.
 *  Requirements provided by Nick Vogel of Amazon
 *  
 *  Few changes were made to accommodate Java style
 *  
 *   - function signature starts with lower case
 *   - Value of -1 is used for undefined array index
 * 
 *  Assumptions
 *  -----------
 *  The items are sorted
 *  Items will be non-NULL
 *  There are no duplicate items
 *  
 *  Additional assumption
 *  boolean value for sort order for the array is assumed to be correct 
 *  and correspond to the actual sort order of the array.
 *  
 *  Implementation uses simple linear search 
 *  
 */
package mk;

/*
 * @author Murat Kumykov
 *  
 *  ArrayAearch class implements a search within an array of sorted 
 *  numbers.
 *  
 */
public class ArraySearch {

	public static enum SearchType {
		LessThan, LessThanEquals, Equals, GreaterThanEquals, GreaterThan
	}

	public static enum SearchResultType {
		NotFound, FoundExact, FoundGreater, FoundLess
	}

	/*
	 * Wrapper for a search results
	 * 
	 * overrides equals(Object) and hashCode() methods to 
	 * facilitate comparison of results for testing
	 * 
	 */
	public static class SearchResult {
		public final int index;
		public final SearchResultType searchResultType;

		public SearchResult(int index, SearchResultType searchResultType) {
			this.index = index;
			this.searchResultType = searchResultType;
		}

		@Override
		public boolean equals(Object o) {
			return ((o instanceof SearchResult)
					&& (this.index == ((SearchResult) o).index) 
					&& (this.searchResultType == ((SearchResult) o).searchResultType));
		}

		@Override
		public int hashCode() {
			int hashCode = 47;
			hashCode = hashCode * 17 + this.index;
		    hashCode = hashCode * 17 + this.searchResultType.hashCode();
		    return hashCode;
		}
		
		public String toString() {
			return "At index " + index + ", " + searchResultType;
		}
	}

	/* Search an array of sorted numbers.
	*
	* @param items     An array of sorted ints, with no duplicates
	* @param ascending true if the array is sorted in ascending order
	* @param key       the key to search for
	* @param type      the type of match to find
	*
	* This function finds the element in the array
	* that best fits the search criteria. It returns
	* the match type and the index of the matching item.
	*
	* LessThan
	* --------
	*  Finds the largest item which is less than the key.
	*  It returns FoundLess if a match is found, NotFound
	*  if no match is found.
	*
	* LessThanEquals
	* --------------
	*  Finds the item which is equal to the key, or the
	*  largest item which is less than the key. Returns
	*  FoundExact if an item that exactly matches the key
	*  is found, FoundLess if a non-exact match is found
	*  and NotFound if no match is found.
	*
	* Equals
	* ------
	*  Finds an item which is equal to the key. Returns
	*  FoundExact if an item if found, NotFound otherwise.
	*
	* GreaterThanEquals
	* -----------------
	*  Finds the item which is equal to the key, or the
	*  smallest item which is greater than the key. Returns
	*  FoundExact if an item that exactly matches the key
	*  is found, FoundGreater if a non-exact match is found
	*  and NotFound if no match is found.
	*
	* GreaterThan
	* -----------
	*  Finds the smallest item which is greater than the
	*  key. Returns FoundGreater if a match if found, NotFound
	*  if no match is found.
	*
	* Examples
	* --------
	*  Given the input array [0, 2, 4, 6, 8] (ascending order)
	*
	*  +-----+-------------------+--------------+-------+
	*  | Key | Type              | Returns      | Index |
	*  +-----+-------------------+--------------+-------+
	*  | -1  | LessThanEquals    | NotFound     | X     |
	*  +-----+-------------------+--------------+-------+
	*  |  0  | LessThan          | NotFound     | X     |
	*  +-----+-------------------+--------------+-------+
	*  |  0  | Equals            | FoundExact   | 0     |
	*  +-----+-------------------+--------------+-------+
	*  |  1  | Equals            | NotFound     | X     |
	*  +-----+-------------------+--------------+-------+
	*  |  2  | GreaterThanEquals | FoundExact   | 1     |
	*  +-----+-------------------+--------------+-------+
	*  |  2  | GreaterThan       | FoundGreater | 2     |
	*  +-----+-------------------+--------------+-------+
	*
	*  Given the input array [8, 6, 4, 2, 0] (descending order)
	*
	*  +-----+-------------------+--------------+-------+
	*  | Key | Type              | Returns      | Index |
	*  +-----+-------------------+--------------+-------+
	*  | -1  | LessThan          | NotFound     | X     |
	*  +-----+-------------------+--------------+-------+
	*  |  4  | LessThanEquals    | FoundExact   | 2     |
	*  +-----+-------------------+--------------+-------+
	*  |  8  | Equals            | FoundExact   | 0     |
	*  +-----+-------------------+--------------+-------+
	*  |  5  | GreaterThanEquals | FoundGreater | 1     |
	*  +-----+-------------------+--------------+-------+
	*  |  2  | GreaterThanEquals | FoundExact   | 3     |
	*  +-----+-------------------+--------------+-------+
	*  |  9  | GreaterThan       | NotFound     | X     |
	*  +-----+-------------------+--------------+-------+
	*
	* Assumptions
	* -----------
	*  The items are sorted
	*  Items will be non-NULL
	*  There are no duplicate items
	*/
	
	public static SearchResult search(int[] items, boolean ascending, int key,
			SearchType type) {
		
		switch(type) {
		case LessThan:
			return searchLessThan(items, ascending, key);
		case LessThanEquals:
			return searchLessThanEquals(items, ascending, key);
		case GreaterThanEquals:
			return searchGreaterThanEquals(items, ascending, key);
		case GreaterThan:
			return searchGreaterThan(items, ascending, key);
		case Equals:
			return searchEquals(items, ascending, key);
		default:
			return new SearchResult(-1, SearchResultType.NotFound);
		}
	}

	/*
	 * Finds if there is an exact match in the array. Note that for the exact match
	 * the ascending vs. descending order does not call for a different search
	 * algorithm and thus can be ignored
	 * 
	 * @param items An array of sorted ints, with no duplicates
	 * @param ascending true if the array is sorted in ascending order
	 * @param key the key to search for
	 */
	private static SearchResult searchEquals(int[] items, boolean ascending,
			int key) {

		int index = -1;
		SearchResultType srt = SearchResultType.NotFound;

		for (int i = 0; i < items.length; i++) {
			if (items[i] == key) {
				index = i;
				srt = SearchResultType.FoundExact;
				break;
			}
		}
		return new SearchResult(index, srt);
	}

	/*
	 * GreaterThan
	 * -----------
	 *  Finds the smallest item which is greater than the
	 *  key. Returns FoundGreater if a match if found, NotFound
	 *  if no match is found.
	 *  
	 * @param items An array of sorted ints, with no duplicates
	 * @param ascending true if the array is sorted in ascending order
	 * @param key the key to search for
	 * 
	 */
	private static SearchResult searchGreaterThan(int[] items,
			boolean ascending, int key) {
		int index = -1;
		SearchResultType srt = SearchResultType.NotFound;

		if (ascending) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] > key) {
					index = i;
					srt = SearchResultType.FoundGreater;
					break;
				}
			}
		} else {
			for (int i = 0; i < items.length; i++) {
				if (items[i] > key) {
					index = i;
					srt = SearchResultType.FoundGreater;
				} else {
					break;
				}
			}
		}
		return new SearchResult(index, srt);
	}

	/*
	 * GreaterThanEquals
	 * -----------------
	 *  Finds the item which is equal to the key, or the
	 *  smallest item which is greater than the key. Returns
	 *  FoundExact if an item that exactly matches the key
	 *  is found, FoundGreater if a non-exact match is found
	 *  and NotFound if no match is found. 
	 * 
	 * @param items An array of sorted ints, with no duplicates
	 * @param ascending true if the array is sorted in ascending order
	 * @param key the key to search for
	 */
	private static SearchResult searchGreaterThanEquals(int[] items,
			boolean ascending, int key) {
		SearchResult sr = searchEquals(items, ascending, key);
		if (sr.searchResultType == SearchResultType.FoundExact) {
			return sr;
		} else {
			return searchGreaterThan(items, ascending, key);
		}
	}

	/*
	 * Finds the largest item which is less than the key. 
	 * It returns FoundLess if a match is found, 
	 * NotFound if no match is found.
	 * 
	 * @param items An array of sorted ints, with no duplicates
	 * @param ascending true if the array is sorted in ascending order
	 * @param key the key to search for
	 */
	private static SearchResult searchLessThan(int[] items, boolean ascending,
			int key) {
		int index = -1;
		SearchResultType srt = SearchResultType.NotFound;
		if (ascending) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] < key) {
					index = i;
					srt = SearchResultType.FoundLess;
				} else {
					break;
				}
			}
		} else {
			for (int i = 0; i < items.length; i++) {
				if (items[i] < key) {
					index = i;
					srt = SearchResultType.FoundLess;
					break;
				}
			}
		}
		return new SearchResult(index, srt);
	}

	/*
	 *  LessThanEquals
	 * --------------
	 *  Finds the item which is equal to the key, or the
	 *  largest item which is less than the key. Returns
	 *  FoundExact if an item that exactly matches the key
	 *  is found, FoundLess if a non-exact match is found
     *  and NotFound if no match is found.
     *  
	 * @param items An array of sorted ints, with no duplicates
	 * @param ascending true if the array is sorted in ascending order
	 * @param key the key to search for
	 */
	private static SearchResult searchLessThanEquals(int[] items,
			boolean ascending, int key) {
		// TODO Auto-generated method stub
		SearchResult rs = searchEquals(items, ascending, key);
		if (rs.searchResultType == SearchResultType.FoundExact) {
			return rs;
		} else {
			return searchLessThan(items, ascending, key);
		}
		
	}

}
