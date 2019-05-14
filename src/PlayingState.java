import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PlayingState implements State {

	private int inGameTimer = 0;
	private int launchCount = 0;
	private TypingList typinglist = TypingList.getInstance();
	private CheckTyping check = CheckTyping.getInstance();
	private String currentText = "";
	private boolean countDown = true;
	private static State currentState;
	private static boolean winCond = false;

	private synchronized void recordState(){
		currentState = this;
	}
	
	public State processKey(String event) {
		if(countDown)
			return this;
		else {
			switch (event) {
				case "ENTER":
					break;
				case "BACK_SPACE":
					break;
				case "ESC":
					JavaFxbyJFrame.pauseVideo();
					return new BossState();
				default:
					check.checkOnTyping(event, currentText, inGameTimer);
					break;
			}
		}
		
		return this;
	}
	
	public static synchronized State getState() {
		return currentState;
	}
	
	public State processMouse(int x, int y) {
		return this;
	}
	
	public State processTime(int msec) {
		inGameTimer++;
		recordState();

		if(!countDown)
			check.checkOnTime(inGameTimer);
		
		if(winCond) {
			JavaFxbyJFrame.stopVideo();
			return new GameClearState();
		}
		return this;
	}
	
	/*
	 * タイトル画面を描画
	 * @param g グラフィックスオブジェクト
	 */
	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Model.getPCWidth(), Model.getPCHeight());

		if(countDown) {
			g.setColor(Color.BLUE);
			g.fillRect(155, 160, 475, 250);
		}
		g.setColor(Color.WHITE);
		switch (inGameTimer) {
			case 0:
				g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 25));
				g.drawString("3", 370, 300);
				break;
			case 1:
				g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
				g.drawString("2", 370, 300);
				break;
			case 2:
				g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 35));
				g.drawString("1", 370, 300);
				break;
			case 3:
				g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 40));
				g.drawString("Start!!!", 330, 300);
				break;
			default:
				countDown = false;
				startGame(g);
				break;
		}
	}

	public void getNewText() {
		currentText = typinglist.getTextList();
		check.setFalse();
	}

	public void startGame(Graphics g) {
		if(launchCount == 0) {
			JavaFxbyJFrame.start();
			launchCount++;
		}
		
		if(check.requestForNewString() && !winCond) {
			check.resetForNextString();
			getNewText();
		}

		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString(currentText, 55, 580);
		g.setColor(Color.RED);
		g.drawString("> " + check.getCorrectText(), 33, 610);
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		if(inGameTimer > 4)
			g.drawString(Integer.toString(inGameTimer - 4) + " / " + Integer.toString((int)JavaFxbyJFrame.getVideoDuration()), 800, 640);		
	}

	public static synchronized boolean getGameCond() {
		return winCond;
	}
	public static synchronized void setGameClear() {
		winCond = true;
	}
	public static synchronized void resetGameClear() {
		winCond = false;
	}
	
}
