import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		
		l.add("One");
		l.add("Two");
		l.add("Three");
		l.add("Four");
		l.add("Five");
		
		System.out.println(l);
		
		List<List<String>> p = permute(l);
		
		System.out.println(p);
		System.out.println(p.size());
		
		List<Number> il = new ArrayList<Number>();
		il.add(1);
		il.add(2);
		il.add(3);
		il.add(4);
		il.add(5);
		
		List<List<Number>> np = permute(il);

		System.out.println(np);
		System.out.println(np.size());
		
		List<Object> ol = new ArrayList<Object>();
		ol.add("One");
		ol.add(2);
		ol.add(true);
		
		List<List<Object>> op = permute(ol);
		
		System.out.println(op);
		System.out.println(op.size());

	}

	private static <T> List<List<T>> permute(List<T> l) {
		
		List<List<T>> rl = new ArrayList<List<T>>();

		if (l.size() == 1) {
			rl.add(l);
			return rl;
		}
		
		Iterator<T> i = l.iterator();
		
		while (i.hasNext()) {
			T s = i.next();
			ArrayList<T> sa = new ArrayList<T>(l);
			sa.remove(s);
			List<List<T>> spl = permute(sa);
			Iterator<List<T>> li = spl.iterator();
			
			while (li.hasNext()) {
				List<T> sl = li.next();
				sl.add(s);
				rl.add(sl);
			}
		}
		return rl;
	}

}
