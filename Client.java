import java.net.*;
import java.io.*;

//**********************
// TODO
// Change currentMoviesTxt to a flag variable or getter call @line56
// Change findShowtimesButton to a flag variable or getter call @line72
// Change movie if needed to a variable containing movie selected @line74
//
// These flag variables or getter calls must be made in movieGUI. I didn't
// want to touch code that was not mine but I have what I think are quick fixes
// to make this work, if you want me to make these changes let me know and I am
// happy to do them. 
//**********************

public class Client
{
    private Socket socket = null;
    //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    //private BufferedReader input = null;
    //private ObjectInputStream dataIn = null;
    private BufferedReader dataIn = null; //need this as BufferedReader for readLine()
    private DataOutputStream dataOut = null;
    String[] movieTitles = { };
    String[] movieTimes = { };
    movieGui movieGUIObj = new movieGui();
    
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
            dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            // currentMoviesTxt needs to be accessible via getter or made public
            if (movieGUIObj.currentMoviesTxt == true) // Change currentMoviesTxt to the flag variable or getter call
            {
                outLine = "Show Movie Titles";
                try 
                {
                    dataOut.writeUTF(outLine);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            
            // Get chosen movie from GUI and send to Server for server times
            // When the flag is set, we will request the info
            // from the server
            if (movieGUIObj.findShowtimesButton == false) // Change findShowtimesButton to a flag variable or getter call @line
            {
                outLine = movieGUIObj.movie; //Need access to movie string in movieGui.java
                try 
                {
                    dataOut.writeUTF(outLine);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }               
            }

            
            // Get list of movie times from server and send to GUI
            

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
    
    public String[] getMovieTitles()
    {
        return movieTitles;
    }
    
    // Read in the movie titles into an array one at a time, may
    // need to be adjusted if we need the movie titles to be sent
    // all at once as they are now
    public void setMovieTitles()
    {
        
        for (int j = 1; j > 3; j++) // currently hardcoding number of recieved titles
        {
            try
            {
                movieTitles[j] = dataIn.readLine();
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
    }
    
    public String[] getMovieTimes()
    {
        return movieTimes;
    }
    
    public void setMovieTimes()
    {
         for (int j = 1; j > 3; j++) // currently hardcoding number of recieved times
        {
            try
            {
                movieTimes[j] = dataIn.readLine();
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }       
    }
}