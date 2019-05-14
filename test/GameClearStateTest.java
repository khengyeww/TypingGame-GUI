import static org.junit.Assert.*;

import org.junit.Test;

public class GameClearStateTest {

	private GameClearState state = new GameClearState();
	
	@Test
	public void test() {

		assertEquals(state, state.processKey(" "));
		
		assertEquals(state, state.processMouse(0,0));
		
		assertEquals(state, state.processTime(1000));
	}

}
