import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Utility {

	static boolean printLog = true;// regulates if system info will be shown

	public static Image safeImage() {
		final Image safeImage = new Image("file:data/assets/images/coin.png");
		return safeImage;
	}

	public static ArrayList<Image> makeSpriteOfImage(String path, int width,
			int height, int x, int y, int amount) {
		Image image = new Image("file:" + path);
		int counter = 0;
		ArrayList<Image> sprite = new ArrayList<Image>();
		try {
			PixelReader reader = image.getPixelReader();
			mark: for (int i = 0; i < y; i++) {
				for (int j = 0; j < x; j++, counter++) {
					if (counter == amount)
						break mark;
					WritableImage newImage = new WritableImage(reader, width
							* j, height * i, width, height);
					sprite.add(newImage);
				}
			}
		} catch (Exception e) {
			sprite.add(safeImage());
			String message = "Can't crop " + path + " image";
			Utility.showMessage(message);
		}

		return sprite;
	}

	public static void showErrorMessage(String message) {
		System.out.println(message);
	}

	public static boolean pathExists(String path) {
		File f = new File(path);
		if (f.exists()) {
			return true;
		} else {
			String message = "File " + path + " doesn't exist";
			showErrorMessage(message);
			return false;
		}

	}

	public static String getText(String path) {
		String story = "";
		if (pathExists(path)) {

			try {
				for (String line : Files.readAllLines(Paths.get(path))) {
					story = story + line+"\n";
				}
			} catch (Exception e) {
				String message = "Unable to read story from path " + path;
				showErrorMessage(message);
			}
		}
		return story;
	}

	public static Map<String, Integer> getStatsList(String path) {
		if (pathExists(path)) {
			Map<String, Integer> list1 = new HashMap<String, Integer>();
			try {
				for (String line : Files.readAllLines(Paths.get(path))) {
					String data[] = line.split("\t");
					list1.put(data[0], Integer.parseInt(data[1]));
				}
			} catch (Exception e) {
				String message = "Unable to read stats from stats file for path "
						+ path;
				showErrorMessage(message);
			}
			if (printLog)
				System.out.println(list1);
			return list1;
		}
		return null;
	}

	public static void showMessage(String message) {
		System.out.println(message);
	}
}
