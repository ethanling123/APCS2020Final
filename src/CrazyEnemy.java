//Imports
import java.util.Random;

/*
 * Authors: Ethan Ling, Kevin Chu
 * Period: 4
 * Date: 5/28/2019
 * MonkeyDefence Capstone Project
 * Class: CrazyEnemy
 */

public class CrazyEnemy extends Enemy {

    // CONSTRUCTOR:
    public CrazyEnemy(int xPos, int yPos, int targetX, int targetY, double health, double damage, double speed) {
    	super(CrazyEnemy.class.getResource(/*insertImagePath*/), xPos, yPos, targetX, targetY, health, damage, speed);
        

    }

    @Override
    public void chase(Player target) {

    }
}
