import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Server {
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(0007);
            System.out.println("\nWaiting for request....");
            Socket socket = serverSocket.accept();
            System.out.println(".\n****Connection Stablished***");
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(">>");
            System.out.println("Enter First Number: ");
            Scanner scanner = new Scanner(System.in);
            int num1 = scanner.nextInt();
            System.out.println("Enter First Number: ");
            int num2 = scanner.nextInt();
            printStream.print(num1+num2);
            socket.close();
            serverSocket.close();
        }		
        catch (Exception exc) {			
            System.out.println("Not Found data for Socket: " + exc);
        }
    }
}
