import java.util.*;

class Assignment6{

    // 1. Unique Paths II (LeetCode 63)
    public static int recursion(int[][] grid, int i, int j, int[][] dp){
        if(i>=grid.length || j>=grid[0].length){
            return 0; // out of bound
        }
        if(grid[i][j]==1) return 0; // obstacle
        if(i==grid.length-1 && j==grid[0].length-1 && grid[i][j]==0){
            return 1; // when destination reached => 1 path found
        }

        if(dp[i][j]!=-1){ // if already evaluated
            return dp[i][j]; // directly return it
        }

        // move down and move left and explore
        dp[i][j] = recursion(grid, i+1, j, dp) + recursion(grid, i, j+1, dp); 

        return dp[i][j];
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return recursion(obstacleGrid, 0, 0, dp);
    }





    // 4. Minimum Falling Path Sum II (LeetCode 1289)
    public static int solve(int[][] matrix, int x, int y, Integer[][] dp){
        if(x==matrix.length-1 && y<matrix[0].length && y>=0){
            return matrix[x][y]; // at dest
        }else if(x>=matrix.length || y>=matrix[0].length || y<0){
            return Integer.MAX_VALUE; // invalid
        }

        if(dp[x][y]!=null){
            return dp[x][y];
        }

        int min = Integer.MAX_VALUE;
        // explore all possible next columns (except current)
        for (int next = 0; next < matrix.length; next++) {
            if (next == y) continue; // we can't move in same column
            min = Math.min(min, solve(matrix, x + 1, next, dp));
        }

        return dp[x][y] = min + matrix[x][y]; // take best
    }
    public static int minFallingPathSum(int[][] grid) {
        int ans = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[grid.length][grid[0].length];
        for(int j=0; j<grid[0].length; j++){
            // try all position to start from 1st row
            ans = Math.min(solve(grid, 0, j, dp), ans); // store min
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(arr));



        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(minFallingPathSum(grid));
    }
}