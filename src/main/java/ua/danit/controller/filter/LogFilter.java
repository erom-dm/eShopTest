package ua.danit.controller.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "ShopFilter")
public class LogFilter implements Filter
{
	private FilterConfig filterConfig;
	private Boolean active;

	@Override
	public void init (FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		if (active == null)
			active = true;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if ( active )
		{
			String ip = request.getLocalAddr() + " with servername " + request.getLocalName() + " was conected "
				+ "throw filter " + filterConfig.getFilterName();
			chain.doFilter(request,response);
		}
	}

	@Override
	public void destroy()
	{
		active = null;
	}
}
