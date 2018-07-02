package shared;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import server.StringProcessor;

/**
 * @author Samuel Nuttall
 */
public class GenericCommand implements Command
{
    private String _className;
    private String _methodName;
    private String[] _paramTypes;
    private Object[] _paramValues;
    
    public GenericCommand(String className, String methodName,
                          String[] paramTypes, Object[] paramValues) {
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
    
            //EQUIVALENT ==> _instance = StringProcessor.getInstance()
            Method getInstanceMethod = receiver.getMethod("getInstance", null);
            Object _instance = getInstanceMethod.invoke(null, null);
    
            // Ger classParamTypes (since it doesn't seem to work with  Class<?>[]) -->
            Class<?>[] classParamTypes = new Class<?>[_paramTypes.length];
            for(int i = 0; i < _paramTypes.length; ++i)
            {
                classParamTypes[i] = Class.forName(_paramTypes[i]);
            }
    
           
            Method method = receiver.getMethod(_methodName, classParamTypes);
            Object objResult = method.invoke(_instance, _paramValues);
            String resultString = (String) objResult;
            
            result.setString(resultString);
        }
        catch (Exception ex)
        {
            String message = ex.getClass().getName() + " " + ex.getCause();
            result.setString(message);
            return result;
        }
        return result;
    }
}
