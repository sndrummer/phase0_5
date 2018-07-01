package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;


import server.JSON_StringObj;
import server.StringProcessor;
import shared.HandlerReadWrite;
import shared.ObjectDecoderEncoder;

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
            
            ObjectDecoderEncoder encoder = new ObjectDecoderEncoder();
            String trimRequest = new String();
            try
            {
                trimRequest = encoder.getRequest(reqData);
                String trimResult = StringProcessor.getInstance().trim(trimRequest);
                //outputResBody(encoder.toJson(trimResult), httpExchange);
    
                JSON_StringObj result = new JSON_StringObj(trimResult);
                System.out.println("RESULT: " + result);
                outputResBody(encoder.toJson(result), httpExchange);
            }
            catch (Exception ex)
            {
                outputResBody(ex.toString(), httpExchange);
                //ex.printStackTrace();
            }
            
        }
        catch (IOException e)
        {
            //HttpExchange.sendResponseHeaders(HttpsURLConnection.HTTP_SERVER_ERROR, 0);
             e.printStackTrace();
        }
    }
    
}
