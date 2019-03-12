package comp3095.amtrix2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressModel {
	public static final AddressModel Empty = new AddressModel("", "", "", "");
	private int _id;
	private String _address;
	private String _city;
	private String _country;
	private String _postal;
	
	public AddressModel(String address, String city, String country, String postal) {
		_address = address;
		_city = city;
		_country = country;
		_postal = postal;
	}
	
	public void setId(int id) { _id = id; }
	
	public int getId() { return _id; }
	public String getAddress() { return _address; }
	public String getCity() { return _city; }
	public String getCountry() { return _country; }
	public String getPostal() { return _postal; }
	
	public static AddressModel fromResultSet(ResultSet results) throws SQLException {
		String address = results.getString("address");
		String city = results.getString("city");
		String country = results.getString("country");
		String postal = results.getString("postal");
		
		AddressModel model = new AddressModel(address, city, country, postal);
		model.setId(results.getInt("id"));
		return model;
	}
}
