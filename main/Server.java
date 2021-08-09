import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(0007);
            System.out.println("Waiting for request....");
            Socket socket = serverSocket.accept();
            System.out.println("Request accepted");
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input the data at server : ");
            printStream.print(bufferedReader.readLine());
            socket.close();
            serverSocket.close();
        }		
        catch (Exception exc) {			
            System.out.println("Not Found data for Socket: " + exc);
        }
    }
}
