import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class InstructionState implements State {
	
	public State processKey(String event) {
		if(event.equals("i"))
			return new TitleState();
		return this;
	}

	public State processMouse(int x, int y) {
		if(600 < x && x <= 750 && 390 < y && y <= 460) {
			return new TitleState();
		}
		
		return this;
	}
	
	public State processTime(int msec) {
		return this;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);

		View.backOption(g);
		
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("How to play:", 50, 100);
		g.drawString("注意:", 50, 270);
		g.drawString("When GameClear:", 50, 380);
		g.drawString("When in Game:", 50, 450);
		
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 20));
		g.drawString("1) Choose your own video in SET VIDEO or leave it for default", 100, 130);
		g.drawString("2) Return to main menu and click PLAY!", 100, 160);
		g.drawString("3) Type the sentence shown and watch your video as it goes", 100, 190);
		g.drawString("4) There is no gameover so EVERYONE can be a WINNER!!!", 100, 220);
		g.drawString("Press 'ESC' key to go meet Boss", 100, 480);
		g.drawString("You can rewatch your result video~~~", 100, 410);
		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 20));
		g.drawString("動画の画面を押したら、キーボードからのインプットはできなくなる。", 100, 300);
		g.drawString("その場合は一旦端末画面に切り替えてください。", 100, 330);
		
		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.drawString("Shortcut Keys:", 15, 520);
		g.drawString("' i ' for return", 30, 540);
		
	}
}
