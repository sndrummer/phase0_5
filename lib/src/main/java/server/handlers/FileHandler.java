package server.handlers;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;

import javax.net.ssl.HttpsURLConnection;

//1. Retrieve the request URL from the HttpExchange
//     2. Translate the request URL path to a physical file path on your server
//    3. Open the requested file, and return its contents in the HTTP
//    response body


public class FileHandler implements HttpHandler
{
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            URI command = exchange.getRequestURI();
            String commandString = command.toString();
            String path = new String();
            if (commandString.contains("css"))
            {
                path = "HTML" + File.separator + "css" + File.separator + "style.css";
                
            }
            else if (commandString.contains("png"))
            {
                path = "HTML" + File.separator + "img" + File.separator + "background.png";
            }
            else if (commandString.contains("404"))
            {
                path = "HTML" + File.separator + "404.html";
            }
            else
            {
                path = "HTML" + File.separator + "index.html";
            }
            System.out.println("Here is the path" + path);
            File file = new File(path);
            if (!file.exists())
            {
                System.out.println(path + " is missing!!");
            }
            else
            {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file.length());
                OutputStream outputStream = exchange.getResponseBody();
                Files.copy(file.toPath(), outputStream);
                outputStream.close();
                exchange.close();
            }
        }
        catch (IOException e)
        {
            exchange.sendResponseHeaders(HttpsURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
        }
    }
}