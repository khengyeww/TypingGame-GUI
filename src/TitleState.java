import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleState implements State {
	
	/*
	 * タイトル状態におけるイベントの処理
	 * @param event イベントを表す文字列
	 * @return イベント処理後の状態
	 */
	public State processKey(String event) {
		switch (event){
			case "p":
				return new PlayingState();
			case "i":
				return new InstructionState();
			case "s":
				return new ChooseVideoState();
			case "r":
				return new ShowRankingState();
			case "q":
				System.exit(0);
				break;
			default:
				break;
		}
		
		return this;
	}

	public State processMouse(int x, int y) {
		if(295 < x && x <= 470 && 232 < y && y <= 300) {
			return new PlayingState();
		}
		else if(260 < x && x <= 520 && 310 < y && y <= 370) {
			return new InstructionState();
		}
		else if(220 < x && x <= 570 && 380 < y && y <= 440) {
			return new ChooseVideoState();
		}
		else if(260 < x && x <= 530 && 450 < y && y <= 510) {
			return new ShowRankingState();
		}

		return this;
	}
	
	public State processTime(int msec) {
		return this;
	}

	/*
	 * タイトル画面を描画
	 * @param g グラフィックスオブジェクト
	 */
	public void paint(Graphics g) {

		String show = "";
		
		// 描画する
		g.setColor(Color.PINK);
		g.fillRect(180, 37, 400, 180);
		g.setColor(Color.GREEN);
		g.fillRect(295, 232, 175, 70);
		g.setColor(Color.ORANGE);
		g.fillRect(260, 310, 260, 60);
		if(Model.getMode())
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.CYAN);
		g.fillRect(220, 380, 350, 60);
		g.setColor(Color.RED);
		g.fillRect(260, 450, 270, 60);
		
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 80));
		g.setColor(Color.BLACK);
		g.drawString("Tvideos", 230, 150);

		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 65));
		g.drawString("PLAY", 300, 290);

		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
		g.drawString("INSTRUCTION", 280, 350);
		if(Model.getMode())
			show = "DEFAULT";
		else
			show = "CUSTOM";
		g.drawString("SET VIDEO : " + show, 225, 420);
		g.drawString("SCORE RANKING", 270, 490);

		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Shortcut Keys:", 15, 470);
		g.drawString("' p ' for play", 30, 490);
		g.drawString("' i '  for instruction", 30, 505);
		g.drawString("' s '  for set video", 30, 520);
		g.drawString("' r '  for score ranking", 30, 535);
		g.drawString("' q ' for quit", 30, 550);

	}

}
