package comp3095.amtrix2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketModel {
	private int _id;
	private int _userId;
	private String _subject;
	private String _message;
	private String _response;
	private boolean _isRead;
	
	public TicketModel(int userId, String subject, String message) {
		_userId = userId;
		_subject = subject;
		_message = message;
		_response = null;
		_isRead = false;
	}
	
	public void setId(int id) { _id = id; }
	
	public void setIsRead(boolean isRead) { _isRead = isRead; }
	public void setResponse(String response) { _response = response; }

	public int getId() { return _id; }
	public int getUserId() { return _userId; }
	public String getSubject() { return _subject; }
	public String getMessage() { return _message; }
	public String getResponse() { return _response; }
	public boolean isRead() { return _isRead; }
	
	
	public static TicketModel fromResultSet(ResultSet results) throws SQLException {
		int userId = results.getInt("user_id");
		String subject = results.getString("subject");
		String message = results.getString("message");
		
		TicketModel model = new TicketModel(userId, subject, message);
		model.setId(results.getInt("id"));
		model.setIsRead(results.getBoolean("is_read"));
		model.setResponse(results.getString("response"));
		return model;
	}
	
}
