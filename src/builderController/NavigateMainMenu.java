package builderController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import builderBoundary.KabasujiBuilderApplication;

/**
 * handles navigateMainMenu action
 * @author tuckerhaydon
 *
 */
public class NavigateMainMenu implements ActionListener {
	
	KabasujiBuilderApplication app;
	
	/**
	 * NavigateMainMenu constructor
	 * @param app
	 */
	public NavigateMainMenu(KabasujiBuilderApplication app){
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		app.displayMainMenu();
	}

}
