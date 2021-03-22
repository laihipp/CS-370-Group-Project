/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
            
            // send list of movies to client
            // util.printMovieTitles();
            
            // get input from client (which movie do they want times for?)
            input = clientSocket.getInputStream();
            // create DataInputStream so that data can be read
            in = new DataInputStream(input);
            
            requestedTitle = in.readUTF();
            
            // get list of times for requested movie
            requestedTimes = new ArrayList<>(util.getRequestedTimes(requestedTitle));
            
            // return list of times to client
            // how do we want to print the list of times?
            
            // when user hits quit on client
            // close datastream
            in.close();
            
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
    private InputStream input = null;
    private DataInputStream in = null;
    private String requestedTitle = "";
    private List<String> requestedTimes;
    private Utility util = new Utility();
}
