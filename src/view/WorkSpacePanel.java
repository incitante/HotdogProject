package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import util.TextLineNumber;
import util.ImageButton;
import util.Main;

public class WorkSpacePanel extends JPanel {
	private ImageButton editButton = new ImageButton("../images/W.png", "../images/W_2.png");
	private ImageButton loadButton = new ImageButton("../images/load_1.png", "../images/load_2.png");
	private ImageButton saveButton = new ImageButton("../images/save_1.png", "../images/save_2.png");
	private JLabel leftField = new JLabel(new ImageIcon(Main.class.getResource("../images/screen.png")));
	private JPanel text = new JPanel();
	private JTextPane pane = new JTextPane();
	private JScrollPane scroll = new JScrollPane();

	public WorkSpacePanel() {
		Init();

	}

	void Init() {
		setLayout(null);
		setBackground(Color.BLACK);
		
		setSize(450, 700);
		editButton.setBounds(15, 633, 36, 38);
		add(editButton);

		loadButton.setBounds(15, 580, 36, 38);
		add(loadButton);

		saveButton.setBounds(15, 520, 36, 38);
		add(saveButton);

		
		
		pane.setEditable(false);
		pane.setBackground(Color.gray);
		pane.setSize(100000, 100000);
		add(pane);
		
		text.setLayout(new BorderLayout());
		text.add(pane);
		text.setSize(100000, 100000);
		add(text);
		
		scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextLineNumber ln = new TextLineNumber(pane);
		scroll.setRowHeaderView(ln);	
		scroll.setBounds(74, 15, 445, 670);
		add(scroll);
		
		leftField.setBounds(0, 0, 524, 690);
		add(leftField);
	}

}
