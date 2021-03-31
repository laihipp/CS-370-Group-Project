import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    //private BufferedReader input = null;
    private ObjectInputStream dataIn = null;
    private DataOutputStream dataOut = null;
    
    //Constructor, builds a socket object
    public Client(String address, int port) throws IOException
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            
            //take input from user and send to socket
            //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            
            // set up data streams
            dataIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOut = new DataOutputStream(socket.getOutputStream());
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
        String[] arrayIn = { };
        String outLine = "";
        
        while(!outLine.equals("Quit")) // This can be changed to whatever we want to end requests
        {            
            // If user hits "Show Movies" Button
            // Send "Show Movie Titles" to Server
            outLine = "Show Movie Titles";
            
            try {
            dataOut.writeUTF(outLine);
            }
            catch(IOException i)
            {
            System.out.println(i);
            }
            
            // Get list of movie titles from server and send to GUI
            
            // if dataOut = "Quit", break
            
            // Get chosen movie from GUI and send to Server
            outLine = "Up";
            
            try {
            dataOut.writeUTF(outLine);
            }
            catch(IOException i)
            {
            System.out.println(i);
            }
            
            // Get list of movie times from server and send to GUI
            
            // Get next request from GUI
            // test requesting times for second movie
            outLine = "Soul";
            try {
            dataOut.writeUTF(outLine);
            }
            catch(IOException i)
            {
            System.out.println(i);
            }
            //test quit statement
            
            outLine = "Quit";
            try {
            dataOut.writeUTF(outLine);
            }
            catch(IOException i)
            {
            System.out.println(i);
            }
            
            // pause to allow communication to server to finish
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex){
                // TBD
            }
        }
        
        try
        {
            dataIn.close();
            dataOut.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    
    public static void main(String args[]) throws IOException
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new movieGui().setVisible(true);
                
            }
        });
        
        Client client = new Client("127.0.0.1", 5000);
    }
}
