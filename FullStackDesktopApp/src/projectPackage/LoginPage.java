package projectPackage;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPackage.HomePage.FrameDragListener;

import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame {

	private String SQLConnectionString = "";
	private String SQLDataBaseName = "";
	private String SQLDataBaseUsername = "";
	private String SQLDataBasePassword = "";
	
	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JTextField PasswordTextField;  
	
	private boolean checkViewPassword = false;
	//private boolean loginActionCheck = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setUndecorated(true); 
					frame.setPreferredSize(new Dimension(500, 510));
					frame.setResizable(false);
					
					FrameDragListener frameDragListener = new FrameDragListener(frame);
	                frame.addMouseListener(frameDragListener);
	                frame.addMouseMotionListener(frameDragListener);

	                frame.pack();
	                frame.setLocationRelativeTo(null);
					
					frame.setVisible(true); 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoginPage() throws IOException {

			  try {
			  File DataBaseInfo = new File("Resources/LoginInfoDataBase.dll");
			  Scanner dataReader = new Scanner(DataBaseInfo);
			
			   while (dataReader.hasNextLine()) {
				   String line = dataReader.nextLine();
			        String[] array = line.replaceAll("\\s","").split(":", 2);
			        
			        if(array[0].equals("CoonectionString"))
			        	SQLConnectionString = array[1];
			        if(array[0].equals("DataBaseName"))
			        	SQLDataBaseName = array[1];
			        if(array[0].equals("Username"))
			        	SQLDataBaseUsername = array[1];
			        if(array[0].equals("Password"))
			        	SQLDataBasePassword = array[1];
			        
			   }
			 
			   dataReader.close();
			 
			  } catch (FileNotFoundException e) 
			  {
				  e.printStackTrace();
			  }catch (IOException e) 
			  {
				  e.printStackTrace();
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			  
		
		setBackground(new Color(61, 61, 61));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(61, 61, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(90, 90, 90))); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton LogInButton = new JButton("Log in");
		LogInButton.setEnabled(false);
		LogInButton.setToolTipText("Log in");
		
		UsernameTextField = new JTextField();
		PasswordTextField = new JPasswordField(); 
		
		JLabel lblIncorrectUsernameOr = new JLabel("Incorrect Username or Password");
		lblIncorrectUsernameOr.setVisible(false); 
		
		Image closeIcon = new ImageIcon(this.getClass().getResource("/CloseIcon.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JButton CloseButton = new JButton("");
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//dispose();  
				System.exit(0);
			}
		});
		
		CloseButton.setIcon(new ImageIcon(closeIcon));
		CloseButton.setBackground(null);
		CloseButton.setBorderPainted(false);
		CloseButton.setBorder(null);
		CloseButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		
		CloseButton.setBounds(450, 30, 20, 20);
		contentPane.add(CloseButton); 
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(207, 57, 84, 55);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(125, 143, 97, 30);
		contentPane.add(lblUsername);
		
		
		UsernameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String username = UsernameTextField.getText().toString(); 
				
				boolean check = false;
				
				lblIncorrectUsernameOr.setVisible(false);
				
				for(String chars : UnAlowedCharacters.characters)
				{
					if(username.contains(chars))
					{
						check = true;  					
						break; 
					}
				}
				
				if(username.length()<5)
					check = true;
				
				
				if(check == true)
				{
					UsernameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 87, 87))); 
				}
				else
				{
					UsernameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(175, 255, 94))); 
				}
				
				String password = PasswordTextField.getText().toString(); 
				
				for(String chars : UnAlowedCharacters.characters)
				{
					if(password.contains(chars))
					{
						check = true;  					
						break; 
					}
				} 

				if(password.length()<8)
					check = true;
				
				if(check == true)
				{
					LogInButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 87, 87))); 
					LogInButton.setEnabled(false);
				}
				else
				{
					LogInButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(175, 255, 94))); 
					LogInButton.setEnabled(true);
				}
			}
		}); 
		
		UsernameTextField.setToolTipText("Your Username");
		UsernameTextField.setForeground(Color.WHITE);
		UsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		UsernameTextField.setBackground(null);
		UsernameTextField.setOpaque(false); 
		UsernameTextField.setBounds(125, 181, 250, 30); 
		UsernameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255))); 
		
		contentPane.add(UsernameTextField);
		UsernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(125, 236, 97, 30);
		contentPane.add(lblPassword);
		
		
		
		
		PasswordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) { 
				
				String password = PasswordTextField.getText().toString(); 
				
				boolean check = false;
				
				lblIncorrectUsernameOr.setVisible(false);
				
				for(String chars : UnAlowedCharacters.characters)
				{
					if(password.contains(chars))
					{
						check = true;  					
						break; 
					}
				} 

				if(password.length()<8)
					check = true;
				
				if(check == true)
				{
					PasswordTextField.setBorder(null);
					PasswordTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 87, 87))); 

					PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 25)));
				}
				else
				{
					PasswordTextField.setBorder(null);
					PasswordTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(175, 255, 94)));

					PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 25)));
				}
				
				
				String username = UsernameTextField.getText().toString(); 
				
				for(String chars : UnAlowedCharacters.characters)
				{
					if(username.contains(chars))
					{
						check = true;  					
						break; 
					}
				}
				
				if(username.length()<5)
					check = true;
				
				if(check == true)
				{
					LogInButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 87, 87))); 
					LogInButton.setEnabled(false);
				}
				else
				{
					LogInButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(175, 255, 94))); 
					LogInButton.setEnabled(true);
				}
			}
		}); 
		
		
		

		Image showPasswordIcon = new ImageIcon(this.getClass().getResource("/ShowPasswordIcon.png")).getImage().getScaledInstance(20, 10, java.awt.Image.SCALE_SMOOTH);
			
		JButton ShowPasswordButton = new JButton("");
		
		ShowPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				if(!checkViewPassword)
				{
					((JPasswordField) PasswordTextField).setEchoChar((char)0);
					PasswordTextField.setFont(new Font(PasswordTextField.getFont().getFontName(), PasswordTextField.getFont().getStyle(), 20));
				}
				else
				{
					((JPasswordField) PasswordTextField).setEchoChar('●');
					PasswordTextField.setFont(new Font(PasswordTextField.getFont().getFontName(), PasswordTextField.getFont().getStyle(), 18));
				}
				
				checkViewPassword = !checkViewPassword; 
			}
		});
		
		
		ShowPasswordButton.setIcon(new ImageIcon(showPasswordIcon));
		ShowPasswordButton.setBackground(null);
		ShowPasswordButton.setBorderPainted(false);
		ShowPasswordButton.setBorder(null);
		ShowPasswordButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		
		ShowPasswordButton.setBounds(355, 282, 20, 10);
		
		contentPane.add(ShowPasswordButton);
		
		

		PasswordTextField.setToolTipText("Your Password");
		PasswordTextField.setOpaque(false);
		PasswordTextField.setForeground(Color.WHITE);
		PasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PasswordTextField.setColumns(10);
		PasswordTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255)));
		PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 25)));
		PasswordTextField.setBackground((Color) null);
		PasswordTextField.setBounds(125, 274, 250, 30); 
		
		((JPasswordField) PasswordTextField).setEchoChar('●');
		PasswordTextField.setFont(new Font(PasswordTextField.getFont().getFontName(), PasswordTextField.getFont().getStyle(), 18));
				
		contentPane.add(PasswordTextField); 
		
		
		LogInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				String username = UsernameTextField.getText().toString().replaceAll("\\s",""); 
				String password = PasswordTextField.getText().toString().replaceAll("\\s",""); 	
				
				try {

					DataBaseConnection connection = new DataBaseConnection(); 
					connection.setCoonectionString(SQLConnectionString);
					connection.setDataBaseName(SQLDataBaseName); 
					connection.setUsername(SQLDataBaseUsername); 
					connection.setPassword(SQLDataBasePassword);
					connection.setSQLStatement("SELECT * FROM users_table WHERE userUsername = '"+username+"' AND userPassword = '"+password+"';");
					
					connection.CreateConnection(); 

					if(connection.isContainsRequest())
					{

						try {
							
							dispose();
							
							User user = new User();
							user.setUserID(connection.getSQLResult().get(0).get(0));
							user.setUserName(connection.getSQLResult().get(0).get(1));
							user.setUserSurname(connection.getSQLResult().get(0).get(2));
							user.setUserUsername(connection.getSQLResult().get(0).get(3));
							user.setUserPassword(connection.getSQLResult().get(0).get(4));
							user.setUserAddress(connection.getSQLResult().get(0).get(5));
							user.setUserPhone(connection.getSQLResult().get(0).get(6));
							user.setUserCredit(connection.getSQLResult().get(0).get(7));
							user.setUserRole(connection.getSQLResult().get(0).get(8));
							user.setUserProfilePicture(connection.getSQLResult().get(0).get(9));
							
							HomePage newPage = new HomePage(user);
							newPage.setUndecorated(true); 
							newPage.setPreferredSize(new Dimension(900, 550));
							newPage.setResizable(false); 
							
							FrameDragListener frameDragListener = new FrameDragListener(newPage);
							newPage.addMouseListener(frameDragListener);
							newPage.addMouseMotionListener(frameDragListener);

							newPage.pack();
							newPage.setLocationRelativeTo(null);
							
							newPage.setVisible(true); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New User logged in."); 
							logs.PrintLogs(); 
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					else
					{
						lblIncorrectUsernameOr.setVisible(true); 
						
						User refusedUser = new User();
						refusedUser.setUserUsername(username);
						refusedUser.setUserPassword(password);
						Logs logs = new Logs(); 
						logs.setUserInfo(refusedUser);
						logs.setLogMessage("User refused to connect."); 
						logs.PrintLogs(); 
					}
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
		
		LogInButton.setForeground(Color.WHITE);
		LogInButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		LogInButton.setBounds(125, 383, 250, 39); 
		LogInButton.setBackground(null);
		LogInButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		LogInButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(255, 255, 255))); 
		contentPane.add(LogInButton); 
		
		
		lblIncorrectUsernameOr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIncorrectUsernameOr.setForeground(new Color(255, 87, 87)); 
		lblIncorrectUsernameOr.setBounds(125, 326, 250, 14);
		contentPane.add(lblIncorrectUsernameOr);
		
	}
	

	public static class FrameDragListener extends MouseAdapter {

    private final JFrame frame;
    private Point mouseDownCompCoords = null;

    public FrameDragListener(JFrame frame) {
        this.frame = frame;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }
}
	
}



