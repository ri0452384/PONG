import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/*
 * This class is used for the racket2 object which is controlled by the user and it only moves left to right
 * also has a separate listener method to check for keyboard input by the user
 * 
 * Racquet2 will be vertical instead of horizontal and the up and down arrow keys on the keyboard allow it to move up and down
 * Added ultimate: player 2 can now press the Page Down button to use ultimate, which would make the ball go faster
 * once the ball touches the racquet.
 * 
 * Made by Rayven N. Ingles
 * All wrongs reversed 2016
 */
public class Racquet2{
	private static final int X = 525;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 100;
	int y = 200;
	int ya = 0;
	private Game game;
	
	protected boolean ultUsed;
	private int ultCount;

	public Racquet2(Game game) {
		this.game = game;
		ultUsed = false;
		ultCount = 1;
	}

	public void move() {
		if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(X, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
	
		
		if (e.getKeyCode() == KeyEvent.VK_UP){
			
			ya = -game.speed -3;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			ya = game.speed + 3;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN ){
			if(ultUsed == false && ultCount > 0){
				ultUsed = true;
				ultCount--;
			}
		}
		
		
	}

	public Rectangle getBounds() {
		return new Rectangle(X, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return y - HEIGHT;
	}
}