package models;

public class Message {
	private String message;
	private User user;

	public Message(String message, User user) {
		this.message = message;
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	public String getMessage(){
		return message;
	}
}
