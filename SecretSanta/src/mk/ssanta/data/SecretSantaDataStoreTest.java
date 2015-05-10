/**
 * 
 */
package mk.ssanta.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dood
 *
 */
public class SecretSantaDataStoreTest {
	
	SecretSantaDataStore ssds;
	String[] family;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ssds = new SecretSantaDataStore();
		family = new String[]{"Peter", "John", "James", "Thomas", "Mary", "Jacob", "Jeremy", "Judas"};
		for (int i=0; i < family.length; i++) {
			ssds.addFamilyMember(new FamilyMember(family[i]));
		}
		ssds.shuffle();
	}

	/**
	 * Test method for {@link mk.ssanta.data.SecretSantaDataStore#addFamilyMember(mk.ssanta.data.FamilyMember)}.
	 */
	@Test
	public void testAddFamilyMember() {
		assertTrue(ssds.addFamilyMember(new FamilyMember("Jesus")));
		assertFalse(ssds.addFamilyMember(new FamilyMember("Judas")));
	}

	/**
	 * Test method for {@link mk.ssanta.data.SecretSantaDataStore#removeFamilyMember(java.lang.String)}.
	 */
	@Test
	public void testRemoveFamilyMember() {
		assertFalse(ssds.removeFamilyMember("Jesus"));
		assertTrue(ssds.removeFamilyMember("Judas"));
		assertFalse(ssds.removeFamilyMember("Judas"));
	}

	/*
	 * Test that all the members of the family entered the shuffle
	 */
	
	@Test
	public void testAttendance() {
		for (String name: family) {
			assertTrue(ssds.present(name));
		}
	}
	
	/**
	 * Test method for SecretSanta shuffle. Passing criteria are 
	 *    1. Everybody gets to be a SecretSanta
	 *    2. Nobody gets more than one SecretSanta
	 *    3. One can not be his own SecretSanta
	 */
	@Test
	public void testDraw() {
		
		ArrayList<String> recipients = new ArrayList<String>();
		
		for (int i=0; i < ssds.size(); i++) {
			FamilyMember fm = ssds.get(i); 
			assertNotNull(fm.getRecipient());
			assertFalse(fm.getName().equals(fm.getRecipient()));
			recipients.add(fm.getRecipient());
		}
		
		HashSet<String> uniqueRecipients = new HashSet<String>(recipients);
		
		assertEquals(recipients.size(), uniqueRecipients.size());
	}
}
