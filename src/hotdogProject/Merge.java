package hotdogProject;

import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Merge {

	static void merge(JTextPane currentPane, JTextPane copyPane, ArrayList<Paragraph> currentParagraphList,
			ArrayList<Paragraph> copyParagraphList) {
		int lineNumber = 0;
		int position = currentPane.getCaretPosition();
		Document doc = currentPane.getDocument();
		try {
			for (int i = 0; i <= position - 1; i++) {
				String s = doc.getText(i, 1);
				if (s.charAt(0) == '\n') {
					lineNumber++;
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < currentParagraphList.size(); i++) {
			int startLine = currentParagraphList.get(i).startLine;
			int endLine = currentParagraphList.get(i).endLine;
			if (lineNumber >= startLine && lineNumber <= endLine) {
				copyParagraphList.set(i, currentParagraphList.get(i));
			}

			ParagraphList.setJtextPaneParagraph(copyParagraphList, copyPane);
		}

	}
}