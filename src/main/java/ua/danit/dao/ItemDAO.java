package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.danit.model.Item;

public class ItemDAO extends AbstractDAO<Item>
{
	@Override
	public void save(Item item)
	{
		String sql = "INSERT INTO item(item_id, name, price) VALUES(?,?,?)";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setString(1, item.getArticleId());
			statement.setString(2, item.getName());
			statement.setInt(3, item.getPrice());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Item item)
	{
		String sql = "UPDATE item SET name=?, price=? WHERE item_id=?";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setString(3, item.getArticleId());
			statement.setString(1, item.getName());
			statement.setInt(2, item.getPrice());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public Item get(Object itemId)
	{
		Item item = new Item();

		String sql = "SELECT * FROM item WHERE login='" + itemId + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
			)
		{
			while ( rSet.next() )
			{
				item.setArticleId(rSet.getString("item_id"));
				item.setName(rSet.getString("name"));
				item.setPrice(rSet.getInt("price"));

				return item;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override public void delete(Object itemId)
	{
		String sql = "DELETE FROM item WHERE item_id=?";

		try (
			Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			)
		{
			statement.setString(1, (String) itemId);
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
}
