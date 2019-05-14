import static org.junit.Assert.*;

import org.junit.Test;

public class PlayingStateTest {

	private PlayingState state = new PlayingState();
	
	@Test
	public void testForPlayingState() {
		assertEquals(state, state.processKey(" "));

		assertEquals(state, state.processMouse(0, 0));
		
		assertEquals(state, state.processTime(1000));
		
		assertNotEquals(PlayingState.getGameCond(), state);
		
		PlayingState.resetGameClear();
		PlayingState.setGameClear();
	}

}
