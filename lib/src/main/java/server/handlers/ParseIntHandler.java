package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import shared.Result;
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
                Result result = new Result(parseIntResult);
                System.out.println("RESULT: " + parseIntResult);
                outputResBody(result, httpExchange);
            }
            catch (Exception ex)
            {
    
                outputResBody(new Result(ex.toString()), httpExchange);
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