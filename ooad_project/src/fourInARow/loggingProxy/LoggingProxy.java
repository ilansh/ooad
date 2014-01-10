package fourInARow.loggingProxy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggingProxy implements InvocationHandler {

	 private Object _obj;
	 
	 private PrintWriter _writer;

    public static Object newInstance(Object obj, PrintWriter writer) {
        return java.lang.reflect.Proxy.newProxyInstance(
            obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(),
            new LoggingProxy(obj, writer));
    }

    private LoggingProxy(Object obj, PrintWriter writer) {
        _obj = obj;
        _writer = writer;
    }
    	
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            _writer.print("Method call: " + m.getName() + "(");
            if(args != null) {
	            for(int i = 0; i < args.length; i++) {
	            	if(args[i] == null) {
	            		_writer.print("null");
	            	}
	            	else {
	            		_writer.print(args[i].toString());
	            	}
	            }
            }
            _writer.println(")");
            result = m.invoke(_obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                                       e.getMessage());
        } finally {
//            System.out.println("after method " + m.getName());
        }
        return result;
    }

}
