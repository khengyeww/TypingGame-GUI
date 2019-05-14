import static org.junit.Assert.*;

import org.junit.Test;

public class InstructionStateTest {

	private InstructionState state = new InstructionState();
	
	@Test
	public void testForInstructionState() {

		assertEquals(state, state.processKey(" "));
		assertNotEquals(state, state.processKey("i"));

		assertEquals(state, state.processMouse(0, 0));
		assertNotEquals(state, state.processMouse(750, 460));
		
		assertEquals(state, state.processTime(1000));
	}

}
