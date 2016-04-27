package playerBoundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import playerController.NavigateMainMenu;
import playerController.PlayLevel;
import playerEntity.GameModel;

/**
 * 
 * @author tuckerhaydon
 *
 */
public class LevelSelectionMenu extends JFrame {
	
	JButton levels[];
	JButton goToMainMenu;
	KabasujiPlayerApplication app;
	GameModel m;
	
	public LevelSelectionMenu(KabasujiPlayerApplication app, GameModel m){
		super();
		this.app = app;
		this.m = m;
		goToMainMenu = new JButton("Main Menu");
		goToMainMenu.setBackground(new Color(128, 128, 128));
		goToMainMenu.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		
		levels = new JButton[15];
		// Create the 15 level buttons
		for(int i = 0; i < 15; i++){
			levels[i] = new JButton("Level " + (i+1));
			try{
				if(m.getLevels()[i].getIsCompleted()){
					levels[i].setBackground(Color.GREEN);
				}
				else if(m.getLevels()[i].getIsUnlocked()){
					levels[i].setBackground(Color.YELLOW);
				}
				else{
					levels[i].setBackground(Color.RED);
				}
			}
			catch(NullPointerException e){
				levels[i].setBackground(Color.GRAY);
			}
			levels[i].setOpaque(true);
			levels[i].setBorderPainted(false);
			levels[i].setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		}
	}
	
	public void initView(){
		
		setResizable(false);
		// Set properties of the frame
		this.setSize(1000,1000);
		this.setTitle("Level Selection Menu");
		this.getContentPane().setBackground(new Color(255, 228, 225));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set the layout of the frame
		getContentPane().setLayout(new GridLayout(3,1));

		// Add the buttons
		this.add(goToMainMenu);
		
		for(JButton b:levels){
			this.add(b);
		}
	}
	
	public void initControllers(){
		goToMainMenu.addActionListener(new NavigateMainMenu(app,m));
		// TODO exportGame controller
		for(int i = 0; i < 15; i++){
			levels[i].addActionListener(new PlayLevel(app, m, i + 1));
		}

	}
	
	public void refreshView(){
		
		for(int i = 0; i < 15; i++){
			try{
				if(m.getLevels()[i].getIsCompleted()){
					levels[i].setBackground(Color.GREEN);
				}
				else if(m.getLevels()[i].getIsUnlocked()){
					levels[i].setBackground(Color.YELLOW);
				}
				else{
					levels[i].setBackground(Color.RED);
				}
			}
			catch(NullPointerException e){
				levels[i].setBackground(Color.GRAY);
			}
			levels[i].setOpaque(true);
			levels[i].setBorderPainted(false);
			levels[i].setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		}
		
		for(JButton button: levels){
			button.repaint();
		}
	}
}


