import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameClearState implements State {

	private int launchCount = 0;
	private int inGameTimer = 0;
	private boolean countDownForGameClear = true;

	public State processKey(String event) {
		if(!countDownForGameClear) {
		switch (event) {
			case "0":
				resetStage();
				return new TitleState();
			case "1":
				resetStage();
				return new PlayingState();
			default:
				break;
		}
		}

		return this;
	}

	public State processMouse(int x, int y) {
		if(400 < x && x <= 590 && 590 < y && y <= 650) {
			resetStage();
			return new TitleState();
		}
		else if(650 < x && x <= 840 && 590 < y && y <= 650) {
			resetStage();
			return new PlayingState();
		}
		
		return this;
	}

	public State processTime(int msec) {
		inGameTimer++;
		return this;
	}

	/*
	 * タイトル画面を描画
	 * @param g グラフィックスオブジェクト
	 */
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Model.getPCWidth(), Model.getPCHeight());

		if(countDownForGameClear) {
			g.setColor(Color.PINK);
			g.fillRect(155, 160, 475, 250);
			g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 40));
			g.setColor(Color.WHITE);
			g.drawString("Well Done!!!", 275, 230);
			g.drawString("Result video in:", 250, 280);
			
			g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 35));
			g.setColor(Color.MAGENTA);
			switch (inGameTimer) {
			case 0:
				g.drawString("5", 370, 330);
				break;
			case 1:
				g.drawString("4", 370, 330);
				break;
			case 2:
				g.drawString("3", 370, 330);
				break;
			case 3:
				g.drawString("2", 370, 330);
				break;
			case 4:
				g.drawString("1", 370, 330);
				break;
			case 5:
				g.drawString("0", 370, 330);
				countDownForGameClear = false;
				break;
			default:
				break;
			}
		}
		else
			startResultVideo(g);

		}

	public void startResultVideo(Graphics g) {
		if(launchCount == 0) {
			JavaFxbyJFrame.start();
			launchCount++;
		}
		g.setColor(Color.BLACK);
		g.fillRect(400, 590, 190, 60);
		g.fillRect(650, 590, 190, 60);

		g.setFont(new Font("Calibri", Font.BOLD, 35));
		g.setColor(Color.PINK);
		g.drawString("Main Menu", 410, 630);
		g.drawString("Retry", 705, 630);
		
		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Shortcut Keys:", 15, 620);
		g.drawString("' 0 ' for Main menu", 30, 640);
		g.drawString("' 1 ' for Retry", 30, 655);
	}

	public void resetStage() {
		JavaFxbyJFrame.stopVideo();
		PlayingState.resetGameClear();
	}
	
}
