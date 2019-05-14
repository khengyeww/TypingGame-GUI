import java.awt.Graphics;

public interface State {
	
	/*
	 * タイトル状態におけるイベントの処理
	 * @param event イベントを表す文字列
	 * @return イベント処理後の状態
	 */	
	public State processKey(String event);

	public State processMouse(int x, int y);

	public State processTime(int msec);
	
	/*
	 * タイトル画面を描画
	 * @param g グラフィックスオブジェクト
	 */
	public void paint(Graphics g);
	
}
