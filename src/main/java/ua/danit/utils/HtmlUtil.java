package ua.danit.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ua.danit.dao.CartDAO;
import ua.danit.dao.ItemDAO;
import ua.danit.model.Cart;
import ua.danit.model.Item;

public class HtmlUtil
{
	static final String userDir = System.getProperty("user.dir");
	static final String sep = System.getProperty("file.separator");

	public static String readPage(String fileName)
	{
		String outText = "";
		String filepath = userDir + sep + "lib" + sep + "html" + sep + fileName;
		try(FileReader reader = new FileReader(filepath);
			BufferedReader buffReader = new BufferedReader(reader))
		{
			String text = "";
			while((text = buffReader.readLine()) != null){
				outText += text;
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			return "Ups! Something goes wrong...";
		}
		return outText;
	}

	public static String getItems(String login)
	{
		String outText="";

		ItemDAO itemDAO = new ItemDAO();

		List<Item> items = itemDAO.getAll();
		if ( !items.isEmpty() ){
			for ( Item item : items ){
				outText += "<tr>\n";

				outText += "<td>\n";
				outText += item.getArticleId();
				outText += "</td>\n";

				outText += "<td>\n";
				outText += item.getName();
				outText += "</td>\n";

				outText += "<td>\n";
				outText += beautyPrice(item.getPrice());
				outText += "</td>\n";

				outText += "<td>\n";
				outText += getItemPage(item.getArticleId(), login);
				outText += "</td>\n";

				outText += "</tr>\n";
			}
		}

		return outText;
	}

	private static String getItemPage(String articleId, String login)
	{
		String outText = "";

		outText += "<form action=\"item\" method=\"post\">\n";
		outText += "\t<input type=\"hidden\" name=\"action\" value=\"preview\">\n";
		outText += "\t<input type=\"hidden\" name=\"login\" value=\""+login+"\">\n";
		outText += "\t<input type=\"hidden\" name=\"item\" value=\""+articleId+"\">\n";
		outText += "\t<input type=\"submit\" value=\"Detail\">\n";
		outText += "</form>\n";

		return outText;
	}

	private static String beautyPrice(Integer price)
	{
		String beautyPrice = String.format("%.2f", ((double)price)/100);
		return beautyPrice+" UAH";
	}

	public static String getCarts(String login)
	{
		String outText="";

		CartDAO cartDAO = new CartDAO();

		List<Cart> carts = cartDAO.getAllByLogin(login);
		if ( !carts.isEmpty() ){
			outText += "<tr>\n";

			outText += "<th>CART No\n";
			outText += "</th>\n";

			outText += "<th>SELL TIME\n";
			outText += "</th>\n";

			outText += "<th>DETAILS\n";
			outText += "</th>\n";

			outText += "</tr>\n";

			int count = 1;
			for ( Cart cart : carts ){

				outText += "<tr>\n";

				outText += "<td>\n";
				outText += "cart " + count;
				outText += "</td>\n";

				outText += "<td>\n";
				outText += beautyTime(cart.getCartTime());
				outText += "</td>\n";

				outText += "<td>\n";
				outText += getCartDetailForm(cart);
				outText += "</td>\n";

				outText += "</tr>\n";

				count++;
			}
		}

		return outText;
	}

	private static String beautyTime(Long cartTime)
	{
		Date             date             = new Date(cartTime);
		SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return formatForDate.format(date);
	}

	private static String getCartDetailForm(Cart cart)
	{
		String outText = "";
		outText += "<form action=\"order\">\n";
		outText += "\t<input type=\"hidden\" name=\"action\" value=\"getByCart\">\n";
		outText += "\t<input type=\"hidden\" name=\"cartId\" value=\""+cart.getCartID()+"\">\n";
		outText += "\t<input type=\"submit\" value=\"More...\">\n";
		outText += "</form>\n";
		return outText;
	}
}
