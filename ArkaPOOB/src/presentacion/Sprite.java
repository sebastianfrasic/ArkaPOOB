package presentacion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


/**
 * Representa un elemento del juego, sabe su imagen y sus posiciones en X y en Y, y si está visible o invisible
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Sprite {

	private BufferedImage imagen;
	private int x,y,width,height;
	private boolean visible;

	public Sprite(int x, int y, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;
	}

	public Sprite(int x, int y, boolean visible, int width, int height){
		this(x,y,visible);
		this.width = width;
		this.height = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVisible(boolean v) {
		this.visible = v;
	}

	public void setRoot(String root) {
		try {
			imagen = ImageIO.read(new File(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int isVisible() {
		return x;
	}
	
	public void paint(Graphics2D g) {
		if (visible)
			if(width==0 && height == 0)
				g.drawImage(imagen, x, y, null);
			else
				g.drawImage(imagen, x, y, width, height, null);
	}
}