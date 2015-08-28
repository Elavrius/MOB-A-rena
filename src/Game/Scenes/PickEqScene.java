package Game.Scenes;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import Game.Buttons.*;
import Game.Objects.*;
import Game.Scenes.*;
import Game.Utility.*;

/*
 * This class creates scene where You will choose Game.Objects.Champion
 * from list of available Champions
*/
public class PickEqScene extends Scene{
	Team team;
	int number;
	private int activeButton;
	PickButton b1;
	PickButton b2;
	
	AnimationTimer animation;
	Scene previousScene;
	
	PickButton[] buttons;
	
	Canvas canvas;
	GraphicsContext gc;

	public PickEqScene(Scene previousScene, Group root, Team team, int nr, int socketNr) {
		super(root, 800, 600);
		this.previousScene=previousScene;
		this.team=team;
		number=nr;
		canvas=new Canvas(800,300);
		buttons=new PickButton[2];
		buttons[0]=b1=new SetEqButton(this, team, nr, "HungryHat",socketNr, 20, 20);
		buttons[1]=b2=new SetEqButton(this, team, nr, "Destroyer",socketNr, 150, 20);
		
		root.getChildren().addAll(buttons);
		root.getChildren().addAll(canvas);
		canvas.setTranslateY(300);
		gc=canvas.getGraphicsContext2D();
		
		activeButton=0;
		b1.setActive(true);
		actionListener();
		animation=new AnimationTimer(){
			public void handle(long currentNanoTime) {
				render();
			}
		};
	}
	public void returnToPreviousScene(){
		this.stopAnimation();
		((ChooseHeroScene) previousScene).startAnimation();
		GameInfo.stage.setScene(previousScene);
	}
	
	public void startAnimation(){
		animation.start();
	}
	public void stopAnimation(){
		animation.stop();
	}
	public void render(){
		((SetEqButton) buttons[activeButton]).drawInfo(gc);
		for(PickButton button: buttons){
			button.render();
		}
	}
	
	public void changeActiveButton(String code){
		buttons[activeButton].setActive(false);
		switch(code){
		case "LEFT":
			activeButton-=1;
			if (activeButton<0)
				activeButton=0;
			break;
		case "RIGHT":
			activeButton+=1;
			if (activeButton>0)
				activeButton=1;
			break;
		case "ENTER":
			buttons[activeButton].use();
			break;
		}
		buttons[activeButton].setActive(true);
	}
	
	public void actionListener(){
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				changeActiveButton(code);				
				}
		
		});
	}

}
