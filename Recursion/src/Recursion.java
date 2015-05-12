import java.util.LinkedList;
import java.util.Arrays;


public class Recursion {

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1,2,3,5,60,44));
		
		System.out.println(l);
		System.out.println(sumList(l));
		
		int i = 127;
		
		System.out.println(intToBaseString(i,2));
		System.out.println(intToBaseString(i,8));
		System.out.println(intToBaseString(i,10));
		System.out.println(intToBaseString(i,16));
		
		LinkedList<Integer> ia = new LinkedList(Arrays.asList(1,2,3,4,5));
		System.out.println(ia);
		System.out.println(reverseList(ia));
		
	}

	/*
	 * Recursive Summ of the list
	 */
	private static int sumList(LinkedList<Integer> l) {
		int head = l.remove();
		return (l.isEmpty() ? head : head + sumList(l));
	}
	
	/*
	 * Recursive convert integer to a string in any base 
	 */
	
	public static String intToBaseString(int i, int base) {
		
		String lt = "0123456789ABCDEF";
		
		if (i < base) return lt.substring(i, i+1);
		
		return intToBaseString(i / base, base) + lt.substring(i%base, i%base+1);
		
	}
	
	public static <T> LinkedList<T> reverseList(LinkedList<T> l) {

		if (l.isEmpty()) return l;
		T head = l.remove();
		LinkedList<T> temp = reverseList(l);
		temp.add(head);
		return temp; 
		
	}
}
