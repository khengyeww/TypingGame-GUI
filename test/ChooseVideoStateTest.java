import static org.junit.Assert.*;

import org.junit.Test;

public class ChooseVideoStateTest {

	private ChooseVideoState state = new ChooseVideoState();
	private InputVideoName inputVideoName = InputVideoName.getInstance();
	
	@Test
	public void TestIfInputIsValid() {
		String event = "b";
		
		assertNotEquals(state.processKey(""), state.getValidName());
		
		if(inputVideoName.getMode())
			assertEquals(state.processKey(event), inputVideoName.getMode());
		assertNotEquals(state.processKey("s"), inputVideoName.getMode());
		assertNotEquals(state.processKey("c"), inputVideoName.getMode());
		assertNotEquals(state.processKey("f"), inputVideoName.getMode());

		if(inputVideoName.confirmSafeToLeave() || !state.getValidName())
			assertNotEquals(state, state.processKey("s"));
		
		assertNotEquals(state.processKey("f"), Model.getMode());
		assertNotEquals(Model.getMode(), inputVideoName.getMode());
		assertEquals(!Model.getMode(), inputVideoName.getMode());
		
		assertNotEquals(inputVideoName.checkIfVideoExist(), state.getValidName());
		assertNotEquals(inputVideoName.getUserVideoName().equals("/"), state.getValidName());
		

		assertEquals(state, state.processTime(1000));
	}

}
