import processing.core.PApplet;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * An actor class that represents any entity on screen
 *
 * @author John
 * @version 05/02/2020
 */
public class Actor extends PApplet {
    private float x;
    private float y;
    private float xVelo;
    private float yVelo;
    private int radius;
    private String path = "";

    /**
     * Constructor for an actor with an initial velocity.
     *
     * @param x  the initial x-coordinates for an actor
     * @param y  the initial y-coordinates for an actor
     * @param r  the radius of the actor's hitbox
     * @param i  the image to represent the actor on the screen
     * @param xv the initial x-velocity of the actor
     * @param yv the initial y-velocity of the actor
     */
    public Actor(float x, float y, int r, String i, float xv, float yv) {
        this.x = x;
        this.y = y;
        radius = r;
        path = i;
        xVelo = xv;
        yVelo = yv;
    }

    public Actor(float x, float y, int r, String i) {
        //this(x, y, r, new ImageIcon(i).getImage());
        this.x = x;
        this.y = y;
        radius = r;
        path = i;
    }

    public Actor(float x, float y, int r, URL i) {
        //this(x, y, r, new ImageIcon(i).getImage());
        this.x = x;
        this.y = y;
        radius = r;
        path = i.getPath();
    }

    public Actor(float x, float y, int r, URL i, float xv, float yv) {
        //this(x, y, r, new ImageIcon(i).getImage(), xv, yv);
        this.x = x;
        this.y = y;
        radius = r;
        path = i.getPath();
        xVelo = xv;
        yVelo = yv;
    }

    /**
     * Whether or not this actor is intersecting any other actors
     *
     * @param other actor to check collisions against
     * @return whether or not this actor and the other actor are intersecting
     */
    public boolean isIntersecting(Actor other) {
        boolean result = (Math.sqrt((Math.pow(Math.abs(this.x - other.x), 2) + Math.pow(Math.abs(this.x - other.x), 2))) - this.radius - other.radius) <= 0.00001;
        return result;
    }

    /**
     * Get the x coordinate for the actor
     *
     * @return the x-coordinate for the actor
     */
    public float getxCord() {
        return x;
    }

    /**
     * Get the y coordinate for the actor
     *
     * @return the y-coordinate for the actor
     */
    public float getyCord() {
        return y;
    }

    /**
     * Get the radius of the actor's hitbox
     *
     * @return the radius of the actor's hitbox
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Add an x-velocity to the actor
     *
     * @param velocity the actor's new x-velocity
     */
    public void addXVelocity(int velocity) {
        xVelo += velocity;
    }

    /**
     * Add a y-velocity to the actor
     *
     * @param velocity the actor's new y-velocity
     */
    public void addYVelocity(int velocity) {
        yVelo += velocity;
    }

    /**
     * Add a new velocity to the actor
     *
     * @param velocityX the actor's new x-velocity
     * @param velocityY the actor's new y-velocity
     */
    public void addVelocity(int velocityX, int velocityY) {
        xVelo += velocityX;
        yVelo += velocityY;
    }

    /**
     * Get the xVelocity of the actor
     *
     * @return xVelocity
     */
    public float getXVelocity() {
        return xVelo;
    }

    /**
     * Get the yVelocity of the actor
     *
     * @return yVelocity
     */
    public float getYVelocity() {
        return yVelo;
    }

    /**
     * An act method for the actor that is called every frame
     */
    public void act() {
        x += xVelo;
        y += yVelo;
    }


    public void draw(DrawingSurface surface) {
        surface.image(surface.loadImage(path), (int)(x - radius), (int)(y - radius), 2 * radius, 2 * radius);
    }

    public String getPath() {
        return path;
    }

//	/**
//	 * Register the event of killing another actor. Used for adding points or a death message.
//	 * @param actorThatDied the actor that has been killed by this actor.
//	 */
//	public void killedActor(Actor hurtActor) {
//		
//	}
}
