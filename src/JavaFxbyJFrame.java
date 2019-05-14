import java.awt.Dimension;
import java.io.File;

import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

// Reference: "https://docs.oracle.com/javafx/2/swing/swing-fx-interoperability.htm"

public class JavaFxbyJFrame {

	private static Game game;
	private static Model model;
	private static JavaFxbyJFrame javaFX;
	
	private static int desiredWidth = 890; // 890 // 534 // 440 // 380
	private static int desiredHeight = 500; // 500 // 300 // 300 // 700
	private static MediaPlayer mediaPlayer;

	private static final JFXPanel fxPanel = new JFXPanel();

	private JavaFxbyJFrame(Game g, Model m) {
		game = g;
		model = m;
	}
	
	public static JavaFxbyJFrame getInstance(Game g, Model m) {
		if(javaFX == null)
			javaFX = new JavaFxbyJFrame(g, m);
		return javaFX;
	}

	private static void initAndShowGUI() {

		// This method is invoked on the EDT thread
		// JFXPanel fxPanel = new JFXPanel();

		Platform.setImplicitExit(false);

		// JFXPanelのサイズを指定してJFrameに追加
		fxPanel.setPreferredSize(new Dimension(desiredWidth, desiredHeight));
		if(PlayingState.getGameCond())
			fxPanel.setPreferredSize(new Dimension(desiredWidth, desiredHeight + 35));
		game.add(fxPanel);

		// JFrame側のパネルサイズを動画に合わせる
		game.getContentPane().setPreferredSize(new Dimension(desiredWidth, desiredHeight));

		game.setSize(920, 700);
		//System.out.println(desiredHeight + "  this width  " + desiredWidth);
		
		fxPanel.setVisible(true);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);
			}
		});

	}
	
	public static void seekFrame(double timeFrame) {
		mediaPlayer.seek(Duration.seconds(timeFrame));
	}

	public static double getVideoDuration() {
		return mediaPlayer.getCycleDuration().toSeconds();
	}
	
	public static synchronized void pauseVideo() {
		if(mediaPlayer.getStatus() == Status.PLAYING) {
			mediaPlayer.pause();
			fxPanel.setVisible(false);
		}
		else {
			mediaPlayer.play();
			fxPanel.setVisible(true);
		}
	}
	
	public static synchronized void stopVideo() {
		mediaPlayer.stop();
		game.setSize(Game.WIN_WIDTH, Game.WIN_HEIGHT);
		fxPanel.setVisible(false);
	}

	private static void initFX(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);

		// Create the media source.
		// File f = new File( "kalimba.mp4" );
		File f = new File( InputVideoName.getReadVideoName() );

		// 動画再生クラスをインスタンス化
		Media media = new Media( f.toURI().toString() );
		//Media media = new Media(source);

		// Create the player and set to play automatically.
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);
		
		if(!PlayingState.getGameCond()) {
			// Create the view and add it to the Scene.
			MediaView mediaView = new MediaView(mediaPlayer);
			root.getChildren().add(mediaView);

			mediaView.fitWidthProperty().bind(scene.widthProperty());
			mediaView.fitHeightProperty().bind(scene.heightProperty());
			mediaView.setPreserveRatio(true);
		}
		else {
			MediaControl mediaControl = new MediaControl(mediaPlayer, scene);
			scene.setRoot(mediaControl);
		}
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			if (null != key.getCode()) switch (key.getCode()) {
			case ENTER:
				model.processKeyTyped("ENTER");
				break;
			case BACK_SPACE:
				model.processKeyTyped("BACK_SPACE");
				break;
			case ESCAPE:
				model.processKeyTyped("ESC");
				break;
			default:
				model.processKeyTyped(key.getText());
				break;
			}
		});
		
		return (scene);
	}

	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initAndShowGUI();
			}
		});
	}
}
