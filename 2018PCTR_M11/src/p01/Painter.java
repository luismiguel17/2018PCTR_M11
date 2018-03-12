package p01;

public class Painter implements Runnable{
	
	public Board board;

	public Painter(Board bd) {
		// TODO Auto-generated constructor stub
		this.board=bd;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while (true){
				board.paint(board.getGraphics());
				Thread.sleep(50);
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
