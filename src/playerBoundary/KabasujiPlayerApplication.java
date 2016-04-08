package playerBoundary;

import java.util.Scanner;

public class KabasujiPlayerApplication {
	
	GameWindow gameWindow;
	MainMenu mainMenu;
	LevelSelectionMenu levelSelectionMenu;
	AchievementsMenu achievementsMenu;
	
	public KabasujiPlayerApplication(){
		init();
	}

	
	public void init(){
		initModel();
		initView();
		initControllers();
		
		/* TESTING */
		Scanner scan = new Scanner(System.in);
		
		displayMainMenu();
		scan.nextLine();
		displayGameWindow();
		scan.nextLine();
		displayLevelSelectionMenu();
		scan.nextLine();
		displayAchievementsMenu();
		/* END TESTING */
	}
	
	public void initModel(){
		
	}
	
	public void initView(){
		gameWindow = new GameWindow(this);
		mainMenu = new MainMenu(this);
		levelSelectionMenu = new LevelSelectionMenu(this);
		achievementsMenu = new AchievementsMenu(this);
		
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
	}
}
