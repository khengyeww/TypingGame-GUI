import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ShowRankingState implements State {
	
	public State processKey(String event) {
		if(event.equals("r"))
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
		g.drawString("Top Score Ranking:", 50, 100);
		
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 20));
		g.drawString("harlo warudo", 100, 150);
		
		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.drawString("Shortcut Keys:", 15, 500);
		g.drawString("' r ' for return", 30, 520);
		
	}
	
}
