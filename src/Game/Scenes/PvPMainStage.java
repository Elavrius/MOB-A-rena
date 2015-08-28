package Game.Scenes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Game.Buttons.*;
import Game.Objects.*;
import Game.Scenes.*;
import Game.Utility.*;

public class PvPMainStage extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		new GameInfo();

		GameInfo.stage=stage;
		Scene scene1=new PvPMenuMainScene(new Group());
		GameInfo.scene1=scene1;
		((PvPMenuMainScene) scene1).startAnimation();
		stage.setScene(scene1);
		stage.show();

	}

}