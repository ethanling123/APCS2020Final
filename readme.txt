AP Computer Science Final Project - README
Authors: Ethan, Han, John
Revision: 4/10/20
Introduction:

The program is a game that allows people to play a bullet-hell game.It addresses the issue of being noted during quarantine, giving the player a way to pass time in what is hopefully a demanding challenge.There is no story related to this game, as it is designed more so for the challenge of a bullet hell experience than for plot. As such, the plot is not the focus of this APCS final project.The player must try and survive as long as possible, eliminating both players and AI enemies, and grazing bullets to increase their score. If they get hit by a bullet once, they lose the game and must rejoin to play, starting back to 0 progress.People who are sufficiently bored enough (like in quarantine) and would want a simple competitive experience will want to play this game, as well as those who wish to see how their skills in coordination and prediction.Professional bullet hell players who want a tournament-style means to measure skill can use this program to compete with other players, measuring skill in evasion and strategy over just survival time.The primary focus of this program is replicating the bullet-hell game genre, which includes a large number of moving projectiles on the screen. If time allows, a multiplayer option would be implemented, although single player survival is the primary gamemode of this project.

Instructions:
You will use WASD to move your player around, with the mouse controlling the point-and-click aiming system for shooting. There will also be a user interface in which the user must use the mouse to navigate the title screen.
The title screen UI will have specific buttons that must be pressed to launch the game mode, while the main gameplay will allow the player to click anywhere on the map to aim their attacks.
There will be a main title screen for the player to navigate as a menu. Even though a pause menu and options menu are standard per game design, only the title screen will be a mandatory menu (like how .io games typically lack pause menus)
First the player will have to navigate a title menu screen before being able to play any game mode, from which they will now move with WASD keys and use the mouse to point and shoot in a certain direction.

Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
 A moveable character that is controlled by the player. The player would have to control the character and prevent losing life points.
Projectiles are easily the most important component in the game, as the screen will often be covered by these for difficulty and challenge.
 In order to make the game more difficult, we will also have enemies that attack the player. These enemies will attempt to damage the player until the player dies.
In order to add some progression, the enemies will become harder as the player progresses through the game.
We will also have a scoring mechanism. As the player progresses, the game will keep track of how many points they have gained and store the highest score.
Score is lost with each hit taken.
A title screen is required for the player to navigate prior to launching the main game campaign.

Want-to-have Features:
A good feature to have would be to have multiple enemies where a different type of enemy would do different amounts of damage and attack in different patterns
Instead of an endless game, the game would have multiple levels that featured a different map, enemies, and weapons.
Powerups would be given to the player to grant them special abilities and make them more powerful.
 Weapons to add variety to the game which would do varying amounts of damage and have their own bullset and attack speed.

Stretch Features:
Multiplayer would include another player able to join them either by networking or locally on the keyboard.
 Bosses would be a very hard enemy to act as the final challenge before finishing a level, perhaps even having multiple life bars with their own unique patterns for added challenge.
Character Selection would feature multiple characters that the player can choose from and they would each have their unique set of abilities.

Class List:
Actor - Superclass for all entities on screen
Projectile - Entities that move
Asteroid - Gets shot
GameCharacter - Entities that move and are controlled
Enemy - Enemy that the player has to avoid
Player - the Player’s character
Screen (Abstract)
FirstScreen
SecondScreen
CreditScreen
DrawingSurface
Screen Switcher(Interface)
DamagableImterface(Interface)
Main

Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
Han: Created framework for GameCharacter, Player, Asteroid, Projectile, DamagableInterface, Implement Shooting. Updated UML and ReadMe as needed.
John: Created the base actor class, assets for the game, helped with implementing shooting and movement.
Ethan: Handled the UI, collision, enemy spawning, fixed bugs, scoring, less lag
We used Mr. Shelby’s MovingImage class to help code for the entities.
We also used some super classes from our previous projects
Our Icon for the game itself is a stock image of a spaceship.