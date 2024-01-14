import javax.swing.*;
public class Snake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width = 600;
		int height = width;
		
		JFrame frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakeGame game = new SnakeGame(width, height);
		frame.add(game);
		frame.pack();
		game.requestFocus();
	}

}