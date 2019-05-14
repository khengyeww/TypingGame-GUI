import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {

	private Game g;
	
    @Test
    public void モデルの管理する時刻データが時間経過とともに増えるかテスト() {
        Model model = new Model(g);
        int time = model.getTime();
        model.processTimeElapsed(100);
        assertEquals(model.getTime(), time + 1);

        // その他のイベントでは増えない
        model.processKeyTyped("a");
        assertEquals(model.getTime(), time + 1);
    }
    
    @Test
    public void testFunctionOfMethods() {
    	Model.setToCustom();
    	Model.setToDefault();
    	Model.swapBetweenMode();
    	Model.getPCHeight();
    	Model.getPCWidth();
    }

}
