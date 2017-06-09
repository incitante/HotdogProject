package view;

import java.awt.Color;

import javax.swing.JPanel;

import util.ImageButton;

public class ActivityPanel extends JPanel{
	private ImageButton copyToleft = new ImageButton("../images/lf.png", "../images/lf_2.png");
	private ImageButton copyToRight = new ImageButton("../images/rt.png", "../images/rt_2.png");
	private ImageButton compareButton = new ImageButton("../images/comapre.png", "../images/comapre_2.png");
	public ActivityPanel(){
		Init();
	}
	private void Init(){
		setLayout(null);
		setBackground(Color.BLACK);
		
		compareButton.setBounds(0, 0, 91, 61);
		add(compareButton);
		
		copyToleft.setBounds(93,0, 42, 27);
		add(copyToleft);
		
		copyToRight.setBounds(93,28,42,27);
		add(copyToRight);
	}
}
