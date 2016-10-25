package pong;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameOfPong extends JPanel {
	@SuppressWarnings("serial")
	private static JFrame frame;
	protected Ball ball;
	//racket to be used for single player mode only
	Racket racket;
	//two racket2 objects for 2 player
	Racket2 racket1;
	Racket2 racket2;
	protected int speed;
	//used to check for game mode
	static boolean twoPlayerMode;
	//to keep track of scores within 2 player mode
	protected  int player1Score;
	protected  int player2Score;
	
	static GameOfPong game;

	

	public GameOfPong() {
		frame = new JFrame("Mini Tennis");
		ball = new Ball(this);
		racket = new Racket(this,0,300);
		racket1 = new Racket2(this,50,200);
		racket2 = new Racket2(this, 525,200);
		speed = 1;
		//used for keyboard input(only key pressed and released, then passes on the action to the racquet object
		
			addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}
	
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
						racket.keyReleased(e);
					if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S){
						racket1.keyReleased(e);
						}
					if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
						racket2.keyReleased(e);
						}
			
				}
	
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
						racket.keyPressed(e);
					if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_Q){
						racket1.keyPressed(e);
						}
					if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
						racket2.keyPressed(e);
						}
			
				}
			});
			
			setFocusable(true);
		Sound.BACK.loop();
	}

	private int getScore() {
		return speed - 1;
	}
	
	private void move() throws InterruptedException {
		//call on both objects to move, essentially to update the game logic
		if(!twoPlayerMode){
			ball.move();
			racket.move();
		}else{
			ball.move2();
			racket1.move();
			racket2.move();
		}
		
	}

	@Override
	public void paint(Graphics g) {
		//rendering the objects to the screen
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(!twoPlayerMode){
			//g is an older Graphics object, we're instantiating a Graphics2D class because it has more helpful methods and values.
			ball.paint(g2d);
			racket.paint(g2d);

			//this is how we can display the score with gray text on the screen
			g2d.setColor(Color.GRAY);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(getScore()), 10, 30);
		}else{
			
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			//g2d.setColor(Color.RED);
			ball.paint(g2d);
			
			g2d.setColor(Color.BLUE);
			racket1.paint(g2d);
			racket2.paint(g2d);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Verdana", Font.BOLD, 15));
			g2d.drawString(("Player 1: "+player1Score), 60, 30);
			g2d.drawString(("Player 2: "+player2Score), 435, 30);
			
		}
		
	}
	
	public void showScore(int playerNumber)throws InterruptedException {
		if(playerNumber == 1){
			JOptionPane.showMessageDialog(this, "Player 1: " + ++player1Score + "points");
			//if player1 scores, the ball goes towards player 2 on next turn.
			ball.xa = 1;
			ball.ya = 1;
		}else if(playerNumber == 2){
			JOptionPane.showMessageDialog(this, "Player 2: " + ++player2Score + "points");
			//if player2 scores, the ball goes towards player 1 on next turn.
			ball.xa = -1;
			ball.ya = -1;
		}
		//special move of either player is turned off
		//ball is returned to the middle of the screen and speed is reset to 1,
		//then the ball's  update method is invoked for changes to take effect
		racket1.ultUsed = false;
		racket2.ultUsed = false;
		ball.x = 300;
		ball.y = 200;
		speed = 1;
		ball.move2();
		
	}

	public void gameOver() throws InterruptedException {
		//triggers when the lose condition is true(ball moves to the bottom part of the screen
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		
		//presents a dialog box to try again or not. If the user clicks yes, the main program runs again. exits the program otherwise.
		
		int choice = JOptionPane.showConfirmDialog(this, "Try Again?", "Game Over", JOptionPane.YES_NO_OPTION);   
		
		if(choice == 0){
			//current frame is closed and another one is created
			frame.dispose();
			GameOfPong.main(null);
		}else{
			System.exit(ABORT);
		}
		
		
	}
	//game over dialog invoked in two player mode. displays the player who won and gives the option to restart the game
	public void gameOver2(int player) throws InterruptedException{
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		int choice = JOptionPane.showConfirmDialog(this, "Player "+player+" wins! \nTry Again?", "Game Over", JOptionPane.YES_NO_OPTION); 
		if(choice == 0){
			//current frame is closed and another one is created
			frame.dispose();
			GameOfPong.main(null);
		}else{
			System.exit(ABORT);
		}
	}


	public static void main(String[] args) throws InterruptedException {
		//declare the main frame titled "Mini Tennis"
		
		game = new GameOfPong();
		
		
		int choice = JOptionPane.showConfirmDialog(game, "Want to play two player mode?", "Game options", JOptionPane.YES_NO_OPTION);   
		
		if(choice == 1){
			
			twoPlayerMode = false;
			frame.add(game);
			game.ball.x = 150;
			game.ball.y = 0;
			game.speed = 1;
			frame.setSize(300, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			while (true) {
				game.move();
				game.repaint();
				Thread.sleep(10);
			}
		}
		else{
			JOptionPane.showMessageDialog(game, "Two Player Mode Selected. \nControls:\n Player 1: W = UP, S = DOWN, Q = SPECIAL MOVE \n"
					+ "\nPlayer 2: Up Arrow = UP, Down Arrow = DOWN\n PAGE DOWN = SPECIAL MOVE");
			twoPlayerMode = true;
			frame.add(game);
			game.player1Score = 0;
			game.player2Score = 0;
			game.ball.x = 300;
			game.ball.y = 200;
			game.speed = 1;
			frame.setSize(600, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			while (true) {
				game.move();
				game.repaint();
				Thread.sleep(10);
			}
		}
	}


}
