package hotdogProject;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class ShowLine {
	public static Color shiftColor(int i) {
		Color[] color = new Color[2];

		i = i % 2;
		color[0] = new Color(255, 205, 158);

		color[1] = new Color(153, 205, 153);

		return color[i];
	}

	public static void highlight(JTextPane t) {
		Highlighter hilite = new MyHighlighter();

		DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(250, 237, 148));
		DefaultHighlightPainter repainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(250, 187, 148));
		t.setHighlighter(hilite);

		try {
			Document doc = t.getDocument();
			String text = doc.getText(0, doc.getLength());
			int start = 0;
			int end = 0;
			int position = 0;
			int i = 0;

			while ((end = text.indexOf('\n', start)) >= 0) {
				if (position >= ParagraphList.leftParagraphList.get(i).startLine
						&& position <= ParagraphList.leftParagraphList.get(i).endLine
						&& !ParagraphList.leftParagraphList.get(i).isLCS) {

					painter = new DefaultHighlighter.DefaultHighlightPainter(shiftColor(i));
					repainter = new DefaultHighlighter.DefaultHighlightPainter(
							new Color(painter.getColor().getRed() - 50, painter.getColor().getGreen() - 50,
									painter.getColor().getBlue() - 50));

					hilite.addHighlight(start, end, repainter);
					hilite.addHighlight(start, end + 1, painter);

				}
				if (position == ParagraphList.leftParagraphList.get(i).endLine)
					i++;
				start = end + 1;
				position++;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}
}
