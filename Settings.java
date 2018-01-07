package texasholdem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

import javax.swing.UIManager;

/*
 * 	create set players name action and update each players name
 * create initial money and update money
 * 
 * 
 * 
 */




public class Settings extends JFrame {

	private JPanel contentPane;
	JTextField textField;
	private Player[] players;
	private Table table;
	private int startMoney=0;
	private final int MAX_OF_PLAYERS=5;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();

				// The window will open at the center of the Frame
					frame.setLocationRelativeTo(null);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public Settings() {

		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		setTitle("Settings");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
// Text field to enter the starting money
		
		JLabel lblNewLabel = new JLabel("Starting money:");
		lblNewLabel.setBounds(90, 122, 107, 16);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		String value = String.valueOf(startMoney);
		textField.setText(value);

		textField.setBounds(196, 116, 134, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBounds(156, 50, 117, 29);
		

		
//================ Action Listener =================
	// Main Menu Action Listener	
		mainMenuButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e){
				MainMenu frame= new MainMenu();
				frame.setStartMoney( getStartMoney() );
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
	
	
		
		textField.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e){
			
				String input = textField.getText();
		// change all players starting money
			try{
				 
				startMoney = Integer.valueOf(input);
				System.out.println("Start Money: " + startMoney);
				setStartMoney(startMoney);
				
			}
		// display errors when text field is not an integer
			catch( Exception ex){
				JOptionPane.showMessageDialog(null, "Please enter an Integer");
			}
			finally
			{
				
			}
		}

	
		});
		
		
		panel.add(mainMenuButton);
	}// ================= END OF SETTINGS() ================= 
	
	
	public void setStartMoney(int money){
		this.startMoney = money;
	}
	
	public int getStartMoney(){
		return startMoney;
	}


	
	
}
