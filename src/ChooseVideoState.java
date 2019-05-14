import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ChooseVideoState implements State {

	private InputVideoName inputVideoName = InputVideoName.getInstance();
	private boolean validName = true;
	
	public State processKey(String event) {
		if(!inputVideoName.getMode()) {
			if(event.equals("s")) {
				if(!inputVideoName.confirmSafeToLeave() || !validName)
					return new BackToDefaultState();
				return new TitleState();
			}
			else if(event.equals("c"))
				Model.swapBetweenMode();
			else if(event.equals("f") && !Model.getMode()) {
				inputVideoName.startInput();
			}
		}
		else
			inputVideoName.inputVideo(event);

		return this;
	}

	public State processMouse(int x, int y) {
		if(600 < x && x <= 750 && 390 < y && y <= 460) {
			if(inputVideoName.getMode())
				return new StillInEditState();
			else if(!inputVideoName.confirmSafeToLeave() || !validName)
				return new BackToDefaultState();
			else
				return new TitleState();
		}
		else if(60 < x && x <= 335 && 295 < y && y <= 355) {
			inputVideoName.setMode(false);
			Model.swapBetweenMode();
		}
		else if(370 < y && y <= 400) {
			if(75 < x && x <= 465 && !inputVideoName.getMode())
				inputVideoName.startInput();
			else if(470 < x && x <= 525 && inputVideoName.getMode()) {
				inputVideoName.saveVideoName();
				inputVideoName.setMode(false);
			}
		}

		return this;
	}
	
	public State processTime(int msec) {
		return this;
	}
	
	public void paint(Graphics g) {

		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Game.WIN_WIDTH, Game.WIN_HEIGHT);

		g.setColor(Color.WHITE);
		g.fillRect(60, 300, 275, 50);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 15));
		g.drawString("DEFAULT", 75, 330);
		g.drawString("CUSTOM", 250, 330);

		View.backOption(g);

		g.setFont(new Font(Model.COMIC_FONT, Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("How to set your video:", 50, 100);

		g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 15));
		g.drawString("1) First, copy and paste your video into same folder ( Please use .mp4 video )", 100, 125);
		g.drawString("2) Press ' c ' or Click CUSTOM at below", 100, 145);
		g.drawString("3) Press ' f ' or Click the black box below to insert the name of your video + \".mp4\"", 100, 165);
		g.drawString("eg: \" myvideo.mp4\"", 130, 185);
		g.drawString("4) If \"This video is invalid\", make sure :", 100, 205);
		g.drawString("1. Is the video name correct?", 130, 225);
		g.drawString("2. Is your file correctly placed in bin?", 130, 245);
		g.drawString("5) If \"Valid!\", your file is detected!", 100, 265);
		g.drawString("6) Hit ENTER key or Click \"Done\" button and proceed to back", 100, 285);

		g.setFont(new Font("Calibri", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Shortcut Keys:", 15, 500);
		g.drawString("' s ' for return", 30, 520);
		g.drawString("' c '  to change between custom/default", 30, 535);
		g.drawString("' f '  to type video name", 30, 550);

		g.setColor(Color.MAGENTA);
		if(Model.getMode()) {
			g.fillRect(62, 295, 170, 60);

			g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 25));
			g.setColor(Color.CYAN);
			g.drawString("DEFAULT", 90, 335);
		}
		else {
			g.fillRect(163, 295, 170, 60);
			g.setColor(Color.BLACK);
			g.fillRect(75, 370, 450, 30);

			g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 25));
			g.setColor(Color.CYAN);
			g.drawString("CUSTOM", 195, 335);

			g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 15));
			g.setColor(Color.WHITE);
			g.drawString("Video Name: ", 85, 390);
			g.drawString(inputVideoName.getUserVideoName(), 180, 390);

			if(inputVideoName.confirmSafeToLeave()) {
				g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 25));
				if(inputVideoName.checkIfVideoExist() && !inputVideoName.getUserVideoName().equals("/")) {
					validName = true;
					g.setColor(Color.GREEN);
					g.drawString("Valid!", 250, 445);
				}
				else {
					validName = false;
					g.setColor(Color.RED);
					g.drawString("This video is invalid", 165, 445);
				}
			}

			if(inputVideoName.getMode()) {
				g.setColor(Color.WHITE);
				g.fillRect(470, 375, 50, 20);
				g.setFont(new Font(Model.COMIC_FONT, Font.BOLD, 15));
				g.setColor(Color.BLACK);
				g.drawString("Done", 475, 390);
			}
		}
	}

	public boolean getValidName(){
		return validName;
	}
	
}
