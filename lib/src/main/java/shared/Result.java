package shared;

/**
 * @author Samuel Nuttall
 */
public class Result
{
    public Result(String _data){
        this.string = _data;
    }
    public Result(){
    
    }
    private String string;
    private String errMessage; //if there is an error
    
    public String getString()
    {
        return string;
    }
    
    public void setString(String string)
    {
        this.string = string;
    }
    
    public String getErrMessage()
    {
        return errMessage;
    }
    
    public void setErrMessage(String errMessage)
    {
        this.errMessage = errMessage;
    }
    
    @Override
    public String toString()
    {
        return "{" +
                "string:" + string + "" +
                "}";
    }
}
