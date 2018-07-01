package server;

/**
 * @author Samuel Nuttall
 */
public class JSON_StringObj
{
    public JSON_StringObj(String _string){
        this.string = _string;
    }
    private String string;
    
    public String getString()
    {
        return string;
    }
    
    public void setString(String string)
    {
        this.string = string;
    }
    
    @Override
    public String toString()
    {
        return "{" +
                "\"string\": \"" + string + "\"" +
                "}";
    }
}
