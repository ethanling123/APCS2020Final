import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;
/**
 * An actor class that represents any entity on screen
 * @author John
 * @version 05/02/2020
 */
public class Actor {
	private int xCord;
	private int yCord;
	private int xVelo;
	private int yVelo;
	private int radius;
	private ImageIcon img;
	
	/**
	 * Constructor for an actor with an initial velocity of <0,0>
	 * @param x the initial x-coordinates for an actor
	 * @param y the initial y-coordinates for an actor
	 * @param r the radius of the actor's hitbox
	 * @param i the image to represent the actor on the screen
	 */
	public Actor(int x,int y,int r, ImageIcon i) {
		xCord=x;
		yCord=y;
		setRadius(r);
		img = new ImageIcon(i.getImage());
		xVelo=0;
		yVelo=0;
	}
	
	/**
	 * Constructor for an actor with an initial velocity.
	 * @param x the initial x-coordinates for an actor
	 * @param y the initial y-coordinates for an actor
	 * @param r the radius of the actor's hitbox
	 * @param i the image to represent the actor on the screen
	 * @param xv the initial x-velocity of the actor
	 * @param yv the initial y-velocity of the actor
	 */
	public Actor(int x,int y,int r, ImageIcon i,int xv,int yv) {
		xCord=x;
		yCord=y;
		setRadius(r);
		img = new ImageIcon(i.getImage());
		xVelo=xv;
		yVelo=yv;
	}
	
	/**
	 * Whether or not this actor is intersecting any other actors
	 * @param another actor to check collisions against
	 * @return whether or not this actor and the other actor are intersecting
	 */
	public boolean isIntersecting(Actor other) {
		boolean result=(Math.sqrt((Math.pow(Math.abs(this.xCord-other.xCord),2)+Math.pow(Math.abs(this.xCord-other.xCord),2)))-this.radius-other.radius)<=0.00001;
		return result;
	}

	/**
	 * Get the x coordinate for the actor
	 * @return the x-coordinate for the actor
	 */
	public int getxCord() {
		return xCord;
	}

	/**
	 * Get the y coordinate for the actor
	 * @return the y-coordinate for the actor
	 */
	public int getyCord() {
		return yCord;
	}

	/**
	 * Get the radius of the actor's hitbox
	 * @return the radius of the actor's hitbox
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Set the radius of the actor's hitbox
	 * @param radius the new radius of the actor's hitbox
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Add an x-velocity to the actor
	 * @param velocity the actor's new x-velocity
	 */
	public void addXVelocity(int velocity) {
		xVelo+=velocity;
	}
	
	/**
	 * Add a y-velocity to the actor
	 * @param velocity the actor's new y-velocity
	 */
	public void addYVelocity(int velocity) {
		yVelo+=velocity;
	}
	
	/**
	 * Add a new velocity to the actor
	 * @param velocityX the actor's new x-velocity
	 * @param velocityY the actor's new y-velocity
	 */
	public void addVelocity(int velocityX,int velocityY) {
		xVelo+=velocityX;
		yVelo+=velocityY;
	}
	
	/**
	 * Get the xVelocity of the actor
	 * @return xVelocity
	 */
	public int getXVelocity() {
		return xVelo;
	}
	
	/**
	 * Get the yVelocity of the actor
	 * @return yVelocity
	 */
	public int getYVelocity() {
		return yVelo;
	}
	
	/**
	 * An act method for the actor that is called every frame
	 */
	public void act() {
		xCord+=xVelo;
		yCord+=yVelo;
	}
	
	/**
	 * Draw the actor on screen.
	 * @param c
	 * @param g
	 */
	public void draw(Component c, Graphics g) {
		img.paintIcon(c, g, xCord+radius,yCord-radius);
	}
}
