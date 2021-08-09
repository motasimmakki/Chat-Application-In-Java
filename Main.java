import java.net.InetAddress;
import java.net.UnknownHostException;

class Main{
    public static void main(String[] args) {
        String host_name =  "";
        try{
            InetAddress ip = InetAddress.getByName("MOTASIM-PC");
            System.out.println("System Name: " + ip.getHostName());
            System.out.println("System IP-Address: " + ip.getHostAddress());
        }catch(UnknownHostException exc){
            System.out.println("Not found the IP-ADDRESS for: " + exc);
        }
    }
}