package pong;
/*
 * This class is used for the racket2 object which is controlled by the user and it only moves up and down.
 * It also has a separate listener method to check for keyboard input by the user
 * 
 * Racquet2 can be controlled either by up or down arrow keys, or W and S keys on the keyboard.
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
	//finalized dimensions of each racket2  object
	private static final int WIDTH = 10;
	private static final int HEIGHT = 100;
	//removed static and final from x and y to allow creating of multiple racket2 objects in a single table.
	int x;
	int y;
	int ya;
	private GameOfPong game;
	//variables for the ultimate
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
	
		//event handling is the same for each direction
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			//added a 3 to the vector to make racket movement faster
			ya = -game.speed -3;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			//added a 3 to the vector to make racket movement faster
			ya = game.speed + 3;
			
		}
		//event handling for the ultimate button if pressed
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
