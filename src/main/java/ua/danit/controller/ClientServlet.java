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
import ua.danit.utils.HtmlUtil;

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
				PrintWriter writer = resp.getWriter();
				String outText = HtmlUtil.readPage("new-user.html");
				String.format(outText, "Passwords are not the same!");

				writer.print(outText);
			}
			else{

				Client clientFromDB = clientDAO.get((String)login);
				if(clientFromDB != null){
					PrintWriter writer = resp.getWriter();
					String outText = HtmlUtil.readPage("new-user.html");
					String.format(outText, "Sorry, but this login already exists!");

					writer.print(outText);
				}
				else{
					Client client = new Client();
					client.setLogin(login);
					client.setPassword(pass);
					client.setFirstName(firstName);
					client.setSecondName(secondName);
					clientDAO.save(client);

					PrintWriter writer = resp.getWriter();
					String outText = HtmlUtil.readPage("item-list.html");

					String.format(outText, client.getFirstName() + " " + client.getSecondName(), client.getLogin(), HtmlUtil.getItems(login));

					writer.print(outText);
				}

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
				String outText = HtmlUtil.readPage("edit-user.html").format("<h1>Congrats! You are user!</h1>", login);


				writer.print(outText);


			}
			else
			{
				resp.sendRedirect("/shop-servlet");
			}
		}
		else if(action.equals("edit"))
		{
//			String    login     = req.getParameter("login");
//			String    pass      = req.getParameter("pass");
//			String    checkPass     = req.getParameter("checkPass");
//			String    firstName      = req.getParameter("firstName");
//			String    secondName      = req.getParameter("secondName");
//			if(!pass.equals(checkPass)){
//				resp.addHeader("action", "edit");
//				resp.sendRedirect("/client");
//			}
//			Client client = new Client();
//			client.setLogin(login);
//			client.setPassword(pass);
//			client.setFirstName(firstName);
//			client.setSecondName(secondName);
//			clientDAO.save(client);
//
//			Client clientFromDB = clientDAO.get((String)client.getLogin());
//			if(clientFromDB != null){
//				PrintWriter writer = resp.getWriter();
//				writer.print("<h1>Congrats! You are registered!</h1> \n...go get some sleep...");
//			}
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
