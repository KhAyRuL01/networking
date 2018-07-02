import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;

public class MyClient2 {

		public static void main(String[] args) throws Exception
		{
			Socket soc = new Socket("localhost",8899);
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
	    	DataInputStream input=new DataInputStream(soc.getInputStream());
	    	Scanner scan = new Scanner(System.in);
	    	String ans = input.readUTF();
	    	System.out.println("Server reply: " + ans);
	    	while(true)
	        {
	    		System.out.print("Client: ");
	        	String str = scan.next();
	        	dout.writeUTF(str);
	        	if(str.equals("quit"))
	        		break;
	        	ans = input.readUTF();
	        	System.out.println("Server reply: " + ans);
	        }
	    	soc.close();
		}
}
