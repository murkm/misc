/*
 * In memory data store for simple SecretSanta implementation
 * 
 *  Usage:
 *  
 *  SecretSantaDataStore ssds = new SecretSantaDataStore();
 *  
 *   if (ssds.addFamilyMember(Name)) {
 *      success ...
 *   } else {
 *      name is already there
 *   }
 *  
 *   ssds.draw()
 *   
 *   ssds.getRecipient(SecretSantaName)
 *   
 *   Part One 
 *   
 *   Part One implements basic SecretSanta shuffle where 
 *   everybody gets to be a secret santa
 *   nobody gets more than one Secret Santa and
 *   nobody is his/her own Secret Santa
 *  
 */

package mk.ssanta.data;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/*
 * SecretSantaDataStore main class that implementsin memory datat store for 
 * the Secret Santa
 * 
 */
public class SecretSantaDataStore {

	Random rand = new Random(); 
	private ArrayList<FamilyMember> extendedFamily = new ArrayList<FamilyMember>();
	private boolean open = true;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean v) {
		open = v;
	}

	/*
	 * Add family member into the shuffle
	 */
	public boolean addFamilyMember(String name) {
		return addFamilyMember(new FamilyMember(name));
	}

	public boolean addFamilyMember(FamilyMember fm) {
		if (present(fm)) {
			return false;
		} else {
			extendedFamily.add(fm);
			return true;
		}

	}

	/*
	 * Check if one is already participating
	 */
	public boolean present(String name) {
		if ((name == null) || name.trim().equals("")) {
			return false;
		}
		return present(new FamilyMember(name));
	}

	private boolean present(FamilyMember fm) {
		if (extendedFamily.contains(fm))
			return true;
		else
			return false;
	}

	public boolean removeFamilyMember(String name) {
		FamilyMember fm = new FamilyMember(name);
		return extendedFamily.remove(fm);
	}

	/*
	 * Generates random SecretSanta assignments making sure that no one gets
	 * assigned two SecretSantas no one is his/her own SecretSanta everybody is
	 * a SecretSanta
	 * 
	 * Implements modified Fisher-Yates shuffle with exclusion criterion
	 */
	public void draw() {

		open = false;

		int[] arr = new int[extendedFamily.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i;

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == i) { // have to shuffle
				int j = randInt(i + 1, arr.length - 1);
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		if (arr[arr.length - 1] == arr.length - 1) {
			int j = randInt(0, arr.length - 2);
			int temp = arr[j];
			arr[j] = arr[arr.length - 1];
			arr[arr.length - 1] = temp;
		}

		for (int i = 0; i < extendedFamily.size(); i++) {
			extendedFamily.get(i).setRecipient(
					extendedFamily.get(arr[i]).getName());
		}
	}

	public String getRecipient(String name) {
		FamilyMember fm = new FamilyMember(name);
		if (extendedFamily.contains(fm)) {
			int index = extendedFamily.indexOf(fm);
			return extendedFamily.get(index).getRecipient();
		}
		return null;
	}

	public void dumpResults(OutputStream out) {
		PrintWriter pw = new PrintWriter(out);
		for (FamilyMember fm : extendedFamily) {
			pw.println(fm);
			pw.flush();
		}
	}

	public int size() {
		return extendedFamily.size();
	}

	public FamilyMember get(int index) {
		return extendedFamily.get(index);
	}

	public int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public String[] getSantaList() {
		String[] santas = new String[size()];
		for (int i = 0; i < extendedFamily.size(); i++) {
			santas[i] = extendedFamily.get(i).getName();
		}
		return santas;
	}
}
