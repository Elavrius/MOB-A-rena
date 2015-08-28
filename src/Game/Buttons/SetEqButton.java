package Game.Buttons;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import Game.Buttons.*;
import Game.Objects.*;
import Game.Scenes.*;
import Game.Utility.*;


public class SetEqButton extends PickButton{
	
	Image icon;
	Scene parentScene;
	Team team;
	int number;
	String name;
	String info;
	int socketNr;

	public SetEqButton(Scene parent, Team team, int nr, String name,int socketNr, int x, int y) {
		super(x, y, 120, 120);
		this.socketNr=socketNr;
		this.name=name;
		this.team = team;
		this.name = name;
		number = nr;
		parentScene = parent;
		String path = "data/equipment/" + name + "/";
		if (Utility.pathExists(path + "sprite/icon.png"))
			icon = new Image("file:" + path + "sprite/icon.png");

		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render() {
		super.render();
		gc.drawImage(icon, 10, 10);
	}

	public void drawInfo(GraphicsContext gc) {
		gc.clearRect(0, 0, 800, 300);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 800, 300);
		gc.setFill(Color.WHITE);
		gc.fillRect(10, 10, 800 - 20, 300 - 20);
		gc.setFill(Color.BLACK);
		gc.fillText(name, 20, 20);
		
	}
	
	public void use() {
		if (team.getChampion(number) != null) {
			try {
				team.buy((Equipment) Class.forName("Game.Objects.Items."+name).newInstance(),team.getChampion(number),socketNr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		((PickEqScene) parentScene).returnToPreviousScene();
	}

}}
