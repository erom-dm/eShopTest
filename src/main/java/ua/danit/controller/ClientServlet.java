package ua.danit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.danit.dao.ClientDAO;
import ua.danit.model.Client;

@WebServlet("/client")
public class ClientServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
/*		PrintWriter writer = resp.getWriter();
		writer.print("Hello!");*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String    login  = (String) req.getParameter("login");
		String    pass   = (String) req.getParameter("pass");
		ClientDAO clientDAO = new ClientDAO();
		Client client = clientDAO.get((String)login);
		if(client != null && pass.equals(client.getPassword())){
			PrintWriter writer = resp.getWriter();
			writer.print("Congrats! You are user!");
		} else {
			resp.sendRedirect("/shop-servlet");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doDelete(req, resp);
	}
}
