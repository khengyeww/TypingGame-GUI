import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings({ "serial", "deprecation" })
public class View extends JPanel {

	private Model model;

	// Sample instance variables:
	private AudioClip sound;

	public View(Model model) {
		this.model = model;

		// サウンドを読み込む
		sound = Applet.newAudioClip(getClass().getResource("bomb.wav"));

	}

	/**
	 * 画面を描画する
	 * @param g  描画用のグラフィックスオブジェクト
	 */
	@Override
	public void paintComponent(Graphics g) {

		// 画面をいったんクリア
		clear(g);

		InputVideoName.setVideoName();

		// モデルから状態を取得し，状態に応じて描画
		State state = model.getState();
		state.paint(g);

	}

	/**
	 * 画面を黒色でクリア
	 * @param g  描画用のグラフィックスオブジェクト
	 */
	public void clear(Graphics g) {
		Dimension size = getSize();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, size.width, size.height);
	}

	public void playBombSound() {
		sound.stop(); // まず音を停めてから
		sound.play(); // 再生する
	}

	public static void backOption(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(600, 390, 150, 70);
		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 45));
		g.setColor(Color.PINK);
		g.drawString("Back", 625, 440);
	}
	
	public static void sameBackground(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Game.WIN_WIDTH, Game.WIN_HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(155, 160, 475, 250);
	}

}