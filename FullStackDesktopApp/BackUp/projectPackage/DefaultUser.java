package projectPackage;

public final class DefaultUser extends User{

	public DefaultUser() {
		super(); 
	}

	public DefaultUser(String userID, String userName, String userSurname, String userUsername, String userPassword,
			String userAddress, String userPhone, String userCredit, String userRole, String userProfilePicture) {
		super(userID, userName, userSurname, userUsername, userPassword, userAddress, userPhone, userCredit, userRole,
				userProfilePicture); 
	}

	@Override
	public String toString() {
		return "This is Default User Account.";
	}

	
}
