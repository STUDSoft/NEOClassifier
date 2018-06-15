package main;

import java.awt.Component;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	/** Date of birth */
	private Date birthDate;
	
	/** Area for patient data*/
	private JTextArea dataTA;
	
	/** Button to save patient data */
	private JButton saveBtn;
	/** Button to close patient tab */
	private JButton closeBtn;
	
	/** Image gallery */
	private JPanel gallery;
	
	/**
	 * Default constructor
	 * @param number The number of the current patient
	 */
	public PatientPanel(int number) {
		currentID=number;
		dataTA= new JTextArea("paziente "+currentID+"\n");
		name="tizio";
		surname="caio";
		birthDate=new Date(1900, 12, 5);
		dataTA.append(name+"\n");
		dataTA.append(surname+"\n");
		dataTA.append(""+birthDate);
		this.add(dataTA);
		
		gallery=new JPanel();
		gallery.add(new JTextField("images"));
		this.add(gallery);
		
		saveBtn=new JButton("save");
		this.add(saveBtn);
		
		closeBtn=new JButton("close");
		this.add(closeBtn);
	}
}







