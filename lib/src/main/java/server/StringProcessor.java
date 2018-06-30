package server;

import shared.IStringProcessor;

/**
 * @author Samuel Nuttall
 */
public class StringProcessor implements IStringProcessor
{
    private static final StringProcessor _instance = new StringProcessor();
    
    public static StringProcessor getInstance()
    {
        return _instance;
    }
    
    private StringProcessor()
    {
    }
    
    @Override
    public String toLowercase(String s)
    {
        return s.toLowerCase();
    }
    
    @Override
    public String trim(String s)
    {
        return s.trim();
    }
    
    @Override
    public String parseInteger(String s)
    {
        Integer i = Integer.parseInt(s);
        return i.toString();
    }
}
