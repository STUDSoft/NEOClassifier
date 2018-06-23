package main;

import java.io.File;

import components.Date;

/**
 * Class for stored images
 * @author Daniele
 *
 */
public class Image {
	
	/** The image file */
	private File image;
	/** When the image was taken */
	private Date when;
	/** What part of the body the image shows */
	private String location;
	/** Whether the image was declared dangerous or not*/
	private boolean danger;
	
	/**
	 * Creates a standard image object
	 * @param img The image file
	 * @param w When the picture was taken
	 */
	public Image(File img, Date w, String where) {
		image=img;
		when=w;
		location=where;
		danger=false;
	}
	
	/**
	 * Used to get the image stored
	 * @return The image stored
	 */
	public File getImg() {
		return image;
	}
	
	/**
	 * Used to know when the image was taken
	 * @return
	 */
	public Date getDate() {
		return when;
	}
	
	/**
	 * Used to know what part of the body the image shows
	 * @return The location of the body
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Used to know if the image is dangerous
	 * @return True if the image is dangerous, false otherwise
	 */
	public boolean isDangerous() {
		return danger;
	}
}
