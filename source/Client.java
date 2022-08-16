package source;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import source.gagawa.trunk.gagawa.src.com.hp.gagawa.java.elements.*;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        final Socket clientSocket; // socket used by client to send and recieve data from server.
        final BufferedReader in;   // object to read data from socket.
        final PrintWriter out;     // object to write data into socket.
        final Scanner sc = new Scanner(System.in); // object to read data from user's keybord.
        final String pre = ">> ";

        File client_content = new File("F:/HNBGU Academics/VI Semester/Minor Project/Source Code/chat_logs/Client.html");
        final String HTMl_START = "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport'content='width=device-width, initial-scale=1.0'><meta http-equiv='X-UA-Compatible' content='ie=edge'><title>Client Test</title></head><body>",
        HTML_END = "</body></html>";
        
        final PrintWriter writer = new PrintWriter(client_content);
        PrintWriter log_writer = new PrintWriter(new FileOutputStream(new File("F:/HNBGU Academics/VI Semester/Minor Project/Source Code/chat_logs/log.txt"), true)); 

        final ClientData cd = new ClientData();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log_writer.append(dtf.format(now) + "\n\n");
        
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

                        // feat: Text onto Web.
                        if(!msg.equalsIgnoreCase("exit")){
                            log_writer.append("[Client]:\t" + msg + "\n");
                            log_writer.flush();
                            cd.setData(cd.getData() + new Div().appendText(msg));
                            writer.println(HTMl_START + cd.getData() + HTML_END);
                            writer.flush();
                        }
                        
                        out.flush();
						if(!msg.equalsIgnoreCase("exit"))
                            System.out.println(pre + "[Message Sent]");
                    }
                    writer.close();
                    log_writer.close();
                    System.out.println(pre);
                    System.out.println(pre + "Successfully Disconnected!");
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
                        System.exit(1);
                    }
                    catch(IOException exc) {
						System.out.println("Opps! Not Found data for Socket: " + exc);
					}
                }
            });
            receiver.start();
	    }
	    catch(Exception exc) {
            System.out.println("Opps! Could Not Able To Stablish The Connection: " + exc);
		}	
    }
}