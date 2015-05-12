package mk;

import static mk.ArraySearch.search;
import static org.junit.Assert.*;
import mk.ArraySearch.SearchResult;
import mk.ArraySearch.SearchResultType;
import mk.ArraySearch.SearchType;

import org.junit.Test;

/*
 * @author Murat Kumykov
 * 
 * This calss implements test cases based on the Example data included in the task
 * 
 * 
 */
public class TestCasesIncludedWitTheTask {
	
	/*
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
	*/
	
	int[] array = { 0, 2, 4, 6, 8};
	
	@Test
	public void test1() {
		SearchResult actual = search(array, true, -1, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		SearchResult actual = search(array, true, 0, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		SearchResult actual = search(array, true, 0, SearchType.Equals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		SearchResult actual = search(array, true, 1, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		SearchResult actual = search(array, true, 2, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test6() {
		SearchResult actual = search(array, true, 2, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(2, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}
	
	
	
	/*  Given the input array [8, 6, 4, 2, 0] (descending order)
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
	*/

	int[] darray = { 8, 6, 4, 2, 0 };
	
	
	@Test
	public void testDescending1() {
		SearchResult actual = search(darray, false, -1, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testDescending2() {
		SearchResult actual = search(darray, false, 4, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(2, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testDescending3() {
		SearchResult actual = search(darray, false, 8, SearchType.Equals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testDescending4() {
		SearchResult actual = search(darray, false, 5, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testDescending5() {
		SearchResult actual = search(darray, false, 2, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(3, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testDescending6() {
		SearchResult actual = search(darray, false, 9, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

}
