import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class logMethodCalls implements java.lang.reflect.InvocationHandler {
	private Object obj;
	private PrintWriter writer;

	public static Object newInstance(Object obj, PrintWriter writer) {
		return java.lang.reflect.Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(),
				new logMethodCalls(obj, writer));
	}

	private logMethodCalls(Object obj, PrintWriter writer) {
		this.obj = obj;
		this.writer = writer;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object result;
		try {
			writer.println("before method " + m.getName());
			if (args != null && args[0] != null){
				writer.println("with arguments "+args[0].toString());
				for(int i = 1; i < args.length; i++ ){
					if (args[i] != null){
						writer.println(args[i].toString());
					}
				}
			}
			result = m.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("unexpected invocation exception: " +
					e.getMessage());
		} finally {
			//System.out.println("after method " + m.getName());
			writer.println("after method " + m.getName());
		}
		return result;
	}

}
