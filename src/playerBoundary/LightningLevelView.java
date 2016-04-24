package playerBoundary;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import playerController.LevelController;
import playerEntity.GameModel;
import playerEntity.LightningLevel;

/**
 * 
 * @author tuckerhaydon
 *
 */
public class LightningLevelView extends LevelView{
	KabasujiPlayerApplication app;
	JLabel timeLeft; 
	GameModel m;
	LightningLevel level;
	
	public LightningLevelView(LightningLevel lightningLvl, GameModel m, KabasujiPlayerApplication app) {
		super();
		this.level = lightningLvl;
		this.m=m;
		this.app = app;
		
		bullpenView = new BullpenView(app, level.getBullpen());
		boardView = new BoardView(level.getBoard(),app,m);

	}

	@Override
	public void initView() {
		// Init the subcomponets views
		bullpenView.initView();
		boardView.initView();
		
		//set properties of the view
		this.setSize(900, 900);
		
		// No layout; using exact coordinates.
		setLayout(null);
		
		// Create the scrollPane
		scrollPane = new JScrollPane(bullpenView, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 25, 850, 7*bullpenView.getSquareWidth());
		add(scrollPane);
		
		
		// Add the boardView
		boardView.setBounds(25, 8*bullpenView.getSquareWidth(), 12*bullpenView.getSquareWidth(), 12*bullpenView.getSquareWidth());
		add(boardView);
		
		String a = new String ("Time Remaining: ");
		a.concat(Integer.toString(level.getRemainingTime()));
		
		JLabel lblTimeLeft = new JLabel(a);
		lblTimeLeft.setBounds(770, 770, 60, 15);
		add(lblTimeLeft);
		
		JLabel lblScoreNStuff = new JLabel("Score n stuff");
		lblScoreNStuff.setBounds(770, 850, 60, 15);
		add(lblScoreNStuff);
		
		setBackground(new Color(255, 228, 225));
		
	}
	
	@Override
	public void initControllers(){
		// Init the controllers of the subcomponents
		bullpenView.initControllers();
		boardView.initControllers();
		
		// Init own controllers
		setMouseAdapter(new LevelController(app,app.getGameWindow().getLevelView(),m));
	}
}