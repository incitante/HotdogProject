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
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Hotdog extends JFrame {

	private ImageButton leftedit = new ImageButton("../images/W.png", "../images/W.png");
	private ImageButton leftload = new ImageButton("../images/load_1.png", "../images/load_2.png");
	private ImageButton leftsave = new ImageButton("../images/save_1.png", "../images/save_2.png");
	private ImageButton rightedit = new ImageButton("../images/W.png", "../images/W.png");
	private ImageButton rightload = new ImageButton("../images/load_1.png", "../images/load_2.png");
	private ImageButton rightsave = new ImageButton("../images/save_1.png", "../images/save_2.png");
	private ImageButton copyToleft = new ImageButton("../images/lf.png", "../images/lf_2.png");
	private ImageButton copyToRight = new ImageButton("../images/rt.png", "../images/rt_2.png");
	private ImageButton compareButton = new ImageButton("../images/comapre.png", "../images/comapre_2.png");
	private JButton exitButton = new JButton(new ImageIcon(main.class.getResource("../images/exitIcon_1.png")));
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
	Font cont = new Font("D2Coding", Font.ITALIC, main.FONT_SIZE);
	
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background = new ImageIcon(main.class.getResource("../images/bg.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(main.class.getResource("../images/menubar.png")));
	public Hotdog() {
		setUndecorated(true);
		setTitle("SimpleMerge");
		InitLayout();

		setSize(1280, 750);
		setVisible(true);
		setResizable(false);
		setBackground(new Color(0,0,0,0));
		centerScreenSet();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void paint(Graphics g) {
		screenImage = createImage(main.SCREEN_WIDTH, main.SCREEN_HEIGHT);
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
		
		rightPane.setEditable(false);
		rightPane.setBackground(Color.gray);
		leftPane.setEditable(false);
		leftPane.setBackground(Color.gray);

		rightPane.setSize(100000, 100000);
		leftPane.setSize(10000, 10000);

		rightText.setLayout(new BorderLayout());
		rightText.add(rightPane);
		rightText.setSize(100000, 100000);

		leftText.setLayout(new BorderLayout());
		leftText.add(leftPane);
		leftText.setSize(100000, 100000);

		rightScroll = new JScrollPane(rightText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		leftScroll = new JScrollPane(leftText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextLineNumber lefttln = new TextLineNumber(leftPane);
		leftScroll.setRowHeaderView(lefttln);
		TextLineNumber righttln = new TextLineNumber(rightPane);
		rightScroll.setRowHeaderView(righttln);

		menuBar.setBounds(0,0,1280,30);
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
		
		exitButton.setBounds(1245,0,30,30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		rightScroll.setBounds(700, 80, 450, 550);
		leftScroll.setBounds(50, 80, 450, 550);
		copyToleft.setBounds(550, 150, 100, 50);
		copyToRight.setBounds(550, 350, 100, 50);
		compareButton.setBounds(550, 250, 100, 50);

		leftedit.setBounds(200, 20, 150, 40);
		leftsave.setBounds(350, 20, 150, 40);
		leftload.setBounds(50, 20, 150, 40);

		rightedit.setBounds(850, 20, 150, 40);
		rightsave.setBounds(1000, 20, 150, 40);
		rightload.setBounds(700, 20, 150, 40);
		ffd.setSize(300, 200);
		fmd.setSize(100, 50);
		setJTextPaneFont(leftPane, cont, Color.white);
		setJTextPaneFont(rightPane, cont, Color.white);
		leftPane.setCaretColor(Color.white);
		rightPane.setCaretColor(Color.white);
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
				FileDialog dialog = new FileDialog(ffd, "Browser for Load", FileDialog.LOAD);
				dialog.setDirectory(".");
				dialog.setVisible(true);

				if (dialog.getFile() == null)
					return;

				String dfName = dialog.getDirectory() + dialog.getFile();
				tmpdir = dfName;

				try {
					BufferedReader reader = new BufferedReader(new FileReader(tmpdir));
					leftPane.setText("");
					do {
						leftPane.read(reader, null);
					} while (reader.readLine() != null);
					reader.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "로딩 실패");
				}
			}
		});

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
				// 마우스 나갈시
				leftEditText();
			}
		});

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
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(tmpdir));
					leftPane.write(writer);
					JOptionPane.showMessageDialog(fmd, "저장 성공");
					writer.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "저장 실패");
				}
			}
		});

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
				FileDialog dialog = new FileDialog(ffd, "Browser for Load", FileDialog.LOAD);
				dialog.setDirectory(".");
				dialog.setVisible(true);

				if (dialog.getFile() == null)
					return;

				String dfName2 = dialog.getDirectory() + dialog.getFile();
				tmpdir2 = dfName2;

				try {
					BufferedReader reader = new BufferedReader(new FileReader(tmpdir2));
					rightPane.setText("");
					do {
						rightPane.read(reader, null);
					} while (reader.readLine() != null);
					reader.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "로딩 실패");
				}
			}
		});

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
				rightEditText();
			}
		});

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
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(tmpdir2));
					rightPane.write(writer);
					JOptionPane.showMessageDialog(fmd, "저장 성공");
					writer.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "저장 실패");
				}
			}
		});

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
				highlight(leftPane);
			}
		});
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
		add(leftload);
		add(leftedit);
		add(leftsave);

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
	}

	public static String getLeftPanelText() {
		return leftPane.getText();
	}

	public static String getRightPanelText() {
		return rightPane.getText();
	}

	public void rightEditText() {
		if (rightPane.isEditable() == false) {
			rightPane.setEditable(true);
			rightPane.setBackground(Color.darkGray);
		} else {
			rightPane.setEditable(false);
			rightPane.setBackground(Color.gray);

		}

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

	public void highlight(JTextPane t) {
		Highlighter hilite = new MyHighlighter();
		DefaultHighlightPainter whitePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.MAGENTA);

		DefaultHighlightPainter painter = whitePainter;
		t.setHighlighter(hilite);
		
		try {
			Document doc = t.getDocument();
			String text = doc.getText(0, doc.getLength());
			int start = 0;
			int end = 0;
			int position = 0;
			int i = 0;

			while ((end = text.indexOf('\n', start)) >= 0) {
				if (position >= ParagraphList.leftParagraphList.get(i).startLine
						&& position <= ParagraphList.leftParagraphList.get(i).endLine
						&& !ParagraphList.leftParagraphList.get(i).isLCS) {
					hilite.addHighlight(start, end + 1, painter);
				}
				if (position == ParagraphList.leftParagraphList.get(i).endLine)
					i++;
				start = end + 1;
				position++;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	
	}

	public void leftEditText() {
		if (leftPane.isEditable() == false) {
			leftPane.setEditable(true);
			leftPane.setBackground(Color.darkGray);
		} else {
			leftPane.setEditable(false);
			leftPane.setBackground(Color.gray);
		}
	}
}