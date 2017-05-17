package hotdogProject;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class compare {
	ArrayList<String> left,right;
	private ArrayList<String> inputText(ArrayList<String> List){
		
		
		
		return List;
	}
	private void setParagraph(Paragraph para,String text){
		//System.out.print(text.getText());
	}
	public void compareCode(){
		int k, i, j, t;
		int sw;
		
		String text;
		
		left = new ArrayList<String>();
		right = new ArrayList<String>();
		
		StringTokenizer inputLeft = new StringTokenizer(GUI.getLeftPanelText()); 
		StringTokenizer inputRight = new StringTokenizer(GUI.getRightPanelText()); 
		
		while(inputLeft.hasMoreTokens()) {  
            // nextToken 인자에는 분리할 토큰(분리할 스트링을 넣어줍니다)  
            left.add(inputLeft.nextToken("\n"));  
        }  
		while(inputRight.hasMoreTokens()) {  
            // nextToken 인자에는 분리할 토큰(분리할 스트링을 넣어줍니다)  
            right.add(inputRight.nextToken("\n"));  
        }  
				
		k = i = j = t = 0;
		sw = -1;
		
		while(true) {
			if(lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) == j) {
				if(sw != 0) {
					ParagraphList.leftParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(lcs.getLeftLCS(i)));
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(lcs.getRightLCS(i)));
				k++;
				i++;
				j++;
			}
			else if(lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) != j) {
				if(sw != 1) {
					sw = 1;
					ParagraphList.leftParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq("\n");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(lcs.getRightLCS(i)));
				j++;
			}
			else {
				if(sw != 2) {
					sw = 2;
					ParagraphList.leftParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(lcs.getLeftLCS(i)));
				ParagraphList.rightParagraphList.get(t).setSeq("\n");
				i++;
			}
		}
	}
}
