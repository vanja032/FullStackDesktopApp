package projectPackage;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.io.FileWriter;  
import java.io.IOException;

public class Logs {

	private User userInfo;
	private String timeNow;
	private String logMessage; 
	
	public User getUserInfo() {
		return this.userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	public String getTimeNow() {
		return this.timeNow;
	}
	public void setTimeNow() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		this.timeNow = format.format(LocalDateTime.now()).toString();  
	}
	public String getLogMessage() {
		return this.logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public Logs(User userInfo, String logMessage) {
		super(); 
		
		this.userInfo = userInfo;
		setTimeNow();
		this.logMessage = logMessage;
	}
	public Logs() {
		super(); 
		setTimeNow();
	}
	
	public void PrintLogs()
	{
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MM_dd");  
			String date = format.format(LocalDateTime.now()).toString(); 
			FileWriter logWrite = new FileWriter("Logs/"+date+"_.logfile", true);
			logWrite.write("User Info: "+userInfo.toString()+"\n\tTime: "+this.timeNow+"\n\tLog Message: "+this.logMessage+"\n\n");
			logWrite.close(); 
	    } 
		catch (IOException e) {
			e.printStackTrace();
	    }
	}
}
