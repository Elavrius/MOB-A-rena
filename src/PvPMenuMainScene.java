import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class PvPMenuMainScene extends Scene {

	private static boolean printLog = true;

	private Pane pane;
	int currentButton;
	ChampionPickButton[] buttons = new ChampionPickButton[8];
	
	AnimationTimer animation;

	PvPMenuMainScene(Group root) {
		super(root, 800, 600);
		currentButton = 0;
		animation=new AnimationTimer(){public void handle(long currentNanoTime) {
			render();
		}
	};
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				buttons[i] = new ChampionPickButton(this,
						GameInfo.team1, i, 0, i * 143);
			} else {
				buttons[i] = new ChampionPickButton(this,
						GameInfo.team2, i - 4, 400, (i - 4) * 143);
			}
		}
		buttons[currentButton].setActive(true);
		pane = new Pane(buttons);
		root.getChildren().add(pane);
		actionListener();

	}
	
	public void startAnimation(){
		animation.start();
	}
	public void stopAnimation(){
		animation.stop();
	}
	public void render() {
		for (ChampionPickButton button : buttons) {
			button.render();
		}

	}

	private void arrangeButton(int newPosition) {
		if (newPosition < 0) {
			currentButton = 3;
		} else if (newPosition > 7) {
			currentButton = newPosition - 4;
		} else {
			currentButton = newPosition;
		}
	}

	public void changeActiveButton(String action) {
		buttons[currentButton].setActive(false);
		switch (action) {
		case "UP":
			if (currentButton == 4)
				arrangeButton(7);
			else
				arrangeButton(currentButton - 1);
			break;
		case "DOWN":
			if (currentButton == 3)
				arrangeButton(0);
			else
				arrangeButton(currentButton + 1);
			break;
		case "LEFT":
			if (currentButton > 3)
				arrangeButton(currentButton - 4);
			else
				arrangeButton(currentButton + 4);
			break;
		case "RIGHT":
			if (currentButton > 3)
				arrangeButton(currentButton - 4);
			else
				arrangeButton(currentButton + 4);
			break;
		case "ENTER":
			buttons[currentButton].use();
			break;
		}

		buttons[currentButton].setActive(true);

	}
	

	private void actionListener() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {

				String code = e.getCode().toString();

				changeActiveButton(code);

				if (printLog) {
					String message = code + "\n" + currentButton + "\n";
					Utility.showMessage(message);
				}

			}
		});
	}

}
