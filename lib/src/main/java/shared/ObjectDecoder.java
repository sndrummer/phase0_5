package shared;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import shared.Requests.LoadRequest;
import shared.Requests.LoginRequest;
import shared.Requests.RegisterRequest;

public class ObjectDecoder
{
    private Gson gson = new Gson();
    private Reader reader;
 
    public String toJson(Object obj)
    {
        String json = gson.toJson(obj);
        return json;
    }

    public LocationData getLocations() throws FileNotFoundException
    {
        reader = new FileReader(locationsString);
        LocationData locData = gson.fromJson(reader, LocationData.class);
        return locData;
    }
    public List<String> getNamesFromJson(File file) throws FileNotFoundException
    {
        List<String> names;
        reader = new FileReader(file);
        nameData = gson.fromJson(reader, NameData.class);
        names = nameData.toStringList();
        return names;
    }
    
    public RegisterRequest getRegisterRequest(String reqData) throws FileNotFoundException
    {
        RegisterRequest registerRequest = gson.fromJson(reqData, RegisterRequest.class);
        return registerRequest;
    }
    public LoginRequest getLoginRequest(String reqData) throws FileNotFoundException
    {
        LoginRequest loginRequest = gson.fromJson(reqData, LoginRequest.class);
        return loginRequest;
    }
    
    public LoadRequest getLoadRequest(String reqData)
    {
        LoadRequest loadRequest = new LoadRequest();
        JsonReader reader = new JsonReader(new StringReader(reqData));
        reader.setLenient(true);
        loadRequest = gson.fromJson(reader, LoadRequest.class);
        return loadRequest;
    }
    public String getTrimRequest(String reqData)
    {
        
        return null;
    }
    public String getToLowerCaseRequest(String reqData)
    {
        
        return null;
    }
    public String getParseIntRequest(String reqData)
    {
        
        return null;
    }
    
}
