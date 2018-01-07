package texasholdem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractAction;

import java.awt.event.*;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Label;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;

import javax.swing.UIManager;
import javax.swing.JSlider;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	private final int MAX_WIDTH=963;
	private final int MAX_HEIGHT=773;
	private static final int AMOUNT_OF_PLAYERS=5;
	
	private static Table table=new Table();
	private ImageIcon exitImage;
	private ImageIcon pokerTableImage;
	private ImageIcon settingsImage;
	private ImageIcon startImage;
	private ImageIcon titleImage;
	
	private Settings settings;
	private int startMoney=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					MainMenu frame = new MainMenu();
					
			// The JFrame is center of the computer displays
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	
	public int getStartMoney(){
		return startMoney;
	}
	
	public void setStartMoney(int money){
		this.startMoney = money;
	}

// =====================  END OF MAIN =====================
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 100, 944, 773);
		
// Menu bar that has File, Setting, Help		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		
		JMenuItem mntmEndGame = new JMenuItem("End Game");
		mntmEndGame.setEnabled(false);
		mnFile.add(mntmEndGame);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowToPlay = new JMenuItem("How To Play");
		mnHelp.add(mntmHowToPlay);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);

		
// ======= Locating Images in any computer ========
		exitImage = new ImageIcon( getClass().getResource("Icons/ExitButton.png") );
		pokerTableImage = new ImageIcon( getClass().getResource("Icons/pokerTable.jpg") );
		settingsImage = new ImageIcon( getClass().getResource("Icons/Settings.png") );
		startImage = new ImageIcon( getClass().getResource("Icons/StartGameButton.png") );
		titleImage = new ImageIcon( getClass().getResource("Icons/Title.png") );

// Exit Button
		JButton exitButton = new JButton("");
		exitButton.setIcon(exitImage);
		exitButton.setBounds(407, 389, 149, 59);
	
// Setting Button		
		JButton settingButton = new JButton("");
		settingButton.setBackground(UIManager.getColor("textHighlight"));
		settingButton.setIcon(settingsImage);
		settingButton.setBounds(338, 314, 287, 59);
		
// Start Button
		JButton startGame = new JButton("");
		startGame.setBackground(UIManager.getColor("textHighlight"));
		startGame.setIcon(startImage);
		startGame.setBounds(309, 236, 366, 59);
		
		
		panel.setLayout(null);
		panel.add(settingButton);
		panel.add(exitButton);
		panel.add(startGame);

// Display the title
		JLabel titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setIcon(titleImage);
		titleLabel.setBounds(66, 32, 830, 223);
		panel.add(titleLabel);
		

// Display poker table at the background
		JLabel wallpaper = new JLabel("");
		wallpaper.setIcon(pokerTableImage);
		wallpaper.setBounds(0, 0, 934, 721);
		panel.add(wallpaper);
	
		
//============= Action Listener =============		
	exitButton.addActionListener(new ActionListener() {
		public void actionPerformed( ActionEvent e ){
			System.exit(0);
		}
		
	});
	
	startGame.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e){
			Main main = new Main();
			main.setStartMoney( getStartMoney() );
			main.game();
			dispose();
		}
	});
	
	settingButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e){
			settings= new Settings();
			settings.setVisible(true);
			settings.setLocationRelativeTo(null);
			dispose();
		}
	});
	
//===================== Menu Bar Action Listener =============================

	
	
	// New Game Action Listener
	class newGameAction implements ActionListener{
		public void actionPerformed( ActionEvent e){
			Main main = new Main();
			main.game();
			dispose();
		}
	}
	
	mntmNewGame.addActionListener(new newGameAction());
	
	// Quit Action Listener
	class quitAction implements ActionListener{
		public void actionPerformed( ActionEvent e){
			System.exit(0);
		}
	}
	
	mntmQuit.addActionListener(new quitAction() );
	
	// About Action Listener
	class aboutAction implements ActionListener{
		public void actionPerformed( ActionEvent e){
			final JFrame aboutFrame= new JFrame("About");
			JPanel aboutPanel= new JPanel();

// Displays the About in Setting
			
			aboutFrame.setSize(300,200);
			
			JLabel aboutLabel= new JLabel("Texas Hold'em Poker");
			aboutLabel.setBounds(85, 20, 150, 50);
			
			JLabel aboutLabel_2= new JLabel("Version 1.0");
			aboutLabel_2.setBounds(110, 50, 100, 50);
			
			aboutPanel.setLayout(null);
			aboutFrame.getContentPane().add(aboutPanel);
			aboutPanel.add(aboutLabel);
			aboutPanel.add(aboutLabel_2);
			aboutFrame.setResizable(false);
	
			aboutFrame.setLocationRelativeTo(null);
			aboutFrame.setVisible(true);
// Action Listener for About button
			JButton aboutButton = new JButton("OK");
			aboutButton.setBounds(120,110, 55, 20);
			aboutButton.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e){
					aboutFrame.setVisible(false);
					aboutFrame.dispose();
				}
			});
			aboutPanel.add(aboutButton);
			
		}
	}
	
	mntmAbout.addActionListener( new aboutAction() );

	}//=================== End of MainMenu Constructor ===================
	


} // ========================== END OF MAIN MENU  ==========================





