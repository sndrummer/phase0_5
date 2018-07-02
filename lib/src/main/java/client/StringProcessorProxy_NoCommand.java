package client;

import shared.IStringProcessor;
import shared.ProxyDecoder;
import shared.Result;

/**
 * @author Samuel Nuttall
 */
public class StringProcessorProxy_NoCommand implements IStringProcessor
{
   
    private static final StringProcessorProxy_NoCommand ourInstance = new StringProcessorProxy_NoCommand();
    private static ClientCommunicator comm = ClientCommunicator.getInstance();
    private static ProxyDecoder objEncoder = new ProxyDecoder();
    public static StringProcessorProxy_NoCommand getInstance()
    {
        return ourInstance;
    }
    
    private StringProcessorProxy_NoCommand()
    {
    }
    
    /*
    *  server.createContext("/parseInt", new ParseIntHandler());
        server.createContext("/trim", new TrimHandler());
        server.createContext("/toLower", new ToLowerCaseHandler());
        server.createContext("/excCmd", new ExecCmdHandler());*/
    @Override
    public String toLowercase(String input)
    {
        //String input = objEncoder.toJson(s);
        Result result = comm.getResult("/toLower",input);
        return result.getString();
    }
    
    @Override
    public String trim(String input)
    {
        //String input = objEncoder.toJson(s);
        Result result = comm.getResult("/trim",input);
        return result.getString();
    }
    
    @Override
    public String parseInteger(String input)
    {
        //String input = objEncoder.toJson(s);
        Result result = comm.getResult("/parseInt",input);
        return result.getString();
    }
}
