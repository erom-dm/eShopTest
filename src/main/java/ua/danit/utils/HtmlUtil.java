package ua.danit.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HtmlUtil
{
	static final String userDir = System.getProperty("user.dir");
	static final String sep = System.getProperty("file.separator");

	public static String readLandingPage()
	{
		String outText = "";
		String filepath = userDir + sep + "lib" + sep + "html" + sep + "index.html";
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
}