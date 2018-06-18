package components;

/**
 * Class used to clarify date format
 * @author Daniele
 *
 */
public class Date {
	
	/** Number of the day of the month */
	private int day;
	/** Number of the month */
	private int month;
	/** Year */
	private int year;
	
	/**
	 * Suggested constructor
	 * @param d Number of Day
	 * @param m Number of Month
	 * @param y Year
	 */
	public Date(int d, int m, int y) {
		day=d;
		month=m;
		year=y;
	}
	
	/**
	 * Alternative constructor
	 * @param date Date in a string in format dd-mm-yyyy
	 */
	public Date(String date) {
		String [] array=date.split("-");
		day=Integer.parseInt(array[0]);
		month=Integer.parseInt(array[1]);
		year=Integer.parseInt(array[2]);
	}
	
	/**
	 * Used to get the number of the day
	 * @return Number of the day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Used to get the number of the month
	 * @return Number of the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Used to get the year
	 * @return Year of the date
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Formats the date in string format
	 * @return The date in dd-mm-yyyy format
	 */
	public String toString() {
		return ""+day+"-"+month+"-"+year;
	}
}
