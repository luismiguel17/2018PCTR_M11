package p01;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Billiards. Clase Ventana que contiene el tablero.
 * 
 * @author LuisMiguel.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Billiards extends JFrame {

	// Ancho Tablero
	public static int Width = 800;
	// Alto Tablero
	public static int Height = 600;

	// Boton parar, iniciar
	private JButton b_start, b_stop,b_restart;

	// Tablero
	private Board board;

	// TODO update with number of group label. See practice statement.
	// Numero de bolas en el tablero.
	private final int N_BALL = 11;
	// Array de bolas.
	private Ball[] balls = new Ball[N_BALL];
	private Thread[] hilos = new Thread[N_BALL + 1];

	// Variable para que el botón "Parar" no funcione si no se ha pulsado antes
	// "Empezar"
	private boolean running = false;

	/**
	 * Billiards. constructor que inicializa los componentes del tablero.
	 */
	public Billiards() {

		balls = new Ball[N_BALL];
		board = new Board();
		board.setForeground(new Color(0, 128, 0));
		board.setBackground(new Color(0, 128, 0));
		//Nuevo
		b_restart = new JButton("Reiniciar");
		b_restart.addActionListener(new RestartListener());

		initBalls();

		b_start = new JButton("Empezar");
		b_start.addActionListener(new StartListener());
		b_stop = new JButton("Parar");
		b_stop.addActionListener(new StopListener());

		JPanel p_Botton = new JPanel();
		p_Botton.setLayout(new FlowLayout());
		p_Botton.add(b_start);
		p_Botton.add(b_stop);
		//Nuevo
		p_Botton.add(b_restart);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(p_Botton, BorderLayout.PAGE_END);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Width, Height);
		setLocationRelativeTo(null);
		setTitle("Práctica programación concurrente objetos móviles independientes");
		setResizable(false);
		setVisible(true);
	}

	/**
	 * initBalls. Metodo que inicializa las bolas.
	 */
	private void initBalls() {
		// TODO init balls
		for (int i = 0; i < N_BALL; i++) {
			balls[i] = new Ball();
		}
		board.setBalls(balls);
	}

	/**
	 * StartListener. Clase interna que inicia la ejecucion del tablero.
	 * 
	 * @author LuisMiguel
	 *
	 */
	private class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code is executed when start button is pushed

			if (running == false) {
				running = true;
				for (int i = 0; i < N_BALL; i++) {
					hilos[i] = new Thread(new HiloBasico(balls[i]));
					hilos[i].start();
				}
				hilos[N_BALL] = new Thread(new Painter(board));
				hilos[N_BALL].start();
			}
		}
	}

	/**
	 * StopListener. Clase interna que para la ejecucion del tablero.
	 * 
	 * @author LuisMiguel
	 *
	 */
	private class StopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code is executed when stop button is pushed
			if (running == true) {
				for (int i = 0; i < N_BALL; i++) {
					hilos[i].interrupt();
				}
				hilos[N_BALL].interrupt();
				running = false;
			}
		}

	}
	
	private class RestartListener implements ActionListener {
		/**
		 * actionPerformed. metodo que reinicia el tablero.
		 * @param arg0 argumento.
		 */
		@Override
		
		public void actionPerformed(ActionEvent arg0) {
			if(running == false){
				initBalls();
				board.paint(board.getGraphics());
			}
		}
	}

	/**
	 * main. Metodo inicial.
	 * 
	 * @param args
	 *            argumentos.
	 */

	public static void main(String[] args) {
		new Billiards();
	}
}
