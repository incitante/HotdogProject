package hotdogProject;


import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Save {
	private Frame fmd = new Frame();

	public Save(JTextPane textPane, String fileName) {
		fmd.setSize(100, 50);
		File file = null;
		try {
			if(fileName == null) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Browser for save");
				
				int returnVal = fc.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				textPane.write(writer);
				JOptionPane.showMessageDialog(fmd, "Save Success");
				writer.close();
			} else {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			textPane.write(writer);
			JOptionPane.showMessageDialog(fmd, "Save Success");
			writer.close();
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(fmd, "Save Failed");
		}
	}
}