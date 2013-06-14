package addressBook;

/**
 * Contact.java
 * 
 * Immutable class for all the contacts.
 * Only constructed through {@code Contact.Builder.build}.
 * {@code name} and {@code contactID} are required, other members may be null.
 * 
 * @author Ashwath
 *
 */
public class Contact implements Comparable<Contact>
{
		
	// Required parameters
	private final Name name;
	private final String contactID;

	
	// Optional parameters
	private EmailAddress emailAddress = null;;
	private PhoneNumber phoneNumber=null;
	private PostalAddress postalAddress=null;
	private String note=null;
	

    
	private Contact(Builder builder)
	{
		name = builder.name;
		emailAddress = builder.emailAddress;
		phoneNumber = builder.phoneNumber;
		postalAddress = builder.postalAddress;
		contactID = builder.contactID;
		note = builder.note;
		validate();
	}
	
	private void validate()
	{
		if(name == null)
			throw new NullPointerException("name");
		if(contactID == null)
			throw new NullPointerException("contactID");
	}
	
	private static <T> int nullCompareTo(Comparable<T> a, T b) 
	{
		if(a == null ^ b == null)
			return (a == null) ? -1 : 1;
		return a.compareTo(b);
	}


	/**
	 * @param obj the object to be compared for equality with this contact
	 * @return true if the specified object is equal to this contact
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (contactID == null) {
			if (other.contactID != null)
				return false;
		} else if (!contactID.equals(other.contactID))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (postalAddress == null) {
			if (other.postalAddress != null)
				return false;
		} else if (!postalAddress.equals(other.postalAddress))
			return false;
		return true;
	}
	
	/**
	 * Returns the hash code value for this contact.
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactID == null) ? 0 : contactID.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((postalAddress == null) ? 0 : postalAddress.hashCode());
		return result;
	}
	
	/**
	 * Compares this contact to another based the following members in
	 * this order: contact ID, name,emailAddress, phoneNumber,postalAddress,note
	 */
	@Override public int compareTo(Contact co) 
	{
        int diffenence = 0;
		
        diffenence = contactID.compareTo(co.contactID);
		if(diffenence != 0) return diffenence;
		
		diffenence = name.compareTo(co.name);
		if(diffenence != 0) return diffenence;
		
		diffenence = nullCompareTo(emailAddress, co.emailAddress);
		if(diffenence != 0) return diffenence;
		
		diffenence = nullCompareTo(phoneNumber, co.phoneNumber);
		if(diffenence != 0) return diffenence;
		
		diffenence = nullCompareTo(postalAddress, co.postalAddress);
		if(diffenence != 0) return diffenence;
		
		diffenence = nullCompareTo(note, co.note);		
		return diffenence;
	}
	
	/**
	 * Returns a string showing this contact in the format
	 * name postalAddress phoneNumber emailAddress note 
	 * @return a string representing this postal address
	 */
	@Override public String toString()
	{
		return (name.toString() + "\n" + postalAddress.toString() + "\n" + phoneNumber.toString()
				+ "\n" + emailAddress.toString() + "\n" + note);
	}
	
	
	/**
	 * @return the name
	 */
	public Name getName() 
	{
		return name;
	}


	/**
	 * @return the contactID
	 */
	public String getContactID() 
	{
		return contactID;
	}


	/**
	 * @return the emailAddress
	 */
	public EmailAddress getEmailAddress()
	{
		return emailAddress;
	}

	
	/**
	 * @return the phoneNumber
	 */
	public PhoneNumber getPhoneNumber() 
	{
		return phoneNumber;
	}


	/**
	 * @return the postalAddress
	 */
	public PostalAddress getPostalAddress() 
	{
		return postalAddress;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Builder pattern based class for building a new instance of an
	 * {@code Contact} class.  Collects all required parameters and any
	 * optional ones.  Calling {@code build} will call the constructor of
	 * {@code Contact} and check any invariants.   
	 */
	public static class Builder implements Buildable<Contact> 
	{
		//Required parameters
		private final Name name;
		private final String contactID;

		//Optional parameters
		private  EmailAddress emailAddress;
		private  PhoneNumber phoneNumber;
		private  PostalAddress postalAddress;
		private  String note;
		
		
		/**
		 * setter for Name member
		 * @param name
		 */				
		public Builder(Name name,String contactID)
		{
			this.name = name;
			this.contactID = contactID;
		}
		
		
		/**
		 * Calls the constructor for {@code Contact} using this builder.
		 * @return {@code Contact} instance built from this builder
		 */
		@Override public Contact build() 
		{
			return new Contact(this);
		}
		
				
		/**
		 * setter for email address member
		 * @param emailAddress
		 * @return this builder
		 */
		public Builder emailAddress(EmailAddress emailAddress)
		{
			this.emailAddress = emailAddress;
			return this;
		}
		
		/**
		 * setter for phone number member
		 * @param phoneNumber
		 * @return this builder
		 */		
		public Builder phoneNumber(PhoneNumber phoneNumber)
		{
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		/**
		 * setter for postal address member
		 * @param postalAddress
		 * @return this builder
		 */				
		public Builder postalAddress(PostalAddress postalAddress)
		{
			this.postalAddress = postalAddress;
			return this;
		}
		
		/**
		 * setter for note member
		 * @param note
		 * @return this builder
		 */				
		public Builder note(String note)
		{
			this.note = note;
			return this;
		}
		
	}
}

