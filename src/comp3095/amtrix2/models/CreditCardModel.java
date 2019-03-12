package comp3095.amtrix2.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardModel {
	private int _id;
	private int _cardType;
	
	private String _cardHolderName;
	private String _cardNumber;
	private Date _expiry;
	private int _userId;
	private boolean _isDefault;
	
	public CreditCardModel(int cardType, String cardHolderName, String cardNumber,
			Date expiry, int userId, boolean isDefault)
	{
		_cardType = cardType;
		_cardHolderName = cardHolderName;
		_cardNumber = cardNumber;
		_expiry = expiry;
		_userId = userId;
		_isDefault = isDefault;
	}
	
	public void setId(int id) { _id = id; }
	public void setIsDefault(boolean isDefault) { _isDefault = isDefault; }
	
	public int getId() { return _id; }
	public int getCardType() { return _cardType; }
	public String getCardHolderName() { return _cardHolderName; }
	public String getCardNumber() { return _cardNumber; }
	public Date getExpiry() { return _expiry; }
	public int getUserId() { return _userId; }
	public boolean isDefault() { return _isDefault; }
	
	public static CreditCardModel fromResultSet(ResultSet results) throws SQLException {
		int cardType = results.getInt("card_type");
		String cardHolderName = results.getString("card_holder_name");
		String cardNumber = results.getString("card_number");
		Date expiry = results.getDate("expiry");
		int userId = results.getInt("user_id");
		boolean isDefault = results.getBoolean("is_default");
		
		CreditCardModel model = new CreditCardModel(
			cardType, cardHolderName, cardNumber,
			expiry, userId, isDefault
		);
		model.setId(results.getInt("id"));
		return model;
	}
}
