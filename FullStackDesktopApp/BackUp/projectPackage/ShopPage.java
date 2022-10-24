package projectPackage;

import java.awt.Desktop;
import java.net.MalformedURLException;
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
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.LayoutManager;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPackage.ShopPage.FrameDragListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ShopPage extends JFrame {

	private User user;
	private JPanel contentPane;
	
	private String SQLConnectionString = "";
	private String SQLDataBaseName = "";
	private String SQLDataBaseUsername = "";
	private String SQLDataBasePassword = "";
	
	private Stock stock = new Stock(); 
	private Cart cart = new Cart(); 
	private Orders orders = new Orders(); 
	int yPos = 30;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopPage frame = new ShopPage(new User());
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
	public ShopPage(User user) throws IOException {
		
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
		
		JLabel labelShop = new JLabel("Shop");
		labelShop.addMouseListener(new MouseAdapter() {
			boolean check = false; 
			@Override
			public void mouseEntered(MouseEvent arg0) {
				labelShop.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
				labelShop.setForeground(new Color(181, 144, 51)); 
				check = false;
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				labelShop.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
				labelShop.setForeground(new Color(255, 198, 54));
				check = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				labelShop.setForeground(new Color(133, 107, 41));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(check == false)
					labelShop.setForeground(new Color(181, 144, 51));
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
		
		labelShop.setForeground(new Color(255, 198, 54));
		labelShop.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelShop.setBounds(64, 296, 60, 35);
		contentPane.add(labelShop);
		
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
		labelHome.setBounds(64, 204, 70, 35);
		contentPane.add(labelHome);
		
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
		lblValue.setBounds(452, 60, 205, 40);
		
		if(user != null && !user.getUserCredit().equals(""))
		{
			lblValue.setText((Math.round(Double.parseDouble(user.getUserCredit())*100.0)/100.0)+"");
		}
		
		contentPane.add(lblValue);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 200, 539, 219); 
		scrollPane.setBackground(new Color(61, 61, 61));
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
		scrollPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(90, 90, 90))); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
				

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(61, 61, 61));
		panel.setBounds(0, 0, 530, 219); 
		scrollPane.setViewportView(panel); 
		
		JLabel lblShowItemsHeader = new JLabel("");
		lblShowItemsHeader.setForeground(Color.WHITE);
		lblShowItemsHeader.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblShowItemsHeader.setBounds(295, 154, 346, 35);
		contentPane.add(lblShowItemsHeader); 
		
		JLabel lblAllItems = new JLabel("All items");
		lblAllItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) { 
				
				panel.removeAll();
				yPos = 30;
								
				for(int i=0;i<stock.getStockSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(stock.getStock().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(stock.getStock().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+stock.getStock().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Add to cart"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 198, 54));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = stock.getStock().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							cart.setStock(removeItem); 
							pan.setVisible(false);
							stock.RemoveElement(removeItem); 

							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item added to cart. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
				
				
				for(int i=0;i<cart.getCartSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(cart.getCart().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(cart.getCart().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+cart.getCart().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Order now"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(175, 255, 94));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = cart.getCart().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							double availableMoney = /*Math.round(*/Double.parseDouble(user.getUserCredit())/**100.0)/100.0*/; 
							double costMoney = /*Math.round(*/Double.parseDouble(removeItem.getItemPrice())/**100.0)/100.0*/; 
							if(availableMoney<costMoney)
							{
								JOptionPane.showMessageDialog(null, "Order cannot be done, because you don't have enough money in your wallet!"); 
								return;
							}
							availableMoney -= costMoney;
							orders.setOrder(removeItem);  
							pan.setVisible(false);
							cart.RemoveElement(removeItem); 
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+""); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item ordered now. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					

				for(int i=0;i<orders.getOrdersSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(orders.getOrders().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(orders.getOrders().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+orders.getOrders().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Remove item"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 87, 87));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = orders.getOrders().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							double availableMoney = Double.parseDouble(user.getUserCredit()); 
							double costMoney = Double.parseDouble(removeItem.getItemPrice()); 
							availableMoney += costMoney;
							
							pan.setVisible(false);
							orders.RemoveElement(removeItem); 
							stock.setStock(removeItem); 
							
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+"");
							
							JOptionPane.showMessageDialog(null, "Your money is going to be back to your wallet."); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("Item removed from Orders. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					
				JOptionPane.showMessageDialog(null, "All Items Displayed.\nNumber of items in stock is "+stock.getStockSize()+"\nNumber of items in cart is "+cart.getCartSize()+"\nNumber of ordered items is "+orders.getOrdersSize()); 
				lblShowItemsHeader.setText("All Items Displayed"); 
				panel.revalidate();
				panel.repaint(); 
			}
		}); 
		 
		lblAllItems.setForeground(Color.WHITE);
		lblAllItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAllItems.setBounds(300, 441, 89, 35);
		lblAllItems.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(lblAllItems);
		
		JLabel lblShoppingCart = new JLabel("Shopping Cart");
		lblShoppingCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) { 

				panel.removeAll();
				yPos = 30;
								
				for(int i=0;i<cart.getCartSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(cart.getCart().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(cart.getCart().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+cart.getCart().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Order now"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(175, 255, 94));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = cart.getCart().get(i); 
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) { 
							double availableMoney = /*Math.round(*/Double.parseDouble(user.getUserCredit())/**100.0)/100.0*/; 
							double costMoney = /*Math.round(*/Double.parseDouble(removeItem.getItemPrice())/**100.0)/100.0*/; 
							if(availableMoney<costMoney)
							{
								JOptionPane.showMessageDialog(null, "Order cannot be done, because you don't have enough money in your wallet!"); 
								return;
							}
							availableMoney -= costMoney;
							orders.setOrder(removeItem);  
							pan.setVisible(false);
							cart.RemoveElement(removeItem); 
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+""); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item ordered now. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					
				JOptionPane.showMessageDialog(null, "Shopping Cart Displayed.\nNumber of items in cart is "+cart.getCartSize()); 
				lblShowItemsHeader.setText("Shopping Cart"); 
				panel.revalidate();
				panel.repaint();
			}
		});
		lblShoppingCart.setForeground(Color.WHITE);
		lblShoppingCart.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblShoppingCart.setBounds(420, 441, 148, 35);
		lblShoppingCart.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(lblShoppingCart);
		
		JLabel lblCheckedOutItems = new JLabel("Checked out Items");
		lblCheckedOutItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

				panel.removeAll();
				yPos = 30;
								
				for(int i=0;i<orders.getOrdersSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(orders.getOrders().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(orders.getOrders().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+orders.getOrders().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Remove item"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 87, 87));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = orders.getOrders().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							double availableMoney = Double.parseDouble(user.getUserCredit()); 
							double costMoney = Double.parseDouble(removeItem.getItemPrice()); 
							availableMoney += costMoney;
							
							pan.setVisible(false);
							orders.RemoveElement(removeItem); 
							stock.setStock(removeItem); 
							
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+"");
							
							JOptionPane.showMessageDialog(null, "Your money is going to be back to your wallet."); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("Item removed from Orders. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					
				JOptionPane.showMessageDialog(null, "All Orders Displayed.\nNumber of ordered items is "+orders.getOrdersSize()); 
				lblShowItemsHeader.setText("All Orders"); 
				panel.revalidate();
				panel.repaint();
			}
		});
		lblCheckedOutItems.setForeground(Color.WHITE);
		lblCheckedOutItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCheckedOutItems.setBounds(595, 441, 200, 35);
		lblCheckedOutItems.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		contentPane.add(lblCheckedOutItems); 
		

		JLabel lblInsertAllItems = new JLabel("Insert items");
		
		lblInsertAllItems.addMouseListener(new MouseAdapter() { 
			
			@Override
			public void mouseClicked(MouseEvent arg0) { 


				panel.removeAll();
				yPos = 30;
								
				for(int i=0;i<stock.getStockSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(stock.getStock().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(stock.getStock().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+stock.getStock().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Add to cart"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 198, 54));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = stock.getStock().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							cart.setStock(removeItem); 
							pan.setVisible(false);
							stock.RemoveElement(removeItem); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item added to cart. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
				
				
				for(int i=0;i<cart.getCartSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(cart.getCart().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(cart.getCart().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+cart.getCart().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Order now"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(175, 255, 94));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = cart.getCart().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							double availableMoney = /*Math.round(*/Double.parseDouble(user.getUserCredit())/**100.0)/100.0*/; 
							double costMoney = /*Math.round(*/Double.parseDouble(removeItem.getItemPrice())/**100.0)/100.0*/; 
							if(availableMoney<costMoney)
							{
								JOptionPane.showMessageDialog(null, "Order cannot be done, because you don't have enough money in your wallet!"); 
								return;
							}
							availableMoney -= costMoney;
							orders.setOrder(removeItem);  
							pan.setVisible(false);
							cart.RemoveElement(removeItem); 
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+""); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item ordered now. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					

				for(int i=0;i<orders.getOrdersSize();i++)
				{ 
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					try { 

						URL url = new URL(orders.getOrders().get(i).getItemImage().toString()); 
						Image image = ImageIO.read(url); 

						image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH); 

						JLabel newItemImage = new JLabel("");
						newItemImage.setBounds(40, 0, 100, 50);
						newItemImage.setIcon(new ImageIcon(image));
						pan.add(newItemImage);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
					JLabel newItemLabel1 = new JLabel(""); 
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(orders.getOrders().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel(""); 
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+orders.getOrders().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Remove item"); 
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 87, 87));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
										
					Item removeItem = orders.getOrders().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							double availableMoney = Double.parseDouble(user.getUserCredit()); 
							double costMoney = Double.parseDouble(removeItem.getItemPrice()); 
							availableMoney += costMoney;
							
							pan.setVisible(false);
							orders.RemoveElement(removeItem); 
							stock.setStock(removeItem); 
							
							user.setUserCredit(availableMoney+""); 
							lblValue.setText((Math.round(availableMoney*100.0)/100.0)+"");
							
							JOptionPane.showMessageDialog(null, "Your money is going to be back to your wallet."); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("Item removed from Orders. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
					
				JOptionPane.showMessageDialog(null, "All Items Displayed.\nNumber of items in stock is "+stock.getStockSize()+"\nNumber of items in cart is "+cart.getCartSize()+"\nNumber of ordered items is "+orders.getOrdersSize()); 
				lblShowItemsHeader.setText("All Items Displayed"); 
				panel.revalidate();
				panel.repaint(); 
				
				try {
					
					DataBaseConnection connection = new DataBaseConnection(); 
					connection.setCoonectionString(SQLConnectionString);
					connection.setDataBaseName(SQLDataBaseName); 
					connection.setUsername(SQLDataBaseUsername); 
					connection.setPassword(SQLDataBasePassword);
					connection.setSQLStatement("SELECT * FROM items_table;");
					
					connection.CreateConnection(); 
					

					if(connection.isContainsRequest())
					{
												
						for(int i = 0; i < connection.getSQLResult().size(); i++)
						{
							stock.setStock(new Item(connection.getSQLResult().get(i).get(0), connection.getSQLResult().get(i).get(1), connection.getSQLResult().get(i).get(2), connection.getSQLResult().get(i).get(3), connection.getSQLResult().get(i).get(4), Integer.parseInt(connection.getSQLResult().get(i).get(5))));
							
							JPanel pan = new JPanel();
							pan.setBackground(new Color(61, 61, 61));
							pan.setBounds(0, yPos, 530, 50);  
							pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
							
							
							URL url = new URL(connection.getSQLResult().get(i).get(2));
							Image image = ImageIO.read(url);
							
							image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
							
							JLabel newItemImage = new JLabel("");
							newItemImage.setBounds(40, 0, 100, 50);
							newItemImage.setIcon(new ImageIcon(image));
							pan.add(newItemImage);
							

							
							JLabel newItemLabel1 = new JLabel("");
							newItemLabel1.setBounds(170, 0, 80, 50);
							newItemLabel1.setForeground(Color.WHITE);
							newItemLabel1.setText(stock.getStock().get(i).getItemName());
							pan.add(newItemLabel1);
							
							JLabel newItemLabel2 = new JLabel("");
							newItemLabel2.setBounds(280, 0, 50, 50);
							newItemLabel2.setForeground(Color.WHITE);
							newItemLabel2.setText("$"+stock.getStock().get(i).getItemPrice());
							pan.add(newItemLabel2);
							
							JLabel newItemLabel3 = new JLabel("Add to cart");
							newItemLabel3.setBounds(360, 0, 70, 50);
							newItemLabel3.setForeground(new Color(255, 198, 54));
							newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
							pan.add(newItemLabel3); 
														
							Item removeItem = stock.getStock().get(i);
							
							newItemLabel3.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) { 
									cart.setStock(removeItem); 
									pan.setVisible(false);
									stock.RemoveElement(removeItem); 
									
									Logs logs = new Logs(); 
									logs.setUserInfo(user);
									logs.setLogMessage("New Item added to cart. "+removeItem.toString()); 
									logs.PrintLogs(); 
								}
							});
							
							panel.add(pan);
							
							yPos+=80;
						}
						
						JOptionPane.showMessageDialog(null, "New Items Added.\nNumber of items in stock is "+stock.getStockSize()); 
						lblShowItemsHeader.setText("New Items Added"); 
						panel.revalidate();
						panel.repaint();
						
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				} 
			}
		});
		
		lblInsertAllItems.setForeground(Color.WHITE);
		lblInsertAllItems.setFont(new Font("Tahoma", Font.PLAIN, 24)); 
		lblInsertAllItems.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		lblInsertAllItems.setBounds(686, 154, 148, 35); 
		contentPane.add(lblInsertAllItems); 
		
		
		try {
			
			DataBaseConnection connection = new DataBaseConnection(); 
			connection.setCoonectionString(SQLConnectionString);
			connection.setDataBaseName(SQLDataBaseName); 
			connection.setUsername(SQLDataBaseUsername); 
			connection.setPassword(SQLDataBasePassword);
			connection.setSQLStatement("SELECT * FROM items_table;");
			
			connection.CreateConnection(); 
			

			if(connection.isContainsRequest())
			{ 
				
				for(int i = 0; i < connection.getSQLResult().size(); i++)
				{
					stock.setStock(new Item(connection.getSQLResult().get(i).get(0), connection.getSQLResult().get(i).get(1), connection.getSQLResult().get(i).get(2), connection.getSQLResult().get(i).get(3), connection.getSQLResult().get(i).get(4), Integer.parseInt(connection.getSQLResult().get(i).get(5))));
					
					JPanel pan = new JPanel();
					pan.setBackground(new Color(61, 61, 61));
					pan.setBounds(0, yPos, 530, 50);  
					pan.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
					
					
					URL url = new URL(connection.getSQLResult().get(i).get(2));
					Image image = ImageIO.read(url);
					
					image = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
					
					JLabel newItemImage = new JLabel("");
					newItemImage.setBounds(40, 0, 100, 50);
					newItemImage.setIcon(new ImageIcon(image));
					pan.add(newItemImage);
					

					
					JLabel newItemLabel1 = new JLabel("");
					newItemLabel1.setBounds(170, 0, 80, 50);
					newItemLabel1.setForeground(Color.WHITE);
					newItemLabel1.setText(stock.getStock().get(i).getItemName());
					pan.add(newItemLabel1);
					
					JLabel newItemLabel2 = new JLabel("");
					newItemLabel2.setBounds(280, 0, 50, 50);
					newItemLabel2.setForeground(Color.WHITE);
					newItemLabel2.setText("$"+stock.getStock().get(i).getItemPrice());
					pan.add(newItemLabel2);
					
					JLabel newItemLabel3 = new JLabel("Add to cart");
					newItemLabel3.setBounds(360, 0, 70, 50);
					newItemLabel3.setForeground(new Color(255, 198, 54));
					newItemLabel3.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
					pan.add(newItemLabel3); 
												
					Item removeItem = stock.getStock().get(i);
					
					newItemLabel3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							cart.setStock(removeItem); 
							pan.setVisible(false);
							stock.RemoveElement(removeItem); 
							
							Logs logs = new Logs(); 
							logs.setUserInfo(user);
							logs.setLogMessage("New Item added to cart. "+removeItem.toString()); 
							logs.PrintLogs(); 
						}
					});
					
					panel.add(pan);
					
					yPos+=80;
				}
				 
				panel.revalidate();
				panel.repaint();
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		} 
		
		
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
