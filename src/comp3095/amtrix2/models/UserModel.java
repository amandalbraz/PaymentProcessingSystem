package comp3095.amtrix2.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import comp3095.amtrix2.helpers.StringHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _password;
	private String _role;
	private Timestamp _created;
	private Date _dob;
	private int _defaultBillingAddressId;
	private int _defaultShippingAddressId;
	
	private Date _lastProfileUpdate;
	private Date _lastLogin;
	
	public int getId() { return _id; }
	public String getFirstName() { return _firstName; }
	public String getLastName() { return _lastName; }
	public String getEmail() { return _email; }
	public String getPassword() { return _password; }
	public String getRole() { return _role; }
	public Timestamp getCreated() { return _created; }
	public Date getDob() { return _dob; }
	
	public int getDefaultBillingAddressId() { return _defaultBillingAddressId; }
	public int getDefaultShippingAddressId() { return _defaultShippingAddressId; }
	
	public Date getLastProfileUpdate() { return _lastProfileUpdate; }
	public Date getLastLogin() { return _lastLogin; }

	public String getDobControlValue() { return StringHelper.dateToControlString(_dob); }
	
	public String getFriendlyLastLogin() { return StringHelper.sqlDateToString(_lastLogin); }
	public String getFriendlyLastProfileUpdate() { return StringHelper.sqlDateToString(_lastProfileUpdate); }
	public String getFriendlyDob() { return StringHelper.sqlDateToString(_dob); }
	
	public void setId(int id) { _id = id; }
	
	public UserModel(String firstName, String lastName, String email, String password, String role, Timestamp created, Date dob,
			int defaultBillingAddressId, int defaultShippingAddressId, Date lastProfileUpdate, Date lastLogin) {
		_firstName = firstName;
		_lastName = lastName;
		_email = email;
		_password = password;
		_role = role;
		_created = created;
		_dob = dob;
		
		_defaultBillingAddressId = defaultBillingAddressId;
		_defaultShippingAddressId = defaultShippingAddressId;
		
		_lastProfileUpdate = lastProfileUpdate;
		_lastLogin = lastLogin;
	}
	
	public void prepareUpdate(PreparedStatement stmt, boolean updateLastProfileUpdate) throws SQLException {
		stmt.setString(1, _firstName);
		stmt.setString(2, _lastName);
		stmt.setString(3, _email);
		stmt.setString(4, _password);
		stmt.setString(5, _role);
		stmt.setDate(6, _dob);
		stmt.setInt(7, _defaultBillingAddressId);
		stmt.setInt(8, _defaultShippingAddressId);
		if (updateLastProfileUpdate)
			stmt.setDate(9, new Date(new java.util.Date().getTime()));
		else
			stmt.setDate(9, _lastProfileUpdate);
		stmt.setDate(10, _lastLogin);		
		stmt.setInt(11, _id);
	}

	public static UserModel fromResultSet(ResultSet results) throws SQLException {
		String firstName = results.getString("firstName");
		String lastName = results.getString("lastName");
		String email = results.getString("email");
		String password = results.getString("password");
		String role = results.getString("role");
		Timestamp created = results.getTimestamp("created");
		Date dob = results.getDate("dob");
		int defaultBillingAddressId = results.getInt("default_billing_address_id");
		int defaultShippingAddressId = results.getInt("default_shipping_address_id");

		Date lastProfileUpdate = results.getDate("last_profile_change");
		Date lastLogin = results.getDate("last_login");
		
		UserModel user = new UserModel(firstName, lastName, email, password, role, created, dob,
				defaultBillingAddressId, defaultShippingAddressId, lastProfileUpdate, lastLogin);
		user._id = results.getInt("id");
		return user;
	}

}
