package shared;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.io.StringReader;

public class ObjectDecoderEncoder
{
    private Gson gson = new Gson();
    private Reader reader;
 
    public String toJson(Object obj)
    {
        String json = gson.toJson(obj);
        return json;
    }
    
    
    public String getRequest(String reqData)
    {
        JsonReader reader = new JsonReader(new StringReader(reqData));
        Result request = gson.fromJson(reader, Result.class);
        System.out.println("HERE IS THE STRING: " + request.getString());
        
        
        //System.out.println("HERE IS THE JSON STRING: " + toJson(request));
        return request.getString();
    }
    
    
}
