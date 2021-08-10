package main;
import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		String pre = ">> ";
		System.out.println("\n" + pre + "Sending a request...\n" + pre);
		try {
			Socket socket = new Socket("127.0.0.1", 0007);
            System.out.println(pre + "[Connection Stablished Successfully]\n" + pre);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			PrintStream printStream = new PrintStream(socket.getOutputStream());
            System.out.print(pre + "Status: Sending\n");
			String message = "";
			while(true){
				System.out.print(pre);
				message = bufferedReader.readLine();
                printStream.println(message);
				if(message.equalsIgnoreCase("exit")){
					System.out.println(pre);
					System.out.println(pre + "Disconnected!");
					socket.close();
					System.exit(1);
				}
				System.out.println(pre + "[Message Sent]");
			}
		} 		
		catch(UnknownHostException exc) {
            System.out.println("Not find the IP-ADDRESS for: " + exc);
		} 		
		catch(IOException exc) {
            System.out.println("Not Found data for Socket: " + exc);
		}	
	}
}