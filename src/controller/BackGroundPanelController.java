package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Window;

import view.BackGroundPanel;

public class BackGroundPanelController implements ActionListener {
	BackGroundPanel background;
	int mouseX,mouseY;
	
	BackGroundPanelController(){
		
	}
	

	public void addView(BackGroundPanel v){
		System.out.println("Controller: adding view");
		this.background = v;
	} //addView()
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(1);
	}
	
	
	
}
