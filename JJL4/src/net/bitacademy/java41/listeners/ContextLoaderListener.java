package net.bitacademy.java41.listeners;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
	ServletContext ctx;
	Hashtable<String,Object> objTable = new Hashtable<String,Object>();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ctx = event.getServletContext();
		ctx.setAttribute("rootPath", ctx.getContextPath());
		
		try {
			prepareObjects(
					ctx.getRealPath("/WEB-INF/context.properties"));
			prepareDependancy();
			saveToContext();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveToContext() {
		Enumeration<String> keyList = objTable.keys();
		String key = null;
		while(keyList.hasMoreElements()) {
			key = keyList.nextElement();
			ctx.setAttribute(key, objTable.get(key));
		}
	}

	private void prepareDependancy() throws Exception {
		Collection<Object> objList = objTable.values();
		for(Object obj : objList) {
			if (obj.getClass() != java.lang.String.class) {
				injectDependancy(obj);
			}
		}
	}

	private void injectDependancy(Object obj) throws Exception {
		System.out.println(obj.getClass().getName() + "---------");
		Method[] methodList = obj.getClass().getMethods();
		for (Method m : methodList) {
			callSetter(obj, m);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void callSetter(Object instance, Method method) throws Exception {
		Class paramClass = null;
		Object paramObject = null;
		if (method.getName().startsWith("set")) {
			paramClass = method.getParameterTypes()[0];
			if (paramClass == java.lang.String.class) {
				String propertyName = extractPropertyName(method.getName());
				method.invoke(instance, objTable.get(propertyName));
			} else {
				paramObject = findInstanceByClass(paramClass);
				method.invoke(instance, paramObject);
			}
			System.out.println("......" + method.getName());
		}
	}

	private String extractPropertyName(String methodName) {
		return methodName.substring(3, 4).toLowerCase()
				+ methodName.substring(4);
	}

	@SuppressWarnings("rawtypes")
	private Object findInstanceByClass(Class paramClass) {
		Collection<Object> instanceList = objTable.values();
		for(Object obj : instanceList) {
			if (obj.getClass() == paramClass) {
				return obj;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private void prepareObjects(String filePath) throws Exception {
		Properties props = new Properties();
		props.load( new FileReader(filePath));
		
		Enumeration enums = props.keys();
		String key = null;
		String value = null;
		Class clazz = null;
		while(enums.hasMoreElements()) {
			key = (String)enums.nextElement();
			value = ((String)props.get(key)).trim(); 
			if (value.charAt(0) == '"') {
				objTable.put(key, value.substring(1, value.length()-1)); 
			} else {
				clazz = Class.forName(value);
				objTable.put(key, clazz.newInstance());
			} 
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}






