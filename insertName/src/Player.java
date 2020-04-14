public class Player extends MovingImage {
	private double xMovement, yMovement;



	public Player(int xPos, int yPos) {
		super("assets/mongy.png",xPos,yPos,50,50);
		xMovement = 0;
		yMovement = 0;
	}

	public void walkX(int dir) {
		if (Math.abs(xMovement) < 4)
			xMovement += dir;
	}
	
	public void walkY(int dir) {
		if (Math.abs(yMovement) < 4) {
			yMovement += dir;
		}
	}
	
	public void update() {
		xMovement *= 0.9;
		yMovement *= 0.9;
		moveByAmount((int) xMovement, (int) yMovement);
	}
}
