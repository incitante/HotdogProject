package hotdogProject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
 
public class Hotdog extends JFrame {
 
   private Button leftedit = new Button("EDIT");
   private Button leftload = new Button("LOAD"); 
   private Button leftsave = new Button("SAVE");
   private Button rightedit = new Button("EDIT");
   private Button rightload = new Button("LOAD"); 
   private Button rightsave = new Button("SAVE");
   private Button copyToRight = new Button("-->");
   private Button copyToleft = new Button("<--");
   private Button compareButton = new Button("compareButton");
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
   Font cont = new Font("D2Coding", Font.BOLD, 13);
  
   public Hotdog() {
      setTitle("SImpleMerge");
      InitLayout();
      
      setSize(1280,720);
      setVisible(true);
      setResizable(false);
      
      centerScreenSet();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   public void centerScreenSet() {
	      frameSize = getSize();
	      screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
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
      
      rightScroll = new JScrollPane(rightText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      leftScroll = new JScrollPane(leftText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
      
      
      rightScroll.setBounds(700,80,450,550);
      leftScroll.setBounds(50,80,450,550);
      copyToleft.setBounds(550, 150, 100, 50);
      copyToRight.setBounds(550, 350, 100, 50);
      compareButton.setBounds(550, 250, 100, 50);
      
      leftedit.setBounds(200, 20, 150, 40);
      leftsave.setBounds(350, 20, 150, 40);
      leftload.setBounds(50, 20, 150, 40);
      
      rightedit.setBounds(850, 20, 150, 40);
      rightsave.setBounds(1000, 20, 150, 40);
      rightload.setBounds(700, 20, 150, 40);
      ffd.setSize(300,200);
      fmd.setSize(100,50);
      setJTextPaneFont(leftPane, cont, Color.white);
      setJTextPaneFont(rightPane, cont, Color.white);
      leftload.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftload.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftload.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  }
      	     	  
    	  @Override
    	  public void mousePressed(MouseEvent e) {
    		  FileDialog dialog = new FileDialog(ffd, "Browser for Load", FileDialog.LOAD);
    	      dialog.setDirectory(".");
    	      dialog.setVisible(true);
    	      
    	      if(dialog.getFile() == null) return;
    	          
    	      String dfName = dialog.getDirectory() + dialog.getFile();
    	      tmpdir = dfName;
    	      
    	      try {
    	             BufferedReader reader = new BufferedReader(new FileReader(tmpdir));
    	             leftPane.setText("");
    	             do {
    	                leftPane.read(reader, null);
    	             } while(reader.readLine() != null);
    	             reader.close();
    	             
    	         } catch (Exception e2) {
    	            JOptionPane.showMessageDialog(fmd, "로딩 실패");
    	         }
    	  }
      });
      
      leftedit.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  //마우스 나갈시
    		  leftEditText();
    	  }
      });
      
      leftsave.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
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
    		  //마우스 진입시
    		  rightload.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightload.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  }
    	  public void mousePressed(MouseEvent e) {
    		  FileDialog dialog = new FileDialog(ffd, "Browser for Load", FileDialog.LOAD);
    	      dialog.setDirectory(".");
    	      dialog.setVisible(true);
    	       
    	      if(dialog.getFile() == null) return;
    	          
    	      String dfName2 = dialog.getDirectory() + dialog.getFile();
    	      tmpdir2 = dfName2;
    	      
    	      try {
    	           BufferedReader reader = new BufferedReader(new FileReader(tmpdir2));
    	           rightPane.setText("");
    	           do {
    	              rightPane.read(reader, null);
    	           } while(reader.readLine() != null);
    	           reader.close();
    	            
    	         } catch (Exception e2) {
    	            JOptionPane.showMessageDialog(fmd, "로딩 실패");
    	         }
    	  }    	  
      });
      
      rightedit.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  rightedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightedit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  rightEditText();
    	  }
      });
      
      rightsave.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  rightsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightsave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
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
    		  //마우스 진입시
    		  compareButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  compareButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  }
    	  public void mousePressed(MouseEvent e) {
    		 
    		  compare.compareCode();
    		  
    	  }
      });
      copyToRight.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  copyToRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  copyToRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  Merge.merge(rightPane,leftPane);
    	  }
      });
      copyToleft.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  copyToleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  copyToleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  Merge.merge(leftPane,rightPane);
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
   }
   
	public static String getLeftPanelText() {
		return leftPane.getText();
	}
	public static String getRightPanelText() {
		return rightPane.getText();
	}
	
   
   public void rightEditText(){
      if(rightPane.isEditable() == false) {
         rightPane.setEditable(true);
         rightPane.setBackground(Color.darkGray);
      }
      else {
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
   public void leftEditText() {
      if(leftPane.isEditable() == false){
         leftPane.setEditable(true);
         leftPane.setBackground(Color.darkGray);
      }
      else {
         leftPane.setEditable(false);
         leftPane.setBackground(Color.gray);
      }
   }
}