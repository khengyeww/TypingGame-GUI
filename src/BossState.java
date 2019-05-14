import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BossState implements State {
	
	public State processKey(String event) {
		if(event.equals("ESC")){
			JavaFxbyJFrame.pauseVideo();
			return PlayingState.getState();
		}
		
		return this;
	}

	public State processMouse(int x, int y) {
		return this;
	}

	public State processTime(int msec) {
		return this;
	}

	public void paint(Graphics g) {
		
		// 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
		Image imagee = Toolkit.getDefaultToolkit().getImage(getClass().getResource("robot.png"));
				
		g.drawImage(imagee, 100, 100, null);
	}

}
