import java.util.Arrays;
import java.util.List;

public class Assignment4 {

    // 1. Word Break (LeetCode 139)
    public static boolean find(List<String> wordDict, String s){
        for(String str : wordDict){
            if(str.equals(s)) return true;
        }
        return false;
    }
    public static boolean recursion(String s, List<String> wordDict, int i, int[] dp){
        if(i==s.length()){
            return true; // true isliye return kiya qki sab milta gya tha abhi tak
        }

        if(find(wordDict, s)) return true;

        if(dp[i]!=-1){
            return dp[i]==0? false : true;
        }

        for(int len=1; i + len <= s.length(); len++){
            String temp = s.substring(i, i+len);
            if(find(wordDict, temp) && recursion(s, wordDict, i+len, dp)){
                dp[i] = 1;
                return true;
            }
        }
        dp[i] = 0;
        return false;
    }
    public static boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return recursion(s, wordDict, 0, dp);
    }






    // 3. N-Queens II (LeetCode 52)
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
            total++;
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
    static int total;
    public static int totalNQueens(int n) {
        char[][] grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }

        total = 0;

        recursion(grid, 0);
        return total;
    }



    // 4. Sudoku Solver (LeetCode 37)
    public static boolean isSafe(char[][] board, int i, int j, char el){
        // check row
        for(int row = 0; row<9; row++){
            if(row!=i){
                if(board[row][j]==el) return false;
            }
        }

        // check col
        for(int col = 0; col<9; col++){
            if(col!=j){
                if(board[i][col]==el) return false;
            }
        }

        // sub grid
        for(int row=(i/3)*3; row<=(i/3)*3+2; row++){
            for(int col=(j/3)*3; col<=(j/3)*3+2; col++){
                if(row !=i && col != j){
                    if(board[row][col]==el) return false;
                }
            }
        }
        return true;
    }

    public static boolean solve(char[][] board, int row){
        // base case
        if(row==board.length){ // last row filled
            return true;
        }

        for(int col=0; col<board[0].length; col++){ // fill each cell of this row
            if(board[row][col]!='.') continue; // which is empty till now
            for(char num='1'; num<='9'; num++){ // try all numbers
                if(isSafe(board, row, col, num)){   // if this num is safe to fill at this pos 
                    board[row][col] = num; // fill
                    if(solve(board, row)){ // check if board is solved
                        return true; // no need to backtrack
                    }
                    // if not solved => backtrack
                    board[row][col] = '.'; // backtrack
                }
            }
            return false; // no number works in this cell
        }
        return solve(board, row+1); // solve for next row
    }





    public static void main(String[] args) {
        char[][] board = {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'}  
                    };
        solve(board, 0);



        totalNQueens(4);
    }
}
