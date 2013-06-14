package pqs.ps1.addressbook;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * Address Entry is an entry of address book, 
 * it contains name, address, phone number, email and note.
 * All the fields must be String. You have to supply the field name
 * in order to create an address entry.
 * @author peihong
 */

public class AddressEntry {
  private String name;
  private String address;
  private String phone;
  private String email;
  private String note;
  
  /**
   * Builder class for Address Entry
   * @author peihong
   */
  public static class Builder{
  	private String name;
  	private String address = "";
    private String phone = "";
    private String email = "";
    private String note = "";
  	
  	/**
  	 * Build for Address Entry with name as a required field
  	 * @param name Contac's name
  	 */
  	public Builder(String name){
  		this.name = name;
  	}
  	
  	/**
  	 * @param val Contact's phone number you would like to add
  	 * @return Builder for Address Entry
  	 */
  	public Builder phone(String val){
  		phone = val;
  		return this;
  	}
  	
  	/**
  	 * @param val Contact's email you would like to add
  	 * @return Builder for Address Entry
  	 */
  	public Builder email(String val){
  		email = val;
  		return this;
  	}
  	
  	/**
  	 * @param val Contact's note you would like to add
  	 * @return Builder for Address Entry
  	 */
  	public Builder note(String val){
  		note = val;
  		return this;
  	}
  	
  	/**
  	 * @param val Contact's address you would like to add
  	 * @return Builder for Address Entry
  	 */
  	public Builder address(String val){
  		address = val;
  		return this;
  	}
  	
  	/**
  	 * @return Address Entry 
  	 */
  	public AddressEntry build(){
  		return new AddressEntry(this);
  	}
  	
  }
  
  /**
   * Private Constructor
   * @param builder
   */
  private AddressEntry(Builder builder){
		name = builder.name;
		phone = builder.phone;
		email = builder.email;
		address = builder.address;
		note = builder.note;
	}
  
  /**
   * Get the names of the fields in this Address Entry
   * @return A list of String that contains the fields of this class 
   */
  private List<String> getFieldNames(){
		List<String> fields = new ArrayList<String>();
		for (int i = 0; i < this.getClass().getDeclaredFields().length; i++){
			fields.add(this.getClass().getDeclaredFields()[i].getName());
		}
		return fields;
  }
  
  /**
   * Get field value of this class according to passed in parameter
   * @param fieldName The field name that want to get value of
   * @return the passed in field's value
   */
  private Object getField(String fieldName){
  	Object field = new Object();
  	try {
			field = this.getClass().getDeclaredField(fieldName).get(this);
		} catch (IllegalArgumentException e) {
	    System.err.println("CaughtIllegalArgumentException: " + e.getMessage());
		} catch (SecurityException e) {
	    System.err.println("CaughtSecurityException: " + e.getMessage());
		} catch (IllegalAccessException e) {
	    System.err.println("CaughtIllegalAccessException: " + e.getMessage());
		} catch (NoSuchFieldException e) {
	    System.err.println("CaughtNoSuchFieldException: " + e.getMessage());
		}
		return field;
  }
  
  /**
   * Check if the specific query is one of the property of this 
   * Address Entry
   * @param query Any property value you want to search for
   * @return
   */
  public <K> boolean contains(K query){
  	List<String> fields = getFieldNames();
  	for (int i = 0; i <fields.size(); i++){
  		if (getField(fields.get(i)) != null && 
  				!getField(fields.get(i)).equals("") &&
  				getField(fields.get(i)).equals(query)){
  			return true;
  		}
  	}
  	return false;
  }
  
  /**
   * Seriliaze this Address Entry to a JSON Object
   * @return An JSON object
   */
  public JSONObject Seriliaze(){
    JSONObject json = new JSONObject();
  	List<String> fields = getFieldNames();
  	for (int i = 0; i <fields.size(); i++){
  		json.put(fields.get(i), getField(fields.get(i)));
  	}
  	return json;
  }
  
  /**
   * Deserialize JSON object to an AddressEntry object
   * @param json
   * @return An AddressEntry object or null
   */
  public static AddressEntry Deseriliaze(JSONObject json){
  	AddressEntry entry = null;
  	try{
    	String name = (String) json.get("name");
    	String address = (String) json.get("address");
    	String phone = (String) json.get("phone");
    	String email = (String) json.get("email");
    	String note = (String) json.get("note");
    	entry = new AddressEntry.Builder(name).address(address).phone(phone).
    			note(note).email(email).build();
  	}
  	catch(Exception e){
	    System.err.println("CaughtException: " + e.getMessage());
  	}
  	return entry;
  }

  /**
	 * Convert this object in a JSON format string
	 */
	@Override
	public String toString() {
		return this.Seriliaze().toJSONString();
	}
  
  
  
}
