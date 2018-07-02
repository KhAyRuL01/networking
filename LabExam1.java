
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LabExam1{
	
	public static void main(String[] args) throws IOException{
		
		ServerSocket PcA = new ServerSocket(6666);
		Socket socket = PcA.accept();
		DataOutputStream dout  = new DataOutputStream(socket.getOutputStream());
		DataInputStream input=new DataInputStream(socket.getInputStream());
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Connected");
		
		Thread  thread1 = new Thread(new Runnable(){
			
			public void run()
			{
				while(true){
				
					String massage = "";
					try {
						massage = input.readUTF();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("PcB:" + massage);
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
					if(massage.equals("bye")){
						break;
					}
				}
			}
		});
		thread2.start();
		
		if(!thread1.isAlive() && !thread2.isAlive())
			socket.close();
	}
}
