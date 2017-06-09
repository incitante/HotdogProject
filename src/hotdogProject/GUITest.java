package hotdogProject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import abbot.tester.ComponentTester;

public class GUITest {
	private ComponentTester tester;
	
	Hotdog main;
	@Before
	public void setUp(){
		tester = ComponentTester.getTester(Hotdog.class);
	}
	
	@After
	public void tearDonw(){
		tester = null;
	}
	
	@Test
	public void test() {
		
	}

}
