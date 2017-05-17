package hotdogProject;
import javax.swing.JTextPane;

public class Merge {
	
	

	static void mergeToRight(JTextPane leftPane,JTextPane rightPane){
		int start = 0;
		int end = 0;
    	  String temp;
    	  int position=rightPane.getCaretPosition();
      	  if(position<=end&&start<=position){
      		  rightPane.select(start,end);
      		  temp=rightPane.getSelectedText();
      		  leftPane.select(start,end);
      		  leftPane.replaceSelection(temp);
      	  }
      	
	}
	
	static void mergeToLeft(JTextPane leftPane,JTextPane rightPane){
		int start = 0;
		int end = 0;
  	  String temp;
  	  int position=rightPane.getCaretPosition();
    	  if(position<=end&&start<=position){
    		  rightPane.select(start,end);
    		  temp=rightPane.getSelectedText();
    		  leftPane.select(start,end);
    		  leftPane.replaceSelection(temp);
    	  }
    	
	}
}