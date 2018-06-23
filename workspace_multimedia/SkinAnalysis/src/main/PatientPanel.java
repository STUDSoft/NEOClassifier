package main;

import main.Patient;

import components.Date;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	/**
	 * Default constructor
	 * @param number The number of the current patient
	 */
	public PatientPanel(int number) {
		currentID=number;
		patient=new Patient(number, "tizio", "caio", new Date(01,01,1996));
		createPanel();
	}
	
	/**
	 * Constructor for loading
	 * @param number The number of the current patient
	 * @param path The path to the folder of the patient
	 */
	public PatientPanel(int number, String path) {
		currentID=number;
		patient=new Patient(path);
		createPanel();
	}
	
	/**
	 * Creates the structure of the tab
	 */
	private void createPanel() {
		dataTA= new JTextArea("paziente "+currentID+"\n");
		dataTA.append(patient.getName()+"\n");
		dataTA.append(patient.getSurname()+"\n");
		dataTA.append(""+patient.getDOB());
		this.add(dataTA);
		
		gallery=new JPanel();
		gallery.setLayout(new BoxLayout(gallery,BoxLayout.Y_AXIS));
		Image[] tmp=patient.getImages();
		if(tmp.length==0)
			gallery.add(new JTextArea("no images"));
		else {
			for(int i=0; i<tmp.length; i++)
				gallery.add(createImgPanel(tmp[i]));
		}
		this.add(gallery);
		
		saveBtn=new JButton("save");
		saveBtn.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				patient.save();
			}
		});
		this.add(saveBtn);
		
		closeBtn=new JButton("close");
		this.add(closeBtn);
	}
	
	/**
	 * Creates the structure for one image
	 * @param img The image to show
	 */
	private JPanel createImgPanel(Image image) {
		JPanel imgPanel=new JPanel();
		imgPanel.setLayout(new BoxLayout(imgPanel,BoxLayout.X_AXIS));
		
		JTextArea when= new JTextArea(""+image.getDate());
		JLabel img=new JLabel();
		img.setIcon(new ImageIcon(image.getImg().getAbsolutePath()));
		//TODO resize
		img.setMinimumSize(new Dimension(100, 100));
		img.setPreferredSize(new Dimension(100, 100));
		img.setMaximumSize(new Dimension(100, 100));
		
		JTextArea danger= new JTextArea("Dangerous: "+image.isDangerous());
		
		imgPanel.add(when);
		imgPanel.add(img);
		imgPanel.add(danger);
		
		return imgPanel;
	}
	
}







