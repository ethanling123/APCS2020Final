import javax.swing.ImageIcon;

/**
 * A player character that has a projectile type and a score, which can be modified.
 * @author Hangyul Yun
 *
 */
public class PlayerCharacter extends GameCharacter {
	
	int points;

	/**
	 * Constructor for player
	 * @param x location of player
	 * @param y location of player
	 * @param r radius of the player's hitbox
	 * @param i image to represent the player on screen.
	 * @param health the health of the player
	 * @param shotType the type of projectile the player shoots.
	 */
	public PlayerCharacter(int x, int y, int r, ImageIcon i, float health, Class<Projectile> shotType) {
		super(x, y, r, i, health, 0.f, shotType);
		
		points = 0;
	}
	
	@Override
	public void killedActor(Actor actorThatDied)
	{
		points += 10;
	}
	
	/**
	 * gets the current amount of points the player has.
	 * @return the amount of points the player currently has.
	 */
	public int getPoints()
	{
		return points;
	}
}
