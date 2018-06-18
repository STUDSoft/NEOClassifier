package main;

import main.Patient;

import components.Date;

import java.awt.Component;
import java.awt.event.ActionEvent;

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
	/** Patient of the panel */
	private Patient patient;
	
	/** Area for patient data*/
	private JTextArea dataTA;
	
	/** Button to save patient data */
	private JButton saveBtn;
	/** Button to close patient tab */
	private JButton closeBtn;
	
	/** Image gallery */
	private JPanel gallery;
	
	static int count=0;
	
	/**
	 * Default constructor
	 * @param number The number of the current patient
	 */
	public PatientPanel(int number) {
		currentID=number;
		dataTA= new JTextArea("paziente "+currentID+"\n");
		patient=new Patient("tizio", "caio", new Date(01,01,1996));
		dataTA.append(patient.getName()+"\n");
		dataTA.append(patient.getSurname()+"\n");
		dataTA.append(""+patient.getDOB());
		this.add(dataTA);
		
		gallery=new JPanel();
		gallery.add(new JTextField("images"));
		this.add(gallery);
		
		saveBtn=new JButton("save");
		saveBtn.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				newPatient();
			}
		});
		this.add(saveBtn);
		
		closeBtn=new JButton("close");
		this.add(closeBtn);
	}
	
	public void newPatient() {
		count++;
		Patient pt=new Patient(""+count, ""+count, new Date(count%12, count%28, 1996) );
		dataTA.setText(pt.toString());
		pt.save();
		pt.load();
		dataTA.append("\n"+pt.toString());
	}
}







