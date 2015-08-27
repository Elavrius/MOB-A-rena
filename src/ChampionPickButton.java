import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChampionPickButton extends PickButton {

	private final static int width = 395;
	private final static int height = 143;

	private Team team;
	private int nrInTeam;


	private ChooseHeroScene scene;
	private Scene parentScene;

	ChampionPickButton(Scene parent, Team team, int nr, int x, int y) {
		super(x,y,width, height);
		parentScene = parent;
		this.team = team;
		nrInTeam = nr;


		scene = new ChooseHeroScene(parent, new Group(), team, nrInTeam);
		gc.setFont(Font.font("Times New Roman", 16));
	}

	private void fillChampInfo() {
		gc.fillText(getChampion().getName(), 120, 35);
		gc.fillText("HP     " + getChampion().getEffectiveHP() + "\n"
				+ "MP     " + getChampion().getEffectiveMP() 
				+ "\n" + "Speed "+ getChampion().getEffectiveSpeed(), 120, 80);
		gc.drawImage(getChampion().getIcon(), 20, 20);
	}
	
	private void fillEqInfo(int n){
		int localX=0;
		int localY=0;
		switch(n){
		case 0:
			localX=230;
			localY=30;
			break;
		case 1:
			localX=300;
			localY=30;
			break;
		case 2:
			localX=230;
			localY=80;
			break;
		case 3:
			localX=300;
			localY=80;
			break;
		}
		gc.drawImage(getChampion().getEquipment(n).getIcon(), localX, localY);
	}
	@Override
	public void render() {
		super.render();
		if (getChampion() != null) {
			fillChampInfo();
			for(int i=0;i<4;i++){
			if(getChampion().getEquipment(i)!=null){
				fillEqInfo(i);
			}}
		} else {
			gc.fillText("Empty", 120, 35);
			gc.setFill(Color.GREY);
			gc.fillRect(20, 20, 100, 100);
		}
	}

	public Champion getChampion() {
		return team.getChampion(nrInTeam);
	}
	@Override
	public void use() {
		((PvPMenuMainScene) parentScene).stopAnimation();
		GameInfo.stage.setScene(scene);
		scene.startAnimation();
	}
}
