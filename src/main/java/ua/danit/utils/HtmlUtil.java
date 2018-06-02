package ua.danit.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import ua.danit.dao.ItemDAO;
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
		outText += "</form>";

		return outText;
	}

	private static String beautyPrice(Integer price)
	{
		String beautyPrice = String.format("%.2f", ((double)price)/100);
		return beautyPrice+" UAH";
	}
}
