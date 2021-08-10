package main;
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
        String pre = ">> ";
        try {
            ServerSocket serverSocket = new ServerSocket(0007);
            System.out.print("\n" + pre + "Waiting for request...\n" + pre);
            Socket socket = serverSocket.accept();
            System.out.println("\n" + pre + "[Connection Stablished Successfully]\n" + pre);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print(pre + "Status: Receiving\n");
            String message = "";
            while(true){
                System.out.print(pre);
                message = bufferedReader.readLine();
                if(message.equalsIgnoreCase("exit")){
                    System.out.println("\n" + pre + "Connection Lost!");
                    socket.close();
                    serverSocket.close();
                    System.exit(1);
                }
                System.out.println(message);
            }
        }		
        catch (IOException exc) {			
            System.out.println("Not Found data for Socket: " + exc);
        }
    }
}
