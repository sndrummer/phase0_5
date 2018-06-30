package shared;

/**
 * @author Samuel Nuttall
 */
public interface IStringProcessor
{
    String toLowercase(String s);
    String trim(String s);
    String parseInteger(String s);
}
