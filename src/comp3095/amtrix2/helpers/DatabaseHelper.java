package comp3095.amtrix2.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3095.amtrix2.models.AddressModel;
import comp3095.amtrix2.models.CreditCardModel;
import comp3095.amtrix2.models.UserModel;

public class DatabaseHelper {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	private final Connection _connection;
	public DatabaseHelper() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		_connection = DriverManager.getConnection("jdbc:mysql://root@localhost:3306/COMP3095?serverTimezone=UTC");
	}
	
	public UserModel getUser(String email) {
		try {
			PreparedStatement stmt = _connection.prepareStatement(
					"SELECT * FROM `USERS` WHERE email=? LIMIT 1");
			stmt.setString(1, email);

			UserModel result;
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				result = UserModel.fromResultSet(results);
			} else {
				result = null;
			}

			results.close();
			stmt.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	public UserModel register(UserModel user) {
		try {
			PreparedStatement stmt = _connection.prepareStatement("INSERT INTO `USERS` (`firstName`, `lastName`, `email`, `password`, `role`, `dob`) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getRole());
			stmt.setDate(6, user.getDob());
			
			UserModel u2 = null;
			if (stmt.executeUpdate() == 1) {
				long idResult = ((com.mysql.cj.jdbc.StatementImpl)stmt).getLastInsertID();
				user.setId((int)idResult);
				u2 = user;
			}
			stmt.close();
			return u2;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public UserModel login(String email, String password) {
		UserModel user = this.getUser(email);
		if (user.getPassword().contentEquals(password))
			return user;
		return null;
	}
	
	public boolean updateProfile(UserModel user) {
		try {
			PreparedStatement stmt = _connection.prepareStatement("UPDATE `USERS` SET firstName=?, lastName=?, email=?, password=?, role=?, dob=?, default_billing_address_id=?, default_shipping_address_id=?, last_profile_change=?, last_login=? WHERE id=?");
			user.prepareUpdate(stmt, true);
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (SQLException e) { }
		return false;
	}
	
	public AddressModel getAddress(int id) {
		try {
			PreparedStatement stmt = _connection.prepareStatement("SELECT * FROM `ADDRESSES` WHERE id = ? LIMIT 1");
			stmt.setInt(1, id);

			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return AddressModel.fromResultSet(results);
			}
		}
		catch (SQLException e) {}
		return null;
	}
	
	public AddressModel getBillingAddress(UserModel user) {
		return getAddress(user.getDefaultBillingAddressId());
	}
	public AddressModel getShippingAddress(UserModel user) {
		return getAddress(user.getDefaultShippingAddressId());
	}
	public CreditCardModel getDefaultCard(UserModel user) {
		try {
			PreparedStatement stmt = _connection.prepareStatement("SELECT * FROM `CREDITCARDS` WHERE `user_id` = ? AND `is_default` = true LIMIT 1;");
			stmt.setInt(0, user.getId());
		
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return CreditCardModel.fromResultSet(results);
			}
		} catch (SQLException e) { }
		return null;
	}
}
