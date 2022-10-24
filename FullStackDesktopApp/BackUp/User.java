package projectPackage;

public class User {
	private String userID;
	private String userName;
	private String userSurname;
	private String userUsername;
	private String userPassword;
	private String userAddress;
	private String userPhone;
	private String userCredit;
	private String userRole;
	private String userProfilePicture;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserCredit() {
		return userCredit;
	}
	public void setUserCredit(String userCredit) {
		this.userCredit = userCredit;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserProfilePicture() {
		return userProfilePicture;
	}
	public void setUserProfilePicture(String userProfilePicture) {
		this.userProfilePicture = userProfilePicture;
	}
	public User(String userID, String userName, String userSurname, String userUsername, String userPassword,
			String userAddress, String userPhone, String userCredit, String userRole, String userProfilePicture) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCredit = userCredit;
		this.userRole = userRole;
		this.userProfilePicture = userProfilePicture;
	}
	public User() {
		super();
		this.userID = "";
		this.userName = "";
		this.userSurname = "";
		this.userUsername = "";
		this.userPassword = "";
		this.userAddress = "";
		this.userPhone = "";
		this.userCredit = "";
		this.userRole = "";
		this.userProfilePicture = "";
	}
	@Override
	public String toString() {
		return "User [userID='" + userID + "', userName='" + userName + "', userSurname='" + userSurname + "', userUsername='"
				+ userUsername + "', userPassword='" + userPassword + "', userAddress='" + userAddress + "', userPhone='"
				+ userPhone + "', userCredit='" + userCredit + "', userRole='" + userRole + "', userProfilePicture='"
				+ userProfilePicture + "']";
	} 
	
	
}
