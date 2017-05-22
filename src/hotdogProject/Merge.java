package hotdogProject;

import javax.swing.JTextPane;

public class Merge {

	static void merge(JTextPane currentPane, JTextPane copyPane) {
		// start : paragraph�� ���� �ε��� end: paragraph�� �� �ε���
		int startIdx = 0;
		int endIdx = 50;
		int position = currentPane.getCaretPosition();

		if (position <= endIdx && startIdx <= position) {
			currentPane.select(startIdx, endIdx);
			String copyText = currentPane.getSelectedText();
			copyPane.select(startIdx, endIdx);
			copyPane.replaceSelection(copyText);
		}
	}
}
