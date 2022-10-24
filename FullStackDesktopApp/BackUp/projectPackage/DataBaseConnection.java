package projectPackage; 

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {
	
	private String coonectionString;
	private String username;
	private String password;
	private String SQLStatement;
	private String dataBaseName; 
	private ArrayList<ArrayList<String>> SQLResult; 
	private String MessageOutput; 
	private boolean containsRequest;
	
	public boolean isContainsRequest() {
		return containsRequest;
	}
	public void setContainsRequest(boolean containsRequest) {
		this.containsRequest = containsRequest;
	}
	public ArrayList<ArrayList<String>> getSQLResult() {
		return SQLResult;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getSQLStatement() {
		return SQLStatement;
	}
	public void setSQLStatement(String sQLStatement) {
		SQLStatement = sQLStatement;
	}
	public String getCoonectionString() {
		return coonectionString;
	}
	public void setCoonectionString(String coonectionString) {
		this.coonectionString = coonectionString;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getMessageOutput() {
		return MessageOutput;
	}
	public void setMessageOutput(String messageOutput) {
		MessageOutput = messageOutput;
	} 
	
	public DataBaseConnection()
	{
		super(); 
	}
	
	public DataBaseConnection(String coonectionString, String username, String password, String sQLStatement,
			String dataBaseName) {
		
		super();
		this.coonectionString = coonectionString;
		this.username = username;
		this.password = password;
		SQLStatement = sQLStatement;
		this.dataBaseName = dataBaseName; 
		
		CreateConnection();
	}
	public void setSQLResult(ArrayList<ArrayList<String>> sQLResult) {
		SQLResult = sQLResult;
	}
	
	public void CreateConnection() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + this.coonectionString + "/" + this.dataBaseName, this.username, this.password);  
			Statement stm = connection.createStatement(); 
			ResultSet result = null;
			result = stm.executeQuery(SQLStatement);
			
			SQLResult = new ArrayList<ArrayList<String>>();
			
			
			if (result != null) 
			{
				
				while(result.next())
				{
					this.containsRequest = true;
					
					int setLength = result.getMetaData().getColumnCount();
					
					
					ArrayList<String> prop = new ArrayList<String>();
						
					for(int i = 1; i <= setLength; i++)
					{
						prop.add(result.getString(i).toString()); 
					}
					
					
					SQLResult.add(prop); 
				}
				
				connection.close(); 
				
				MessageOutput = "Successful Request";
			}
			else
			{
				this.containsRequest = false;
				MessageOutput = "Empty Request";
			}
		}
		catch (Exception e) {
			MessageOutput = e.getMessage();
		}
	}

}
