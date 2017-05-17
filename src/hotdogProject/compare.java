package hotdogProject;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class compare {
	
	private ArrayList<String> inputText(ArrayList<String> List){
		
		
		
		return List;
	}
	private void setParagraph(Paragraph para,String text){
		//System.out.print(text.getText());
	}
	public static void compareCode(){
		int k, i, j, t, limitK, limitI, limitJ;
		int sw;
		ArrayList<String> left,right;
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
		
		for(int a=0;a<left.size();a++) {
			System.out.println(left.get(a));
		}
		
		lcs.findLcs(left, right);
		
		k = i = j = 0;
		t = -1;
		sw = -1;
		
		limitK = lcs.getLcsSize();
		limitI = lcs.getSizeOfLeftLCS();
		limitJ = lcs.getSizeOfRightLCS();
		
		System.out.println(limitK);
		System.out.println(limitI);
		System.out.println(limitJ);
		
		while(k < limitK) {
			if(lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) == j) {
				if(sw != 0) {
					sw = 0;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
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
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq("\n");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(lcs.getRightLCS(i)));
				j++;
			}
			else {
				if(sw != 2) {
					sw = 2;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(lcs.getLeftLCS(i)));
				ParagraphList.rightParagraphList.get(t).setSeq("\n");
				i++;
			}
		}
		
		if(i < limitI) {
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			t++;
			while(i < limitI) {
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(lcs.getLeftLCS(i)));
				ParagraphList.rightParagraphList.get(t).setSeq("\n");
				i++;
			}
		}
		
		if(j < limitJ) {
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			t++;
			while(j < limitJ) {
				ParagraphList.leftParagraphList.get(t).setSeq("\n");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(lcs.getRightLCS(i)));
				j++;
			}
		}
		for(i = 0; i < t; i++) {
			for(j = 0; j < ParagraphList.leftParagraphList.get(i).getSize(); j++) {
				System.out.print(ParagraphList.leftParagraphList.get(i).getSeq(j));
			}
		}
	}
}
