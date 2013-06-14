package pqs.ps1.addressbook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Address book allows duplicate address entries in one address book
 * It supports add Entry, remove entry, search specific entry, 
 * It can also be saved to file and load from file
 * @author peihong
 */
public class AddressBook {
	
  private List<AddressEntry> entries = new LinkedList<AddressEntry>();
	
  /**
   * Private Constructor
   */
  private AddressBook(){
  }
  
  /**
   * Create instance of address book
   * @return An instance
   */
  public static AddressBook newInstance(){
  	return new AddressBook();
  }
  
	/**
	 * Add address entry to the address book
	 * @param entry Address entry that is need to be added
	 * @return Return true when add operation success, 
	 * return false when operation failed
	 */
	public boolean addEntry(AddressEntry entry){
		if (entry == null){
			return false;
		}
		entries.add(entry);
		return true;
	}
	
	/**
	 * Remove address entry from the address book
	 * @param entry Address entry that is going to be removed from the 
	 * Address book
	 * @return Return true when remove operation success,
	 * return false when operation failed
	 */
	public boolean removeEntry(AddressEntry entry){
		if (entry == null){
			return false;
		}
		return entries.remove(entry);
	}
	
	/**
	 * Search for the address entry given a property of the address entry
	 * @param query Any property belongs to the address entry
	 * that you would like to use for searching 
	 * @return Return the matched Address Entry
	 */
	public List<AddressEntry> search(String query){
		List<AddressEntry> res = new ArrayList();
		for(AddressEntry entry:entries){
			if (entry.contains(query)){
				res.add(entry);
			}
		}
		return res;	
	}
	
	/**
	 * Save address book to local with desired name
	 * @param filename Saved file name of the address book
	 */
	public void save(String filename){
		try {
			File file = new File(filename);
			FileWriter fw = new FileWriter(file);
			fw.write(this.toString());
			fw.close();
		} catch (IOException e) {
	    System.err.println("CaughtIOException: " + e.getMessage());
		}
	}
	
	/**
	 * Read the address book from a file
	 * @param file file name which is going to be load as 
	 * an Address book
	 * @return Return Address Book Object
	 */
	public static AddressBook read(String file){
		AddressBook book = new AddressBook();
		try {
			JSONParser parser = new JSONParser();
			JSONArray jarray = (JSONArray) parser.parse(new FileReader(file));
			for (int i = 0; i < jarray.size(); i++) {
				JSONObject obj = (JSONObject) jarray.get(i);
				if (AddressEntry.Deseriliaze(obj) != null ){
					book.addEntry(AddressEntry.Deseriliaze(obj));
				}
			}
    } catch (FileNotFoundException e) {
    	System.err.println("CaughtFileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("CaughtIOException: " + e.getMessage());
		} catch (ParseException e) {
	    System.err.println("CaughtParseException: " + e.getMessage());
		}
		return book;
	}
	
	/**
	 * Convert this object in a JSON format String
	 */
	@Override
	public String toString() {
		JSONArray jarray = new JSONArray();
		for(AddressEntry entry: entries){
			if (entry != null){
				jarray.add(entry.Seriliaze());
			}
		}
		return jarray.toJSONString();
	}

}
