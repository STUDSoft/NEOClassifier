package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import components.ButtonTabComponent;
import components.Date;

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
	/** Button to load a patient data from mobile */
	private JButton mobilePatientBt;
	/** Button to update an existent patient data */
	private JButton updatePatientBt;
	/** Button to load an existent patient data */
	private JButton loadPatientBt;
	/** Button to close application */
	private JButton closeApp;
	/*TODO Default border for the app buttons */
	//private Border buttonBorder=BorderFactory.createEmptyBorder(0, 10, 10, 0);
	
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
		//start.setLayout( new BoxLayout(start, BoxLayout.Y_AXIS));
		
		addPatientBt=new JButton("Nuovo paziente");
		addPatientBt.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPatient();
			}
		});

		mobilePatientBt=new JButton("Carica paziente da smartphone ");
		mobilePatientBt.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				mobilePatient();
			}
		});
		
		updatePatientBt=new JButton("Aggiorna paziente");
		
		loadPatientBt=new JButton("Carica un paziente esistente");
		loadPatientBt.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadPatient();
			}
		});
		
		closeApp=new JButton("Close App");
		closeApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destroy();
				System.exit(0);
			}
		});
		
		JPanel btnPanel=new JPanel();
		btnPanel.setLayout( new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		btnPanel.add(addPatientBt);
		btnPanel.add(mobilePatientBt);
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
		System.out.println("Closing");
	}
	
	/**
	 * Creates a new tab for a client
	 */
	private void addPatient() {
		
		JPanel newP=new JPanel();
		newP.setLayout(new BoxLayout(newP, BoxLayout.Y_AXIS));
		
		JLabel nameL=new JLabel("Nome: ");
		JTextArea nameTA=new JTextArea("Tizio");
		JLabel surnameL=new JLabel("Cognome: ");
		JTextArea surnameTA=new JTextArea("Caio");
		JLabel dobL=new JLabel("Data di Nascita: ");
		JTextArea dobTA=new JTextArea("1990-01-01");
		
		newP.add(nameL);
		newP.add(nameTA);
		newP.add(surnameL);
		newP.add(surnameTA);
		newP.add(dobL);
		newP.add(dobTA);
		
	    int result = JOptionPane.showConfirmDialog(this, newP,
	            "Nuovo Paziente", JOptionPane.OK_CANCEL_OPTION);
		if(result==JOptionPane.OK_OPTION) {
			Patient pt=new Patient(patientN+1, nameTA.getText(), surnameTA.getText(), new Date(dobTA.getText()));
			PatientPanel patientPanel=new PatientPanel(tabPane, patientN+1, pt);
			createPanel(patientPanel);
		}
		
	}
	
	/**
	 * Creates a connection with the mobile app and loads patient data from there
	 */
	private void mobilePatient() {
		Connector.newConnection();
	}
	
	/**
	 * Creates a new tab and load an existing patient
	 */
	private void loadPatient() {
		
		JFileChooser c = new JFileChooser();
		String path= new String();
		c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
	    int rVal = c.showOpenDialog(SkinAnalysis.this);
	    if (rVal == JFileChooser.APPROVE_OPTION) {
	        path=c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName();

			try {
				PatientPanel patientPanel;
				patientPanel = new PatientPanel(tabPane, patientN+1, path);
				
				createPanel(patientPanel);
			} catch (WrongDirectoryException e) {
				JPanel msg=new JPanel();
				msg.add(new JLabel("Cartella non accettata, file mancanti"));
				JOptionPane.showMessageDialog(this, msg);
			}
			
			
	    }
	    else if (rVal == JFileChooser.CANCEL_OPTION) {
	        System.out.println("Loading cancelled");
	    }
		
	}
	
	/**
	 * Creates a new tab given the patient panel
	 * @param ptPanel The panel of the new tab
	 */
	private void createPanel(PatientPanel ptPanel) {
		patientN++;
		String title=new String("Patient "+patientN);
		tabPane.addTab(title, ptPanel);		
		ButtonTabComponent btn= ptPanel.getBtnTabComponent();
		tabPane.setTabComponentAt(tabPane.indexOfTab(title), btn);
	}
	
	/**
	 * Starts application interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Avvio");
		new SkinAnalysis();
	}

}
