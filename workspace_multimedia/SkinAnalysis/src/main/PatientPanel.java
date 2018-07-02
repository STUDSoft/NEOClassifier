package main;

import main.Patient;
import components.ButtonTabComponent;
import components.Date;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Class for the tabs of the patients
 * @author Daniele
 *
 */
public class PatientPanel extends JPanel {

	/** The current ID for the patient */
	private int currentID;
	/** The pane container of the panel */
	private JTabbedPane fPane;
	/** The component of the title of the pane */
	private ButtonTabComponent tabComponent;
	/** Patient of the panel */
	private Patient patient;
	
	/** Area for the patient data */
	private JPanel pData;
	/** Area for the patient ID */
	private JLabel pID;
	/** Area for the patient name */
	private JLabel pName;
	/** Area for the patient surname */
	private JLabel pSurname;
	/** Area for the patient date of birth */
	private JLabel pDOB;
	/** Default border for patient labels */
	private Border labelBorder=BorderFactory.createEmptyBorder(10, 0, 0, 10);
	
	/** Area for the image gallery */
	private JPanel gallery;
	/** Scroll pane for the image gallery */
	private JScrollPane imgSP;
	/** Images size */
	private Dimension imgDim= new Dimension(200,200);//width, height
	/** Separators size */
	private Dimension sepDim= new Dimension(10,300);

	/** Area for the buttons */
	private JPanel btnPanel;
	/** Button to save patient data */
	private JButton saveBtn;
	/** Button to close patient tab */
	private JButton closeBtn;
	
	/**
	 * Default constructor
	 * @param fatherPane The pane father of the tab
	 * @param number The number of the current patient
	 */
	public PatientPanel(JTabbedPane fatherPane, int number) {
		patient=new Patient(number, "tizio", "caio", new Date(01,01,1996));
		createPanel(fatherPane, number);
	}
	
	/**
	 * Constructor for new patient
	 * @param fatherPane The pane father of the tab
	 * @param number The number of the current patient
	 * @param pt The data of the new patient
	 */
	public PatientPanel(JTabbedPane fatherPane, int number, Patient pt) {
		patient=pt;
		createPanel(fatherPane, number);
	}
	
	/**
	 * Constructor for loading
	 * @param fatherPane The pane father of the tab
	 * @param number The number of the current patient
	 * @param path The path to the directory of the patient
	 * @throws WrongDirectoryException If the directory is not accepted
	 */
	public PatientPanel(JTabbedPane fatherPane, int number, String path) throws WrongDirectoryException {
		patient=new Patient(path);
		createPanel(fatherPane, number);
	}
	
	/**
	 * Creates the structure of the tab
	 * @param fatherPane The pane father of the tab
	 * @param number The current number of the patient
	 */
	private void createPanel(JTabbedPane fatherPane, int number) {

		currentID=number;
		fPane=fatherPane;
		tabComponent=new ButtonTabComponent(fPane);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pData=new JPanel();
		pData.setLayout(new BoxLayout(pData, BoxLayout.X_AXIS));
		pID=new JLabel("ID "+currentID);
		pID.setBorder(labelBorder);
		pName=new JLabel ("Nome: "+patient.getName());
		pName.setBorder(labelBorder);
		pSurname=new JLabel ("Cognome: "+patient.getSurname());
		pSurname.setBorder(labelBorder);
		pDOB=new JLabel ("Data di Nascita: "+patient.getDOB());
		pDOB.setBorder(labelBorder);
		pData.add(pID);
		pData.add(pName);
		pData.add(pSurname);
		pData.add(pDOB);
		this.add(pData);
		
		gallery=new JPanel();
		gallery.setBorder(BorderFactory.createTitledBorder("Foto"));
		gallery.setLayout(new BoxLayout(gallery,BoxLayout.X_AXIS));
		imgSP= new JScrollPane(gallery);
		PatientImage[] tmp=patient.getImages();
		if(tmp.length==0)
			gallery.add(new JLabel("no images"));
		else {
			for(int i=0; i<tmp.length; i++) {
				gallery.add(createImgPanel(tmp[i]));
				JSeparator jSep=new JSeparator(JSeparator.VERTICAL);
				jSep.setMaximumSize(sepDim);
				//jSep.setPreferredSize(sepDim);
				gallery.add(jSep);
			}
		}
		this.add(imgSP);
		
		btnPanel=new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		saveBtn=new JButton("save");
		saveBtn.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){
				patient.save();
			}
		});
		btnPanel.add(saveBtn);
		
		closeBtn=new JButton("close");
		closeBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=fPane.indexOfTabComponent(tabComponent);
				if(i!=-1)
					fPane.remove(i);
				else
					System.out.println("errore");
			}
		});
		btnPanel.add(closeBtn);
		
		this.add(btnPanel);
		
	}
	
	/**
	 * Returns the close button component of the tab
	 * @return A reference to the tab button component
	 */
	public ButtonTabComponent getBtnTabComponent() {
		return tabComponent;
	}
	
	/**
	 * Creates the structure for one image
	 * @param img The image to show
	 */
	private JPanel createImgPanel(PatientImage image) {
		JPanel imgPanel=new JPanel();
		imgPanel.setLayout(new BoxLayout(imgPanel,BoxLayout.Y_AXIS));
		
		JLabel when= new JLabel("Date: "+image.getDate());
		JLabel where=new JLabel("Where: "+image.getLocation());
		JLabel danger= new JLabel("Dangerous: "+image.isDangerous());
		
		BufferedImage img=null;
		try {
			img=ImageIO.read(image.getImg());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel imgLabel=new JLabel( new ImageIcon(img.getScaledInstance(imgDim.width, imgDim.height, Image.SCALE_DEFAULT)));
		
		imgPanel.add(when);
		imgPanel.add(where);
		imgPanel.add(danger);
		imgPanel.add(imgLabel);

		
		return imgPanel;
	}
	
}







