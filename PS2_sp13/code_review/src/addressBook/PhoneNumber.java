package addressBook;

/**
 * PhoneNumber.java
 * 
 * This class represents a North American phone number consisting of
 * an Area Code, a Prefix and a Line Number.  It is immutable and
 * cannot be extended.
 * 
 * @author Ashwath
 *
 */
public final class PhoneNumber implements Comparable<PhoneNumber> 
{

	//All fields required and immutable.
	private final short areaCode;
	private final short prefix;
	private final short lineNumber;
	
	
	private static void rangeCheck(int arg,int min, int max,String name) 
	{
		if (arg < min || arg > max) 
			throw new IllegalArgumentException(name +": " + arg);
	}
	
	
	/**
	 * Constructor that creates a PhoneNumber from the individual parts.
	 * 
	 * @param areaCode the First three digits
	 * @param prefix the middle three digits
	 * @param lineNumber The last four digits
	 */
	public PhoneNumber(int areaCode, int prefix, int lineNumber){
		rangeCheck(areaCode,0,  999, "areaCode");
		rangeCheck(prefix,0,  999, "prefix");
		rangeCheck(lineNumber,0, 9999, "lineNumber");
		this.areaCode = (short)areaCode;
		this.prefix = (short)prefix;
		this.lineNumber= (short)lineNumber;
	}
	
	/**
	 * @param phoneNumberString exactly 10 digits and any amount of characters
	 * @return phone number instance represented by the string
	 * @throws IllegalArgumentException if phoneNumberString is invalid
	 */
	public static PhoneNumber fromString(String phoneNumberString){
		String name=phoneNumberString;
		String stripped = phoneNumberString.replaceAll("\\D", "");
			if(stripped.length() != 10)
				throw new IllegalArgumentException(name + ": " + phoneNumberString);
		return new PhoneNumber(
				Integer.parseInt(stripped.substring(0, 3)),
				Integer.parseInt(stripped.substring(3, 6)),
				Integer.parseInt(stripped.substring(6)));
	}
	
	/**
	 * Compares the specified object with this list for equality.
	 * Returns true if and only if the specified object is also a 
	 * PhoneNumber and has the same field values.
	 * 
	 * @param obj the object being compared for equality with this PhoneNumber
	 * @return true if the specified object is equal to this PhoneNumber
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (prefix != other.prefix)
			return false;
		return true;
	}
	
	/**
	 * Returns the hash code value for this PhoneNumber.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaCode;
		result = prime * result + lineNumber;
		result = prime * result + prefix;
		return result;
	}	
	
	/**
	 * Compares this PhoneNumber to another PhoneNumber.
	 * Each field is compared in this order:
	 * areaCode, prefix, lineNumber
	 * 
	 * @return < 0 if this PhoneNumber is less than the other
	 * > 0 if it is greater and 0 otherwise
	 */
	@Override public int compareTo(PhoneNumber pn) {
		int areaCodeDiff = areaCode - pn.areaCode;
		if(areaCodeDiff != 0)
			return areaCodeDiff;
		
		int prefixDiff = prefix - pn.prefix;
		if(prefixDiff != 0)
			return prefixDiff;
		
		return lineNumber - pn.lineNumber;
	}	
	
	/**
	 * Returns a string representation of this phone number in the format
	 * (areaCode)prefix-lineNumber
	 * @return string representing this phone number
	 */
	@Override public String toString(){
		return String.format("%d-%d-%d",areaCode, prefix, lineNumber);
	}
	
	/**
	 * Returns immutable, non-null area code for this phone number
	 * @return area code value
	 */	
	public short getAreaCode(){
		return areaCode;
	}
		
	/**
	 * Returns immutable, non-null prefix for this phone number
	 * @return prefix value
	 */	
	public short getPrefix(){
		return prefix;
	}
	
	/**
	 * Returns immutable, non-null line number for this phone number
	 * @return line number value
	 */	
	public short getLineNumber(){
		return lineNumber;
	}

}
