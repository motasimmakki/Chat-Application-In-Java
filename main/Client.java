import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        final Socket clientSocket; // socket used by client to send and recieve data from server.
        final BufferedReader in;   // object to read data from socket.
        final PrintWriter out;     // object to write data into socket.
        final Scanner sc = new Scanner(System.in); // object to read data from user's keybord.
        final String pre = ">> ";

        System.out.println("\n" + pre + "Sending a request...\n" + pre);
        try {
            clientSocket = new Socket("127.0.0.1", 0007);
            System.out.println(pre + "[Connection Stablished Successfully]\n" + pre);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread sender = new Thread(new Runnable() {
                String msg = "";
                @Override
                public void run() {
                    while(!msg.equalsIgnoreCase("exit")){
                    	System.out.print(pre);
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                        System.out.println(pre + "[Message Sent]");
                    }
                    System.out.println(pre);
                    System.out.println(pre + "Successfully Disconnected!");
                    // out.close();
                    // clientSocket.close();
                    // serverSocket.close();
                    System.exit(1);
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg = "";
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(!msg.equalsIgnoreCase("exit")){
                            System.out.println("Server : "+ msg);
                            System.out.print(pre);
                            msg = in.readLine();
                        }
                        System.out.print("\n" + pre + "Server Disconnected!");
                        // out.close();
                        // clientSocket.close();
                        System.exit(1);
                    }
                    catch(IOException exc) {
						System.out.println("Opps! Not Found data for Socket: " + exc);
					}
                }
            });
            receiver .start();
	    }
	    catch(Exception exc) {
            System.out.println("Opps! Could Not Able To Stablish The Connection: " + exc);
		}	
    }
}