import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockPanel extends JPanel {

	private int[][] boardArray;
	private Block block;
	private int width;
	private int height;
	
	public BlockPanel(int width, int height) {
		boardArray = new int[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				boardArray[i][j] = 0;
			}
		}
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 135, 201);
        g.setColor(Color.DARK_GRAY);
        for(int i = 3; i < 135; i+=33) {
        	for(int j = 3; j < 201; j+=33) {
        		g.fillRect(i, j, 30, 30);
        	}
        }
        
        if(block != null) {
	        int random = block.getColor();
	        if(random == 0) {
	        	boardArray[1][1] = 1;
	        	boardArray[1][2] = 1;
	        	boardArray[2][1] = 1;
	        	boardArray[2][2] = 1;
	        }
	        else if(random == 1) {
	        	boardArray[1][1] = 2;
	        	boardArray[1][2] = 2;
	        	boardArray[1][3] = 2;
	        	boardArray[1][4] = 2;
	        }
			else if(random == 2) {
				boardArray[1][1] = 3;
	        	boardArray[1][2] = 3;
	        	boardArray[2][2] = 3;
	        	boardArray[2][3] = 3;
			}
			else if(random == 3) {
				boardArray[2][1] = 4;
	        	boardArray[2][2] = 4;
	        	boardArray[1][2] = 4;
	        	boardArray[1][3] = 4;
			}
			else if(random == 4) {
				boardArray[1][1] = 5;
	        	boardArray[1][2] = 5;
	        	boardArray[1][3] = 5;
	        	boardArray[2][3] = 5;
			}
			else if(random == 5) {
				boardArray[2][1] = 6;
	        	boardArray[2][2] = 6;
	        	boardArray[2][3] = 6;
	        	boardArray[1][3] = 6;
			}
			else if(random == 6) {
				boardArray[1][1] = 7;
	        	boardArray[1][2] = 7;
	        	boardArray[2][2] = 7;
	        	boardArray[1][3] = 7;
			}
	        
	        for(int i = 0; i < 4; i++) {
	        	for(int j = 0; j < 6; j++) {
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
        }
	}
	
	public void addBlock(Block block) {
		this.block = block;
	}
	
	public void removeBlock() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				boardArray[i][j] = 0;
			}
		}
	}
	
	public Block getBlock() {
		return block;
	}
	
}
