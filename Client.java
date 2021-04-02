import java.net.*;
import java.io.*;
import java.util.Arrays;

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
    public static ObjectInputStream dataIn = null; //need this as BufferedReader for readLine()
    public static DataOutputStream dataOut = null;
    public static String[] movieTitles = {"Test"};
    public static String[] movieTimes = {"Test"};
    //public static String selectedMovie = "";
    //movieGui movieGUIObj = new movieGui();
    
    
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
            dataIn = new ObjectInputStream((socket.getInputStream()));
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
        
        
        String outLine = "";
        
        while(!outLine.equals("Quit")) // This can be changed to whatever we want to end requests
        {            
            // If user hits "Show Movies" Button
            // Send "Show Movie Titles" to Server
            // currentMoviesTxt needs to be accessible via getter or made public
            if (movieGui.RETRIEVEMOVIELIST == true) // Change currentMoviesTxt to the flag variable or getter call
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
                
                try
                {
                    movieTitles = (String[]) dataIn.readObject();
                    //System.out.println(Arrays.toString(movieTitles));
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                catch(ClassNotFoundException o)
                {
                    System.out.println();
                } 
            }
            
            //Get the movieTitles from the server
            //movieTitles[0] = "Test2";
            
            
            //movieGUIObj.setMovieList(dataIn);
            
            // Get chosen movie from GUI and send to Server for server times
            // When the flag is set, we will request the info
            // from the server
            //if (movieGui.RETRIEVEMOVIETIMES == true) // Change findShowtimesButton to a flag variable or getter call @line
            /*{
                outLine = movieGui.movie; //Need access to movie string in movieGui.java
                //outLine = "Up";
                try 
                {
                    dataOut.writeUTF(outLine);
                    System.out.println(outLine);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }  
                
                //Get the movieTimes from the server
                try
                {
                    movieTimes = (String[]) dataIn.readObject();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                catch(ClassNotFoundException o)
                {
                    System.out.println();
                }  
            }*/
            
            
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
    
    public static String[] getMovieTimes(String selectedMovie)
    {
        String movie = selectedMovie; //Need access to movie string in movieGui.java
                //outLine = "Up";
                try 
                {
                    dataOut.writeUTF(movie);
                    System.out.println(movie);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }  
                
                //Get the movieTimes from the server
                try
                {
                    movieTimes = (String[]) dataIn.readObject();
                    return movieTimes;
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                catch(ClassNotFoundException o)
                {
                    System.out.println();
                }
        return null;
    }
    
    public static void main(String args[]) throws IOException
    {
        
        movieGui movieGUIObj = new movieGui();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                movieGUIObj.setVisible(true);
                
            }
        });
        
        Client client = new Client("127.0.0.1", 5000);
    }
}
