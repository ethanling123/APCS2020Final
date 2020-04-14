/*
 * Authors: Ethan Ling, Kevin Chu
 * Period: 4
 * Date: 5/15/2019
 * Capstone Project
 * Class: Poop
 */

// This class extends Weapon, and it is the strongest but slowest version of weapon, poop
public class DeathRay extends Weapon {
    //FIELDS:
    public static final int DAMAGE = 27;
    public static final double SPEED = 0.01;

    //CONSTRUCTOR:
    public DeathRay(int xPos, int yPos, int targetX, int targetY) {
    	super(DeathRay.class.getResource("/assets/Poop.png"), DAMAGE, SPEED, xPos, yPos, targetX, targetY);
    }
}