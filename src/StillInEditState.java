import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StillInEditState implements State {

	public State processKey(String event) {
		if(event.equals("ENTER"))
			return new ChooseVideoState();
		return this;
	}

	public State processMouse(int x, int y) {
		if(340 < x && x <= 460 && 325 < y && y <= 375) {
			return new ChooseVideoState();
		}

		return this;
	}
	
	public State processTime(int msec) {
		return this;
	}

	public void paint(Graphics g) {

		View.sameBackground(g);

		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 35));
		g.setColor(Color.RED);
		g.drawString("WARNING", 320, 220);
		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("You are in the middle of typing video name!!!", 185, 265);
		
		g.setColor(Color.PINK);
		g.fillRect(340, 325, 120, 50);
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("Okay", 365, 360);
		
		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Shortcut Keys:", 15, 500);
		g.drawString("' ENTER ' for return", 30, 520);
	}
}
