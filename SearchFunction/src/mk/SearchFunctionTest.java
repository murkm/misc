/**
 * 
 */
package mk;

import static org.junit.Assert.*;

import mk.ArraySearch.SearchResult;
import static mk.ArraySearch.SearchType;
import static mk.ArraySearch.SearchResultType;
import static mk.ArraySearch.search;


import org.junit.Test;

/**
 * @author Murat Kumykov
 * 
 * Exhaustive test for ArraySearch.search function
 * 
 * for any given sorted array of integers with no duplicates there
 * are four possible relations to it an arbitrary integer can have
 * 1. Present in an array
 * 2. Absent in an array but within range of values
 * 3. Absent and less than any value in the array
 * 4. Absent and is greater that any value in the array.
 * 
 * Edge cases will be tested as well
 * 
 * 5. Smallest Value in the array.
 * 6. Largest value in the array.
 * 
 * Test data set will be comprised of two arrays with ascending and descending
 * order
 * 
 * And one set of search keys per respective array.
 * 
 * Therefore search function will be tested with both arrays against six search 
 * keys totaling 60 test cases.
 *
 */
public class SearchFunctionTest {
	
	/*
	 * Data set for exhaustive search
	 */
	int[] ascendingArray  = { -5, 0, 3, 6, 17, 22 };
	int[] descendingArray = { 22, 17, 6, 3, 0, -5 };
	
	int exactKey 		= 17;
	int exactKeyIdx 	= 4;

	int smallestKey 	= -5;
	int largestKey 		= 22;
	
	
	int inRangeKey 		= 10;
	int belowRangeKey 	= -7;
	
	int aboveRangeKey 	= 25;
	
	/*
	 *  Testing LessThan search mode
	 */
	
	@Test
	public void testLessThanWithExactKey() {
		SearchResult actual = search(ascendingArray, true, exactKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(3, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanWithSmallestKey() {
		SearchResult actual = search(ascendingArray, true, smallestKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanWithLargestKey() {
		SearchResult actual = search(ascendingArray, true, largestKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanWithKeyInRange() {
		SearchResult actual = search(ascendingArray, true, inRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(3, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanWithKeyBelowRange() {
		SearchResult actual = search(ascendingArray, true, belowRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanWithKeyAboveRange() {
		SearchResult actual = search(ascendingArray, true, aboveRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithExactKey() {
		SearchResult actual = search(descendingArray, false, exactKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(2, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithSmallestKey() {
		SearchResult actual = search(descendingArray, false, smallestKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithLargestKey() {
		SearchResult actual = search(descendingArray, false, largestKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithKeyInRange() {
		SearchResult actual = search(descendingArray, false, inRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(2, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithKeyBelowRange() {
		SearchResult actual = search(descendingArray, false, belowRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanOnDescendingArrayWithKeyAboveRange() {
		SearchResult actual = search(descendingArray, false, aboveRangeKey, SearchType.LessThan);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	/*
	 * Testing LessThanEquals search mode
	 */
	
	@Test
	public void testLessThanEqualsWithExactKey() {
		SearchResult actual = search(ascendingArray, true, exactKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsWithSmallestKey() {
		SearchResult actual = search(ascendingArray, true, smallestKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsWithLargestKey() {
		SearchResult actual = search(ascendingArray, true, largestKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsWithKeyInRange() {
		SearchResult actual = search(ascendingArray, true, inRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(3, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsWithKeyBelowRange() {
		SearchResult actual = search(ascendingArray, true, belowRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsWithKeyAboveRange() {
		SearchResult actual = search(ascendingArray, true, aboveRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithExactKey() {
		SearchResult actual = search(descendingArray, false, exactKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithSmallestKey() {
		SearchResult actual = search(descendingArray, false, smallestKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithLargestKey() {
		SearchResult actual = search(descendingArray, false, largestKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithKeyInRange() {
		SearchResult actual = search(descendingArray, false, inRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(2, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithKeyBelowRange() {
		SearchResult actual = search(descendingArray, false, belowRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testLessThanEqualsOnDescendingArrayWithKeyAboveRange() {
		SearchResult actual = search(descendingArray, false, aboveRangeKey, SearchType.LessThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundLess);
		assertEquals(expected, actual);
	}


	/*
	 * Testing Equals search mode
	 */
	
	@Test
	public void testEqualsWithExactKey() {
		SearchResult actual = search(ascendingArray, true, exactKey, SearchType.Equals);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithSmallestKey() {
		SearchResult actual = search(ascendingArray, true, smallestKey, SearchType.Equals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithLargestKey() {
		SearchResult actual = search(ascendingArray, true, largestKey, SearchType.Equals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithKeyInRange() {
		SearchResult actual = search(ascendingArray, true, inRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithKeyBelowRange() {
		SearchResult actual = search(ascendingArray, true, belowRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithKeyAboveRange() {
		SearchResult actual = search(ascendingArray, true, aboveRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithExactKey() {
		SearchResult actual = search(descendingArray, false, exactKey, SearchType.Equals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithSmallestKey() {
		SearchResult actual = search(descendingArray, false, smallestKey, SearchType.Equals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithLargestKey() {
		SearchResult actual = search(descendingArray, false, largestKey, SearchType.Equals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithKeyInRange() {
		SearchResult actual = search(descendingArray, false, inRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithKeyBelowRange() {
		SearchResult actual = search(descendingArray, false, belowRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsOnDescendingArrayWithKeyAboveRange() {
		SearchResult actual = search(descendingArray, false, aboveRangeKey, SearchType.Equals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	/* 
	 * Testing GreaterThanEquals search mode
	 * 
	 */
	@Test
	public void testGreaterThanEqualsWithExactKey() {
		SearchResult actual = search(ascendingArray, true, exactKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsWithSmallestKey() {
		SearchResult actual = search(ascendingArray, true, smallestKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsWithLargestKey() {
		SearchResult actual = search(ascendingArray, true, largestKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsWithKeyInRange() {
		SearchResult actual = search(ascendingArray, true, inRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsWithKeyBelowRange() {
		SearchResult actual = search(ascendingArray, true, belowRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsWithKeyAboveRange() {
		SearchResult actual = search(ascendingArray, true, aboveRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithExactKey() {
		SearchResult actual = search(descendingArray, false, exactKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithSmallestKey() {
		SearchResult actual = search(descendingArray, false, smallestKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithLargestKey() {
		SearchResult actual = search(descendingArray, false, largestKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundExact);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithKeyInRange() {
		SearchResult actual = search(descendingArray, false, inRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithKeyBelowRange() {
		SearchResult actual = search(descendingArray, false, belowRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanEqualsOnDescendingArrayWithKeyAboveRange() {
		SearchResult actual = search(descendingArray, false, aboveRangeKey, SearchType.GreaterThanEquals);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	
	/*
	 * Testing greaterThan search mode
	 */
	
	@Test
	public void testGreaterThanWithExactKey() {
		SearchResult actual = search(ascendingArray, true, exactKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanWithSmallestKey() {
		SearchResult actual = search(ascendingArray, true, smallestKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanWithLargestKey() {
		SearchResult actual = search(ascendingArray, true, largestKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanWithKeyInRange() {
		SearchResult actual = search(ascendingArray, true, inRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanKeyBelowRange() {
		SearchResult actual = search(ascendingArray, true, belowRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanKeyAboveRange() {
		SearchResult actual = search(ascendingArray, true, aboveRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayWithExactKey() {
		SearchResult actual = search(descendingArray, false, exactKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(0, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayWithSmallestKey() {
		SearchResult actual = search(descendingArray, false, smallestKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(4, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayWithLargestKey() {
		SearchResult actual = search(descendingArray, false, largestKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayWithKeyInRange() {
		SearchResult actual = search(descendingArray, false, inRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(1, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayKeyBelowRange() {
		SearchResult actual = search(descendingArray, false, belowRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(5, SearchResultType.FoundGreater);
		assertEquals(expected, actual);
	}

	@Test
	public void testGreaterThanOnDescendingArrayKeyAboveRange() {
		SearchResult actual = search(descendingArray, false, aboveRangeKey, SearchType.GreaterThan);
		SearchResult expected = new SearchResult(-1, SearchResultType.NotFound);
		assertEquals(expected, actual);
	}

}
