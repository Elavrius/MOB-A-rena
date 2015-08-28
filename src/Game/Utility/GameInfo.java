package Game.Utility;

import Game.Objects.Team;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameInfo {

public static Team team1;
public static Team team2;
public GameInfo(){
	team1=new Team("Team1");
	team2=new Team("Team2");
}
public static Stage stage;
public static Scene scene1;

}
