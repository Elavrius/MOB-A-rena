import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChooseHeroButton extends PickButton {
	PickHeroScene scene;
	Team team;
	int number;
	Scene parentScene;

	ChooseHeroButton(Scene parent, int x, int y, int width, int height,
			Team team, int nr) {
		super(x, y, width, height);
		parentScene = parent;
		this.team = team;
		this.number = nr;
		scene = new PickHeroScene(parent, new Group(), team, nr);

	}

	public void render() {
		super.render();
		if (team.getChampion(number) != null) {
			gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
			gc.fillText(team.getChampion(number).getName(), 20,	50);
			gc.fillText("HP     "+team.getChampion(number).getEffectiveHP() + "\n"
					+ "MP     "+team.getChampion(number).getEffectiveMP() + "\n"
					+"Speed "+ team.getChampion(number).getEffectiveSpeed(),170,
					50);
			gc.drawImage(team.getChampion(number).getIcon(), 20, 70);
			gc.setFont(Font.font("Times New Roman", 24));
			gc.fillText(team.getChampion(number).getStory(), 400, 60);
		} else {
			super.gc.fillText("Empty", 20, 20);
		}

	}

	@Override
	public void use() {
		((ChooseHeroScene) parentScene).stopAnimation();
		scene.startAnimation();
		GameInfo.stage.setScene(scene);
	}

}
