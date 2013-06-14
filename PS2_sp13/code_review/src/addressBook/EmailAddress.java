package addressBook;

/**
 * EmailAddress.java
 * Immutable final class representing an email address.
 * {@code user} and {@code domain} are required fields.
 * @author Ashwath
 *
 */
public final class EmailAddress implements Comparable<EmailAddress> 
{
    //Required parameters
	private final String username;
	private final String domain;
		
	/**
	 * Constructor with required fields.
	 * @throws NullPointerException when user or domain  is null
	*/
	public EmailAddress(String username, String domain)
	{
		this.username = username;
		if(this.username == null)
			throw new NullPointerException("username");
		this.domain = domain;
		if(this.domain == null)
			throw new NullPointerException("domain");
	}
		
	/**
	 * Static factory method for creating an {@code EmailAddress} from a
	 * 	{@code String}.
	 * @param email Must contain a single ampersand between two groups
	 * 	of characters, i.e: host@domain 
	 * @return Instance of {@code EmailAddress} from {@code emailStr}.
	 * @throws IllegalArgumentException if {@code emailStr} is invalid
	 */
	public static EmailAddress fromString(String email)
	{
		String[] parts = email.split("@");
		if(parts.length != 2)
			throw new IllegalArgumentException("email: " + email);
		return new EmailAddress(parts[0], parts[1]);
	}
		

	/**
	 * Compares the specified object with this email address for equality.
	 * Returns true if and only if the specified object is also an
	 * email address and all members are {@code equal}.
	 * 
	 * @param obj the object to be compared for equality with this email
	 * @return true if the specified object is equal to this email
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailAddress other = (EmailAddress) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
		
	/**
	 * Returns the hash code value for this email address.
	 * @return integer hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}	
		
	/**
	 * Compares this email address to another based on domain then user members.
	 * @param eadr object
	 * @return integer comparison code
	 */
	@Override public int compareTo(EmailAddress eadr) 
	{
		int domainDiff = domain.compareTo(eadr.domain);
		return domainDiff != 0 ? domainDiff : username.compareTo(eadr.username);
	}
		
	/**
	 * Returns a string representation of this email address with the format
	 * user@domain
	 * @return a string representing this email address
	 */
	@Override public String toString()
	{
		return String.format("%s@%s", username, domain);
	}
		
	/**
	 * Returns immutable, non-null user for this email address.
	 * @return user value
	 */
	public String getUser()
	{
		return username;
	}
		
	/**
	 * Returns immutable, non-null domain for this email address
	 * @return domain value
	 */
	public String getDomain()
	{
		return domain;
	}

}

