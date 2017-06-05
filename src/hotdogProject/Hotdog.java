package hotdogProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Hotdog extends JFrame {

	private ImageButton leftedit = new ImageButton("../images/W.png", "../images/W_2.png");
	private ImageButton leftload = new ImageButton("../images/load_1.png", "../images/load_2.png");
	private ImageButton leftsave = new ImageButton("../images/save_1.png", "../images/save_2.png");
	private ImageButton rightedit = new ImageButton("../images/W.png", "../images/W_2.png");
	private ImageButton rightload = new ImageButton("../images/load_1.png", "../images/load_2.png");
	private ImageButton rightsave = new ImageButton("../images/save_1.png", "../images/save_2.png");
	private ImageButton copyToleft = new ImageButton("../images/lf.png", "../images/lf_2.png");
	private ImageButton copyToRight = new ImageButton("../images/rt.png", "../images/rt_2.png");
	private ImageButton compareButton = new ImageButton("../images/comapre.png", "../images/comapre_2.png");
	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exitIcon_1.png")));
	private JLabel hd = new JLabel(new ImageIcon(Main.class.getResource("../images/hd.png")));
	private JPanel rightText = new JPanel();
	private JPanel leftText = new JPanel();
	private static JTextPane rightPane = new JTextPane();
	private static JTextPane leftPane = new JTextPane();
	private Dimension frameSize, screenSize;
	private JScrollPane rightScroll;
	private JScrollPane leftScroll;
	private Frame ffd = new Frame();
	private Frame fmd = new Frame();
	private String tmpdir;
	private String tmpdir2;
	private int mouseX, mouseY;
	Font cont = new Font("D2Coding", Font.ITALIC, Main.FONT_SIZE);
	private JLabel rightField = new JLabel(new ImageIcon(Main.class.getResource("../images/screen.png")));
	private JLabel leftField = new JLabel(new ImageIcon(Main.class.getResource("../images/screen.png")));

	private Image screenImage;
	private Graphics screenGraphic;
	private Image background = new ImageIcon(Main.class.getResource("../images/bg.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));

	public Hotdog() {
		setUndecorated(true);
		setTitle("SimpleMerge");
		InitLayout();

		setSize(1280, 750);
		setVisible(true);
		setResizable(false);
		setBackground(new Color(0, 0, 0, 0));
		centerScreenSet();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

	public void centerScreenSet() {
		frameSize = getSize();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}

	public void InitLayout() {
		setLayout(null);
		/****************************************************************************************
		 * Pane&Text
		 ***************************************************************************************/
		rightPane.setEditable(false);
		rightPane.setBackground(Color.gray);
		rightPane.setSize(100000, 100000);

		leftPane.setEditable(false);
		leftPane.setBackground(Color.gray);
		leftPane.setSize(10000, 10000);

		rightText.setLayout(new BorderLayout());
		rightText.add(rightPane);
		rightText.setSize(100000, 100000);

		leftText.setLayout(new BorderLayout());
		leftText.add(leftPane);
		leftText.setSize(100000, 100000);

		/****************************************************************************************
		 * Scroll
		 ***************************************************************************************/
		rightScroll = new JScrollPane(rightText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextLineNumber righttln = new TextLineNumber(rightPane);
		rightScroll.setRowHeaderView(righttln);
		rightScroll.setBounds(620, 41, 445, 660);

		leftScroll = new JScrollPane(leftText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextLineNumber lefttln = new TextLineNumber(leftPane);
		leftScroll.setRowHeaderView(lefttln);
		leftScroll.setBounds(86, 41, 445, 660);

		/****************************************************************************************
		 * menuBar
		 ***************************************************************************************/
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});

		/****************************************************************************************
		 * exitbutton
		 ***************************************************************************************/
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				copyToleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				copyToleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(1);
			}
		});

		/****************************************************************************************
		 * mergebutton
		 ***************************************************************************************/
		leftPane.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				copyToRight.setEnabled(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				copyToRight.setEnabled(true);
			}
		});
		rightPane.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				copyToleft.setEnabled(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				copyToleft.setEnabled(true);
			}
		});
		copyToleft.setBounds(1200, 240, 42, 27);
		copyToleft.setEnabled(false);
		copyToleft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				copyToleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				copyToleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Merge.merge(leftPane, rightPane);
			}
		});
		copyToRight.setBounds(1200, 210, 42, 27);
		copyToRight.setEnabled(false);
		copyToRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				copyToRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				copyToRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Merge.merge(rightPane, leftPane);
			}
		});
		/****************************************************************************************
		 * comparebutton
		 ***************************************************************************************/
		compareButton.setBounds(1100, 210, 96, 61);
		compareButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				compareButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				compareButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				compare.compareCode();
				ParagraphList.setJtextPaneParagraph(ParagraphList.leftParagraphList, leftPane);
				ParagraphList.setJtextPaneParagraph(ParagraphList.rightParagraphList, rightPane);
				ShowLine.highlight(leftPane);
				ShowLine.highlight(rightPane);
			}
		});
		/****************************************************************************************
		 * savebutton
		 ***************************************************************************************/
		rightsave.setBounds(562, 540, 36, 38);
		rightsave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				rightsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				rightsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				new Save(rightPane, tmpdir2);
			}
		});
		leftsave.setBounds(29, 540, 36, 38);
		leftsave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				leftsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				leftsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				new Save(leftPane, tmpdir);
			}
		});

		/****************************************************************************************
		 * loadbutton
		 ***************************************************************************************/
		leftload.setBounds(29, 600, 36, 38);
		leftload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				leftload.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				leftload.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Load loadFileToRight = new Load(leftPane);
				tmpdir = loadFileToRight.getFileName();
			}
		});
		rightload.setBounds(562, 600, 36, 38);
		rightload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				rightload.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				rightload.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				Load loadFileToRight = new Load(rightPane);
				tmpdir2 = loadFileToRight.getFileName();
						}
		});
		/****************************************************************************************
		 * editbutton
		 ***************************************************************************************/
		leftedit.setBounds(29, 660, 36, 38);
		leftedit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				leftedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				leftedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스 클릭시;
				isEditableText(leftPane);
			}
		});
		rightedit.setBounds(562, 660, 36, 38);
		rightedit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 진입시
				rightedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스 나갈시
				rightedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isEditableText(rightPane);
			}
		});
		leftField.setBounds(13, 25, 524, 690);
		rightField.setBounds(546, 25, 524, 690);

		ffd.setSize(300, 200);
		fmd.setSize(100, 50);
		/****************************************************************************************
		 * Caret & Font
		 ***************************************************************************************/
		setJTextPaneFont(leftPane, cont, Color.white);
		setJTextPaneFont(rightPane, cont, Color.white);
		leftPane.setCaretColor(Color.white);
		rightPane.setCaretColor(Color.white);

		hd.setBounds(1100, 550, 129, 127);
		
		
		add(leftload);
		add(leftedit);
		add(leftsave);
		add(hd);
		add(rightload);
		add(rightedit);
		add(rightsave);


		add(copyToleft);
		add(copyToRight);
		add(rightScroll);
		add(leftScroll);
		add(compareButton);
		add(exitButton);
		add(menuBar);
		add(leftField);
		add(rightField);

	}

	public static String getLeftPanelText() {
		return leftPane.getText();
	}

	public static String getRightPanelText() {
		return rightPane.getText();
	}

	public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
		// Start with the current input attributes for the JTextPane. This
		// should ensure that we do not wipe out any existing attributes
		// (such as alignment or other paragraph attributes) currently
		// set on the text area.
		MutableAttributeSet attrs = jtp.getInputAttributes();

		// Set the font family, size, and style, based on properties of
		// the Font object. Note that JTextPane supports a number of
		// character attributes beyond those supported by the Font class.
		// For example, underline, strike-through, super- and sub-script.
		StyleConstants.setFontFamily(attrs, font.getFamily());
		StyleConstants.setFontSize(attrs, font.getSize());
		StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
		StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

		// Set the font color
		StyleConstants.setForeground(attrs, c);

		// Retrieve the pane's document object
		StyledDocument doc = jtp.getStyledDocument();

		// Replace the style for the entire document. We exceed the length
		// of the document by 1 so that text entered at the end of the
		// document uses the attributes.
		doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
	}

	public void isEditableText(JTextPane jtp) {
		if (jtp.isEditable() == false) {
			jtp.setEditable(true);
			jtp.setBackground(Color.darkGray);
		} else {
			jtp.setEditable(false);
			jtp.setBackground(Color.gray);
		}
	}

}