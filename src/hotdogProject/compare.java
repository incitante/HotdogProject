package hotdogProject;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class compare {

	private ArrayList<String> inputText(ArrayList<String> List) {

		return List;
	}

	private void setParagraph(Paragraph para, String text) {
		// System.out.print(text.getText());
	}

	public static void inputTextToArrayList(String srcText, ArrayList<String> dest) {
		String temp;
		try {
			BufferedReader reader = new BufferedReader(new StringReader(srcText));
			while ((temp = reader.readLine()) != null) {
				dest.add(temp);
			}
			
			reader.close();

		} catch (Exception e2) {
			e2.getMessage();
		}
	}

	public static void compareCode() {
		int k, i, j, t, limitK, limitI, limitJ;
		int sw;
		ArrayList<String> left, right;
		
		left = new ArrayList<String>();
		right = new ArrayList<String>();
		
		inputTextToArrayList(Hotdog.getLeftPanelText(),left);
		inputTextToArrayList(Hotdog.getRightPanelText(),right);

		lcs.findLcs(left, right);

		k = i = j = 0;
		t = -1;
		sw = -1;

		limitK = lcs.getLcsSize();
		limitI = left.size();
		limitJ = right.size();
		
		ParagraphList.clear();

		while (k < limitK) {
			if (lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) == j) {
				if (sw != 0) {
					sw = 0;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				k++;
				i++;
				j++;
			} else if (lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) != j) {
				if (sw != 1) {
					sw = 1;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq("");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				j++;
			} else {
				if (sw != 2) {
					sw = 2;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq("");
				i++;
			}
		}

		if (i < limitI) {
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			t++;
			while (i < limitI) {
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq("");
				i++;
			}
		}

		if (j < limitJ) {
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			t++;
			while (j < limitJ) {
				ParagraphList.leftParagraphList.get(t).setSeq("");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				j++;
			}
		}
		System.out.println("left");
		for (i = 0; i <= t; i++) {
			for (j = 0; j < ParagraphList.leftParagraphList.get(i).getSize(); j++) {
				System.out.println(ParagraphList.leftParagraphList.get(i).getSeq(j));
			}
		}
		System.out.println("right");
		for (i = 0; i <= t; i++) {
			for (j = 0; j < ParagraphList.rightParagraphList.get(i).getSize(); j++) {
				System.out.println(ParagraphList.rightParagraphList.get(i).getSeq(j));
			}
		}
	}
}
