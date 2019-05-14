import static org.junit.Assert.*;

import org.junit.Test;

public class TitleStateTest {

	private TitleState state = new TitleState();
	
	@Test
	public void testForTitleState() {

		assertEquals(state, state.processKey(" "));
		assertNotEquals(state, state.processKey("p"));
		assertNotEquals(state, state.processKey("i"));
		assertNotEquals(state, state.processKey("s"));
		assertNotEquals(state, state.processKey("r"));

		assertEquals(state, state.processMouse(0, 0));
		assertNotEquals(state, state.processMouse(470, 300));
		
		assertEquals(state, state.processTime(1000));
	}

}
