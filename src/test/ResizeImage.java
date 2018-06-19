package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ResizeImage {

	public static void main(String... arguments) {
		try {
			Path input = Paths.get(System.getProperty("user.home"), "desktop", "resize");
			Path output = Paths.get(input.toString(), "output");
			for (File inputFile : input.toFile().listFiles()) {
				if (!inputFile.getName().endsWith(".png")) {
					continue;
				}
				BufferedImage image = ImageIO.read(inputFile);
				image = replace(image, new int[] { 255, 0, 255, 255 }, new int[] { 255, 255, 255, 0 });
				if (image == null) {
					continue;
				}

				int minimumWidth = 15;
				int minimumHeight = 15;

				int width = image.getWidth();
				int height = image.getHeight();

				while (width > minimumWidth && height > minimumHeight) {
					width--;
					height--;
				}

				Image scaled = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
				BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D graphics = outputImage.createGraphics();
				graphics.drawImage(scaled, 0, 0, null);
				graphics.dispose();
				File outputFile = new File(output.toString() + "/" + inputFile.getName());
				outputFile.createNewFile();
				ImageIO.write(outputImage, "PNG", outputFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage replace(BufferedImage image, int[] existing, int[] replacement) {
		int width = image.getWidth();
		int height = image.getHeight();
		WritableRaster raster = image.getRaster();

		for (int x = 0; x < width; x++) {
			outer: for (int y = 0; y < height; y++) {
				int[] pixels = raster.getPixel(x, y, (int[]) null);

				for (int index = 0; index < pixels.length; index++) {
					if (existing[index] != pixels[index]) {
						continue outer;
					}

					if (++index > 2) {
						break;
					}
				}

				pixels[0] = replacement[0];
				pixels[1] = replacement[1];
				pixels[2] = replacement[2];
				pixels[3] = replacement[3];
				raster.setPixel(x, y, pixels);
			}
		}
		return image;
	}

}
