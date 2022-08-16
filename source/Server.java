package source;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws FileNotFoundException{
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        final String pre = ">> ";

        // File log_file = new File("F:/HNBGU Academics/VI Semester/Minor Project/Source Code/chat_logs/log.txt");
        // final PrintWriter log_writer = new PrintWriter(log_file);
        PrintWriter log_writer = new PrintWriter(new FileOutputStream(new File("F:/HNBGU Academics/VI Semester/Minor Project/Source Code/chat_logs/log.txt"), true));

        try {
            serverSocket = new ServerSocket(0007);
            System.out.print("\n" + pre + "Waiting for request...\n" + pre);
            clientSocket = serverSocket.accept();
            System.out.println("\n" + pre + "[Connection Stablished Successfully]\n" + pre);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

            Thread sender= new Thread(new Runnable() {
                String msg = ""; // variable that will contains the data writter by the user
                @Override        // annotation to override the run method
                public void run() {
                    while(!msg.equalsIgnoreCase("exit")){
                        System.out.print(pre);
                        msg = sc.nextLine(); // reads data from user's keybord

                        if(!msg.equalsIgnoreCase("exit")){
                            log_writer.append("[Server]:\t" + msg + "\n");
                            log_writer.flush();
                            out.println(msg);    // write data stored in msg in the clientSocket
                            out.flush();         // forces the sending of the data
                        }
                        if(!msg.equalsIgnoreCase("exit"))
                            System.out.println(pre + "[Message Sent]");
                    }
                    log_writer.close();
                    System.out.println(pre);
                    System.out.println(pre + "Successfully Disconnected!");
                    System.exit(1);
                }
            });
            sender.start();

            Thread receive= new Thread(new Runnable() {
                String msg = "";
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(!msg.equalsIgnoreCase("exit")){
                            System.out.println("Client: " + msg);
                            System.out.print(pre);
                            msg = in.readLine();
                        }
                        System.out.println("\n" + pre + "Client Disconnected!");
                        System.exit(1);
                    }
                    catch(IOException exc) {
                        System.out.println("Opps! Not Found data for Socket: " + exc);
                    }
                }
            });
            receive.start();
        }
        catch(Exception exc) {
            System.out.println("Opps! Could Not Able To Stablish The Connection: " + exc);
        }   
    }
}