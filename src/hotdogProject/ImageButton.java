package hotdogProject;

import java.awt.Insets;
import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JButton;


class ImageButton extends JButton{
	
	private ImageIcon defaultIcon;
	private ImageIcon modifyIcon;
	
	public ImageButton(String img1,String img2) {
		this.defaultIcon = new ImageIcon(img1);
		this.modifyIcon = new ImageIcon(img2);
		setIcon(defaultIcon);
		setRolloverIcon(modifyIcon);
		setMargin(new Insets(0,0,0,0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorder(null);
		setText(null);
		setSize(defaultIcon.getImage().getWidth(null), defaultIcon.getImage().getHeight(null));
	}
}

