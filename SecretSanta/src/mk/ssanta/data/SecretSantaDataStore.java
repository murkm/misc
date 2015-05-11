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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * SecretSantaDataStore main class that implementing memory data 
 * store for the Secret Santa 
 * 
 * Historical data is saved in files so they could be used in 
 * subsequent SecretSanta gigs to make sure no one gets the same person 
 * assigned more than once every three years
 * 
 */
public class SecretSantaDataStore {

	private Random rand; 
	private ArrayList<FamilyMember> extendedFamily;
	private boolean open;
	private String basedir;

	public SecretSantaDataStore() {
		rand = new Random();
		extendedFamily = new ArrayList<FamilyMember>();
		open = true;
		basedir = ".";
		
		findAndLoadPastData();
	}
	
	private void findAndLoadPastData() {
		File cwd = new File(basedir);
		
		String[] files = cwd.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt") && name.startsWith("ssdump_");
			}});
		
		Arrays.sort(files);

		if (files.length > 0) {
			String mostRecentFile = files[files.length-1];
			//System.out.println(mostRecentFile);
			Scanner sc;
			try {
				sc = new Scanner(new File(mostRecentFile));
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] name = line.split(": ");
					if (name.length == 2) {
						FamilyMember fm = new FamilyMember(name[0]);
						fm.addConstraint(name[0]);
						fm.addConstraint(name[1]);
						addFamilyMember(fm);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (files.length > 1) {
			String mostRecentFile = files[files.length-2];
			//System.out.println(mostRecentFile);
			Scanner sc;
			try {
				sc = new Scanner(new File(mostRecentFile));
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] name = line.split(": ");
					if ((name.length == 2) && 
							(name[1] != null) && 
							(! name[1].trim().equals("null"))) {
						int index = extendedFamily.indexOf(new FamilyMember(name[0]));
						if (index >=0) {
							extendedFamily.get(index).addConstraint(name[1]);
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// dumpData();
	}

	private void dumpData() {
		for (FamilyMember fm : extendedFamily) {
			System.out.println(fm);
			System.out.println(fm.getConstraints());
		}
	}

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
	 * Generates random SecretSanta assignments making sure that 
	 * no one gets assigned two SecretSantas 
	 * no one is his/her own SecretSanta 
	 * everybody is a SecretSanta
	 * 
	 * This shuffle takes into account costraints that added to each Family Member
	 */
	
	public void shuffle() {
		int count = 1;
		while (! shufflePass()) {
			count = count + 1; // not using count++ to avoid eclipse warning 
		}
	}
	
	public boolean shufflePass() {

		open = false;
		
		List<String> assigneePool = getNameList();
		
		for (FamilyMember fm: extendedFamily) {
			List<String> candidates = getAssigneesSansConstraints(assigneePool, fm);
			if (!candidates.isEmpty()) {
				int randomIndex = randInt(0,candidates.size()-1);
				fm.setRecipient(candidates.get(randomIndex));
				assigneePool.remove(candidates.get(randomIndex));
			} else {
				// This signifies that proper solution was not found 
				return false;
			}
		}
		
		return true;
	}

	private List<String> getAssigneesSansConstraints(List<String> assigneePool,
			FamilyMember fm) {
		
		List<String> tmp = new ArrayList<String>(assigneePool);
		
		List<String> constraints = fm.getConstraints();
		
		for (String name: constraints) {
			tmp.remove(name);
		}
		
		return tmp;
	}

	private List<String> getNameList() {
		List<String> nameList = new ArrayList<String>();
		for (FamilyMember fm: extendedFamily) {
			nameList.add(fm.getName());
		}
		return nameList;
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

	public void dumpResults() {
		if (! isOpen()) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd.HHmmss");
			String dumpFileName = "ssdump_" + df.format(new Date()) + ".txt";
			try {
				FileOutputStream fos = new FileOutputStream(dumpFileName);
				dumpResults(fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
