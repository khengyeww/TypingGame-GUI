import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BackToDefaultState implements State {

	private InputVideoName inputVideoName = InputVideoName.getInstance();
	
	public State processKey(String event) {
		
		switch (event) {
			case "ENTER":
				inputVideoName.resetUserVideoName();
				Model.setToDefault();
				return new TitleState();
			case "n":
				return new ChooseVideoState();
			default:
				break;
		}
		
		return this;
	}

	public State processMouse(int x, int y) {
		if(270 < x && x <= 370 && 325 < y && y <= 375) {
			inputVideoName.resetUserVideoName();
			Model.setToDefault();
			return new TitleState();
		}
		else if(420 < x && x <= 520 && 325 < y && y <= 375) {
			return new ChooseVideoState();
		}

		return this;
	}
	
	public State processTime(int msec) {
		return this;
	}
	
	public void paint(Graphics g) {

		View.sameBackground(g);

		g.setColor(Color.PINK);
		g.fillRect(270, 325, 100, 50);
		g.setColor(Color.CYAN);
		g.fillRect(420, 325, 100, 50);
		
		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 35));
		g.setColor(Color.RED);
		g.drawString("The video name is invalid", 185, 220);
		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("Change back to Default?", 280, 270);

		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("Yes", 295, 360);
		g.drawString("No", 450, 360);

		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Shortcut Keys:", 15, 500);
		g.drawString("' ENTER ' for yes", 30, 520);
		g.drawString("' n ' for no", 30, 535);
	}
}
