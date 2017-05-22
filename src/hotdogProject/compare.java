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
		int k, i, j, t, limitK, limitI, limitJ, line;
		int sw;
		ArrayList<String> left, right;
		
		left = new ArrayList<String>();
		right = new ArrayList<String>();
		
		inputTextToArrayList(Hotdog.getLeftPanelText(),left);
		inputTextToArrayList(Hotdog.getRightPanelText(),right);

		lcs.findLcs(left, right);

		k = i = j = line = 0;
		t = -1;
		sw = -1;

		limitK = lcs.getLcsSize();
		limitI = left.size();
		limitJ = right.size();
		
		ParagraphList.clear();

		while (k < limitK) {
			if (lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) == j) {
				if (sw != 0) {
					if(t != -1) {
						ParagraphList.leftParagraphList.get(t).endline = line - 1;
						ParagraphList.rightParagraphList.get(t).endline = line - 1;
					}
					sw = 0;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
					ParagraphList.leftParagraphList.get(t).startline = line;
					ParagraphList.rightParagraphList.get(t).startline = line;
					ParagraphList.leftParagraphList.get(t).isLCS = true;
					ParagraphList.rightParagraphList.get(t).isLCS = true;
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				k++;
				i++;
				j++;
			} else if (lcs.getLeftLCS(k) == i && lcs.getRightLCS(k) != j) {
				if (sw != 1) {
					if(t != -1) {
						ParagraphList.leftParagraphList.get(t).endline = line - 1;
						ParagraphList.rightParagraphList.get(t).endline = line - 1;
					}
					sw = 1;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
					ParagraphList.leftParagraphList.get(t).startline = line;
					ParagraphList.rightParagraphList.get(t).startline = line;
					ParagraphList.leftParagraphList.get(t).isLCS = false;
					ParagraphList.rightParagraphList.get(t).isLCS = false;
				}
				ParagraphList.leftParagraphList.get(t).setSeq("");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				j++;
			} else {
				if (sw != 2) {
					if(t != -1) {
						ParagraphList.leftParagraphList.get(t).endline = line - 1;
						ParagraphList.rightParagraphList.get(t).endline = line - 1;
					}
					sw = 2;
					t++;
					ParagraphList.leftParagraphList.add(new Paragraph());
					ParagraphList.rightParagraphList.add(new Paragraph());
					ParagraphList.leftParagraphList.get(t).startline = line;
					ParagraphList.rightParagraphList.get(t).startline = line;
					ParagraphList.leftParagraphList.get(t).isLCS = false;
					ParagraphList.rightParagraphList.get(t).isLCS = false;
				}
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq("");
				i++;
			}
			line++;
		}

		if (i < limitI) {
			if(t != -1) {
				ParagraphList.leftParagraphList.get(t).endline = line - 1;
				ParagraphList.rightParagraphList.get(t).endline = line - 1;
			}
			t++;
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			ParagraphList.leftParagraphList.get(t).startline = line;
			ParagraphList.rightParagraphList.get(t).startline = line;
			ParagraphList.leftParagraphList.get(t).isLCS = false;
			ParagraphList.rightParagraphList.get(t).isLCS = false;
			while (i < limitI) {
				ParagraphList.leftParagraphList.get(t).setSeq(left.get(i));
				ParagraphList.rightParagraphList.get(t).setSeq("");
				i++;
				line++;
			}
		}

		if (j < limitJ) {
			if(t != -1) {
				ParagraphList.leftParagraphList.get(t).endline = line - 1;
				ParagraphList.rightParagraphList.get(t).endline = line - 1;
			}
			t++;
			ParagraphList.leftParagraphList.add(new Paragraph());
			ParagraphList.rightParagraphList.add(new Paragraph());
			ParagraphList.leftParagraphList.get(t).startline = line;
			ParagraphList.rightParagraphList.get(t).startline = line;
			ParagraphList.leftParagraphList.get(t).isLCS = false;
			ParagraphList.rightParagraphList.get(t).isLCS = false;
			while (j < limitJ) {
				ParagraphList.leftParagraphList.get(t).setSeq("");
				ParagraphList.rightParagraphList.get(t).setSeq(right.get(j));
				j++;
				line++;
			}
		}
		
		ParagraphList.leftParagraphList.get(t).endline = line - 1;
		ParagraphList.rightParagraphList.get(t).endline = line - 1;
		
		System.out.println("left");
		for (i = 0; i <= t; i++) {
			System.out.println(ParagraphList.leftParagraphList.get(i).startline + " " + ParagraphList.leftParagraphList.get(i).endline);
		}
		System.out.println("right");
		for (i = 0; i <= t; i++) {
			System.out.println(ParagraphList.rightParagraphList.get(i).startline + " " + ParagraphList.rightParagraphList.get(i).endline);
		}
	}
}
