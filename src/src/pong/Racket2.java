package pong;
/*
 * This class is used for the racket2 object which is controlled by the user and it only moves left to right
 * also has a separate listener method to check for keyboard input by the user
 * 
 * Racquet2 will be vertical instead of horizontal and the up and down arrow keys on the keyboard allow it to move up and down
 * Added ultimate: players can now press the Q or Page Down button to use ultimate, which would make the ball go faster
 * once the ball touches the racket.
 * 
 * Made by Rayven N. Ingles
 * All wrongs reversed 2016
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket2{
	
	private static final int WIDTH = 10;
	private static final int HEIGHT = 100;
	
	int x;
	int y;
	int ya;
	private GameOfPong game;
	
	protected boolean ultUsed;
	private int ultCount;
	
	
	
	public Racket2(GameOfPong game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		ultUsed = false;
		ultCount = 1;
		ya = 0;
	}
	
	
	
	public void move() {
		if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
			y = y + ya;
	}


	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
	
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			
			ya = -game.speed -3;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			ya = game.speed + 3;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN || e.getKeyCode() == KeyEvent.VK_Q){
			if(ultUsed == false && ultCount > 0){
				ultUsed = true;
				ultCount--;
			}
		}
		
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return y;
	}



	public void paint(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

}
