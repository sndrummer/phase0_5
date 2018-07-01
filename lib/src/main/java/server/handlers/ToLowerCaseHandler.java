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
public class ToLowerCaseHandler extends HandlerReadWrite implements HttpHandler
{
    
    @Override
    public void handle(HttpExchange httpExchange)
    {
        try
        {
            System.out.println("Handling  /lowercase");
        
            InputStream reqBody = httpExchange.getRequestBody();
            String reqData = readString(reqBody);
        
            ObjectDecoderEncoder encoder = new ObjectDecoderEncoder();
            String lowerCaseRequest = new String();
            try
            {
                lowerCaseRequest = encoder.getRequest(reqData);
                String lowerCaseResult = StringProcessor.getInstance().toLowercase(lowerCaseRequest);
                //outputResBody(encoder.toJson(lowerCaseResult), httpExchange);
                JSON_StringObj result = new JSON_StringObj(lowerCaseResult);
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
