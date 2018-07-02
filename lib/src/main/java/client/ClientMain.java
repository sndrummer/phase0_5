package client;

/**
 * @author Samuel Nuttall
 */
public class ClientMain
{
    public static void main(String[] args)
    {
        //have arguments be the urlPath, and the second is a string input
        StringProcessorProxy_NoCommand proxy = StringProcessorProxy_NoCommand.getInstance();
        proxy.trim("cheese    ");
        proxy.toLowercase("PPOOPP");
        proxy.parseInteger("000000");
        proxy.parseInteger("llll");
      
       
       StringProcessorProxy_Command command = StringProcessorProxy_Command.getInstance();
       command.trim("cheese    ");
       command.toLowercase("PPOOPP");
       command.parseInteger("000000");
        command.parseInteger("llll");
       
    }
}
