
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class PickButton extends Canvas {

	private boolean active = false;
	private Color activeColor = Color.BLACK;

	private int width;
	private int height;
	private int x;// top left x
	private int y;// top left y

	GraphicsContext gc;

	PickButton(  int x, int y, int width, int height) {
		super(width, height);
		this.width=width;
		this.height=height;
		this.x = x;
		this.y = y;
		move(this.x, this.y);
		gc = this.getGraphicsContext2D();
	}

	public void render() {
		gc.clearRect(0, 0, width, height);
		gc.setFill(activeColor);
		gc.fillRect(0, 0, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(10, 10, width - 20, height - 20);
		gc.setFill(Color.BLACK);
	}

	public void setActive(boolean act) {
		active = act;
		if (active) {
			activeColor = Color.RED;
		} else {
			activeColor = Color.BLACK;
		}
	}

	public void move(int x, int y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

	public void use() {
		//To do
	}
}
