package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import server.StringProcessor;
import shared.Command;
import shared.HandlerReadWrite;
import shared.ObjectDecoderEncoder;
import shared.Result;

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
           // System.out.println("EXECUTE COMMAND " + reqData);
            ObjectDecoderEncoder encoder = new ObjectDecoderEncoder();
            //String excCmdRequest = new String();
            try
            {
                Command cmd = encoder.getCommand(reqData);
                //String trimResult = StringProcessor.getInstance().trim(excCmdRequest);
                //outputResBody(encoder.toJson(trimResult), httpExchange);
        
                Result result = cmd.execute();
                outputResBody(result, httpExchange);
            }
            catch (Exception ex)
            {
                outputResBody(new Result(ex.toString()), httpExchange);
                ex.printStackTrace();
            }
        
        }
        catch (IOException e)
        {
            //HttpExchange.sendResponseHeaders(HttpsURLConnection.HTTP_SERVER_ERROR, 0);
            //e.printStackTrace();
        }
    }
}