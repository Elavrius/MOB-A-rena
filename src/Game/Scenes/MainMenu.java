package Game.Scenes;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import Game.Buttons.*;
import Game.Objects.*;
import Game.Scenes.*;
import Game.Utility.*;

public class MainMenu extends Application {

	private boolean printLog = true;
	private int choose;
	private AnimatedSprite coin;
	private GraphicsContext gc;
	private Canvas canvas;
	private Group root;
	private Scene scene;
	StackPane panel;
	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Arena");
		coin = new AnimatedSprite("data/assets/images/coin3.png", 47, 47, 3, 2,	5);
		coin.setReverse(true);
		coin.update(500, 35);
		choose = 0;

		canvas = new Canvas(600, 200);
		panel=new StackPane(canvas);
		panel.setStyle("-fx-background-color: white");
		root = new Group(panel);
		
		scene = new Scene(root,Color.BLACK);
		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.BLACK);
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 48));

		actionListener();
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				render();
			}
		}.start();

		stage.show();

	}

	public void changeActiveButton() {
		if (choose == 1) {
			choose = 0;
			coin.update(500, 35);
		} else if (choose == 0) {
			choose = 1;
			coin.update(350, 130);
		}
	}

	public void render() {
		gc.clearRect(0, 0, 600, 200);
		gc.fillText("Player VS Player", 50,70);
		gc.fillText("Exit", 250, 170);
		gc.strokeText("Player VS Player", 50, 70);
		gc.strokeText("Exit", 250, 170);
		coin.render(gc);
	}

	public void close() {
		scene.addEventFilter(Event.ANY, e -> {
			e.consume();
		});
		KeyValue opacity = new KeyValue(panel.opacityProperty(), 0);
		KeyFrame end = new KeyFrame(Duration.millis(500), opacity);
		Timeline t = new Timeline(end);
		t.play();
		t.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.close();
			}
		});
	}

	public void actionListener() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				if (code.equals("UP")) {
					changeActiveButton();

				} else if (code.equals("DOWN")) {
					changeActiveButton();
				} else if (code.equals("ENTER")) {
					if (choose == 1) {
						close();

					} else if (choose == 0) {
						try {
							new PvPMainStage().start(new Stage());
						} catch (Exception e1) {
							e1.printStackTrace();
							String message = "Can't open PvP Menu "
									+ e1.getStackTrace();
							Utility.showErrorMessage(message);
						}

						stage.close();
					}
				}
				if (printLog) {
					String message = code + "\n" + choose + "\n";
					Utility.showMessage(message);
				}

			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
