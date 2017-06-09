package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import util.ImageButton;
import util.Main;





public class BackGroundPanel extends JFrame{
	private JLabel menuBar= new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));;
	private ImageButton exitButton =  new ImageButton("../images/exitIcon_1.png", "../images/exitIcon_2.png");
	private JLabel leftField ;

	private Image screenImage;
	private Graphics screenGraphic;
	private Image background = new ImageIcon(Main.class.getResource("../images/bg.png")).getImage();
	int mouseX,mouseY;
	
	private WorkSpacePanel leftpanel = new WorkSpacePanel();
	private WorkSpacePanel rightpanel = new WorkSpacePanel();
	private ActivityPanel activitypanel = new ActivityPanel();
	
	public BackGroundPanel(){
		setUndecorated(true);
		
		setTitle("SimpleMerge");
		//InitLayout();
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		add(exitButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		add(menuBar);
		
		leftpanel.setBounds(13, 40,524, 750);
		add(leftpanel);
		
		rightpanel.setBounds(550,40,524, 750);
		add(rightpanel);
		
		activitypanel.setBounds(1100, 210, 200, 80);
		add(activitypanel);
		
		setSize(1280, 750);
		setVisible(true);
		setResizable(false);
		//setBackground(new Color(100, 100, 100, 0));
		//centerScreenSet();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		
	}
	
	
	public void addController(ActionListener e/*,MouseMotionListener l*/){
		//menuBar.addMouseMotionListener(l);
		exitButton.addActionListener(e);
	}
	
	
	
}
