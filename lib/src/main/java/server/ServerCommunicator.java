package server;

/**
 * @author Samuel Nuttall
 */

import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.ExecCmdHandler;
import server.handlers.FileHandler;
import server.handlers.ParseIntHandler;
import server.handlers.ToLowerCaseHandler;
import server.handlers.TrimHandler;


public class ServerCommunicator
{
    private static final int MAX_WAITING_CONNECTIONS = 12;
    
    private HttpServer server;
    
    private void run(String portNumber)
    {
        try
        {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        
        server.setExecutor(null); // set the default executor
        
        System.out.println("Creating contexts");
        server.createContext("/parseInt", new ParseIntHandler());
        server.createContext("/trim", new TrimHandler());
        server.createContext("/toLower", new ToLowerCaseHandler());
        server.createContext("/excCmd", new ExecCmdHandler());
        //Default
        server.createContext("/", new FileHandler());
        
        
        System.out.println("Starting server");
        server.start();
    }
    
    public static void main(String[] args)
    {
        String portNumber = args[0];
        
        File htmlF = new File("HTML");
        if(!htmlF.exists())
        {
            System.out.println("The HTML folder is missing!");
            return;
        }
        
        System.out.println("Server started on port: " + args[0]);
        new ServerCommunicator().run(portNumber);
        
    }
}
