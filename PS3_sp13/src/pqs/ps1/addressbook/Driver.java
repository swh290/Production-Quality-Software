package pqs.ps1.addressbook;

/**
 * Driver Class that is used for testing the program
 * @author peihong
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AddressBook book = AddressBook.newInstance();
		AddressEntry entry1 = new AddressEntry.Builder("Peihong Chai").
				address("NYU").email("pc1336@nyu.edu").phone("917-688-9725").
				note("Peihong's taking PQS").build();
		AddressEntry entry2 = new AddressEntry.Builder("Wen").
				address("NYU").email("wen@nyu.edu").phone("917-688-9756").
				note("Wen's auditing PQS").build();
		AddressEntry entry3 = new AddressEntry.Builder(null).build();
				
		book.addEntry(entry1);
		book.addEntry(entry2);
		book.addEntry(entry3);
		
		System.out.println("Current Address Book: ");
		System.out.println(book.toString());	
		System.out.println("====================================");
		System.out.println("Search 917-688-9725 in Address Book");
		System.out.println(book.search("917-688-9725"));
		System.out.println("====================================");
		System.out.println("Saving Address Book as myAdressBook...");
		book.save("myAddressBook");
		System.out.println("Done");
		System.out.println("====================================");
		System.out.println("Read saved address book with name myAddressBook");
		System.out.println(book.read("myAddressBook").toString());

	
	}

}
