import java.awt.*;


public class CreditScreen extends Screen {

	private DrawingSurface surface;

	public CreditScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;


	}

	/*




	 */


	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		surface.fill(0);
		surface.textSize(12);
		surface.text("Han: Created framework for GameCharacter, Player, Asteroid, and Projectile, \n" +
				" + create DamagableInterface, Implement Shooting, helped with readme", 200,100);
		surface.text("John: Created the base actor class, created all necessary assets for the game, \n" +
				" helped with implementing shooting and movement, helped with readme.", 200,300);
		surface.text("Ethan: Handle Main Menu, Player point counter (part of the UI), Main Menu screen, \n " +
				"and the Drawing Surface on which the graphics are drawn, helped with readme.",200,500);
		surface.popStyle();
	}

}

