package server.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;


import javax.net.ssl.HttpsURLConnection;

import shared.HandlerReadWrite;
import shared.ObjectDecoder;

/**
 * @author Samuel Nuttall
 */
public class TrimHandler extends HandlerReadWrite implements HttpHandler
{
    
    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {
        try
        {
            System.out.println("Handling  /trim");
            
            InputStream reqBody = httpExchange.getRequestBody();
            String reqData = readString(reqBody);
    
            ObjectDecoder objectDecoder = new ObjectDecoder();
            String trimRequest = new String();
            try
            {
                trimRequest = objectDecoder.getTrimRequest(reqData);
                trimRequest = objectDecoder.getLoadRequest(reqData);
            }
            catch (Exception ex)
            {
                outputResBody("Bad JSON formatting", httpExchange);
            }
            
        }
        catch (IOException e)
        {
            //HttpExchange.sendResponseHeaders(HttpsURLConnection.HTTP_SERVER_ERROR, 0);
            // e.printStackTrace();
        }
    }
    
}
