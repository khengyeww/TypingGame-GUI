import java.io.File;

public class InputVideoName {

	private static InputVideoName inputvideo;
	
	private static final String DEFAULT_VIDEO_NAME = "kalimba.mp4";
	private String storePrevVideoName = "";
	private static String userVideoName = "";

	public static String readVideoName = "";
	private boolean isInputtingVideoName = false;
	
	private InputVideoName() {}
	
	public static InputVideoName getInstance() {
		if(inputvideo == null)
			inputvideo = new InputVideoName();
		return inputvideo;
	}
	
	public boolean getMode() {
		return isInputtingVideoName;
	}

	public void setMode(boolean mode) {
		isInputtingVideoName = mode;
	}
	
	public void inputVideo(String key) {
		switch (key) {
			case "ENTER":
				saveVideoName();
				isInputtingVideoName = false;
				break;
			case "BACK_SPACE":
				if(!userVideoName.equals(""))
					userVideoName = userVideoName.substring(0, userVideoName.length() - 1);
				break;
			case "ESC":
				break;
			default:
				userVideoName += key;
				break;
		}
	}

	public static synchronized void setVideoName() {

		if(Model.getMode()) {
			readVideoName = DEFAULT_VIDEO_NAME;
		}
		else {
			readVideoName = userVideoName;
		}

	}
	
	public boolean confirmSafeToLeave() {
		return (!Model.getMode() && !userVideoName.equals("") || Model.getMode());
	}
	
	public String getUserVideoName() {
		return userVideoName;
	}
	
	public static synchronized String getReadVideoName() {
		return readVideoName;
	}
	
	public void resetUserVideoName() {
		userVideoName = "";
	}
	
	public void startInput() {
		resetUserVideoName();
		isInputtingVideoName = true;
	}
	
	public void saveVideoName() {
		if(userVideoName.equals(""))
			userVideoName = storePrevVideoName;
		storePrevVideoName = userVideoName;
	}
	
	public boolean checkIfVideoExist() {
		File tmpDir = new File(userVideoName);

		return tmpDir.exists();
	}
}