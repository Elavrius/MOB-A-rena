import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChooseHeroScene extends Scene {

	private int activeButton;
	ChampionPickButton butt;
	Scene previousScene;

	PickButton heroButton;
	PickButton b1;
	PickButton b2;
	PickButton b3;
	PickButton b4;
	PickButton back;
	PickButton[] buttons;
	
	AnimationTimer animation;	

	public ChooseHeroScene(Scene previousScene, Group root, Team team, int nr) {

		super(root, 800, 600);
		this.previousScene=previousScene;
		activeButton = 0;
		buttons=new PickButton[6];
		buttons[0]=heroButton = new ChooseHeroButton(this, 0, 0, 800, 250, team, nr);
		buttons[1]=b1=new ChooseEqButton(this,0,250,400,150,team, nr,0);
		buttons[2]=b2=new ChooseEqButton(this,400,250,400,150,team, nr,1);
		buttons[3]=b3=new ChooseEqButton(this,0,400,400,150,team, nr,2);
		buttons[4]=b4=new ChooseEqButton(this,400,400,400,150,team, nr,3);
		buttons[5]=back=new PickButton(0,550,800,50){
			@Override
			public void use(){
				returnToPreviousScene();
			}
			@Override 
			public void render(){
				super.render();
				gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
				gc.fillText("Back",350,35);
			}
		};
		
		root.getChildren().addAll(heroButton,b1,b2,b3,b4,back);	
		buttons[activeButton].setActive(true);
		
		animation=new AnimationTimer(){
			public void handle(long currentNanoTime) {
				render();
			}
		};
		actionListener();


	}
	public void returnToPreviousScene(){
		this.stopAnimation();
		((PvPMenuMainScene) previousScene).startAnimation();
		GameInfo.stage.setScene(previousScene);
		
	}
	
	public void startAnimation(){
		animation.start();
	}
	public void stopAnimation(){
		animation.stop();
	}
	
	private void changeActiveButton(String code){
		buttons[activeButton].setActive(false);
		switch(code){
		case("UP"):
			activeButton-=2;
			if(activeButton<0)
				activeButton=0;			
			break;
		case("DOWN"):
			activeButton+=2;
			if(activeButton>5)
				activeButton=5;
			break;
		case("LEFT"):
			activeButton-=1;
			if(activeButton<0)
				activeButton=0;
			break;
		case("RIGHT"):
			activeButton+=1;
				if(activeButton>5)
					activeButton=5;
			break;
		case("ENTER"):
			buttons[activeButton].use();
			break;
		}
		buttons[activeButton].setActive(true);

	}
	
	private void actionListener(){
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {

				String code = e.getCode().toString();
				changeActiveButton(code);


			}
		});
	}

	public void render() {
		heroButton.render();
		b1.render();
		b2.render();
		b3.render();
		b4.render();
		back.render();
	}

}
