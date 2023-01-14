import java.util.Arrays;

public class Sudoku {
    public static boolean solve(int[][] board,int row, int col){
        if(col >= board.length) {
            row++;
            col = 0;
        } if (row >= board.length) {
            return true;
        }
        if(board[row][col] != 0) {
            return solve(board,row,col+1);
        }
        for (int num = 1; num <= board.length; num++) {
            if(canPlace(board,row,col,num)){
            	board[row][col] = num;
            	if(solve(board,row,col+1) == true) {
            		return true;
            	}
            	board[row][col] = 0;
            }
        }


        return false;
    }

    private static boolean canPlace(int[][] board, int row, int col, int num) {
        return  isRowSafe(board,row,col,num) &&
                isColSafe(board,row,col,num) &&
                isSquareSafe(board,row,col,num);

    }

    private static boolean isRowSafe(int[][] board, int row, int col, int num) {
    	for (int i = 0; i < 9; i++) {
    		if (board[row][i] == num) {
    			return false;
    		}
    	}
        return true;
    }
    private static boolean isColSafe(int[][] board, int row, int col, int num) {
    	for (int i = 0; i < 9; i++) {
    		if (board[i][col] == num) {
    			return false;
    		}
    	}
        return true;
    }
    private static boolean isSquareSafe(int[][] board, int row, int col, int num) {
    	int rowCorner = row - row % 3;
    	int colCorner = col - col % 3;
    	for(int i = 0;i<3;i++) {
    		for(int j =0; j<3;j++) {
    			if (board[rowCorner + i][colCorner + j] == num) {
    				return false;
    			}
    		}
    	}	
        return true;
    }
    
    public static void main (String args[]) {
    	int[][] board = new int[][]{
    		{0, 0, 4, 3, 0, 7, 8, 0, 0},
    		{0, 6, 0, 0, 1, 0, 0, 9, 0},
    		{0, 0, 0, 0, 0, 0, 0, 0, 0},
    		{0, 0, 8, 0, 0, 4, 9, 0, 0},
    		{0, 7, 0, 0, 0, 0, 0, 6, 0},
    		{0, 0, 3, 2, 0, 0, 1, 0, 0},
    		{0, 0, 0, 0, 0, 0, 0, 0, 0},
    		{0, 9, 0, 0, 7, 0, 0, 1, 0},
    		{0, 0, 7, 4, 0, 1, 3, 0, 0}
    	};

    	solve(board, 0, 0);
    	for (int[] row: board) {
			 System.out.println(Arrays.toString(row));
		 }
    }
}
