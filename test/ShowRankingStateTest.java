import static org.junit.Assert.*;

import org.junit.Test;

public class ShowRankingStateTest {

	private ShowRankingState state = new ShowRankingState();
	
	@Test
	public void testForShowRankingState() {

		assertEquals(state, state.processKey(" "));
		assertNotEquals(state, state.processKey("r"));

		assertEquals(state, state.processMouse(0, 0));
		assertNotEquals(state, state.processMouse(750, 460));
		
		assertEquals(state, state.processTime(1000));
	}

}
