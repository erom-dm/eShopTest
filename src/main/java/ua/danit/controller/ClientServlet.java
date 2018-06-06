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

@WebServlet("/client") public class ClientServlet extends HttpServlet
{
	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

	}

	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String action = req.getParameter("action");

		ClientDAO clientDAO = new ClientDAO();

		if ( action.equals("create") )
		{
			String login      = req.getParameter("login");
			String pass       = req.getParameter("pass");
			String checkPass  = req.getParameter("checkPass");
			String firstName  = req.getParameter("firstName");
			String secondName = req.getParameter("secondName");

			if ( !pass.equals(checkPass) )
			{
				PrintWriter writer  = resp.getWriter();
				String      outText = HtmlUtil.readPage("new-user.html");
				outText = String.format(outText, "Passwords are not the same!");

				writer.print(outText);
			}
			else
			{

				Client clientFromDB = clientDAO.get((String) login);
				if ( clientFromDB != null )
				{
					PrintWriter writer  = resp.getWriter();
					String      outText = HtmlUtil.readPage("new-user.html");
					outText = String.format(outText, "Sorry, but this login already exists!");

					writer.print(outText);
				}
				else
				{
					Client client = new Client();
					client.setLogin(login);
					client.setPassword(pass);
					client.setFirstName(firstName);
					client.setSecondName(secondName);
					clientDAO.save(client);

					PrintWriter writer  = resp.getWriter();
					String      outText = HtmlUtil.readPage("item-list.html");

					outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(), client.getLogin(), HtmlUtil.getItems(login));

					writer.print(outText);
				}

			}
		}
		else if ( action.equals("checkLogin") )
		{
			String login  = req.getParameter("login");
			String pass   = req.getParameter("pass");
			Client client = clientDAO.get((String) login);
			if ( client != null && pass.equals(client.getPassword()) )
			{
				PrintWriter writer  = resp.getWriter();
				String      outText = HtmlUtil.readPage("item-list.html");

				outText = String.format(outText, client.getFirstName() + " " + client.getSecondName(), client.getLogin(), HtmlUtil.getItems(login));

				writer.print(outText);
			}
			else
			{
				resp.sendRedirect("/shop-servlet");
			}
		}
		else if ( action.equals("profile") )
		{
			String login  = req.getParameter("login");
			Client client = clientDAO.get((String) login);

			PrintWriter writer  = resp.getWriter();
			String      outText = HtmlUtil.readPage("user-cabinet.html");

			outText = String.format(outText,
				client.getFirstName() + " " + client.getSecondName(),
				client.getLogin(),
				client.getLogin(),
				HtmlUtil.getCarts(login));

			writer.print(outText);

		}
		else if ( action.equals("edit") )
		{

		}
		else if ( action.equals("delete") )
		{

		}
	}
}
