import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
	
	private class Tile{
		int x;
		int y;
		
		Tile(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	int width;
	int height;
	int tileSize = 20;
	
	Tile head;
	Tile fruit;
	ArrayList<Tile> body;
	
	Random random;
	Timer gameLoop;
	
	int xVelocity;
	int yVelocity;
	boolean gameOver = false;
	
	SnakeGame(int width, int height){
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(this.width, this.height));
		setBackground(Color.white);
		addKeyListener(this);
		setFocusable(true);
		
		head = new Tile(5,5);
		body = new ArrayList<Tile>();
		
		fruit = new Tile(20, 20);
		random = new Random();
		placeFruit();
		
		xVelocity = 0;
		yVelocity = 0;
		
		gameLoop = new Timer(100, this);
		gameLoop.start();	
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		//Grid
		for(int i =0; i < width/tileSize; i++){
			g.drawLine(i*tileSize, 0, i*tileSize, height);
			g.drawLine(0, i*tileSize, width, i*tileSize);
			
		}
		
		//Snake
		g.setColor(Color.red);
		g.fill3DRect(head.x*tileSize, head.y*tileSize, tileSize, tileSize, true);
		
		//Body
		for(int i=0; i < body.size(); i++) {
			Tile snakePart = body.get(i);
			g.fill3DRect(snakePart.x * tileSize, snakePart.y*tileSize, tileSize, tileSize, true);
		}
		
		//Fruit
		g.setColor(Color.GREEN);
		g.fill3DRect(fruit.x*tileSize, fruit.y*tileSize, tileSize, tileSize, true);
		
		//Score
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		if(gameOver) {
			g.setColor(Color.red);
			g.drawString("Game Over" + String.valueOf(body.size()), tileSize - 16, tileSize);
		}
		else {
			g.drawString("Score: " + String.valueOf(body.size()), tileSize - 16, tileSize);
		}
	}
	
	public void placeFruit() {
		fruit.x = random.nextInt(width/tileSize);
		fruit.y = random.nextInt(height/tileSize);
	}
	
	public boolean collision(Tile tile1, Tile tile2) {
		return tile1.x == tile2.x && tile1.y == tile2.y;
	}
	
	
	public void move() {
		
		if(collision(head, fruit)) {
			body.add(new Tile(fruit.x, fruit.y));
			placeFruit();
		}
		
		//Body
		for(int i = body.size()-1; i >= 0; i--) {
			Tile part = body.get(i);
			if(i == 0) {
				part.x = head.x;
				part.y = head.y;
			}
			else {
				Tile prePart = body.get(i-1);
				part.x = prePart.x;
				part.y = prePart.y;
			}
			
		}
		
		head.x += xVelocity;
		head.y += yVelocity;
		
		for(int i=0; i < body.size(); i++) {
			if(head.x == body.get(i).x && head.y == body.get(i).y) {
				gameOver = true;
			}
		}
		
		if(head.x*tileSize < 0 || head.x*tileSize > width 
				|| head.y*tileSize < 0 || head.y*tileSize > height) {
			gameOver = true;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		move();
		repaint();
		if(gameOver) {
			gameLoop.stop();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && yVelocity != 1) {
			xVelocity = 0;
			yVelocity = -1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN && yVelocity != -1) {
			xVelocity = 0;
			yVelocity = 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT && xVelocity != 1) {
			xVelocity = -1;
			yVelocity = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT && xVelocity != -1) {
			xVelocity = 1;
			yVelocity = 0;
		}
	}
	
	//Dont need
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
