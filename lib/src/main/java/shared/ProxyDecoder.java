package shared;



import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.regex.Pattern;

/**
 *
 */
public class ProxyDecoder
{
    private Gson gson = new Gson();
    private Reader reader;
    
    
    public String toJson(Object obj)
    {
        String json = gson.toJson(obj);
        return json;
    }
    
  
    public String toResult(String respData, String result) throws Exception
    {
        String myResult = result;
        try
        {
            myResult = gson.fromJson(respData, result.getClass());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return myResult;
    }
    
  
}
