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
 */

package mk.ssanta.data;

import java.util.ArrayList;
import java.util.Random;

/*
 *  Encapsulates SecretSanta - recipient relationship
 */
class FamilyMember {

	private String name;
	private String recipient;

	public FamilyMember(String name) {
		this.name = name;
		this.recipient = null;
	}

	public String getName() {
		return name;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String name) {
		this.recipient = name;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		FamilyMember fm = (FamilyMember) obj;

		return this.name.equals(fm.getName());

	}
	
	public int hashCode() {
		return this.name.hashCode();
	}

}

public class SecretSantaDataStore {
	
	Random rand =  new Random();
	private ArrayList<FamilyMember> extendedFamily = new ArrayList<FamilyMember>();


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
	 * Generates random SecretSanta assignments making sure that 
	 *   no one gets assigned two SecretSantas 
	 *   no one is his/her own SecretSanta
	 *   everybody is a SecretSanta
	 * 
	 * Implements modified Fisher-Yates shuffle with exclusion criterion
	 * 
	 */
	public void draw() {
		int[] arr = new int[extendedFamily.size()];
		for (int i=0; i < arr.length; i++) arr[i] = i;

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == i) { //have to shuffle
				int j = randInt(i + 1, arr.length - 1);
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		if (arr[arr.length - 1] == arr.length - 1) {
			int j = randInt(0, arr.length - 2);
			int temp = arr[j];
			arr[j] = arr[arr.length -1];
			arr[arr.length - 1] = temp;
		}
		
		for (int i = 0; i < extendedFamily.size(); i++) {
			extendedFamily.get(i).setRecipient(extendedFamily.get(arr[i]).getName());
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
	
	public int size() {
		return extendedFamily.size();
	}
	
	public FamilyMember get(int index) {
		return extendedFamily.get(index);
	}
	
	public int randInt(int min, int max) {
	    return rand.nextInt((max - min) + 1) + min;
	}
}
