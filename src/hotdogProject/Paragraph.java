package hotdogProject;
import java.util.ArrayList;

public class Paragraph {
	int line;
	private ArrayList<String> seq = new ArrayList<String>();
	
	public int getSize() {
		return seq.size();
	}
	
	public String getSeq(int i) {
		return seq.get(i);
	}
	
	public void setSeq(String string) {
		this.seq.add(string);
	}
}
