package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author amberlaihipp
 */
public class Server {
    
    public Server(int port) {
        // start server and wait for a connection
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            
            clientSocket = serverSocket.accept();
            System.out.println("Client accepted");
            
            // create DataInputStream so that data can be read
            dataIn = new DataInputStream(clientSocket.getInputStream());
            // crete DataOutputStream so that data can be sent out
            dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
            //BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            
            String inLine = "";
            
            // get initial request from client
            inLine = dataIn.readUTF();
            
            // Communicate until client sends "Quit"
            while (!inLine.equals("Quit")) {                
                // if client sends "Show Movie Titles", send list of movies to client
                if(inLine.equals("Show Movie Titles")) {
                    // create output (list of movies) as an array of strings
                    movieTitlesArray = util.createMovieTitles().toArray(new String[0]);
                    
                    // send list to client
                    dataOut.writeObject(movieTitlesArray);
                    
                // get input from client (which movie do they want times for?)
                requestedTitle = dataIn.readUTF();
                }
                
                // otherwise the list of movies has already been shown and they are requesting times for a different movie
                requestedTitle = inLine;
            
                // get list of times for requested movie as an array of strings
                requestedTimesArray = util.getRequestedTimes(requestedTitle).toArray(new String[0]);
            
                // return list of times to client
                dataOut.writeObject(requestedTimesArray);
                
                // check for next client request?
                inLine = dataIn.readUTF();
            }
            
            // when user hits quit on client
            // close datastreams
            dataIn.close();
            dataOut.close();
            
            // close sockets
            serverSocket.close();
            clientSocket.close();
        }
        catch(IOException exception) {
            System.out.println(exception);
        }
    }
    
    
    public static void main(String args[]) {
        Server server = new Server(5000);   
    } 
    
    private Socket clientSocket = null;
    private ServerSocket serverSocket = null;
    private ObjectOutputStream dataOut = null;
    private DataInputStream dataIn = null;
    private String[] movieTitlesArray;
    private String requestedTitle = "";
    private String[] requestedTimesArray;
    private Utility util = new Utility();
}
