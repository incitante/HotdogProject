package hotdogProject;
import java.util.ArrayList;

public class Paragraph {
	int startline, endline;
	boolean isLCS;
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
	
	public void printParagraph(Paragraph hanjun){
		
		
	}
}
