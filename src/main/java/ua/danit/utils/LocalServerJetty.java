package ua.danit.utils;

import java.util.EnumSet;
import java.util.Enumeration;
import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.ClientServlet;
import ua.danit.controller.ShopServlet;
import ua.danit.controller.filter.LogFilter;

public class LocalServerJetty
{
	public static void start(){
		Server server = new Server(8080);

		ServletContextHandler handler = new ServletContextHandler();

		ServletHolder holder = new ServletHolder(new ShopServlet());
		handler.addServlet(holder, "/shop-servlet");

		ServletHolder clientHolder = new ServletHolder(new ClientServlet());
		handler.addServlet(clientHolder, "/client");

		handler.addFilter(LogFilter.class, "/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

		server.setHandler(handler);

		try
		{
			server.start();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		try
		{
			server.join();
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
}
