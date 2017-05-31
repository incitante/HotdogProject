package hotdogProject;


import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Save {
	private Frame fmd = new Frame();

	public Save(JTextPane textPane, String fileName) {
		fmd.setSize(100, 50);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			textPane.write(writer);
			JOptionPane.showMessageDialog(fmd, "저장 성공");
			writer.close();

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(fmd, "저장 실패");
		}
	}
}
