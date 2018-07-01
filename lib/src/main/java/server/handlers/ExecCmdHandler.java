package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import server.StringProcessor;
import shared.HandlerReadWrite;
import shared.ObjectDecoderEncoder;

/**
 * @author Samuel Nuttall
 */
public class ExecCmdHandler extends HandlerReadWrite implements HttpHandler
{
    //TODO probably a different implementation for this one !!!!!!!!!!!!!!!!
    @Override
    public void handle(HttpExchange httpExchange)
    {
        try
        {
            System.out.println("Handling /excCmd");
        
            InputStream reqBody = httpExchange.getRequestBody();
            String reqData = readString(reqBody);
        
            ObjectDecoderEncoder encoder = new ObjectDecoderEncoder();
            String parseIntRequest = new String();
            try
            {
            
            }
            catch (Exception ex)
            {
                outputResBody(ex.toString(), httpExchange);
            }
        
        }
        catch (IOException e)
        {
            //HttpExchange.sendResponseHeaders(HttpsURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
        }
    }
}