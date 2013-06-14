package addressBook;

/**
 * PostalAddress.java
 * 
 * Simple immutable final class containing members for a postal address.
 * 
 * @author Ashwath
 *
 */
public final class PostalAddress implements Comparable<PostalAddress> 
{

	//Immutable required fields
	private final String streetAddress;
	private final String city;
	private final String state;
	private final String country;
	private final String zipCode;
	
	/**
	 * Constructor with all required fields
	 * @param streetAddress city state  country and zipCode in String format
	 * @throws NullPointerException if any fields are null
	 */
	public PostalAddress(String streetAddress, String city, String state,String country, String zipCode)
	{
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		
		validateNonNullMember(this.streetAddress, "streetAddress");
		validateNonNullMember(this.city, "city");
		validateNonNullMember(this.state, "state");
		validateNonNullMember(this.state, "country");
		validateNonNullMember(this.zipCode, "zipCode");
	}
	
	private static void validateNonNullMember(Object member, String name)
	{
		if(member == null)
			throw new NullPointerException(name);
	}

	/**
	 * @param obj the object to be compared for equality with this address
	 * @return true if the specified object is equal to this address
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostalAddress other = (PostalAddress) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	/**
	 * Returns the hash code value for this postal address.
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}	
	
	/**
	 * Compares this postal address to another based the following members in
	 * this order: state, city,, country, zipCode, streetAddress
	 */	
	@Override public int compareTo(PostalAddress pa)
	{
		int difference = 0;
		
		difference = state.compareTo(pa.state);
		if(difference != 0) return difference;		
		
		difference = city.compareTo(pa.city);
		if(difference != 0) return difference;	
		
		difference = country.compareTo(pa.country);
		if(difference != 0) return difference;
		
		difference = zipCode.compareTo(pa.zipCode);
		if(difference != 0) return difference;

		difference = streetAddress.compareTo(pa.streetAddress);		
		return difference;
	}
	
	/**
	 * Returns a string showing this postal address in the format
	 * streetAddress city, state, zip code,country
	 * @return a string representing this postal address
	 */
	@Override public String toString()
	{
		return String.format("%s ,%s, %s ,%s, %s", 
				streetAddress, city, state, zipCode,country);
	}
	
	/**
	 * Returns immutable non-null street address
	 * @return street address value
	 */
	public String getStreetAddress()
	{
		return streetAddress;
	}
	
	/**
	 * Returns immutable non-null city
	 * @return city value
	 */	
	public String getCity()
	{
		return city;
	}
	
	/**
	 * Returns immutable non-null state
	 * @return state value
	 */	
	public String getState()
	{
		return state;
	}
	
	/**
	 * Returns immutable non-null state
	 * @return the country
	 */
	public String getCountry() 
	{
		return country;
	}	
	
	/**
	 * Returns immutable non-null zipCode
	 * @return zip code value
	 */	
	public String getZipCode()
	{
		return zipCode;
	}

	
}

