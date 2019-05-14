
public class CheckTyping {

	private static CheckTyping checktypin;
	private String correctTextToDisplay = "";
	private int numCountOfText = 0;
	private boolean stringIsEmpty = true;

	private int timeOfLastKeyPressed = 0;
	private int setPlayFrame = 0;
	
	private CheckTyping() {}

	public static CheckTyping getInstance() {
		if(checktypin == null)
			checktypin = new CheckTyping();
		return checktypin;
	}

	public boolean requestForNewString() {
		return stringIsEmpty;
	}
	
	public void setFalse() {
		stringIsEmpty = false;
	}
	
	public void checkOnTyping(String input, String text, int timer) {
		char userInput = input.charAt(0);
		char generatedText = text.charAt(numCountOfText);
		
		timeOfLastKeyPressed = timer;
		correctTextToDisplay = correctTextToDisplay.substring(0, numCountOfText) + input;
		
		if(userInput == generatedText) {
			numCountOfText++;

			setPlayFrame = timer;
		}
		else {
			replayFrame();
		}
		
		if(numCountOfText == text.length()) {
			stringIsEmpty = true;

			if(timer - 4 > JavaFxbyJFrame.getVideoDuration()) {
				setPlayFrame = 0;
				timeOfLastKeyPressed = 0;
				PlayingState.setGameClear();
			}
		}
	}
	
	public void checkOnTime(int timer) {
		if((timer - timeOfLastKeyPressed) > 1)
			replayFrame();
	}
	
	public void replayFrame() {
		JavaFxbyJFrame.seekFrame(((setPlayFrame - 4.0) % JavaFxbyJFrame.getVideoDuration()) - 0.3);
	}
	
	public String getCorrectText() {
		return correctTextToDisplay;
	}
	
	public void resetForNextString() {
		numCountOfText = 0;
		correctTextToDisplay = "";
	}
	
}
