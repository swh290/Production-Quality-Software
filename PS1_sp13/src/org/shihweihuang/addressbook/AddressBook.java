package org.shihweihuang.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

/**
 * An address book library that create an empty address book or load already
 * exist data form file. Provide methods that data can be add into or remove
 * from the address book, search amount all properties, and save to file.
 * 
 * @author shihweihuang
 * @version 1.0
 */
public class AddressBook {
	private List<Data> dataList;

	public AddressBook() {
		dataList = new ArrayList<Data>();
	}

	/**
	 * Create an AddressBook Object with address book file
	 * 
	 * @param path
	 *          the absolute path or the related path to the working directory of
	 *          the loaded file
	 */
	public AddressBook(String path) {
		dataList = new ArrayList<Data>();
		load(path);
	}

	/**
	 * Add an entry into address book
	 * 
	 * @param data
	 *          a Data Object attempt to add into address book
	 * @return true if add successfully
	 */
	public boolean add(Data data) {
		return dataList.add(data);
	}

	/**
	 * Add a list of entries into address book
	 * 
	 * @param datas
	 *          a list of Data Objects attempt to add into address book
	 */
	public void add(List<Data> datas) {
		for (Data data : datas) {
			add(data);
		}
	}

	/**
	 * Remove an entry from address book
	 * 
	 * @param data
	 *          a Data Object attempt to remove from address book
	 * @return true if remove successfully
	 */
	public boolean remove(Data data) {
		if (!dataList.contains(data)) {
			return false;
		} else {
			dataList.remove(data);
			return true;
		}
	}

	/**
	 * Remove a list of entries from address book
	 * 
	 * @param datas
	 *          a list of Data Objects attempt to remove from address book
	 */
	public void remove(ArrayList<Data> datas) {
		for (Data data : datas) {
			remove(data);
		}
	}

	/**
	 * Save address book to file
	 * 
	 * @param path
	 *          the absolute path or the related path to the working directory of
	 *          the saving file
	 */
	public void save(String path) {
		BufferedWriter bw;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			for (Data data : dataList) {
				bw.write(data.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load address book from file
	 * 
	 * @param path
	 *          the absolute path or the related path to the working directory of
	 *          the loaded file
	 */
	public void load(String path) {
		dataList.clear();
		Scanner scanner = null;
		Data data;
		try {
			scanner = new Scanner(new FileReader(path));
			while (scanner.hasNextLine()) {
				data = parseLine(scanner.nextLine());
				add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	/**
	 * Search the address book for the keyword in any properties
	 * 
	 * @param keyword
	 *          the keyword to search in address book
	 * @return a list of entries that contains keyword
	 */
	public ArrayList<Data> search(String keyword) {
		ArrayList<Data> list = new ArrayList<Data>();
		for (Data data : dataList) {
			if (data.getName().contains(keyword)
					|| data.getPostalAddress().contains(keyword)
					|| data.getPhoneNumber().contains(keyword)
					|| data.getEmailAddress().contains(keyword)
					|| data.getNote().contains(keyword)) {
				list.add(data);
			}
		}
		return list;
	}

	private Data parseLine(String line) {
		Data data = new Data.Builder("").build();
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			String string = scanner.next();
			int start = string.indexOf(":") + 1;
			int end = string.length();
			String content = (start <= end) ? string.substring(start, end) : "";
			if (string.contains("Name:")) {
				data.setName(content);
			} else if (string.contains("Postal Address:")) {
				data.setPostalAddress(content);
			} else if (string.contains("Phone Number:")) {
				data.setPhoneNumber(content);
			} else if (string.contains("Email Address:")) {
				data.setEmailAddress(content);
			} else if (string.contains("Note:")) {
				data.setNote(content);
			}
		}
		return data;
	}

	@Override
	public String toString() {
		String string = "";
		for (Data data : dataList) {
			string = string + data.toString() + "\n";
		}
		return string;
	}
}
