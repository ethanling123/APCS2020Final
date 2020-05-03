
public class Actor {
	private int xCord;
	private int yCord;
	private int radius;
	
	public Actor(int x,int y,int r) {
		xCord=x;
		yCord=y;
		setRadius(r);
	}
	
	public boolean isIntersecting(Actor other) {
		boolean result=(Math.sqrt((Math.pow(Math.abs(this.xCord-other.xCord),2)+Math.pow(Math.abs(this.xCord-other.xCord),2)))-this.radius-other.radius)<=0.00001;
		return result;
	}

	public int getxCord() {
		return xCord;
	}

	public int getyCord() {
		return yCord;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
