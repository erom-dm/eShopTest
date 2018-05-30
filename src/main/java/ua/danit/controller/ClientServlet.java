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
		String action = req.getParameter("action");
		/*if(action == null || action.isEmpty()){
			action = req.getHeader("action");
		}*/
		ClientDAO clientDAO = new ClientDAO();
		if(action.equals("create"))
		{
			String    login     = req.getParameter("login");
			String    pass      = req.getParameter("pass");
			String    checkPass     = req.getParameter("checkPass");
			String    firstName      = req.getParameter("firstName");
			String    secondName      = req.getParameter("secondName");
			if(!pass.equals(checkPass)){
				resp.addHeader("action", "create");
				resp.sendRedirect("/client");
			}
			Client client = new Client();
			client.setLogin(login);
			client.setPassword(pass);
			client.setFirstName(firstName);
			client.setSecondName(secondName);
			clientDAO.save(client);

			Client clientFromDB = clientDAO.get((String)client.getLogin());
			if(clientFromDB != null){
				PrintWriter writer = resp.getWriter();
				writer.print("<h1>Congrats! You are registered!</h1> \n...go get some sleep...");
			}
		}
		else if(action.equals("checkLogin"))
		{
			String    login     = req.getParameter("login");
			String    pass      = req.getParameter("pass");
			Client    client    = clientDAO.get((String) login);
			if ( client != null && pass.equals(client.getPassword()) )
			{
				PrintWriter writer = resp.getWriter();
				writer.print("<h1>Congrats! You are user!</h1> \n...go get some sleep...");
			}
			else
			{
				resp.sendRedirect("/shop-servlet");
			}
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
