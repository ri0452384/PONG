import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/*
 * 
 * This class is used for the racket1 object which is controlled by the user and it only moves left to right
 * also has a separate listener method to check for keyboard input by the user
 * The W and S keys will allow it to move up and down
 * Racquet1 will be vertical instead of horizontal
 * Added ultimate: player 1 can now press the Q button to use ultimate, which would make the ball go faster
 * once the ball touches the racquet.
 * Made by Rayven N. Ingles
 * All wrongs reversed 2016
 */

public class Racquet1{
	private static final int X = 50;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 100;
	int y = 200;
	int ya = 0;
	private Game game;
	public boolean ultUsed;
	private int ultCount;

	public Racquet1(Game game) {
		this.game = game;
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
		
		if (e.getKeyCode() == KeyEvent.VK_W){
			ya = -game.speed -3 ;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S){
			ya = game.speed + 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			if(ultUsed == false && ultCount >0){
				ultUsed = true;
				ultCount--;
			}
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle(50, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return y - HEIGHT;
	}
}