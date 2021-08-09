import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
	public static void main(String[] args) {
		System.out.println("\nSending a request...");
		try {
			Socket socket = new Socket("127.0.0.1", 0007);
            System.out.println(".\n****Connection Stablished***");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(">>");
            System.out.println("Addition At Client side: " + Integer.valueOf(bufferedReader.readLine()));
            socket.close();
		} 		
		catch(UnknownHostException e) {
            System.out.println("Not find the IP-ADDRESS for: " + e);
		} 		
		catch(IOException e) {
            System.out.println("Not Found data for Socket: " + e);
		}	
	}
}