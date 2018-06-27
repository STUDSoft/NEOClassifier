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
	public static void main(String[] args) {
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					ServerSocket ss=new ServerSocket(port);
					System.out.println("running");
					
					Socket s = ss.accept();
					System.out.println("accepted");
					
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//TODO
		t.start();
		
	}
	
}
