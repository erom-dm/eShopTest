package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.danit.model.Cart;

public class CartDAO extends AbstractDAO<Cart>
{

	@Override public void save(Cart cart)
	{
		String sql = "INSERT INTO cart(id, cart_time) VALUES(?,?)";

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setInt(1, cart.getCartID());
			statement.setLong(2, cart.getCartTime());
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Cart obj)
	{

	}

	@Override public Cart get(Object cartID)
	{
		String sql = "SELECT * FROM cart WHERE id="+cartID+"";
		Cart cart = new Cart();

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery(); )
		{
			while(rSet.next()){
				cart.setCartID(rSet.getInt("id"));
				cart.setCartTime(rSet.getLong("cart_time"));
				return cart;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override public void delete(Object pk)
	{
		String sql = "DELETE FROM cart WHERE id="+pk;

		try ( Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
}
