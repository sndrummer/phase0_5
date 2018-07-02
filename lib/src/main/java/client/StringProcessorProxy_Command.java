package client;

import shared.Command;
import shared.GenericCommand;
import shared.IStringProcessor;
import shared.Result;

/**
 * @author Samuel Nuttall
 */
public class StringProcessorProxy_Command implements IStringProcessor
{
    private static final StringProcessorProxy_Command ourInstance = new StringProcessorProxy_Command();
    public static StringProcessorProxy_Command getInstance()
    {
        return ourInstance;
    }
    private static ClientCommunicator comm = ClientCommunicator.getInstance();
    
    private static final String receiverClass = "server.StringProcessor";
    private static final String stringClass = "java.lang.String";
    private static final String lowercaseMethod = "toLowercase";
    private static final String trimMethod = "trim";
    private static final String parseIntMethod = "parseInteger";
    
    private StringProcessorProxy_Command()
    {
    }
    
    /*
    * server.createContext("/parseInt", new ParseIntHandler());
        server.createContext("/trim", new TrimHandler());
        server.createContext("/toLower", new ToLowerCaseHandler());
        server.createContext("/excCmd", new ExecCmdHandler());
        
        
         private Result getCommandResult(String urlPath, Command cmd)
    {
        String json = objEncoder.toJson(cmd);
        return getResult(urlPath, json);
    }
    * */
    @Override
    public String toLowercase(String input)
    {
    
    
        Command cmd = new GenericCommand( receiverClass,
                                          lowercaseMethod,
                                          new String[]{ stringClass },
                                          new Object[]{input});
        Result result = comm.getCommandResult(cmd);
        return result.getString();
        
    }
    
    @Override
    public String trim(String input)
    {
    
        Command cmd = new GenericCommand( receiverClass,
                                          trimMethod,
                                          new String[]{ stringClass },
                                          new Object[]{input});
    
        Result result = comm.getCommandResult(cmd);
        return result.getString();
    
    }
    
    @Override
    public String parseInteger(String input)
    {
    
        Command cmd = new GenericCommand( receiverClass,
                                          parseIntMethod,
                                          new String[]{ stringClass },
                                          new Object[]{input});
    
    
        Result result = comm.getCommandResult(cmd);
        return result.getString();
    }
}
