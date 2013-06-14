package org.shihweihuang.addressbook;

/**
 * An entry to address book. Field name is required, other fields can be empty
 * String
 * 
 * @author shihweihuang
 * @version 1.0
 */
public class Data {
	private String name;
	private String postalAddress;
	private String phoneNumber;
	private String emailAddress;
	private String note;

	/**
	 * Builder for Data Objects
	 * 
	 * @author shihweihuang
	 */
	public static class Builder {
		private String name = "";
		private String postalAddress = "";
		private String phoneNumber = "";
		private String emailAddress = "";
		private String note = "";

		/**
		 * Field name is required
		 * 
		 * @param name
		 */
		public Builder(String name) {
			this.name = name;
		}

		/**
		 * Optional Field postalAddress, default as empty string
		 * 
		 * @param postalAddress
		 * @return
		 */
		public Builder postalAddress(String postalAddress) {
			this.postalAddress = postalAddress;
			return this;
		}

		/**
		 * Optional Field phoneNumber, default as empty string
		 * 
		 * @param phoneNumber
		 * @return
		 */
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		/**
		 * Optional Field emailAddress, default as empty string
		 * 
		 * @param emailAddress
		 * @return
		 */
		public Builder emailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		/**
		 * Optional Field note, default as empty string
		 * 
		 * @param note
		 * @return
		 */
		public Builder note(String note) {
			this.note = note;
			return this;
		}

		/**
		 * @return Data Object
		 */
		public Data build() {
			return new Data(this);
		}
	}

	private Data(Builder builder) {
		name = builder.name;
		postalAddress = builder.postalAddress;
		phoneNumber = builder.phoneNumber;
		emailAddress = builder.emailAddress;
		note = builder.note;
	}

	/**
	 * @param name
	 *          name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param postalAddress
	 *          postal address to set
	 */
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * @param phoneNumber
	 *          phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param emailAddress
	 *          email address to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @param note
	 *          note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return name for this data
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return postal address for this data
	 */
	public String getPostalAddress() {
		return postalAddress;
	}

	/**
	 * @return phone number for this data
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return email address for this data
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return note for this data
	 */
	public String getNote() {
		return note;
	}

	@Override
	public String toString() {
		return "Name:" + name + ",Postal Address:" + postalAddress
				+ ",Phone Number:" + phoneNumber + ",Email Address:" + emailAddress
				+ ",Note:" + note;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Data)) {
			return false;
		}
		Data data = (Data) o;
		return data.name.equals(name) && data.postalAddress.equals(postalAddress)
				&& data.phoneNumber.equals(phoneNumber)
				&& data.emailAddress.equals(emailAddress) && data.note.equals(note);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + postalAddress.hashCode();
		result = 31 * result + phoneNumber.hashCode();
		result = 31 * result + emailAddress.hashCode();
		result = 31 * result + note.hashCode();
		return result;
	}
}
