import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TetrisMain {

	public static void main(String[] args) {
		JFrame window = new JFrame("Tetris");
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(1, 2));
		
		Board board = new Board(10, 20);
		
		board.findBlockAndReplace();
        
		ControlPanel controlPanel = new ControlPanel(board);
		board.requestFocusInWindow();
		TetrisListener listener = new TetrisListener(board, controlPanel);
		
		container.add(board);
		container.add(controlPanel);
		
		JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenu("Tetris", listener, new String[]{"About", "Restart", "Quit"}));
        window.setJMenuBar(menuBar);
		
		window.setContentPane(container);
		window.setSize(700, 707);
        window.setLocation(150,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
	}
	
	public static JMenu createMenu(String menuName, ActionListener listener,String[] itemNames) {
		JMenu menu = new JMenu(menuName);
		for(String itemName : itemNames) {
			if(itemName == null) {
					menu.addSeparator();
			}
			else {
					JMenuItem item = new JMenuItem(itemName);
					item.addActionListener(listener);
					menu.add(item);
			}
		
		}
		return menu;
	}
	
}
