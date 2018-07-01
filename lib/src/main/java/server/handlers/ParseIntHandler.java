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
public class ParseIntHandler extends HandlerReadWrite implements HttpHandler
{
    
    @Override
    public void handle(HttpExchange httpExchange)
    {
        try
        {
            System.out.println("Handling /parseInt");
        
            InputStream reqBody = httpExchange.getRequestBody();
            String reqData = readString(reqBody);
            System.out.println("request Body: " + reqData);
            ObjectDecoderEncoder encoder = new ObjectDecoderEncoder();
            String parseIntRequest = new String();
            try
            {
                parseIntRequest = encoder.getRequest(reqData);
                String parseIntResult = StringProcessor.getInstance().parseInteger(parseIntRequest);
                JSON_StringObj result = new JSON_StringObj(parseIntResult);
                System.out.println("RESULT: " + parseIntResult);
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