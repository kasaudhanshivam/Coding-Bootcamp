
class Lect4 {

    public static boolean isSafe(char[][] grid, int i, int j) {

        // upper column
        for (int row = i-1; row >= 0; row--) {
            if (grid[row][j] == 'Q') {
                return false;
            }
        }

        
        // upper-left diagonal
        for (int r = i-1, col=j-1; r >= 0 && col>=0; r--, col--) {
            if (grid[r][col] == 'Q') {
                return false;
            }
        }


        // upper-right diagonal
        for (int r = i-1, col = j+1; r >= 0 && col<grid[0].length; r--, col++) {
            if (grid[r][col] == 'Q'){
                return false;
            }
        }

        return true;
    }

    public static void recursion(char[][] grid, int i) {
        if (i == grid.length) {
            printBoard(grid);
            return;
        }

        for (int j = 0; j < grid[0].length; j++) {
            if (isSafe(grid, i, j)) {
                grid[i][j] = 'Q';
                recursion(grid, i + 1);
                grid[i][j] = '.';
            }
        }
    }

    public static void solveNQueens(int n) {
        char[][] grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }

        recursion(grid, 0);
    }

    public static void printBoard(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("----------");
    }

    public static void main(String[] args) {
        solveNQueens(4);
    }
}