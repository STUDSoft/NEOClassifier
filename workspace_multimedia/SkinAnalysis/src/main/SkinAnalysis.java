package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import components.ButtonTabComponent;

/**
 * Launches application
 * 
 * @author Daniele Monaco
 *
 */
public class SkinAnalysis extends JFrame {
	
	/** Counts the number of patients */
	private int patientN=0;
	
	/** A tabbed pane is used to view patients */
	private JTabbedPane tabPane;
	/** The starting tab */
	private JPanel start;
	
	/** Button used to add a patient */
	private JButton addPatientBt;
	/** Button to update an existent patient data */
	private JButton updatePatientBt;
	/** Button to load an existent patient data */
	private JButton loadPatientBt;
	/** Button to close application */
	private JButton closeApp;
	
	/** Default dimension for the app */
	private Dimension defaultDim = new Dimension(950,550);
	/** Dimension for the tabbed pane */
	private Dimension tabPaneDim = new Dimension(925,450);
	
	/**
	 * Default Constructor
	 */
	public SkinAnalysis() {
		
		//creating tabs
		tabPane=new JTabbedPane();
		
		//creating starting tab
		start = new JPanel();
		start.setLayout( new BoxLayout(start, BoxLayout.X_AXIS));
		start.add(new JTextArea("ciao"));
		
		addPatientBt=new JButton("Add Patient");
		addPatientBt.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPatient();
			}
		});
		
		updatePatientBt=new JButton("Update an existing patient");

		loadPatientBt=new JButton("Load an existing patient");
		
		closeApp=new JButton("Close App");
		
		JPanel btnPanel=new JPanel();
		btnPanel.setLayout( new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		btnPanel.add(addPatientBt);
		btnPanel.add(updatePatientBt);
		btnPanel.add(loadPatientBt);
		btnPanel.add(closeApp);
		
		start.add(btnPanel);
		
		tabPane.addTab("Start", start);
		tabPane.setMaximumSize(tabPaneDim);
		
		//container for all the interface
		JPanel total= new JPanel();
		total.setLayout( new BoxLayout(total, BoxLayout.Y_AXIS) );
		total.add(tabPane);
		
		//setting up the interface
		this.getContentPane().add(total);
		this.setTitle("SkinAnalysis");
		this.setSize(defaultDim);
		this.addWindowListener( new java.awt.event.WindowAdapter(){
			public void windowClosing(WindowEvent e){
				destroy();
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	
	/**
	 * Closes the application
	 */
	public void destroy() {
		
	}
	
	/**
	 * Creates a new tab for a client
	 */
	private void addPatient() {
		//TODO
		patientN++;
		Component patientPanel=new PatientPanel(patientN);
		String title=new String("Patient"+patientN);
		tabPane.addTab(title, patientPanel);
		
		ButtonTabComponent btn= new ButtonTabComponent(tabPane);
		tabPane.setTabComponentAt(tabPane.indexOfTab(title), btn);
		
	}
	
	/**
	 * Starts application interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Avvio");
		new SkinAnalysis();
	}

}
