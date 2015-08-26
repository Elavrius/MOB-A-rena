import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChampionPickButton extends Canvas {

	private boolean active = false;
	private Color activeColor = Color.BLACK;

	private final static int width = 395;
	private final static int height = 143;
	private int x;// top left x
	private int y;// top left y

	private Team team;
	private int nrInTeam;

	private GraphicsContext gc;

	private ChooseHeroScene scene;
	private Scene parentScene;

	ChampionPickButton(Scene parent, Team team, int nr, int x, int y) {

		super(width, height);
		parentScene = parent;
		this.team = team;
		nrInTeam = nr;
		this.x = x;
		this.y = y;
		move(this.x, this.y);
		gc = this.getGraphicsContext2D();

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
		gc.drawImage(getChampion().getEquipment(n).getIcon(),localX,localY);
	}

	public void render() {
		gc.clearRect(0, 0, width, height);
		gc.setFill(activeColor);
		gc.fillRect(0, 0, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(10, 10, width - 20, height - 20);
		gc.setFill(Color.BLACK);
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

	public void setActive(boolean act) {
		active = act;
		if (active) {
			activeColor = Color.RED;
		} else {
			activeColor = Color.BLACK;
		}
	}

	private void move(int x, int y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

	public Team getTeam() {
		return team;
	}

	public Champion getChampion() {
		return team.getChampion(nrInTeam);
	}

	public void use() {
		((PvPMenuMainScene) parentScene).stopAnimation();
		GameInfo.stage.setScene(scene);
		scene.startAnimation();
	}
}
