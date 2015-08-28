package Game.Utility;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	private int positionX;
	private int positionY;

	private Image image;

	public Sprite(String path) {
		if (Utility.pathExists(path)) {
			setImage(new Image("file:" + path));
			setPositionX(0);
			setPositionY(0);
		}
		else
			this.setImage(Utility.safeImage());
	}


	public void update(int x, int y) {
		setPositionX(x);
		setPositionY(y);

	}

	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), getPositionX(), getPositionY());
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getPositionX() {
		return positionX;
	}

	private void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	private void setPositionY(int positionY) {
		this.positionY = positionY;
	}
}
