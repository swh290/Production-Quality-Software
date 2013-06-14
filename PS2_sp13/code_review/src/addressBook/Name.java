package addressBook;

/**
 * Name.java
 * 
 * Simple immutable final class containing first name,middle name and last name.
 * 
 * @author Ashwath
 *
 */
public final class Name implements Comparable<Name>
{
	
	//Required parameters
	private final String firstName;
	private final String lastName;
	
	//Optional parameters
	private String middleName = "";
	
	/**
	 * Constructor with all the fields
	 * @param firstName middleName and lastName in String format
	 */
	public Name(String firstName, String middleName,String lastName) 
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	private static <T> int nullCompareTo(Comparable<T> a, T b) 
	{
		if(a == null ^ b == null)
			return (a == null) ? -1 : 1;
		return a.compareTo(b);
	}

	/**
	 * @param o the object to be compared for equality with this name
	 * @return true if the specified object is equal to this name
	 */	
	@Override public boolean equals(Object o) 
	{
		if (o == this)
			return true;
		if (!(o instanceof Name))
			return false;
		
		Name name = (Name)o;
		
		return name.firstName.equals(firstName)
		&& name.middleName.equals(middleName) 
		&& name.lastName.equals(lastName);
	}
	
	/**
	 * Returns the hash code value for this name.
	 * @see Object#hashCode()
	 */
	@Override public int hashCode() 
	{
		int result = 17;
		result = 31 * result + firstName.hashCode();
		result = 31 * result + (middleName == null ? 0 : middleName.hashCode());
		result = 31 * result + lastName.hashCode();
		return result;
	}

	/**
	 * Compares this name to another based the following members in
	 * this order: first name, last name and middle name
	 * @param name object
	 */
	@Override public int compareTo(Name name) 
	{
		int difference = 0;
		
		difference = firstName.compareTo(name.firstName);
		if(difference != 0) return difference;		
		
		difference = lastName.compareTo(name.lastName);
		if(difference != 0) return difference;	

		difference = nullCompareTo(middleName, name.middleName);	
		return difference;
	}
	
	/**
	 * Returns a string showing this name in the format
	 * firstName middleName lastName 
	 * @return a string representing this postal address
	 */
	@Override public String toString()
	{
		return (firstName + " " + middleName + " "+ lastName);
	}
	
	/**
	 * Returns immutable non-null firstName
	 * @return first name value
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	
	/**
	 * Returns immutable non-null lastName
	 * @return last name value
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Returns immutable null middleName
	 * @return middle name value
	 */
	public String getMiddleName()
	{
		return middleName;
	}

}

