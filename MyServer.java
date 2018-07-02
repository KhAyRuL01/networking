import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class MyServer {
    
    public static void main(String[] args) throws Exception
    {
    	ServerSocket serverSoc = new ServerSocket(8899);
    	Thread client1 = new Thread(new Runnable() {
    		public void run()
    		{
    			try {
					Socket clientSocket = serverSoc.accept();
					DataOutputStream dout=new DataOutputStream(clientSocket.getOutputStream());
			    	DataInputStream input=new DataInputStream(clientSocket.getInputStream());
			    	dout.writeUTF("You are connected to the Date server");
			    	while(true)
			    	{
			    		String str = input.readUTF();
			    		System.out.println("Client01 says: " + str);
			    		if(str.equals("quit"))
							break;
			    		dout.writeUTF(getInf(str));
			    	}
			    	
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	client1.start();
    	
    	Thread client2 = new Thread(new Runnable() {
    		public void run()
    		{
    			try {
					Socket clientSocket = serverSoc.accept();
					DataOutputStream dout=new DataOutputStream(clientSocket.getOutputStream());
			    	DataInputStream input=new DataInputStream(clientSocket.getInputStream());
			    	dout.writeUTF("You are connected to the Date server");
			    	while(true)
			    	{
			    		String str = input.readUTF();
			    		System.out.println("Client02 says: " + str);
						if(str.equals("exit"))
							break;
			    		dout.writeUTF(getInf(str));
			    	}
			    	
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	client2.start();
    }
    
    public static String getInf(String str)
    {
    	Date date = new Date();
    	String tmp = date.toString().substring(4,7);
    	String month = "";
		switch(tmp)
		{
			case "Jan":
				month += "01";
				break;
			case "Feb":
				month += "02";
				break;
			case "Mar":
				month += "03";
				break;
			case "Apr":
				month += "04";
				break;
			case "May":
				month += "05";
				break;
			case "Jun":
				month += "06";
				break;
			case "Jul":
				month += "07";
				break;
			case "Aug":
				month += "08";
				break;
			case "Sep":
				month += "09";
				break;
			case "Oct":
				month += "10";
				break;
			case "Nov":
				month += "11";
				break;
			case "Dec":
				month += "12";
				break;
		}
    	if(str.equals("Date"))
    	{
    		String ans = date.toString().substring(8,10);
    		ans += "/" + month;
    		ans += "/" + date.toString().substring(24,28);
    		return ans;
    	}
    	else if(str.equals("Time"))
    	{
    		return date.toString().substring(12,20);
    	}
    	else if(str.equals("Day"))
    		return date.toString().substring(8,11);
    	else if(str.equals("Month"))
			return month;
		else if(str.equals("Year"))
			date.toString().substring(24,28);
    	return "";

    }
}
