import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.lang.Thread;

public class ServerSoc {
    
    public static void main(String[] args) throws Exception
    {
		boolean f = false;
        ServerSocket ss = new ServerSocket(8892);
		Socket s = ss.accept(); 
		System.out.println("One Client is connected Waiting for second client");
		DataOutputStream dout1 = new DataOutputStream(s.getOutputStream());
		dout1.writeUTF("You are connected to the Chat server please wait for second client");
		Socket s1 = ss.accept();
		System.out.println("\nSecond Client is Connected");
		DataOutputStream dout2 = new DataOutputStream(s1.getOutputStream());
		dout1.writeUTF("Server Reply: You can now send message to the Client 02.");
		dout2.writeUTF("");
		dout2.writeUTF("Server Reply: You can now send message to the Client 01.");
		f = true;
    	Thread thread1 = new Thread(new Runnable(){
		
			@Override
			public void run() {
				try{
					DataOutputStream dout=new DataOutputStream(s.getOutputStream());
					DataOutputStream dout2 = new DataOutputStream(s1.getOutputStream());
					DataInputStream input=new DataInputStream(s.getInputStream());
					while (true) {
						String str = input.readUTF();
						System.out.println("Client01 says: " + str);
						if (str.equals("stop")) {
							dout2.writeUTF("Client01 says: " + str);
							dout1.writeUTF("Server Reply: Chat\nSession is closed.\nProgram Terminated");
							break;
						}
						dout2.writeUTF("Client01 says: " + str);

					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
					DataOutputStream dout1 = new DataOutputStream(s.getOutputStream());
					DataInputStream input = new DataInputStream(s1.getInputStream());
					while (true) {
						String str = input.readUTF();
						System.out.println("Client02 says: " + str);
						if (str.equals("bye")) {
							dout1.writeUTF("Client02 says: " + str);
							dout1.writeUTF("Server Reply: Chat\nSession is closed.\nProgram Terminated");
							break;
						}
						dout1.writeUTF("Client02 says: " + str);

					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		if(f)
		{
			thread1.start();
			thread2.start();
		}
        ss.close();
    }
    
}
