package models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private static List<User> users = new ArrayList<User>();
	private String ip;
	private String name;
	
	public String getIp(){
		return ip;
	}

	public User(String ip) {
		this.ip = ip;
	}
	
	public User(String ip, String name) {
		this.ip = ip;
		this.name = name;
	}
	
	public static User getByIP(String ip) {
		for(User user: users){
			if(user.ip == ip){
				return user;
			}
		}
		
		User user = new User(ip);
		
		users.add(user);
		
		return user;
	}

	public String getName() {
		return name;
	}

}
