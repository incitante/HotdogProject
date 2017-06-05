package hotdogProject;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Load {

	private Frame ffd = new Frame();
	private Frame fmd = new Frame();
	String fileName;

	public Load(JTextPane textPane) {
		ffd.setSize(300, 200);
		fmd.setSize(100, 50);

		FileDialog dialog = new FileDialog(ffd, "Browser for Load", FileDialog.LOAD);
		dialog.setDirectory(".");
		dialog.setVisible(true);

		if (dialog.getFile() == null)
			return;

		String dfName2 = dialog.getDirectory() + dialog.getFile();
		fileName = dfName2;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			textPane.setText("");
			do {
				textPane.read(reader, null);
			} while (reader.readLine() != null);
			reader.close();

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(fmd, "로딩 실패");
		}

	}

	public String getFileName() {
		return fileName;

	}
}
