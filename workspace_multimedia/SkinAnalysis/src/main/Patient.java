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

	/** ID of the patient */
	private int ID;
	/** Name of the patient*/
	private String name;
	/** Surname of the patient */
	private String surname;
	/** Date of Birth of the patient */
	private Date dateOfBirth;
	/** Array of images of the patient */
	private Image[] imgs;
	/** Default save path for patients*/
	private String dSavePath="C:\\Users\\Daniele\\Downloads\\Universita\\Patients";
	
	/**
	 * Default Constructor of the class
	 * @param n The name of the patient
	 * @param s The surname of the patient
	 * @param dob The date of birth of the patient
	 */
	public Patient(int id, String n, String s, Date dob) {
		ID=id;
		name=n;
		surname=s;
		dateOfBirth=dob;
		imgs=new Image[0];
		
		File file=new File(dSavePath);
		if(!file.exists())
			file.mkdir();
		dSavePath=dSavePath+"\\"+ID+"_"+surname+"_"+dateOfBirth.getYear();
		
	}
	
	/**
	 * Constructor to load the patient's data
	 * @param path
	 */
	public Patient(String path) {
		dSavePath=path;
		this.load();
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
	 * Used to get the patient images
	 * @return An array of Image objects
	 */
	public Image[] getImages() {
		return imgs;
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
		save(dSavePath);
	}
	
	/**
	 * Saves the patient data 
	 * @param path The path of the patient folder
	 */
	public void save(String path) {
		File file=new File(path);
		try {
			if(!file.exists())
				file.mkdir();
			
			String dataP=path+"\\data.dat";
			file=new File(dataP);
			if(!file.exists())
				file.createNewFile();
			PrintWriter wr=new PrintWriter(dataP, "UTF-8");
			wr.println(name);
			wr.println(surname);
			wr.println(dateOfBirth);
			wr.close();
			
			String imgP=path+"\\images";
			file=new File(imgP);
			if(!file.exists())
				file.mkdir();
			//TODO aggiornamento immagini
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the patient data using the default path
	 */
	public void load() {
		load(dSavePath);
	}
	
	/**
	 * Loads the patient data using the input file path
	 * @param path The path to the patient folder
	 */
	public void load(String path) {
		try {
			String dataP=path+"\\data.dat";
			BufferedReader br = new BufferedReader(new FileReader(dataP));
			try {
				name = br.readLine();
				surname = br.readLine();
				dateOfBirth = new Date(br.readLine());
			} finally {
				br.close();
			}
			
			String imgP=path+"\\images";
			File file=new File(imgP);
			if(file.exists()) {
				File[] imgDir=file.listFiles();
				System.out.println(""+imgDir.length);
				imgs=new Image[imgDir.length];
				for(int i=0; i<imgDir.length; i++) {
						Image tmp=new Image(imgDir[i], new Date(2000,12,16), "somewhere");
						imgs[i]=tmp;
					}
			}
			else
				imgs=new Image[0];
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		dSavePath=path;//Reimposta il save path di default in caso di caricamento da cartella diversa
	
		
	}
	
	public String toString() {
		return name+" "+surname+", "+dateOfBirth;
	}
}
