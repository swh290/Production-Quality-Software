package addressBook;

import java.util.List;
import java.util.ArrayList;

/**
 * Test.java
 * Used for testing the AddressBook Library
 *  
 * @author Ashwath
 *
 */
public class Test 
{

  /**
   * @param args
   */
  public static void main(String[] args) 
  {
    
    List<AddressBook> addressBookList = new ArrayList<AddressBook>(); 
    List<Contact> contactList = new ArrayList<Contact>();
    AddressBook adr = new AddressBook("UID1","adressBook1");
    boolean flag;
  
    
    adr.createAddressBook("UID1","adressBook1");
    
    addressBookList.add(adr);
    
    Name name1 = new Name("ABC","PQR","XYZ");
    PostalAddress postalAddress = new PostalAddress("20", "NYC","NY","USA", "10012");
    PhoneNumber phoneNumber = new PhoneNumber(917,917,9171);
    EmailAddress emailAddress = new EmailAddress("abc","nyu.edu");
    String note = "Hello";
    
    adr.addEntry(name1,postalAddress,phoneNumber,emailAddress,note);
    
    //Testing with optional middle name parameter
    Name name2  = new Name("QWE",null,"ASD");
    
    adr.addEntry(name2,postalAddress,phoneNumber,emailAddress,note);
    
    Name name3  = new Name("SID",null,"TOM");
    
    //Testing with only name and all parameters null
    adr.addEntry(name3,null,null,null,null);
    
    adr.saveAddressBookToFile("UID1","adressBook1");
    
    contactList = adr.searchEntry("PQR");
    
    if(contactList.size() == 0)
    {
      System.out.println("Entry not found.");
    }
    else
    {
      System.out.println("Please find below the search results:");
      for(int index=0;index<contactList.size();index++)
      {
        System.out.println(contactList.get(index).toString());
      }
    }
    
    contactList =adr.searchEntry("DC");
    
    if(contactList.size() == 0)
    {
      System.out.println("Entry not found for search.");
    }
    else
    {
      System.out.println("Please find below the search results:");
      for(int index=0;index<contactList.size();index++)
      {
        System.out.println(contactList.get(index).toString());
      }
    }
    
    contactList =adr.searchEntry("3321424");
    
    if(contactList.size() == 0)
    {
      System.out.println("Entry not found for search.");
    }
    else
    {
      System.out.println("Please find below the search results:");
      for(int index=0;index<contactList.size();index++)
      {
        System.out.println(contactList.get(index).toString());
      }
    }
    
    contactList =adr.searchEntry("pqr@nyu.edu");
    
    if(contactList.size() == 0)
    {
      System.out.println("Entry not found for search.");
    }
    else
    {
      System.out.println("Please find below the search results:");
      for(int index=0;index<contactList.size();index++)
      {
        System.out.println(contactList.get(index).toString());
      }
    }
    
    contactList =adr.searchEntry("Hey");
    
    if(contactList.size() == 0)
    {
      System.out.println("Entry not found for search.");
    }
    else
    {
      System.out.println("Please find below the search results:");
      for(int index=0;index<contactList.size();index++)
      {
        System.out.println(contactList.get(index).toString());
      }
    }
    
    flag =adr.removeEntry("contactID2");
    
    if(flag == true)
    {
      System.out.println("Entry delted successfully.");
    }
    else
    {
      System.out.println("Entry not found for deletion.");
    }
    
    adr.saveAddressBookToFile("UID1","adressBook1");
    
    adr.readAddressBookFromFile("UID1","adressBook1");
    
    
  }

}
