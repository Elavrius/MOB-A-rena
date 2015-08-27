import javafx.scene.Group;
import javafx.scene.Scene;


public class ChooseEqButton extends PickButton {
    PickEqScene scene;
    Scene parentScene;

    int slot;
    Team team;
    int number;

    ChooseEqButton(Scene parent, int x, int y, int width, int height, Team team, int nr, int slot) {
        super(x, y, width, height);
        scene = new PickEqScene(parent, new Group(), team, nr, slot);
        parentScene = parent;
        this.team = team;
        number = nr;
        this.slot = slot;
    }

    @Override
    public void render() {
        super.render();
        if (team.getChampion(number) != null) {
            if (team.getChampion(number).getEquipment(slot) != null) {
                gc.drawImage(team.getChampion(number).getEquipment(slot).getIcon(), 10, 10);
            }
        }
    }

    @Override
    public void use() {
        if (team.getChampion(number) != null) {
            ((ChooseHeroScene) parentScene).stopAnimation();
            scene.startAnimation();
            GameInfo.stage.setScene(scene);
        }
    }

}
