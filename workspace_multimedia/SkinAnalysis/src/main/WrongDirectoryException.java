package main;

/**
 * Exception class used to handle the choice of a wrong directory during loading
 * 
 * @author Daniele
 *
 */
public class WrongDirectoryException extends Exception {

	/**
	 * String used to contain a message
	 */
	private String msg;
	
	/**
	 * Most used constructor
	 * 
	 * @param message a message used to identify the problem
	 */
	public WrongDirectoryException(String message){
		this.msg=message;
	}
	
	/**
	 * Used to return the message the exception object carries
	 * 
	 * @return the message brought by the exception
	 */
	public String getMsg(){
		return this.msg;
	}
}
