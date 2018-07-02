
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LabExam2{
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		final Socket socket = new Socket("localhost", 6666);
		final DataOutputStream dout  = new DataOutputStream(socket.getOutputStream());
		final DataInputStream input=new DataInputStream(socket.getInputStream());
		final Scanner scan = new Scanner(System.in);
		if(socket!=null)
			System.out.println("Connected");
		
		Thread  thread1 = new Thread(new Runnable(){
			
			public void run()
			{
				while(true){
				
					String massage = "";
					try {
						massage = input.readUTF();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("PcA:" + massage);
					if(massage.equals("bye"))
						break;
				}
			}
		});
		
		thread1.start();
		
		Thread  thread2 = new Thread(new Runnable(){
			
			public void run()
			{
				while(true){
				
					String massage = scan.nextLine();
					try {
						dout.writeUTF(massage);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(massage.equals("bye"))
						break;
				}
			}
		});
		thread2.start();
	}
}
