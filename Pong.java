package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JFrame;

public class Pong implements ActionListener, KeyListener {
	
	public static Pong pong;
	
	public int width = 700,height = 700; //Integers for the frame.
	
	public Renderer renderer;
	
	public Paddle player1;
	
    public Paddle player2;
    
    public Ball ball;
    
    public boolean w,s,up,down; //Boolean values for the paddles to move up or down.
    
    public int gameStatus = 0; //0 = Stopped, 1 = Paused, 2 = Playing
    
	public Pong() { //Constructor
		
		javax.swing.Timer timer = new javax.swing.Timer(20, this);
		JFrame jframe = new JFrame("Pong");
		
		renderer = new Renderer();
		
		jframe.setSize(width + 15, height + 35); //To define the size of our frame.
		jframe.setVisible(true); //To make sure our frame is visible.
		jframe.add(renderer);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //To make sure our frame closes when we try to exit.
		jframe.addKeyListener(this); //To make our game playable with keys.
		
		start();
		
		timer.start();
	}
	
	public void start() { // To initiate paddles in the game.
		player1 = new Paddle(this,1);
		player2 = new Paddle(this,2);
		
		ball = new Ball(this);
	}
	
	public void update() { //To update the key presses and the movements of the paddles and the ball.
		
		if(w) {
			player1.move(true);	
		}
		if(s) {
			player1.move(false);
		}
		if(up) {
			player2.move(true);
		}
		if(down) {
			player2.move(false);
		}
		ball.update(player1, player2);
	}
	
	public void render(Graphics g) { //To make the background.
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		if(gameStatus == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1 , 50));
			g.drawString("PONG", width/2 - 75, 50);
			
			g.setFont(new Font("Arial", 1 , 30));
			g.drawString("Press Space to start", width/2 - 150, height / 2 - 50);
			
		}
		
		if(gameStatus == 1 ) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1 , 50));
			g.drawString("PAUSED", width/2 - 103, height/2 - 25);
		}
		
		if(gameStatus == 2 || gameStatus == 1) {
		g.setColor(Color.WHITE);
		g.drawLine(width/2, 0, width/2, height);
		
		g.setFont(new Font("Arial", 1 , 50));
		g.drawString(String.valueOf(player1.score), width/2 - 75, 50);//For drawing the score of player one.
		g.drawString(String.valueOf(player2.score), width/2 + 45, 50);//For drawing the score of player two.
		
		player1.render(g);
		player2.render(g);
		ball.render(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(gameStatus == 2) {
		update();
		}
		renderer.repaint();
		
	}
	
	public static void main(String[] args) {
		pong = new Pong();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int id = e.getKeyCode(); //I created an integer called id to get an key event and to be able to use keyboard keys to control paddles.
		
		if(id == KeyEvent.VK_W) {
			w = true;
		}
		if(id == KeyEvent.VK_S) {
			s = true;
		}
		if(id == KeyEvent.VK_UP) {
			up = true;
		}
		if(id == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(id == KeyEvent.VK_SPACE) {
			if (gameStatus == 0) {
				gameStatus = 2;
			}
			else if (gameStatus == 1) {
				gameStatus = 2;
			}
			else if (gameStatus == 2) {
				gameStatus = 1;
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int id = e.getKeyCode();
		
		if(id == KeyEvent.VK_W) {
			w = false;
		}
		if(id == KeyEvent.VK_S) {
			s = false;
		}
		if(id == KeyEvent.VK_UP) {
			up = false;
		}
		if(id == KeyEvent.VK_DOWN) {
			down = false;
		}
		
	}

	

	

}
