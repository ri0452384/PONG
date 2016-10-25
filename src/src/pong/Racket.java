package pong;

import pong.GameOfPong;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
//only used in the single player portion of the game. code was not modified except for the constructor
public class Racket {
	private static int Y;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int x;
	int xa = 0;
	private GameOfPong game;

	public Racket(GameOfPong game,int x, int y) {
		this.game = game;
		Y = y;
		this.x = x;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		//event handling only involves left and right, same as the classic pong
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
}
