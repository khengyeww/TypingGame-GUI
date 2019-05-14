import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Model {

	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static final int PC_WIDTH = gd.getDisplayMode().getWidth();
	public static final int PC_HEIGHT = gd.getDisplayMode().getHeight();
	
    private View view;
    private Controller controller;
	private static boolean defaultMode = true;
    @SuppressWarnings("unused")
	private JavaFxbyJFrame javafx = null;

	public static final String COMIC_FONT = "Comic San MS";
    
    // ソフトウェアの状態を表す．初期状態はタイトル状態
    private State state = new TitleState();

    // Sample instance variables:
    private int time;
    private String typedChar = "";
    private int mx;
    private int my;

    public Model(Game g) {
        view = new View(this);
        controller = new Controller(this);
        // 動画の生成
        javafx = JavaFxbyJFrame.getInstance(g, this);
    }
    
    public static boolean getMode(){
    	return defaultMode;
    }
    public static synchronized void swapBetweenMode(){
    	defaultMode = !defaultMode;
    }
    public static synchronized void setToDefault(){
    	defaultMode = true;
    }
    public static synchronized void setToCustom(){
    	defaultMode = false;
    }

    public static synchronized int getPCWidth(){
    	return PC_WIDTH;
    }
    public static synchronized int getPCHeight(){
    	return PC_HEIGHT;
    }
    
    public synchronized void processTimeElapsed(int msec) {
        time++;
        //System.out.println(time);
        state = state.processTime(msec);
        view.repaint();
    }

    public synchronized void processKeyTyped(String typed) {
        //typedChar = typed;
        //System.out.println(typed);
    	state = state.processKey(typed);
        view.repaint();
    }

    public synchronized void processMousePressed(int x, int y) {
    	state = state.processMouse(x, y);
        mx = x;
        my = y;
        //System.out.println("X = " + mx + ", " + "Y = " + my);
        view.repaint();
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public int getTime() {
        return time;
    }

    public String getTypedChar() {
        return typedChar;
    }

    public int getMX() {
        return mx;
    }

    public int getMY() {
        return my;
    }
    
    public State getState() { 
    	return state; 
    }
    
}
