package shared;

import java.lang.reflect.Method;

/**
 * @author Samuel Nuttall
 */
public class GenericCommand implements Command
{
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;
    
    public GenericCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }
    
    @Override
    public Result execute()
    {
        Result result = new Result();
        try {
            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName, _paramTypes);
            String resultString = (String) method.invoke(null, _paramValues);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
