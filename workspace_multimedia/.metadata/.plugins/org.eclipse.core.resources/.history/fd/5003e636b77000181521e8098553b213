package main;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class for the tabs of the patients
 * @author Daniele
 *
 */
public class PatientPanel extends JPanel {

	/** The current ID for the patient */
	private int currentID;
	/** Name of the patient */
	private String name;
	/** Surname of the patient */
	private String surname;
	
	
	/**
	 * Default constructor
	 * @param number The number of the current patient
	 */
	public PatientPanel(int number) {
		currentID=number;
		JTextArea msg= new JTextArea("paziente "+currentID);
		this.add(msg);
	}
}
