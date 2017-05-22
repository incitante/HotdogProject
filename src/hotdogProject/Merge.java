package hotdogProject;

import javax.swing.JTextPane;

public class Merge {

	static void merge(JTextPane currentPane, JTextPane copyPane) {
		// start : paragraph의 시작 인덱스 end: paragraph의 끝 인덱스
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
