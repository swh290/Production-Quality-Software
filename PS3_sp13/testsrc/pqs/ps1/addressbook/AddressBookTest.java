package pqs.ps1.addressbook;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {
	AddressBook addressBook;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		addressBook = AddressBook.newInstance();
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUp() {
		System.setErr(null);
	}

	@Test
	public void testAddEntry() { // addEntry works
		AddressEntry entry = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		addressBook.addEntry(entry);
		assertTrue(addressBook.addEntry(entry));
	}

	@Test
	public void testAddNullEntry() { // Add a null entry fails
		assertFalse(addressBook.addEntry(null));
	}

	@Test
	public void testRemoveNullEntry() { // Remove a null entry fails
		assertFalse(addressBook.removeEntry(null));
	}

	@Test
	public void testRemoveEntry() { // Remove entry works
		AddressEntry entry = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		addressBook.addEntry(entry);
		assertTrue(addressBook.removeEntry(entry));
	}

	@Test
	public void testSearchEntryFail() { // Search an attribute that not exist
																			// returns an empty list
		List<AddressEntry> expected = new ArrayList<AddressEntry>();
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry2);
		assertEquals(expected, addressBook.search("QQ"));
	}

	@Test
	public void testSearchEntryFoundOne() { // Search works
		List<AddressEntry> expected = new ArrayList<AddressEntry>();
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry2);
		expected.add(entry1);
		assertEquals(expected, addressBook.search("abc 123"));
	}

	@Test
	public void testSearchEntryFoundTwo() { // Search same query in to different
																					// entry works
		List<AddressEntry> expected = new ArrayList<AddressEntry>();
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.email("a@b.c").build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.note("hot").build();
		AddressEntry entry3 = new AddressEntry.Builder("Charlie")
				.address("abc 123").phone("800000000").build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry2);
		addressBook.addEntry(entry3);
		expected.add(entry1);
		expected.add(entry3);
		assertEquals(expected, addressBook.search("abc 123"));
	}

	@Test
	public void testSearchEntryFoundTwoDifferentAttribute() {
		// Search same query in to different attribute works
		List<AddressEntry> expected = new ArrayList<AddressEntry>();
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.build();
		AddressEntry entry3 = new AddressEntry.Builder("Charlie")
				.address("qwertyu").phone("800000000").build();
		AddressEntry entry4 = new AddressEntry.Builder("Duke").address("qwertyu")
				.note("800000000").build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry4);
		addressBook.addEntry(entry2);
		addressBook.addEntry(entry3);
		expected.add(entry4);
		expected.add(entry3);
		assertEquals(expected, addressBook.search("800000000"));
	}

	@Test
	public void testSaveAndRead() {// should have passed but fail because lack of
																	// override equals method
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.build();
		AddressEntry entry3 = new AddressEntry.Builder("Charlie")
				.address("qwertyu").phone("800000000").build();
		AddressEntry entry4 = new AddressEntry.Builder("Duke").address("qwertyu")
				.note("800000000").build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry4);
		addressBook.addEntry(entry2);
		addressBook.addEntry(entry3);
		addressBook.save("addressBook1");

		assertEquals(addressBook, AddressBook.read("addressBook1"));
	}

	@Test
	public void testReadThenSearch() {// should have passed but fail because lack
																		// of override equals method
		AddressEntry entry1 = new AddressEntry.Builder("Bob").address("abc 123")
				.build();
		AddressEntry entry2 = new AddressEntry.Builder("Alice").address("bcd 456")
				.build();
		AddressEntry entry3 = new AddressEntry.Builder("Charlie")
				.address("qwertyu").phone("800000000").build();
		AddressEntry entry4 = new AddressEntry.Builder("Duke").address("qwertyu")
				.note("800000000").build();
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry4);
		addressBook.addEntry(entry2);
		addressBook.addEntry(entry3);
		addressBook.save("addressBook2");
		addressBook = AddressBook.read("addressBook2");
		List<AddressEntry> expected = new ArrayList<AddressEntry>();
		AddressEntry entry = new AddressEntry.Builder("Charlie").address("qwertyu")
				.phone("800000000").build();
		expected.add(entry);
		assertEquals(expected, addressBook.search("Charlie"));
	}

	@Test
	public void testSaveGetExctption() {
		addressBook.save("");
		assertEquals("CaughtIOException: (No such file or directory)\n",
				errContent.toString());
	}

	@Test
	public void testReadGetFileNotFoundExctption() {
		AddressBook.read("");
		assertEquals("CaughtFileNotFoundException:  (No such file or directory)\n",
				errContent.toString());
	}

	@Test
	public void testReadGetParseExctption() {
		AddressBook.read("trunk/PS3_sp13/testsrc/parseExceptionFile");
		assertEquals("CaughtParseException: null\n", errContent.toString());
	}

}
