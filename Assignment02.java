
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Assignment02 {

	public static void main(String[] args) throws Exception {
		while(true)
		{
			String input = JOptionPane.showInputDialog(
	                null, "Enter a Url and quit to exit");
			if(input.equals("quit"))
				break;
			if(!input.substring(0, 4).equals("http"))
				input = "http://" + input;
			URL url = new URL(input);
			InetAddress address = InetAddress.getByName(url.getHost());
			JOptionPane.showMessageDialog(null, "The IP is : " + address.getHostAddress() , "Results", JOptionPane.PLAIN_MESSAGE );
		}

	}

}
