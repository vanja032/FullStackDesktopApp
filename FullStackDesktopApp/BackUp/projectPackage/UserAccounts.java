package projectPackage;

import java.util.ArrayList;

public class UserAccounts {

	ArrayList<User> allUsers;

	public UserAccounts() {
		super(); 
		allUsers = new ArrayList<User>();
	}

	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(ArrayList<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	public void setUser(User user) {
		this.allUsers.add(user);
	}
	
}
