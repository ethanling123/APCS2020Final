import java.awt.*;


public class CreditScreen extends Screen {

	private DrawingSurface surface;

	public CreditScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;


	}

	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		surface.fill(0);
		surface.textSize(12);
		surface.text("Han: Created framework for GameCharacter, Player, Asteroid, Projectile, DamagableInterface, Implement Shooting. Updated UML and ReadMe as needed.", 200,100);
		surface.text("John: Created the base actor class, assets for the game, helped with implementing shooting and movement.", 200,300);
		surface.text("Ethan: Handled the UI, collision, enemy spawning, fixed bugs, scoring, fixed major lag issues",200,500);
		surface.popStyle();
	}

}

