import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    //private BufferedReader input = null;
    private DataOutputStream out = null;
    
    //Constructor, builds a socket object
    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            
            //take input from user and send to socket
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        
        // String that reads message from input
        String line = "";
        
        while(!line.equals("Over")) // This can be changed to whatever we want to end requests
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}