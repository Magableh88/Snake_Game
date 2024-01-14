import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	
	public GUI() {
		makeFrame();
		makeMenuBar(frame);
	}
	
	public void makeFrame() {
		
		frame = new JFrame("Gub Malek el Job");
		Container contentPane = frame.getContentPane();
		
		
		JLabel label = new JLabel("3al unc");
		contentPane.add(label);
		
		
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void makeMenuBar(JFrame frame, ActionEvent ev) {
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		JMenu fileMenu = new JMenu("riks 3a zebi");
		menu.add(fileMenu);
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		openItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {openFile();}});
		
		
	}
	
	public void openFile() {
		
		File selected = 
	}
	
	@Override

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUI test = new GUI();
	}

	

}
