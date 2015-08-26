import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUItry extends Application {

	private GraphicsContext gc;
	private Canvas canvas;
	private Group root;
	private Scene scene;
	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		Image image = new Image("file:data/assets/images/coin3.png");

		ArrayList<Image> sprite = new ArrayList<Image>();

		PixelReader reader = image.getPixelReader();
		for (int i = 0; i < 3; i++) {
			WritableImage newImage = new WritableImage(reader, 47 * i, 0, 47,
					47);
			sprite.add(newImage);
		}

		canvas = new Canvas(600, 200);
		root = new Group(canvas);
		scene = new Scene(root);
		stage.setScene(scene);

		int x = 0;

		gc = canvas.getGraphicsContext2D();
		for (Image img : sprite) {
			gc.drawImage(img, x, 100);
			x = x + 50;
		}

		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}