import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SetChampionButton extends PickButton {
	Image icon;
	Scene parentScene;
	Team team;
	int number;
	String name;
	String info;

	SetChampionButton(Scene parent, Team team, int nr, String name, int x, int y) {
		super(x, y, 120, 120);
		this.team = team;
		this.name = name;
		number = nr;
		parentScene = parent;
		String path = "data/champions/" + name + "/";
		if (Utility.pathExists(path + "sprite/icon.png"))
			icon = new Image("file:" + path + "sprite/icon.png");

	}

	@Override
	public void render() {
		super.render();
		gc.drawImage(icon, 10, 10);
	}
	
	public void drawInfo(GraphicsContext gc){
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
			if (!team.getChampion(number).getName().equals(name)) {
				team.removeChampion(number);
				try {
					team.setChampion((Champion) Class.forName(name)
							.newInstance(), number);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		} else {
			try {
				team.setChampion((Champion) Class.forName(name).newInstance(),
						number);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		((PickHeroScene) parentScene).returnToPreviousScene();
	}

}
