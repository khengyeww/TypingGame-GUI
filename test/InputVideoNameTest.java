import static org.junit.Assert.*;

import org.junit.Test;

public class InputVideoNameTest {

	private InputVideoName inputVideoName = InputVideoName.getInstance();
	
	@Test
    public void test() {
        assertEquals(inputVideoName.confirmSafeToLeave(), true);
        assertEquals(inputVideoName.getMode(), false);
        
        assertEquals(inputVideoName.getUserVideoName().equals(""), Model.getMode());
        assertEquals(!inputVideoName.getUserVideoName().equals(""), !Model.getMode());
		 assertNotEquals(inputVideoName.getUserVideoName().equals(""), inputVideoName.getMode());
		 assertNotEquals(inputVideoName.getUserVideoName().equals(""), InputVideoName.getReadVideoName());
        assertEquals(Model.getMode(), inputVideoName.confirmSafeToLeave());
        assertNotEquals(!Model.getMode(), inputVideoName.confirmSafeToLeave());
        assertNotEquals(!inputVideoName.getUserVideoName().equals(""), inputVideoName.confirmSafeToLeave());
    }
	
	
}
