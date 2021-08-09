import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
	public static void main(String[] args) {
		System.out.println("Sending a request...");
		try {
			Socket socket = new Socket("127.0.0.1", 0007);
            System.out.println("Connected successfully...");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Response from server...");
            System.out.println("Client side: " + bufferedReader.readLine());
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