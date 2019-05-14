import static org.junit.Assert.*;

import org.junit.Test;

public class BackToDefaultStateTest {

	private BackToDefaultState state = new BackToDefaultState();
	
	@Test
	public void testForBackToDefaultState() {

		assertEquals(state, state.processKey(" "));
		assertNotEquals(state, state.processKey("n"));
		assertNotEquals(state, state.processKey("ENTER"));
		
		assertEquals(state, state.processMouse(0, 0));
		assertNotEquals(state, state.processMouse(370, 375));
		
		for(int x = 421; x <= 520; x++) {
			for(int y = 326; y <= 375; y++) {
				assertNotEquals(state, state.processMouse(x, y));
			}
		}
		
		assertEquals(state, state.processTime(1000));
	}

}
