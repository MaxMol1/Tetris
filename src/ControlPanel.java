import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private BlockPanel nextBlock;
	private BlockPanel heldBlock;
	private Board board;
	private int score = 0;
	private JLabel scoreLabel;
	
	public ControlPanel(Board board) {
		this.board = board;
		this.setLayout(new GridLayout(3, 1));
		
		nextBlock = new BlockPanel(4, 6);
		nextBlock.addBlock(new Block(board.getNextBlock(), board, 3, 1));
		heldBlock = new BlockPanel(4, 6);
		scoreLabel = new JLabel("Score: " + score);
		
		this.add(nextBlock);
		this.add(heldBlock);
		this.add(scoreLabel);
	}
	
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public BlockPanel getNextBlock() {
		return nextBlock;
	}
	
	public BlockPanel getHeldBlock() {
		return heldBlock;
	}
	
}
