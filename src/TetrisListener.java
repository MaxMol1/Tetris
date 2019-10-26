import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;

public class TetrisListener implements KeyListener, ActionListener, FocusListener {

	private Board gameBoard;
	private ControlPanel panel;
	private Timer timer;
	private boolean canHold = true;
	private int scoreCount = 1;
	
	public TetrisListener(Board gameBoard, ControlPanel panel) {
		this.gameBoard = gameBoard;
		this.panel = panel;
		gameBoard.addKeyListener(this);
		gameBoard.addFocusListener(this);
		gameBoard.setFocusable(true);
		timer = new Timer(800, this);
		//MusicPlayer musicPlayer;
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		updateScore();
		
		if(gameBoard.getScore() - (1500 * scoreCount) > 0) {
			scoreCount++;
			int newDelay = timer.getDelay();
			if(newDelay < 200)
				newDelay = newDelay - 20;
			else
				newDelay = (int)(newDelay * 0.9);
			timer.setDelay(newDelay);
		}
		
		if (s != null) {
			if(s.equals("Quit")) {
				System.exit(0);
			}
			else if(s.equals("About")) {
				JOptionPane.showMessageDialog(gameBoard, "This is the classic game Tetris, enjoy!");
			}
			else if(s.equals("Restart")) {
				gameBoard.restart();
				panel.getNextBlock().removeBlock();
				panel.getNextBlock().addBlock(new Block(gameBoard.getNextBlock(), gameBoard, 3, 1));
				panel.getNextBlock().repaint();
			}
		}
		else if(gameBoard.getIsGameOver()) {
			gameOver();
		}
		else if(gameBoard.getBlock().checkAvailableDown()) {
			gameBoard.getBlock().moveShapeDown();
			gameBoard.repaint();
		}
		else {
			gameBoard.addBlockToBoard();

			canHold = true;
			gameBoard.setIsMovement(true);
			
			gameBoard.checkBoardClear();
			gameBoard.findBlockAndReplace();
			
			panel.getNextBlock().removeBlock();
			panel.getNextBlock().addBlock(new Block(gameBoard.getNextBlock(), gameBoard, 3, 1));
			panel.getNextBlock().repaint();
	        gameBoard.repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();  
        if (code == KeyEvent.VK_LEFT) {
        	if(gameBoard.getBlock().checkAvailableLeft() && gameBoard.getIsMovement()) {
        		gameBoard.repaint();
        		gameBoard.getBlock().moveShapeLeft();
        	}
            gameBoard.repaint();
        }
        else if (code == KeyEvent.VK_RIGHT) {
        	if(gameBoard.getBlock().checkAvailableRight() && gameBoard.getIsMovement()) {
        		gameBoard.repaint();
        		gameBoard.getBlock().moveShapeRight();
        	}
            gameBoard.repaint();
        }
        else if (code == KeyEvent.VK_DOWN) {
        	if(gameBoard.getBlock().checkAvailableDown() && gameBoard.getIsMovement()) {
        		gameBoard.repaint();
        		gameBoard.getBlock().moveShapeDown();
        		gameBoard.setScore(gameBoard.getScore() + 1);
        		updateScore();
        	}
            gameBoard.repaint();
        }
        else if (code == KeyEvent.VK_UP) {
        	if(gameBoard.getBlock().checkAvailableRotate() && gameBoard.getIsMovement()) {
        		gameBoard.repaint();
        		gameBoard.getBlock().rotate(gameBoard.getBlock());
        	}
            gameBoard.repaint();
        }
        else if (code == KeyEvent.VK_SPACE) {
        	if(gameBoard.getIsMovement()) {
	        	gameBoard.repaint();
	        	gameBoard.getBlock().moveDownFast();
	            gameBoard.repaint();
	            gameBoard.setIsMovement(false);
        	}
        }
        else if (code == KeyEvent.VK_C) {
        	
        	if(canHold) {
	        	if(panel.getHeldBlock().getBlock() == null) {
	        		panel.getHeldBlock().removeBlock();
	    			panel.getHeldBlock().addBlock(new Block(gameBoard.getCurrentBlockColor(), gameBoard, 3, 1));
	    			panel.getHeldBlock().repaint();
	    			
	    			gameBoard.findBlockAndReplace();
	                gameBoard.repaint();
	                
	                panel.getNextBlock().removeBlock();
	    			panel.getNextBlock().addBlock(new Block(gameBoard.getNextBlock(), gameBoard, 3, 1));
	    			panel.getNextBlock().repaint();
	    			canHold = false;
	        	}
	        	else {
	        		int color = panel.getHeldBlock().getBlock().getColor();
	        		panel.getHeldBlock().removeBlock();
	    			panel.getHeldBlock().addBlock(new Block(gameBoard.getCurrentBlockColor(), gameBoard, 3, 1));
	    			panel.getHeldBlock().repaint();
	    			gameBoard.addNewBlock(color);
	    			gameBoard.findBlockAndReplace();
	    			canHold = false;
	        	} 
        	}
        }
        gameBoard.repaint();
	}

	@Override
	public void focusGained(FocusEvent e) {
		timer.start();
	}

	@Override
	public void focusLost(FocusEvent e) {
		timer.stop();
	}
	
	public void updateScore() {
		panel.setScore(gameBoard.getScore());
		panel.getScoreLabel().setText("Score: " + panel.getScore());
	}
	
	public void gameOver() {
		timer.stop();
		JFrame window = new JFrame();
	    JPanel content = new JPanel();
	    JButton exit = new JButton();
	    JButton restart = new JButton();
	    JLabel score = new JLabel("Game Over! Score: " + panel.getScore());

	    exit.setText("Exit");
	    exit.addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });
	    
	    restart.setText("Restart");
	    restart.addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	        	timer.start();
	            gameBoard.restart();
	            window.dispose();
	            panel.getNextBlock().removeBlock();
				panel.getNextBlock().addBlock(new Block(gameBoard.getNextBlock(), gameBoard, 3, 1));
				panel.getNextBlock().repaint();
	        }
	    });
	    
	    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

	    window.setSize(400, 200);
	    score.setPreferredSize(new Dimension(window.getWidth() - 10, window.getHeight() / 2));
        exit.setPreferredSize(new Dimension(window.getWidth() - 10, window.getHeight() / 2));
        restart.setPreferredSize(new Dimension(window.getWidth() - 10, window.getHeight() / 2));

        window.setLocation(300, 200);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit.setFocusable(false);

        content.add(score);
        content.add(exit);
        content.add(restart);
        window.add(content);

        window.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
