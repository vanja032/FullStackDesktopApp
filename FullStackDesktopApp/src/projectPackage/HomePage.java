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

import projectPackage.HomePage.FrameDragListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomePage extends JFrame {

	private User user;
	private ArrayList<Image> sliderImages = new ArrayList<Image>();
	private JPanel contentPane;
	private int slider_current_index = 0;
	
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
					HomePage frame = new HomePage(new User());
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
	public HomePage(User user) throws IOException {
		
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
			  
		try {
		
		DataBaseConnection connection = new DataBaseConnection(); 
		connection.setCoonectionString(SQLConnectionString);
		connection.setDataBaseName(SQLDataBaseName); 
		connection.setUsername(SQLDataBaseUsername); 
		connection.setPassword(SQLDataBasePassword);
		connection.setSQLStatement("SELECT * FROM home_images_slider;");
		
		connection.CreateConnection(); 

		if(connection.isContainsRequest())
		{
			for(int i=0;i<connection.getSQLResult().size();i++)
			{
				URL url = new URL(connection.getSQLResult().get(i).get(1));
				Image image = ImageIO.read(url);
				
				image = image.getScaledInstance(300, image.getHeight(null)/(image.getWidth(null)/300), java.awt.Image.SCALE_SMOOTH);
				sliderImages.add(image);
			}
			
		}
		
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
		

		JLabel lblWelcomeToShop_1 = new JLabel("");
		JLabel LeftSlideButton = new JLabel("");
		JLabel RightSlideButton = new JLabel("");
		
		if(sliderImages.size()>0)
		{
			lblWelcomeToShop_1.setIcon(new ImageIcon(sliderImages.get(0)));
			slider_current_index = 0;
		}
		else
		{
			LeftSlideButton.setVisible(false);
			RightSlideButton.setVisible(false);
		}
		
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
			public void mouseEntered(MouseEvent arg0) {
				labelHome.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelHome.setForeground(new Color(181, 144, 51)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelHome.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelHome.setForeground(new Color(255, 198, 54));
				check = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelHome.setForeground(new Color(133, 107, 41));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelHome.setForeground(new Color(181, 144, 51));
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
		labelHome.setForeground(new Color(255, 198, 54));
		labelHome.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelHome.setBounds(64, 204, 70, 35);
		contentPane.add(labelHome);
		
		JLabel labelAccount = new JLabel("Account");
		labelAccount.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent e) {
				labelAccount.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelAccount.setForeground(new Color(170, 170, 170)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelAccount.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelAccount.setForeground(Color.WHITE); 
				check = true; 
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelAccount.setForeground(new Color(140, 140, 140)); 
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelAccount.setForeground(new Color(170, 170, 170));
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
		labelAccount.setForeground(Color.WHITE);
		labelAccount.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelAccount.setBounds(64, 250, 83, 35);
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
		
		JLabel lblUserNameAnd = new JLabel("User Name and Surname"); 
		lblUserNameAnd.setText(user.getUserName() + " " + user.getUserSurname());
		lblUserNameAnd.setForeground(Color.WHITE);
		lblUserNameAnd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserNameAnd.setBounds(160, 60, 242, 40);
		contentPane.add(lblUserNameAnd);
		
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
		lblValue.setBounds(452, 60, 205, 40);
		
		if(user != null && !user.getUserCredit().equals(""))
		{
			lblValue.setText((Math.round(Double.parseDouble(user.getUserCredit())*100.0)/100.0)+"");
		}
		
		contentPane.add(lblValue);
		
				
		JLabel lblWelcomeToShop = new JLabel("Welcome to Shop");
		lblWelcomeToShop.setForeground(Color.WHITE);
		lblWelcomeToShop.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWelcomeToShop.setBounds(418, 204, 220, 35);
		contentPane.add(lblWelcomeToShop);
		
		
		lblWelcomeToShop_1.setForeground(Color.WHITE);
		lblWelcomeToShop_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWelcomeToShop_1.setBounds(380, 261, 300, 160);
		contentPane.add(lblWelcomeToShop_1);
		
		
		Image LeftIcon = new ImageIcon(this.getClass().getResource("/LeftArrowIcon.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image RightIcon = new ImageIcon(this.getClass().getResource("/RightArrowIcon.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		
		LeftSlideButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { 
				LeftSlideButton.setVisible(false);
				
				slider_current_index--;
				if(slider_current_index < 0)
					slider_current_index = sliderImages.size()-1;
				lblWelcomeToShop_1.setIcon(new ImageIcon(sliderImages.get(slider_current_index)));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				LeftSlideButton.setVisible(true);
			}
		});
		LeftSlideButton.setForeground(Color.WHITE);
		LeftSlideButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		LeftSlideButton.setIcon(new ImageIcon(LeftIcon));
		LeftSlideButton.setBounds(310, 316, 50, 50); 
		LeftSlideButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(LeftSlideButton);
		
	
		RightSlideButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RightSlideButton.setVisible(false);
				
				slider_current_index++;
				if(slider_current_index > sliderImages.size()-1)
					slider_current_index = 0;
				lblWelcomeToShop_1.setIcon(new ImageIcon(sliderImages.get(slider_current_index)));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				RightSlideButton.setVisible(true);
			}
		});
		RightSlideButton.setForeground(Color.WHITE);
		RightSlideButton.setFont(new Font("Tahoma", Font.BOLD, 25)); 
		RightSlideButton.setIcon(new ImageIcon(RightIcon));
		RightSlideButton.setBounds(700, 316, 50, 50); 
		RightSlideButton.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(RightSlideButton);
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
