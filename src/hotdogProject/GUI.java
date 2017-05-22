package hotdogProject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
 
public class GUI extends JFrame {
 
   private Button leftEditButton = new Button("EDIT");
   //private ImageButton leftEditButton = new ImageButton("emil.png", "emil1.png"); //Example for Changing button into image button
   private Button leftLoadButton = new Button("LOAD"); 
   private Button leftSaveButton = new Button("SAVE");
   private Button rightEditButton = new Button("EDIT");
   private Button rightLoadButton = new Button("LOAD"); 
   private Button rightSaveButton = new Button("SAVE");
   private Button copyToRightButton = new Button("-->");
   private Button copyToLeftButton = new Button("<--");
   private Button compareButton = new Button("COMPARE");
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
   
   public GUI() {
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
      rightPane.setBackground(Color.lightGray);
      leftPane.setEditable(false);
      leftPane.setBackground(Color.lightGray);
      
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
      copyToLeftButton.setBounds(550, 150, 100, 50);
      copyToRightButton.setBounds(550, 350, 100, 50);
      compareButton.setBounds(550, 250, 100, 50);
      
      leftEditButton.setBounds(200, 20, 150, 40);
      leftSaveButton.setBounds(350, 20, 150, 40);
      leftLoadButton.setBounds(50, 20, 150, 40);
      
      rightEditButton.setBounds(850, 20, 150, 40);
      rightSaveButton.setBounds(1000, 20, 150, 40);
      rightLoadButton.setBounds(700, 20, 150, 40);
      ffd.setSize(300,200);
      fmd.setSize(100,50);
      
      leftLoadButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
      
      leftEditButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftEditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftEditButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  //마우스 나갈시
    		  leftEditText();
    	  }
      });
      
      leftSaveButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  leftSaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  leftSaveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
      
      rightLoadButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  rightLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
      
      rightEditButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  rightEditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightEditButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		  rightEditText();
    	  }
      });
      
      rightSaveButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  rightSaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  rightSaveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
    	  }
      });
      copyToRightButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  copyToRightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  copyToRightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		 // Merge.mergeToRight(leftPane,rightPane);
    	  }
      });
      copyToLeftButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseEntered(MouseEvent e) {
    		  //마우스 진입시
    		  copyToLeftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  }
    	  @Override
    	  public void mouseExited(MouseEvent e) {
    		  //마우스 나갈시
    		  copyToLeftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	  } @Override
    	  public void mousePressed(MouseEvent e) {
    		 // Merge.mergeToLeft(leftPane,rightPane);
    	  }
      });
      add(leftLoadButton);
      add(leftEditButton);
      add(leftSaveButton);
      
      add(rightLoadButton);
      add(rightEditButton);
      add(rightSaveButton);
      
      add(copyToLeftButton);
      add(copyToRightButton);
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
         rightPane.setBackground(Color.white);
      }
      else {
         rightPane.setEditable(false);
         rightPane.setBackground(Color.lightGray);
            
      }
   
   }
   public void leftEditText() {
      if(leftPane.isEditable() == false){
         leftPane.setEditable(true);
         leftPane.setBackground(Color.white);
      }
      else {
         leftPane.setEditable(false);
         leftPane.setBackground(Color.LIGHT_GRAY);
      }
   }
   public static void main(String[] args) {
	   GUI g = new GUI();
   }
}