package ua.danit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.danit.model.Client;

/**
 *
 */
public class ClientDAO extends AbstractDAO<Client>
{
	@Override public void save(Client client)
	{
		String sql = "INSERT INTO client(login, password, first_name, second_name) VALUES(?,?,?,?)";

		try ( Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); )
		{
			statement.setString(1, client.getLogin());
			statement.setString(3, client.getFirstName());
			statement.setString(2, client.getPassword());
			statement.setString(4, client.getSecondName());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override public void update(Client client)
	{
		String sql = "UPDATE client SET password=?, first_name=?, second_name=? WHERE login=?";

		try (
			Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			)
		{

			statement.setString(4, client.getLogin());
			statement.setString(2, client.getFirstName());
			statement.setString(1, client.getPassword());
			statement.setString(3, client.getSecondName());

			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public Client get(Object pk)
	{
		Client client = new Client();

		String sql = "SELECT * FROM client WHERE login='" + pk + "'";

		try (
			Connection        connection  = ConnectionToDB.getConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
			)
		{
			while ( rSet.next() )
			{
				client.setLogin(rSet.getString("login"));
				client.setPassword(rSet.getString(2));
				client.setFirstName(rSet.getString("first_name"));
				client.setSecondName(rSet.getString(4));

				return client;
			}
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override public void delete(Object login)
	{
		String sql = "DELETE FROM client WHERE login=?";

		try (
			Connection connection = ConnectionToDB.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			)
		{
			statement.setString(1, (String) login);
			statement.executeUpdate();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
}
