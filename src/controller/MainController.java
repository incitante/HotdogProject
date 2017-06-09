package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import view.ActivityPanel;
import view.BackGroundPanel;
import view.WorkSpacePanel;

public class MainController {
	public MainController() {
		int mouseX,mouseY;
		//view
		BackGroundPanel background = new BackGroundPanel();
		WorkSpacePanel leftpanel = new WorkSpacePanel();
		WorkSpacePanel rightpanel = new WorkSpacePanel();;
		ActivityPanel activitypanel = new ActivityPanel();
		
		
		
		//controller
		BackGroundPanelController backgroundController = new BackGroundPanelController();
		WorkSpacePanelController leftpanelController = new WorkSpacePanelController();
		WorkSpacePanelController rightpanelController = new WorkSpacePanelController();;
		ActivityPanelController activitypanelController= new ActivityPanelController();;
		
		backgroundController.addView(background);
		background.addController(backgroundController);
		
	}
}
