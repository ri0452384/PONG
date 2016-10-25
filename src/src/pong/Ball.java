package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
/*
 * 	This class is the placeholder of all the variables that involve the ball.
 *  In two-player mode, the ball turns RED when either player triggers the special move
 */
public class Ball {
	private static final int DIAMETER = 30;
	//ball position variables:
	protected int x;
	protected int y;
	//speed of the ball on the x and y axis
	int xa;
	int ya;
	//needed so that the ball can check for collision
	private GameOfPong game;

	public Ball(GameOfPong game) {
		this.game = game;
		Random ran = new Random();
		//randomize direction of ball on start
		if(ran.nextBoolean()){
			xa = 1;
			ya = 1;			
		}else{
			xa = -1;
			ya = -1;
		}
		
		x = x + xa;
		y = y + ya;
		
		
	}

	void move() throws InterruptedException {
		//boolean value only to be used to play the ball sound
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			y = game.racket.getTopY() - DIAMETER;
			game.speed++;
		} else 
			changeDirection = false;
		
		if (changeDirection) {
			Sound.BALL.play();
		}
		x = x + xa;
		y = y + ya;
	}
	
	//only invoked in two player mode, has collision checking with either racket and invokes the gameover method of Game if the ball touches either left or right edge of the frame
	void move2() throws InterruptedException{
		//boolean value only to be used to play the ball sound
		boolean changeDirection = true;
		
		if (x + xa < 0){
			game.showScore(2);
			game.speed = 1;
			if(game.player2Score > 2 || game.player1Score > 2)
				game.gameOver2(2);
			
		}
		else if (x + xa > game.getWidth() - DIAMETER){
			game.showScore(1);
			game.speed = 1;
			if(game.player2Score > 2 || game.player1Score > 2)
				game.gameOver2(1);	
			}
		else if(collidesWithOne()){
			
			if(game.racket1.ultUsed == true){
				game.speed +=4;
				game.racket1.ultUsed = false;
			}
			
			xa = game.speed;
			y = game.racket1.getTopY() - DIAMETER;
			game.speed++;
		}else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER){
			ya = -game.speed;
		}else if (collidesWithTwo()){
			if(game.racket2.ultUsed == true){
				game.speed +=4;
				game.racket2.ultUsed = false;
			}
			xa = -game.speed;
			y = game.racket2.getTopY() - DIAMETER;
			game.speed++;
			
		}else 
			changeDirection = false;
		
		if (changeDirection) {
			Sound.BALL.play();
			
		}
		x = x + xa;
		y = y + ya;
		
	}
	
	boolean collidesWithOne(){
		//only used in two-player mode
		//checks whether the racket1 touched the ball in game
		return game.racket1.getBounds().intersects(getBounds());
		
	}
	boolean collidesWithTwo(){
		return game.racket2.getBounds().intersects(getBounds());
	}

	private boolean collision() {
		//checks whether the racket touched the ball in game
		return game.racket.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		//simple method that draws the ball object
		if(game.racket2.ultUsed == true || game.racket1.ultUsed == true){
			g.setColor(Color.red);
		}else	
			g.setColor(Color.blue);
		
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		//technically the square hit box used for collision testing
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
