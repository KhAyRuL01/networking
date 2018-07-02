import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;

public class MyClient {

		public static void main(String[] args) throws Exception
		{
			Socket soc = new Socket("localhost",8892);
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
	    	DataInputStream input=new DataInputStream(soc.getInputStream());
	    	Scanner scan = new Scanner(System.in);
	    	String ans = input.readUTF();
			System.out.println(ans);
			ans = input.readUTF();
			System.out.println(ans);
	    	while(true)
	        {
	    		System.out.print("Client01: ");
	        	String str = scan.next();
	        	dout.writeUTF(str);
	        	ans = input.readUTF();
	        	System.out.println(ans);
	        	if(str.equals("bye"))
	        		break;
	        }
	    	soc.close();
		}
}
