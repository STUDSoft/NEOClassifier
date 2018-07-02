package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class used to create a connection between mobile and desktop to receive patient data 
 * @author Daniele
 *
 */
public class Connector {

	/** Connection port */
	private static int port=6024;
	
	/** File Location */
	private static String path="C:\\Users\\Daniele\\Downloads\\Universita\\img.png";
	
	/**
	 * Establishes a new connection with the mobile application
	 */
	public static void newConnection() {

		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					ServerSocket ss=new ServerSocket(port);
					System.out.println("Waiting for mobile - port: "+port);
					
					Socket s = ss.accept();
					System.out.println("Received");
					
					ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
					
					byte [] buffer= (byte[]) ois.readObject();
					FileOutputStream file=new FileOutputStream(path);
					file.write(buffer);
					
					file.close();
					ois.close();
					s.close();
					ss.close();
					
				}
				catch(IOException e) {
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		//TODO
		t.start();
	
	}
	
}
