

import java.awt.event.*;


public class SecondScreen extends Screen {
	
	private int x, y;
	
	private DrawingSurface surface;
	
	public SecondScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		
		x = 30;
		y = 30;
	}
	
	public void draw() {
		
		// Draw stuff
		
		surface.pushStyle();
		
		surface.background(255);   // Clear the screen with a white background
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();

		surface.rect(x,y,30,30);
		
		surface.fill(0);
		surface.text("Move: Arrow keys",10,30);
		surface.text("Menu: Space",10,50);

		surface.popStyle();

		
		
		// Change stuff

		if (surface.isPressed(KeyEvent.VK_LEFT))
			x -= 5;
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			x += 5;
		if (surface.isPressed(KeyEvent.VK_UP))
			y -= 5;
		if (surface.isPressed(KeyEvent.VK_DOWN))
			y += 5;


		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			surface.switchScreen(ScreenSwitcher.SCREEN1);
		}
	}
	
	
}