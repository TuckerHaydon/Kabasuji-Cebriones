package playerBoundary;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import playerController.LoadGame;
import playerEntity.GameModel;

/**
 * 
 * @author tuckerhaydon
 *
 */
public class KabasujiPlayerApplication {
	
	GameWindow gameWindow;
	MainMenu mainMenu;
	LevelSelectionMenu levelSelectionMenu;
	AchievementsMenu achievementsMenu;
	GameModel m;
	
	public KabasujiPlayerApplication(){
		init();
		playThemeSong();
	}
	
	public KabasujiPlayerApplication(String path){
		initBuilderModel(path);
		initView();
		initControllers();
		displayGameWindow();
	}
	
	public void init(){
		displaySplashScreen();
		initModel();
		initView();
		initControllers();
		displayMainMenu();
	}
	
	public void initModel(){
		m = new GameModel();
		m.loadLevel("src/resources/games/ExampleGame1");
		m.setCurrentLevel(1);
		m.initModel();
	}
	
	void initBuilderModel(String path){
		m = new GameModel();
		m.loadLevel(path);
		m.setCurrentLevel(1);
		m.initModel();
	}
	
	public void initView(){
		
		// Create the various frames
		gameWindow = new GameWindow(this, m);
		mainMenu = new MainMenu(this, m);
		levelSelectionMenu = new LevelSelectionMenu(this, m);
		achievementsMenu = new AchievementsMenu(this, m);
		
		// Initialize all the views in the various frames
		gameWindow.initView();
		mainMenu.initView();
		levelSelectionMenu.initView();
		achievementsMenu.initView();
		
	}
	
	public void initControllers(){
		gameWindow.initControllers();
		mainMenu.initControllers();
		levelSelectionMenu.initControllers();
		achievementsMenu.initControllers();
	}
	
	public void displayMainMenu(){
		mainMenu.setVisible(true);
		gameWindow.setVisible(false);
		levelSelectionMenu.setVisible(false);
		achievementsMenu.setVisible(false);
	}
	
	public void displayGameWindow(){
		mainMenu.setVisible(false);
		gameWindow.setVisible(true);
		levelSelectionMenu.setVisible(false);
		achievementsMenu.setVisible(false);
	}
	
	public void displayLevelSelectionMenu(){
		mainMenu.setVisible(false);
		gameWindow.setVisible(false);
		levelSelectionMenu.setVisible(true);
		achievementsMenu.setVisible(false);
	}
	
	public void displayAchievementsMenu(){
		mainMenu.setVisible(false);
		gameWindow.setVisible(false);
		levelSelectionMenu.setVisible(false);
		achievementsMenu.setVisible(true);
		achievementsMenu.updateView();
	}
	
	public GameWindow getGameWindow(){
		return gameWindow;

	}
	
	public GameModel getGameModel(){
		return m;
	}
	
	public AchievementsMenu getAchievementsMenu(){
		return this.achievementsMenu;
	}
	
	void displaySplashScreen(){
//		JFrame splash = new JFrame("Splash Screen");
		JFrame splash = new JFrame("Tucker,Dorothy,Kacper,Joey,Nidhi,Damani");
		splash.setBounds(200, 250, 505, 400);
		JLabel meow = new JLabel("");
		meow.setIcon(new ImageIcon("src/resources/pictures/RainbowBunchie.gif"));
//		splash.getContentPane().add(new JLabel("Welcome!\nAuthors: Tucker, Dorothy, Kacper, Nidhi, and Damani", SwingConstants.CENTER));
		splash.getContentPane().add(meow);
		
		splash.setVisible(true);
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < 3000);
		splash.dispose();
	}
	
	void playThemeSong(){
		try {
			File f = new File("src/resources/audio/rainbow_bunchie.wav");
			//File f = new File("src/resources/audio/themesong.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(f));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
