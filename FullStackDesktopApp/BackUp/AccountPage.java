package projectPackage;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPackage.AccountPage.FrameDragListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AccountPage extends JFrame {

	private User user;
	private JPanel contentPane;
	
	private String SQLConnectionString = "";
	private String SQLDataBaseName = "";
	private String SQLDataBaseUsername = "";
	private String SQLDataBasePassword = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountPage frame = new AccountPage(new User());
					frame.setUndecorated(true); 
					frame.setPreferredSize(new Dimension(900, 550));
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
	public AccountPage(User user) throws IOException {
		
		this.user = user;
		
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
			  
		
		setBounds(100, 100, 900, 550);
		setBackground(new Color(61, 61, 61));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(61, 61, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
		contentPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(90, 90, 90))); 
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
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
		
		CloseButton.setBounds(850, 30, 20, 20);
		contentPane.add(CloseButton); 
		
		
		Image AccountIcon = new ImageIcon(this.getClass().getResource("/AccountIcon.png")).getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		JLabel AccountIconLabel = new JLabel("");
		AccountIconLabel.setBounds(64, 45, 70, 70);
		AccountIconLabel.setIcon(new ImageIcon(AccountIcon)); 
		if(user != null && !user.getUserProfilePicture().equals(""))
		{
			URL url = new URL(user.getUserProfilePicture());
			Image image = ImageIO.read(url);
			
			image = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			
			AccountIconLabel.setIcon(new ImageIcon(image));
		}
		
		contentPane.add(AccountIconLabel);
		
		JLabel labelHome = new JLabel("Home");
		labelHome.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent e) {
				labelHome.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelHome.setForeground(new Color(170, 170, 170)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelHome.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelHome.setForeground(Color.WHITE); 
				check = true; 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelHome.setForeground(new Color(140, 140, 140));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelHome.setForeground(new Color(170, 170, 170));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					dispose();
					
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
				} catch (Exception ext) {
					ext.printStackTrace();
				}
			}
		});
		labelHome.setForeground(Color.WHITE);
		labelHome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelHome.setBounds(64, 204, 62, 35);
		contentPane.add(labelHome);
		
		
		JLabel labelAccount = new JLabel("Account");
		labelAccount.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent arg0) {
				labelAccount.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelAccount.setForeground(new Color(181, 144, 51)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelAccount.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelAccount.setForeground(new Color(255, 198, 54));
				check = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelAccount.setForeground(new Color(133, 107, 41));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelAccount.setForeground(new Color(181, 144, 51));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					dispose();
					
					AccountPage newPage = new AccountPage(user);
					newPage.setUndecorated(true); 
					newPage.setPreferredSize(new Dimension(900, 550));
					newPage.setResizable(false); 
					
					FrameDragListener frameDragListener = new FrameDragListener(newPage);
					newPage.addMouseListener(frameDragListener);
					newPage.addMouseMotionListener(frameDragListener);

					newPage.pack();
					newPage.setLocationRelativeTo(null);
					
					newPage.setVisible(true); 
				} catch (Exception ext) {
					ext.printStackTrace();
				}
			}
			
		});
		labelAccount.setForeground(new Color(255, 198, 54));
		labelAccount.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelAccount.setBounds(64, 250, 130, 35);
		contentPane.add(labelAccount);
		
		
		JLabel labelShop = new JLabel("Shop");
		labelShop.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent e) { 
				labelShop.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelShop.setForeground(new Color(170, 170, 170)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelShop.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelShop.setForeground(Color.WHITE); 
				check = true; 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelShop.setForeground(new Color(140, 140, 140));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelShop.setForeground(new Color(170, 170, 170));
			} 
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					dispose();
					
					ShopPage newPage = new ShopPage(user);
					newPage.setUndecorated(true); 
					newPage.setPreferredSize(new Dimension(900, 550));
					newPage.setResizable(false); 
					
					FrameDragListener frameDragListener = new FrameDragListener(newPage);
					newPage.addMouseListener(frameDragListener);
					newPage.addMouseMotionListener(frameDragListener);

					newPage.pack();
					newPage.setLocationRelativeTo(null);
					
					newPage.setVisible(true); 
				} catch (Exception ext) {
					ext.printStackTrace();
				}
			}
		});
		labelShop.setForeground(Color.WHITE);
		labelShop.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelShop.setBounds(64, 296, 52, 35);
		contentPane.add(labelShop);
		
		JLabel labelSignOut = new JLabel("Sign out");
		labelSignOut.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent e) { 
				labelSignOut.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelSignOut.setForeground(new Color(170, 170, 170)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent arg0) { 
				labelSignOut.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelSignOut.setForeground(Color.WHITE); 
				check = true; 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelSignOut.setForeground(new Color(140, 140, 140));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelSignOut.setForeground(new Color(170, 170, 170));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				
				dispose();
				
				LoginPage newPage = new LoginPage();
				newPage.setUndecorated(true); 
				newPage.setPreferredSize(new Dimension(500, 510));
				newPage.setResizable(false); 
				
				FrameDragListener frameDragListener = new FrameDragListener(newPage);
				newPage.addMouseListener(frameDragListener);
				newPage.addMouseMotionListener(frameDragListener);

				newPage.pack();
				newPage.setLocationRelativeTo(null);
				
				newPage.setVisible(true); 
				
				Logs logs = new Logs(); 
				logs.setUserInfo(user);
				logs.setLogMessage("User signed out from App."); 
				logs.PrintLogs(); 
				
			} catch (Exception ext) {
				ext.printStackTrace();
			}
			}
		});
		labelSignOut.setForeground(Color.WHITE);
		labelSignOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelSignOut.setBounds(64, 342, 86, 35);
		contentPane.add(labelSignOut);
		
		JLabel labelAdminPanel = new JLabel("Admin panel");
		labelAdminPanel.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent e) { 
				labelAdminPanel.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelAdminPanel.setForeground(new Color(170, 170, 170)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelAdminPanel.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelAdminPanel.setForeground(Color.WHITE); 
				check = true; 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelAdminPanel.setForeground(new Color(140, 140, 140));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelAdminPanel.setForeground(new Color(170, 170, 170));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				
				dispose();
				
				AdminPage newPage = new AdminPage(user);
				newPage.setUndecorated(true); 
				newPage.setPreferredSize(new Dimension(900, 550));
				newPage.setResizable(false); 
				
				FrameDragListener frameDragListener = new FrameDragListener(newPage);
				newPage.addMouseListener(frameDragListener);
				newPage.addMouseMotionListener(frameDragListener);

				newPage.pack();
				newPage.setLocationRelativeTo(null);
				
				newPage.setVisible(true); 
			} catch (Exception ext) {
				ext.printStackTrace();
			}
			}
		});
		labelAdminPanel.setForeground(Color.WHITE);
		labelAdminPanel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelAdminPanel.setBounds(64, 388, 130, 35);
		contentPane.add(labelAdminPanel);


		JLabel lblUserNameAnd_1 = new JLabel("");
		lblUserNameAnd_1.setForeground(Color.WHITE);
		lblUserNameAnd_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserNameAnd_1.setBounds(220, 204, 5, 219); 
		lblUserNameAnd_1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(255, 255, 255))); 
		contentPane.add(lblUserNameAnd_1);
		
		
		if(user.getUserRole().equals("admin"))
		{
			labelAdminPanel.setVisible(true); 
			//lblUserNameAnd_1.setBounds(220, 204, 5, 219); 
			
		}
		else
		{
			labelAdminPanel.setVisible(false); 
			//lblUserNameAnd_1.setBounds(220, 204, 5, 173); 
		}
		
		
		
		JLabel lblUserNameAnd = new JLabel("User Name and Surname"); 
		lblUserNameAnd.setText(user.getUserName() + " " + user.getUserSurname());
		lblUserNameAnd.setForeground(Color.WHITE);
		lblUserNameAnd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserNameAnd.setBounds(160, 60, 242, 40);
		contentPane.add(lblUserNameAnd);
		
		
		Image HelpIcon = new ImageIcon(this.getClass().getResource("/HelpIcon.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JButton HelpButton = new JButton(""); 
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
					String urlOpen = "";
				    try {
				    	try {
							  File DataInfo = new File("Resources/HelpInfoData.dll");
							  Scanner dataReader = new Scanner(DataInfo);
							  
							  if(dataReader.hasNextLine()) {
								   String line = dataReader.nextLine();
							       String[] array = line.replaceAll("\\s","").split(":", 2);
							       
							       if(array[0].equals("ExternalHelpLink"))
							    	   urlOpen = array[1]; 
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
							  
				    	
						Desktop.getDesktop().browse(new URI(urlOpen));
						
					} catch (IOException | URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		});
		HelpButton.setIcon(new ImageIcon(HelpIcon));
		HelpButton.setBorderPainted(false);
		HelpButton.setBorder(null);
		HelpButton.setBackground((Color) null);
		HelpButton.setBounds(30, 500, 20, 20);
		HelpButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(HelpButton);

		
		Image MoneyIcon = new ImageIcon(this.getClass().getResource("/MoneyIcon.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel MoneyLabel = new JLabel("");
		MoneyLabel.setIcon(new ImageIcon(MoneyIcon));
		MoneyLabel.setBounds(412, 65, 30, 30);
		contentPane.add(MoneyLabel);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setForeground(Color.WHITE);
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblValue.setBounds(452, 60, 141, 40);
		
		if(user != null && !user.getUserCredit().equals(""))
		{
			lblValue.setText((Math.round(Double.parseDouble(user.getUserCredit())*100.0)/100.0)+"");
		}
		
		contentPane.add(lblValue);
		
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(341, 204, 171, 35); 
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname: ");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSurname.setBounds(341, 250, 171, 35); 
		contentPane.add(lblSurname);
		
		JLabel lblName_1_1 = new JLabel("Username: ");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName_1_1.setBounds(341, 296, 171, 35);
		contentPane.add(lblName_1_1);
		
		JLabel lblName_1_1_1 = new JLabel("Address: ");
		lblName_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName_1_1_1.setBounds(341, 342, 171, 35);
		contentPane.add(lblName_1_1_1);
		
		JLabel lblName_1_1_1_1 = new JLabel("Phone: ");
		lblName_1_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName_1_1_1_1.setBounds(341, 388, 171, 35);
		contentPane.add(lblName_1_1_1_1);
		
		JLabel lblRole = new JLabel("Role: ");
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRole.setBounds(627, 61, 62, 40);
		contentPane.add(lblRole);
		
		JLabel labelName = new JLabel("User Name");
		labelName.setForeground(Color.WHITE);
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelName.setBounds(522, 204, 311, 35);
		if(user != null && !user.getUserName().equals(""))
		{
			labelName.setText(user.getUserName());
		}
		
		contentPane.add(labelName);
		
		JLabel labelSurname = new JLabel("User Surname");
		labelSurname.setForeground(Color.WHITE);
		labelSurname.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelSurname.setBounds(522, 250, 311, 35); 
		if(user != null && !user.getUserSurname().equals(""))
		{
			labelSurname.setText(user.getUserSurname());
		}
		
		contentPane.add(labelSurname);
		
		JLabel labelUsername = new JLabel("User Username");
		labelUsername.setForeground(Color.WHITE);
		labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelUsername.setBounds(522, 296, 311, 35); 
		if(user != null && !user.getUserUsername().equals(""))
		{
			labelUsername.setText(user.getUserUsername());
		}
		
		contentPane.add(labelUsername);
		
		JLabel labelAddress = new JLabel("User Address");
		labelAddress.setForeground(Color.WHITE);
		labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelAddress.setBounds(522, 342, 311, 35); 
		if(user != null && !user.getUserAddress().equals(""))
		{
			labelAddress.setText(user.getUserAddress());
		}
		
		contentPane.add(labelAddress);
		
		JLabel labelPhone = new JLabel("User Phone");
		labelPhone.setForeground(Color.WHITE);
		labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPhone.setBounds(522, 388, 311, 35); 
		if(user != null && !user.getUserPhone().equals(""))
		{
			labelPhone.setText(user.getUserPhone());
		}
		
		contentPane.add(labelPhone);
		
		JLabel labelRole = new JLabel("User Role");
		labelRole.setForeground(Color.WHITE);
		labelRole.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelRole.setBounds(699, 61, 171, 40); 
		if(user != null && !user.getUserRole().equals(""))
		{
			labelRole.setText(user.getUserRole());
		}
		
		contentPane.add(labelRole);
		
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
