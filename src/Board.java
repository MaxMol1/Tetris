import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	private int[][] boardArray;
	private Block block;
	private int score = 0;
	private ArrayList<Integer> randoms = new ArrayList();
	private boolean isGameOver;
	private boolean isMovement = true;
	
	public Board(int width, int height) {
		boardArray = new int[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				boardArray[i][j] = 0;
			}
		}
		for(int i = 0; i < 5000; i++) {
			randoms.add((int)(Math.random()*7));
		}
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 333, 663);
        g.setColor(Color.BLACK);
        for(int i = 3; i < 333; i+=33) {
        	for(int j = 3; j < 663; j+=33) {
        		g.fillRect(i, j, 30, 30);
        	}
        }
        
        // adds current block to the board array
        addBlockToBoard();
        
        // paints every part of the array onto the screen
        for(int i = 0; i < 10; i++) {
        	for(int j = 0; j < 20; j++) {
        		if(boardArray[i][j] == 1) {
        			g.setColor(Color.YELLOW);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 2) {
        			g.setColor(Color.CYAN);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 3) {
        			g.setColor(Color.RED);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 4) {
        			g.setColor(Color.GREEN);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 5) {
        			g.setColor(Color.ORANGE);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 6) {
        			g.setColor(Color.BLUE);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == 7) {
        			g.setColor(Color.MAGENTA);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        		else if(boardArray[i][j] == -1) {
        			g.setColor(Color.DARK_GRAY);
        			g.fillRect((i*30)+(i*3)+3, (j*30)+(j*3)+3, 30, 30);
        		}
        	}
        }
        
        // refreshes the most recent block by clearing it from the array 
        for(int row = block.getX(); row < block.getX() + block.getWidth(); row++) {
			for(int col = block.getY(); col < block.getY() + block.getHeight(); col++) {
				if(boardArray[row][col] - 1 == block.getColor() &&
						block.getBoardArray()[row-block.getX()][col-block.getY()] != 0)
					boardArray[row][col] = 0;
			}
				
		}
        
	}
	// adds data to the board array
	public void addBlockToBoard() {
		for(int j = 0; j < block.getHeight(); j++) {
			for(int k = 0; k < block.getWidth(); k++) {
				if(block.getBoardArray()[k][j] != 0) {
					if(boardArray[k+block.getX()][j+block.getY()] == 0)
						boardArray[k+block.getX()][j+block.getY()] = block.getBoardArray()[k][j];
					else {
						isGameOver = true;
						isMovement = false;
					}
				}
			}
		}
	}
	// Replaces the board's current block with the next one
	public void findBlockAndReplace() {
		if(randoms.size() == 1) {
			for(int i = 0; i < 5000; i++) {
				randoms.add((int)(Math.random()*7));
			}
		}
		
		Block block = new Block(randoms.get(0), this, 4, 1);
		this.block = block;
		randoms.remove(0);
	}
	
	public int[][] getBoardArray() {
		return boardArray;
	}
	
	public void setBoardArray(int[][] boardArray) {
		this.boardArray = boardArray;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public int getNextBlock() {
		return randoms.get(0);
	}
	
	public int getCurrentBlockColor() {
		return block.getColor();
	}
	
	public void addNewBlock(int random) {
		randoms.add(0, random);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean getIsGameOver() {
		return isGameOver;
	}
	
	public void setIsGameOver(boolean bool) {
		isGameOver = bool;
	}
	
	public boolean getIsMovement() {
		return isMovement;
	}
	
	public void setIsMovement(boolean bool) {
		isMovement = bool;
	}
	// checks if any rows are filled and if they should be cleared from the baord
	public void checkBoardClear() {
		boolean shouldClear = true;
		int clearedRows = 0;
		for(int i = boardArray[0].length - 1; i > 1; i--) {
			shouldClear = true;
			for(int j = 0; j < boardArray.length; j++) {
				if(boardArray[j][i] == 0) 
					shouldClear = false;
			}
			if(shouldClear) {
				for(int x = i; x > 1; x--) {
					for(int y = 0; y < boardArray.length; y++) {
						boardArray[y][x] = boardArray[y][x-1];
					}
				}
				clearedRows++;
				i++;
			}
		}
		switch(clearedRows) {
			case 1: {
				score += 200;
				break;
			}
			case 2: {
				score += 400;
				break;
			}
			case 3:  {
				score += 600;
				break;
			}
			case 4: {
				score += 800;
				break;
			}
			default: {
				score += 0;			
				break;	
			}
		}
	}
	// restart game function
	public void restart() {
		boardArray = new int[10][20];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 20; j++) {
				boardArray[i][j] = 0;
			}
		}
		for(int x = 0; x < randoms.size(); x++) {
			randoms.remove(0);
		}
		for(int i = 0; i < 5000; i++) {
			randoms.add((int)(Math.random()*7));
		}
		score = 0;
		findBlockAndReplace();
		isMovement = true;
		isGameOver = false;
	}
	
}
