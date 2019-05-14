import static org.junit.Assert.*;

import org.junit.Test;

public class StillInEditStateTest {

	private StillInEditState state = new StillInEditState();

	@Test
	public void testForStillInEditState() {

		assertEquals(state, state.processKey(" "));
		assertNotEquals(state, state.processKey("ENTER"));

		assertEquals(state, state.processMouse(0, 0));
		assertNotEquals(state, state.processMouse(460, 375));
		
		assertEquals(state, state.processTime(1000));
	}

}
