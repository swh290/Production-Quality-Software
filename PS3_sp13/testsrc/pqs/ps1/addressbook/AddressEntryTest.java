package pqs.ps1.addressbook;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;

public class AddressEntryTest {

	@Test
	public void testEquals() {// should have passed but fail because lack of
		// override equals method
		AddressEntry entry1 = new AddressEntry.Builder("Charlie").email("QQ@aa.aa")
				.note("nothing").address("abc 123").phone("800000000").build();
		AddressEntry entry2 = new AddressEntry.Builder("Charlie").email("QQ@aa.aa")
				.note("nothing").address("abc 123").phone("800000000").build();
		assertEquals(entry1, entry2);
	}
	
	@Test
	public void testSerializeDeseriliaze() {// should have passed but fail because lack of
		// override equals method
		AddressEntry entry1 = new AddressEntry.Builder("Charlie").email("QQ@aa.aa")
				.note("nothing").address("abc 123").phone("800000000").build();
		JSONObject j = entry1.Seriliaze();
		assertEquals(entry1, AddressEntry.Deseriliaze(j));
	}
	
	@Test
	public void testSerialize() {// Serialize works
		AddressEntry entry1 = new AddressEntry.Builder("Charlie").email("QQ@aa.aa")
				.note("nothing").address("abc 123").phone("800000000").build();
		AddressEntry entry2 = new AddressEntry.Builder("Charlie").email("QQ@aa.aa")
				.note("nothing").address("abc 123").phone("800000000").build();
		JSONObject j1 = entry1.Seriliaze();
		JSONObject j2 = entry2.Seriliaze();
		assertEquals(j1, j2);
	}
	
	@Test
	public void testContains() {// Contains works
		AddressEntry entry1 = new AddressEntry.Builder("Charlie")
				.note("nothing").address("abc 123").phone("").build();
		assertTrue(entry1.contains("nothing"));
		assertFalse(entry1.contains(""));
		assertFalse(entry1.contains(null));
	}

}
