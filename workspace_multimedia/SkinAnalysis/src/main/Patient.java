package main;

import components.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Class to organize patients data
 * @author Daniele
 *
 */
public class Patient {

	/** Name of the patient*/
	private String name;
	/** Surname of the patient */
	private String surname;
	/** Date of Birth of the patient */
	private Date dateOfBirth;
	/** Default save path for patients*/
	private String dSavePath="C:\\Users\\Daniele\\Downloads\\Universita\\";
	
	/**
	 * Default Constructor of the class
	 * 
	 * @param n The name of the patient
	 * @param s The surname of the patient
	 * @param dob The date of birth of the patient
	 */
	public Patient(String n, String s, Date dob) {
		name=n;
		surname=s;
		dateOfBirth=dob;
	}
	
	/**
	 * Used to get the name of the patient
	 * @return The name of the patient
	 */
	public String getName() {
		return name;
	}

	/**
	 * Used to get the surname of the patient
	 * @return The surname of the patient
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Used to get the date of birth of the patient
	 * @return The date of birth of the patient
	 */
	public Date getDOB() {
		return dateOfBirth;
	}
	
	/**
	 * Used to change the name of the patient
	 * @param n the new name of the patient
	 */
	public void setName(String n) {
		name=n;
	}
	

	/**
	 * Used to change the surname of the patient
	 * @param s the new surname of the patient
	 */
	public void setSurname(String s) {
		surname=s;
	}
	
	/**
	 * Saves the patient data using the default path
	 */
	public void save() {
		save(dSavePath+surname+dateOfBirth.getYear()+".txt");
	}
	
	/**
	 * Saves the patient data 
	 * @param path The path of the patient data
	 */
	public void save(String path) {
		File file=new File(path);
		try {
			if(!file.exists())
				file.createNewFile();
			PrintWriter wr=new PrintWriter(path, "UTF-8");
			wr.println(name);
			wr.println(surname);
			wr.println(dateOfBirth);
			wr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the patient data using the default path
	 */
	public void load() {
		load(dSavePath+surname+dateOfBirth.getYear()+".txt");
	}
	
	/**
	 * Loads the patient data using the input file path
	 * @param path The path to the patient data
	 */
	public void load(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			try {
				name = br.readLine();
				surname = br.readLine();
				dateOfBirth = new Date(br.readLine());
			} finally {
				br.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String toString() {
		return name+" "+surname+", "+dateOfBirth;
	}
}
