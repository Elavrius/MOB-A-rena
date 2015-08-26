import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/*
 * Class for animated Sprites, that contains array if images and will play them in order
 * that is set by wait time. Render is called from parent class and with each call
 * it will update class info
*/
public class AnimatedSprite extends Sprite {

	private final int wait = 2; //how many extra frames should be awaited before actual image change
	private boolean reverse = false; //if true, after each image in sprite is used, sequence will be played backwards
	int currentFrame = 0; //number of the current rendered frame
	int maxFrame;//how many frames at all there is
	int renderCounter = 0;//counter that checks how many frames till actual image change
	int frameIterator = 1;//is +1 or -1, if sprite is reversed it will set will order increase or decrease

	ArrayList<Image> sprite;//contains all images of the sprite

	//Constructor.
	AnimatedSprite(String path, int width, int height, int x, int y, int amount) {
		super(path);
		sprite = new ArrayList<Image>(Utility.makeSpriteOfImage(path, width,
				height, x, y, amount));
		maxFrame = sprite.size() - 1;
	}
	
	//reverse
	public void setReverse(boolean rev) {
		reverse = rev;
	}
	
	//render order 1-2-3-1-2-3...
	private void normalRender(GraphicsContext gc) {
		if (currentFrame > maxFrame) {
			currentFrame = 0;
		}

		gc.drawImage(sprite.get(currentFrame), getPositionX(), getPositionY());

		if (renderCounter == wait) {
			currentFrame++;
			renderCounter = 0;
		} else {
			renderCounter++;
		}
	}

	//render order 1-2-3-2-1-2-3...
	private void reversedRender(GraphicsContext gc) {
		if (currentFrame == maxFrame) {
			frameIterator = -1;
		}
		if (currentFrame == 0) {
			frameIterator = 1;
		}
		gc.drawImage(sprite.get(currentFrame), getPositionX(), getPositionY());
		if (renderCounter == wait) {
			currentFrame = currentFrame + frameIterator;
			renderCounter = 0;
		} else {
			renderCounter++;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (!reverse) {
			normalRender(gc);
		} else {
			reversedRender(gc);
		}

	}

}
