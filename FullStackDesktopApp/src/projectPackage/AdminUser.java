package projectPackage;

public final class AdminUser extends User{

	public AdminUser() {
		super(); 
	}

	public AdminUser(String userID, String userName, String userSurname, String userUsername, String userPassword,
			String userAddress, String userPhone, String userCredit, String userRole, String userProfilePicture) {
		super(userID, userName, userSurname, userUsername, userPassword, userAddress, userPhone, userCredit, userRole,
				userProfilePicture); 
	}

	@Override
	public String toString() {
		return "This is Admin User Account.";
	}

	
}
