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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		rightScroll.setBounds(700, 80, 450, 550);

		leftScroll = new JScrollPane(leftText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextLineNumber lefttln = new TextLineNumber(leftPane);
		leftScroll.setRowHeaderView(lefttln);
		leftScroll.setBounds(50, 80, 450, 550);

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
				// ���콺 ���Խ�
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(1);
			}
		});

		/****************************************************************************************
		 * mergebutton
		 ***************************************************************************************/
		copyToleft.setBounds(550, 150, 100, 50);
		copyToleft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				copyToleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
				copyToleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Merge.merge(leftPane, rightPane);
			}
		});
		copyToRight.setBounds(550, 350, 100, 50);
		copyToRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				copyToRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
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
		compareButton.setBounds(550, 250, 100, 50);
		compareButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				compareButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
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
		rightsave.setBounds(1000, 20, 150, 40);
		rightsave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				File file = null;
				try {
					if (tmpdir2 == null) {
						JFileChooser fc = new JFileChooser();
						fc.setDialogTitle("Browser for save");

						int returnVal = fc.showOpenDialog(null);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							file = fc.getSelectedFile();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						rightPane.write(writer);
						JOptionPane.showMessageDialog(fmd, "save success");
						writer.close();
					} else {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						rightPane.write(writer);
						JOptionPane.showMessageDialog(fmd, "save success");
						writer.close();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "save failed");
				}
				/*
				 * try { if (tmpdir2 == null) { File f = new File(); FileDialog
				 * dialog = new FileDialog(ffd, "Browser for Save",
				 * FileDialog.SAVE); dialog.setDirectory(".");
				 * dialog.setVisible(true); if (dialog.getFile() == null)
				 * return; }
				 * 
				 * else { BufferedWriter writer = new BufferedWriter(new
				 * FileWriter(tmpdir2)); rightPane.write(writer);
				 * JOptionPane.showMessageDialog(fmd, ""); writer.close(); } }
				 * catch (Exception e2) { JOptionPane.showMessageDialog(fmd,
				 * "���� ����"); }
				 */
			}
		});
		leftsave.setBounds(350, 20, 150, 40);
		leftsave.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				leftsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
				leftsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				File file = null;
				try {
					if (tmpdir == null) {
						JFileChooser fc = new JFileChooser();
						fc.setDialogTitle("Browser for save");

						int returnVal = fc.showOpenDialog(null);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							file = fc.getSelectedFile();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						rightPane.write(writer);
						JOptionPane.showMessageDialog(fmd, "save success");
						writer.close();
					} else {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						rightPane.write(writer);
						JOptionPane.showMessageDialog(fmd, "save success");
						writer.close();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(fmd, "save failed");
				}
			}
		});

		/****************************************************************************************
		 * loadbutton
		 ***************************************************************************************/
		leftload.setBounds(50, 20, 150, 40);
		leftload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				leftload.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
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
					JOptionPane.showMessageDialog(fmd, "�ε� ����");
				}
			}
		});
		rightload.setBounds(700, 20, 150, 40);
		rightload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				rightload.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
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
					JOptionPane.showMessageDialog(fmd, "�ε� ����");
				}
			}
		});
		/****************************************************************************************
		 * editbutton
		 ***************************************************************************************/
		leftedit.setBounds(200, 20, 150, 40);
		leftedit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				leftedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
				leftedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���콺 Ŭ����;
				isEditableText(leftPane);
			}
		});
		rightedit.setBounds(850, 20, 150, 40);
		rightedit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// ���콺 ���Խ�
				rightedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���콺 ������
				rightedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isEditableText(rightPane);
			}
		});

		ffd.setSize(300, 200);
		fmd.setSize(100, 50);
		/****************************************************************************************
		 * Caret & Font
		 ***************************************************************************************/
		setJTextPaneFont(leftPane, cont, Color.white);
		setJTextPaneFont(rightPane, cont, Color.white);
		leftPane.setCaretColor(Color.white);
		rightPane.setCaretColor(Color.white);

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