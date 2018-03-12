package p01;

public class HiloBasico implements Runnable{

	public Ball ball;
	public HiloBasico(Ball b) {
		// TODO Auto-generated constructor stub
		this.ball = b;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				ball.move();
				//dormimos el hilo.
				Thread.sleep(50);
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}

}
