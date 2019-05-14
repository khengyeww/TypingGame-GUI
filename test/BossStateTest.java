import static org.junit.Assert.*;

import org.junit.Test;

public class BossStateTest {

	private BossState state = new BossState();

	@Test
	public void testForBossState() {
		assertEquals(state, state.processKey(" "));
		
		assertEquals(state, state.processMouse(0,0));

		assertEquals(state, state.processTime(1000));
	}

}
