import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class YieldingSlider extends Slider {
	private long lastTimeMousePressed = 0;

    public YieldingSlider()
    {
        addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(final MouseEvent event)
            {
                lastTimeMousePressed = System.currentTimeMillis();
            }
        });
    }

    public boolean mouseWasPressedWithinLast(long t)
    {
        return (System.currentTimeMillis() - lastTimeMousePressed) <= t;
    }
}
