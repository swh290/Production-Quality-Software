package addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * AddressBook.java
 * 
 * Immutable class for all the addressBooks.
 * 
 * @author Ashwath
 *
 */
public class AddressBook implements Comparable<AddressBook>
{
	// Required parameters
	private final String userID;
	private final String addressBookName;
	
	private int contactCount=0;
	File file;
	
	private List<Contact> contacts = new ArrayList<Contact>();
	
	/**
	 * Constructor with all the fields
	 * @param userID and addressBookName in the string format
	 * 
	 */ 
	public AddressBook(String userID, String addressBookName)
	{
		this.userID = userID;
		this.addressBookName = addressBookName;
	}
	
	/**
	 *  Create an empty address book
	 *  @param userID and addressBookName in the string format
	 *  @throws IOException
	 */
	public  void createAddressBook(String userID,String addressBookName)
	{
		try
		{
			file=new File(userID+addressBookName +".adb");
		  
			if(!file.exists())
			{
				file.createNewFile();
			} 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
  	
	/**
	 *  Adds an entry to the address book
	 *  @param name PostalAddress PhoneNumber EmailAddress instances and note which is a string
	 */
	public void addEntry(Name name,PostalAddress postalAddress,
			PhoneNumber phoneNumber,EmailAddress emailID,String note)
	{
		Contact contact 
		= new Contact.Builder
         (name, "contactID" + ++contactCount).postalAddress(postalAddress).phoneNumber(phoneNumber).emailAddress
		(emailID).note(note).build();
		contacts.add(contact);
	}
	
	/**
	 *  Removes a contact entry from the address book
	 *  @param contactID which is in the String format
	 */
	public boolean removeEntry(String contactID)
	{
		boolean flag=false;
		for(int index=0;index<contacts.size();index++)
		{
			if(contacts.get(index).getContactID().equals(contactID))
			{
				contacts.remove(index);
				flag =true;
				return flag;
			}
		}
		return flag;
	}
	
	
	/**
	 *  Search for an entry from the address book
	 *  @param searchField in the sting format
	 *  @return contact instance or instances which match the search
	 */
	public List<Contact> searchEntry(String searchField)
	{
		List<Contact> contactList= new ArrayList<Contact>();
		
        for(int index=0;index<contacts.size();index++)
        {
            String name = contacts.get(index).getName().toString();
            
            if(name.contains(searchField))
            {
            	contactList.add(contacts.get(index));
            	break;
            }
            
            if(contacts.get(index).getPostalAddress() != null)
            {
            	String postalAddress = contacts.get(index).getPostalAddress().toString();
            	
            	if(postalAddress.contains(searchField))
                {
                	contactList.add(contacts.get(index));
                	break;
                }
            }
            
            if(contacts.get(index).getEmailAddress() != null)
            {
            	String emailID = contacts.get(index).getEmailAddress().toString();
            	
            	if(emailID.contains(searchField))
                {
                	contactList.add(contacts.get(index));
                	break;
                }
            }
            
            if(contacts.get(index).getPhoneNumber() != null)
            {
            	String phoneNumber = contacts.get(index).getPhoneNumber().toString();
            	
                if(phoneNumber.contains(searchField))
                {
                	contactList.add(contacts.get(index));
                	break;
                }
            }
            
            if(contacts.get(index).getNote() != null)
            {
            	String note = contacts.get(index).getNote();
               
                if(note.contains(searchField))
                {
                	contactList.add(contacts.get(index));
                	break;
                }
            } 		   
        }
        return contactList;
    } 
	
	
	/**
	 *  Saves the addressBook to the file
	 *  @param userID and addressBookName
	 *  @throws IOException
	 */
	public void saveAddressBookToFile(String userID,String addressBookName)
	{		
       try
       {
    	   BufferedWriter out= 
        			new BufferedWriter
        			(new FileWriter(userID+addressBookName +".adb"));
        	
    	   for(int index=0;index<contacts.size();index++)
           {	      
    		   String str = 
    			   contacts.get(index).getName().toString() + "delimiter"+ 
    			   (contacts.get(index).getPostalAddress() != null ? 
    					 contacts.get(index).getPostalAddress().toString(): " ")+"delimiter"+
    			   (contacts.get(index).getPhoneNumber() != null ? 
           				contacts.get(index).getPhoneNumber().toString() : " ")+"delimiter"+
           		   (contacts.get(index).getPhoneNumber() != null ? 
           				contacts.get(index).getEmailAddress().toString() :" ")+"delimiter"+
           		   (contacts.get(index).getNote() != null ? contacts.get(index).getNote() :" ");
           	  
                out.write(str);
           	  	out.newLine();
    
    		   
           }
            
       out.close();
       }
       catch(IOException e)
       {
    	   e.printStackTrace();
       }
	}
	
   /**
	*  Saves the addressBook to the file
	*  @param userID and addressBookName
	*  @throws IOException
	*/
   public void readAddressBookFromFile(String userID,String addressBookName)
   {
	   try
	   {
		   BufferedReader in = 
			   new BufferedReader
			   (new FileReader(userID+addressBookName +".adb"));
		
		   String strLine;
		   int contactCount=0;
		
		   while((strLine = in.readLine())!= null)
		   {
			   String temp[];
			   temp= strLine.split("delimiter");
			
			   if(temp.length == 1)
			   {
				   String tempName[] = temp[0].split(" ");
				   Name name = new Name(tempName[0],tempName[1],tempName[2]);
				   
				   Contact contact = new Contact.Builder(name,"contactID" + ++contactCount).build();
				   contacts.add(contact);
			   }
			   else if(temp.length == 2)
			   {
				   String tempName[] = temp[0].split(" ");
				   Name name = new Name(tempName[0],tempName[1],tempName[2]);
				
				   String tempAddress[] = temp[1].split(",");
				   PostalAddress postalAddress = new 
				   PostalAddress(tempAddress[0],tempAddress[1],tempAddress[2],tempAddress[3],tempAddress[4]); 
				
				   Contact contact = new Contact.Builder
				   (name,"contactID" + ++contactCount).postalAddress(postalAddress).build();
				   contacts.add(contact);
			   }
			   else if(temp.length == 3)
			   {
				   String tempName[] = temp[0].split(" ");
				   Name name = new Name(tempName[0],tempName[1],tempName[2]);
				
				   String tempAddress[] = temp[1].split(",");
				   PostalAddress postalAddress = new 
				   PostalAddress(tempAddress[0],tempAddress[1],tempAddress[2],tempAddress[3],tempAddress[4]); 
				
				   String tempPhone[] = temp[2].split("-");
				   PhoneNumber phoneNumber = new
				   PhoneNumber(Integer.parseInt(tempPhone[0]),
						   Integer.parseInt(tempPhone[1]),Integer.parseInt(tempPhone[2]));
				
				   Contact contact = new Contact.Builder
				   (name,"contactID" + ++contactCount).postalAddress(postalAddress).phoneNumber(phoneNumber).build();
				   contacts.add(contact);
			   }
			   else if(temp.length == 3)
			   {
				   String tempName[] = temp[0].split(" ");
				   Name name = new Name(tempName[0],tempName[1],tempName[2]);
				
				   String tempAddress[] = temp[1].split(",");
				   PostalAddress postalAddress = new 
				   PostalAddress(tempAddress[0],tempAddress[1],tempAddress[2],tempAddress[3],tempAddress[4]); 
				
				   String tempPhone[] = temp[2].split("-");
				   PhoneNumber phoneNumber = new
				   PhoneNumber(Integer.parseInt(tempPhone[0]),
						   Integer.parseInt(tempPhone[1]),Integer.parseInt(tempPhone[2]));
				
				   String tempEmailID[] = temp[3].split("@");
				   EmailAddress emailAddress = new
				   EmailAddress(tempEmailID[0],tempEmailID[1]);
	
				   Contact contact = new Contact.Builder
				   (name,"contactID" + ++contactCount).postalAddress(postalAddress).phoneNumber(phoneNumber)
				   .emailAddress(emailAddress).build();
				   contacts.add(contact);
			   }
			   else if(temp.length == 4)
			   {
				   String tempName[] = temp[0].split(" ");
				   Name name = new Name(tempName[0],tempName[1],tempName[2]);
				
				   String tempAddress[] = temp[1].split(",");
				   PostalAddress postalAddress = new 
				   PostalAddress(tempAddress[0],tempAddress[1],tempAddress[2],tempAddress[3],tempAddress[4]); 
				
				   String tempPhone[] = temp[2].split("-");
				   PhoneNumber phoneNumber = new
				   PhoneNumber(Integer.parseInt(tempPhone[0]),
						   Integer.parseInt(tempPhone[1]),Integer.parseInt(tempPhone[2]));
				
				   String tempEmailID[] = temp[3].split("@");
				   EmailAddress emailAddress = new
				   EmailAddress(tempEmailID[0],tempEmailID[1]);
				
				   String note =temp[4];
	
				   Contact contact = new Contact.Builder
				   (name,"contactID" + ++contactCount).postalAddress(postalAddress).phoneNumber(phoneNumber)
				   .emailAddress(emailAddress).note(note).build();
				   contacts.add(contact);
			   }
		   }
		   in.close();
	   }
	   catch(IOException e)
	   {
		   e.printStackTrace();
	   }
   }
	
   /**
	 * @param o the object to be compared for equality with this address book
	 * @return true if the specified object is equal to this address book
	 */
	@Override public boolean equals(Object o)
	{ 
		if (o == this)
			return true; 
		if (!(o instanceof AddressBook))
			return false; 
		AddressBook adr = (AddressBook)o; 
		return
			adr.userID.equals(userID) &&
			addressBookName.equals(addressBookName) &&
			adr.contacts.equals(contacts);
	}
	
	/**
	 * Returns the hash code value for this address book.
	 * @see Object#hashCode()
	 */
	@Override public int hashCode() { 
		int result = 17;
		result = 31 * result + userID.hashCode() ;
		result = 31 * result + addressBookName.hashCode() ;
		result = 31 * result + contacts.hashCode();
		return result;
	}	
	
	/**
	 * Compares this address book to another based the following members in
	 * this order: userID , addressBookName
	 */
	@Override public int compareTo(AddressBook a) {
		int difference = 0;
		
		difference = userID.compareTo(a.userID);
		if(difference != 0) return difference;
		
		difference = addressBookName.compareTo(a.addressBookName);
		return difference;		
	}
	

	/**
	 * @return the userID
	 */
	public String getUserID() 
	{
		return userID;
	}

	/**
	 * @return the addressBookID
	 */
	public String getAddressBookName() 
	{
		return addressBookName;
	}

	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() 
	{
		return contacts;
	}
	
}
