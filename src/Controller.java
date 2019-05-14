import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener, MouseListener {

    private static final int DELAY = 1000; // msec
    private Model model;
    private Timer timer;
    private static final int ENTER = KeyEvent.VK_ENTER;
    private static final int BCK = KeyEvent.VK_BACK_SPACE;
    private static final int ESC = KeyEvent.VK_ESCAPE;

    public Controller(Model model) {
    	// モデルを保持（イベントの通知先）
        this.model = model;
        timer = new Timer(DELAY, this);
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.processTimeElapsed(DELAY);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	if(e.getKeyChar() != ENTER && e.getKeyChar() != BCK && e.getKeyChar() != ESC)
    		model.processKeyTyped(Character.toString(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // do nothing
    	switch (e.getKeyCode()) {
    		case ENTER:
        		model.processKeyTyped("ENTER");
    			break;
    		case BCK:
        		model.processKeyTyped("BACK_SPACE");
    			break;
    		case ESC:
        		model.processKeyTyped("ESC");
    			break;
    		default:
    			break;
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        model.processMousePressed(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }
}