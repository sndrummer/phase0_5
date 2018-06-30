package shared;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.HttpsURLConnection;


/**
 *
 */
public class HandlerReadWrite
{
    
    public void writeString(String str, OutputStream os) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
        
    }
    
    public String readString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0)
        {
            sb.append(buf, 0, len);
        }
        //ProxyDecoder decoder = new ProxyDecoder();
        //decoder.toJson(sb.toString());
        return sb.toString();
    }
    
    public void outputResBody(String result, HttpExchange exchange) throws IOException
    {
        
            ProxyDecoder dec = new ProxyDecoder();
            String respData = dec.toJson(result);
            if (respData.contains("isCleared"))
            {
                sendWriteClose(exchange, result.toString());
            }
            else
            {
                sendWriteClose(exchange, respData);
            }
        
    }
    
    
    public void sendWriteClose(HttpExchange exchange, String respData) throws IOException
    {
        exchange.sendResponseHeaders(HttpsURLConnection.HTTP_OK, 0);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respData, respBody);
        respBody.close();
    }
}
