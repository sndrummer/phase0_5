package client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import shared.Command;
import shared.HandlerReadWrite;
import shared.ProxyDecoder;
import shared.Result;

/**
 * @author Samuel Nuttall
 */
public class ClientCommunicator
{
    private static final ClientCommunicator _instance = new ClientCommunicator();
    private static final String SERVER_HOST = "localhost";
    private static final String SERVER_PORT = "8080";
    private static String urlString = "http://" + SERVER_HOST + ":" + SERVER_PORT;
    private static final ProxyDecoder objEncoder = new ProxyDecoder();
    private static final HandlerReadWrite readWrite = new HandlerReadWrite();
    private ClientCommunicator(){}
    
    
    public static ClientCommunicator getInstance()
    {
        return _instance;
    }
    
    public Result getResult(String urlPath, String input)
    {
    
        Result result = new Result();
        try
        {
            result = httpPostRequest(input, urlString + urlPath);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    private Result httpPostRequest(String request, String urlString) throws Exception
    {
        Result result = new Result(request);
        URL url = new URL(urlString);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);  // There is a request body
        result = sendRequest(http, result);
        return result;
    }
    
    private Result sendRequest(HttpURLConnection http, Result request) throws Exception
    {
        System.out.println("This is the request ==> " + objEncoder.toJson(request));
        http.addRequestProperty("Content-Type", "application/json");
        String reqData = objEncoder.toJson(request);
        http.setConnectTimeout(5000);
        OutputStream reqBody = http.getOutputStream();
        readWrite.writeString(reqData, reqBody);
        reqBody.close();
        return readResponse(http);
    }
    
    private Result readResponse(HttpURLConnection http) throws Exception
    {
        Result result = new Result();
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            
            InputStream respBody = http.getInputStream();
            String respData = readWrite.readString(respBody);
            System.out.println("RESPDATA " + respData);
            result = objEncoder.toResult(respData, result);
        } else
        {
            System.out.println("ERROR: " + http.getResponseMessage());
        }
        return result;
        
    }
    
    //String urlPath,
    public Result getCommandResult(String urlPath, Command cmd)
    {
        String json = objEncoder.toJson(cmd);
        
        //Send to the Server as JSON object
        return getResult(urlPath, json);
    }
  
    
    
    
}
