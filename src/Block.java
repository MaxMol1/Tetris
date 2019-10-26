
public class Block {
	
	private Board board;
	private Board gameBoard;
	private int random;
	private int width;
	private int height;
	private int x;
	private int y;
	private int rotatedState = 0;
	
	public Block(int random, Board gameBoard, int x, int rotatedState) {
		this.gameBoard = gameBoard;
		this.random = random;
		if(random == 0) {
			board = new Board(2, 2);
			width = 2;
			height = 2;
		}
		else if(random == 1) {
			board = new Board(1, 4);
			width = 1;
			height = 4;
		}
		else if(random == 2) {
			board = new Board(3, 2);
			width = 3;
			height = 2;
		}
		else if(random == 3) {
			board = new Board(3, 2);
			width = 3;
			height = 2;
		}
		else if(random == 4) {
			board = new Board(2, 3);
			width = 2;
			height = 3;
		}
		else if(random == 5) {
			board = new Board(2, 3);
			width = 2;
			height = 3;
		}
		else {
			board = new Board(2, 3);
			width = 2;
			height = 3;
		}
		generateShape();
		this.x = x;
		y = 0;
		this.rotatedState = rotatedState;
	}

	public void generateShape() {
		if(random == 0) {
			board.getBoardArray()[0][0] = 1;
			board.getBoardArray()[0][1] = 1;
			board.getBoardArray()[1][0] = 1;
			board.getBoardArray()[1][1] = 1;
		}
		else if(random == 1) {
			board.getBoardArray()[0][0] = 2;
			board.getBoardArray()[0][1] = 2;
			board.getBoardArray()[0][2] = 2;
			board.getBoardArray()[0][3] = 2;
		}
		else if(random == 2) {
			board.getBoardArray()[0][1] = 3;
			board.getBoardArray()[1][0] = 3;
			board.getBoardArray()[1][1] = 3;
			board.getBoardArray()[2][0] = 3;
		}
		else if(random == 3) {
			board.getBoardArray()[0][0] = 4;
			board.getBoardArray()[1][0] = 4;
			board.getBoardArray()[1][1] = 4;
			board.getBoardArray()[2][1] = 4;
		}
		else if(random == 4) {
			board.getBoardArray()[0][0] = 5;
			board.getBoardArray()[0][1] = 5;
			board.getBoardArray()[0][2] = 5;
			board.getBoardArray()[1][2] = 5;
		}
		else if(random == 5) {
			board.getBoardArray()[0][2] = 6;
			board.getBoardArray()[1][0] = 6;
			board.getBoardArray()[1][1] = 6;
			board.getBoardArray()[1][2] = 6;
		}
		else {
			board.getBoardArray()[0][0] = 7;
			board.getBoardArray()[0][1] = 7;
			board.getBoardArray()[0][2] = 7;
			board.getBoardArray()[1][1] = 7;
		}
	}
	
	public int[][] getBoardArray() {
		return board.getBoardArray();
	}
	
	public void setBoardArray(int[][] board) {
		this.board.setBoardArray(board);
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getColor() {
		return random;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getRotatedState() {
		return rotatedState;
	}
	
	public void setRotatedState(int rotatedState) {
		this.rotatedState = rotatedState;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void moveShapeLeft() {
		gameBoard.getBlock().setX(gameBoard.getBlock().getX()-1);
	}
	
	public void moveShapeRight() {
		gameBoard.getBlock().setX(gameBoard.getBlock().getX()+1);
	}
	
	public void moveShapeDown() {
		gameBoard.getBlock().setY(gameBoard.getBlock().getY() + 1);
	}
	
	public void moveDownFast() {
		int score = 0;
		while(checkAvailableDown()) {
			moveShapeDown();
			score++;
		}
		gameBoard.setScore(gameBoard.getScore() + 2*score);
	}
	
	public int[][] rotateArray(int[][] array) {
		int[][] rotatedArray = new int[array[0].length][array.length];
		for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                rotatedArray[y][x] = array[x][array[0].length - y - 1];
            }
        }
		return rotatedArray;
	}
	
	public void rotate(Block block) {
		int h = block.getHeight();
		int w = block.getWidth();
		
		block.setHeight(w);
		block.setWidth(h);
		block.setBoardArray(rotateArray(block.getBoardArray()));
		
		if(block.getColor() == 1) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
				block.setY(block.getY() + 1);
				block.setX(block.getX() - 1);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setY(block.getY() - 1);
				block.setX(block.getX() + 2);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
				block.setY(block.getY() + 2);
				block.setX(block.getX() - 2);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
				block.setY(block.getY() - 2);
				block.setX(block.getX() + 1);
			}
		}
		else if(block.getColor() == 2) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
				block.setX(block.getX() + 1);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setY(block.getY() + 1);
				block.setX(block.getX() - 1);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
				block.setY(block.getY() - 1);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
			}
		}
		else if(block.getColor() == 3) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
				block.setX(block.getX() + 1);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setY(block.getY() + 1);
				block.setX(block.getX() - 1);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
				block.setY(block.getY() - 1);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
			}
		}
		else if(block.getColor() == 4) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
				block.setY(block.getY() + 1);
				block.setX(block.getX() - 1);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setY(block.getY() - 1);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
				block.setX(block.getX() + 1);
			}
		}
		else if(block.getColor() == 5) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setX(block.getX() + 1);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
				block.setX(block.getX() - 1);
				block.setY(block.getY() + 1);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
				block.setY(block.getY() - 1);
			}
		}
		else if(block.getColor() == 6) {
			if(block.getRotatedState() == 1) {
				block.setRotatedState(2);
				block.setY(block.getY() + 1);
				block.setX(block.getX() - 1);
			}
			else if(block.getRotatedState() == 2) {
				block.setRotatedState(3);
				block.setY(block.getY() - 1);
			}
			else if(block.getRotatedState() == 3) {
				block.setRotatedState(4);
			}
			else if(block.getRotatedState() == 4) {
				block.setRotatedState(1);
				block.setX(block.getX() + 1);
			}
		}
	}
	
	public boolean checkAvailableRotate() {
		Block copy = new Block(gameBoard.getBlock().getColor(), gameBoard, gameBoard.getBlock().getX(), 
				gameBoard.getBlock().getRotatedState());
		
		copy.setY(gameBoard.getBlock().getY());
		copy.setBoardArray(rotateArray(copy.getBoardArray()));
		copy.setWidth(gameBoard.getBlock().getWidth());
		copy.setHeight(gameBoard.getBlock().getHeight());
		
		rotate(copy);
		
		if(copy.getY() + copy.getHeight() < 19 && copy.getX() > -1 && 
				copy.getX() + copy.getWidth() <= 10) {
			for(int i = 0; i < copy.getWidth(); i++) {
				for(int j = 0; j < copy.getHeight(); j++) {
					if(gameBoard.getBoardArray()[copy.getX() + i][copy.getY() + j] != 0 &&
							copy.getBoardArray()[i][j] != 0)
						return false;
				}
			}
			
			if(copy.getX() < 0) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean checkAvailableLeft() {
		if(gameBoard.getBlock().getX() <= 0) {
			return false;
		}
		for(int i = 0; i < gameBoard.getBlock().getHeight(); i++) {
			int counter = 0;
			if(gameBoard.getBlock().getBoardArray()[0][i] == 0) {
				counter++;
				if(gameBoard.getBlock().getBoardArray()[1][i] == 0) 
					counter++;
			}
			if(gameBoard.getBoardArray()[gameBoard.getBlock().getX() - 1 + counter][gameBoard.getBlock().getY() + i] != 0)
				return false;
		}
		return true;
	}
	
	public boolean checkAvailableRight() {
		if(gameBoard.getBlock().getX() + gameBoard.getBlock().getWidth()  > 9) {
			return false;
		}
		for(int i = 0; i < gameBoard.getBlock().getHeight(); i++) {
			int counter = 0;
			if(gameBoard.getBlock().getBoardArray()[gameBoard.getBlock().getWidth() - 1][i] == 0) {
				counter++;
				if(gameBoard.getBlock().getBoardArray()[gameBoard.getBlock().getWidth() - 2][i] == 0) {
					counter++;
				}
			}
			if(gameBoard.getBoardArray()[gameBoard.getBlock().getX() + gameBoard.getBlock().getWidth() - counter][gameBoard.getBlock().getY() + i] != 0)
				return false;
		}
		return true;
	}
	
	public boolean checkAvailableDown() {
		if(gameBoard.getBlock().getHeight() + gameBoard.getBlock().getY() >= 20) {
			return false;
		}
		for(int i = 0; i < gameBoard.getBlock().getWidth(); i++) {
			int counter = 0;
			if(gameBoard.getBlock().getBoardArray()[i][gameBoard.getBlock().getHeight() - 1] == 0) {
				counter++;
				if(gameBoard.getBlock().getBoardArray()[i][gameBoard.getBlock().getHeight() - 2] == 0) {
					counter++;
				}
			}
			if(gameBoard.getBoardArray()[gameBoard.getBlock().getX() + i][gameBoard.getBlock().getY() + gameBoard.getBlock().getHeight() - counter] != 0)
				return false;
		}
		return true;
	}
	
}
