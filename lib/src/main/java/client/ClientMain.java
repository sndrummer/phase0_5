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
        String result = proxy.parseInteger("12");
        System.out.println("Here is the result: " + result + ".");
        String result2 = proxy.trim("poooo        ");
        System.out.println("Here is the result: " + result2 + ".");
        String result3 = proxy.toLowercase("BUTTT");
        System.out.println("Here is the result: " + result3 + ".");
    }
}
