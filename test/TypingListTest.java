import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TypingListTest {

	private RandNumGenerator rand = RandNumGenerator.getInstance();
	private ArrayList<String> listLine = new ArrayList<>();
	private ArrayList<Integer> trackDuplicates = new ArrayList<>();
	
	@Test
	public void testForTypingListCheckRandomGenerator() {
		listLine.add("a");
		assertEquals(1, listLine.size());
		
		int random = rand.nextInt(listLine.size());
		assertEquals(0, random);
		
		trackDuplicates.add(random);
		assertEquals(trackDuplicates.size(), listLine.size());
		
	}

}
