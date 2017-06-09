
package model;
import java.util.ArrayList;

import javax.swing.JTextPane;


public class ParagraphList {
	static ArrayList<Paragraph> leftParagraphList = new ArrayList<Paragraph>();
	static ArrayList<Paragraph> rightParagraphList = new ArrayList<Paragraph>();
	
	public static void setJtextPaneParagraph(ArrayList<Paragraph> paraList,JTextPane jtp){
		String temp="";
		for(int i=0;i<paraList.size();i++)
			temp = temp + Paragraph.printParagraph(paraList.get(i));

		//System.out.println(temp);
		jtp.setText(temp);
	   }
	public static void clear(){
		leftParagraphList.clear();
		rightParagraphList.clear();	
	}
	
}
