package p01;

import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Ball. Clase  que simula a un conjunto de bolas.
 * @author LuisMiguel.
 * @version 1.0
 *
 */
//TODO Transform the code to be used safely in a concurrent context.  
public class Ball {
	// TODO Find an archive named Ball.png
	private String Ball = "Ball.png";

	// coordenadas
	private double x, y, dx, dy;
	// fi angulo de movimiento de la bola
	// v velocidad de la bola
	private double v, fi;
	//imagen
	private Image image;
	//tamanio imagen
	private final int IMG_TAM_X, IMG_TAM_Y;

	/**
	 * Ball. constructor que inicializa los distintos componentes.
	 */
	public Ball() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(Ball));
		image = ii.getImage();

		// TODO Depend of image size
		IMG_TAM_X = 32;
		IMG_TAM_Y = 32;

		x = Billiards.Width / 4 - 16;
		y = Billiards.Height / 2 - 16;
		v = 5;
		fi = Math.random() * Math.PI * 2;
		
	}

	/**
	 * move. Metodo que realiza los movimientos de las bolas.
	 */
	public void move() {
		v = v * Math.exp(-v / 1000);
		dx = v * Math.cos(fi);
		dy = v * Math.sin(fi);
		if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {
			dx = 0;
			dy = 0;
		}
		x += dx;
		y += dy;

		reflect();

		// TODO Check postcondition
	}

	/**
	 * reflect. Metodo que cambia el angulo del movimiento, es decir, reedirecciona las bolas.
	 */
	private void reflect() {
		if (Math.abs(x + IMG_TAM_X - Board.RIGHTBOARD) < Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y + IMG_TAM_Y - Board.BOTTOMBOARD) < Math.abs(dy)) {
			fi = -fi;
		}
		if (Math.abs(x - Board.LEFTBOARD) < Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y - Board.TOPBOARD) < Math.abs(dy)) {
			fi = -fi;
		}
	}

	/**
	 * getX. Metodo que devuelve la coordenada x. 
	 * @return x coordenada eje x.
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * getY. Metodo que devuelve la coordenada y.
	 * @return y coordenada eje y.
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * getFi. Metodo que devuelve el angulo.
	 * @return fi angulo.
	 */
	public double getFi() {
		return fi;
	}

	
	public double getdr() {
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * setX. Metodo que establece la coordenada x.
	 * @param x coordenada eje x.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * setY. Metodo que establece la coordenada y.
	 * @param y coordenada eje y.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Image. Metodo que devuelve la imagen de la bolas.
	 * @return image de la bolas.
	 */
	public Image getImage() {
		return image;
	}

}