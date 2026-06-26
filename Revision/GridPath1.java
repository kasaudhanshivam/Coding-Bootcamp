import java.util.*;
public class GridPath1{
    static int MOD = 1000000000+7;
    static Integer[][] dp;
    public static int solve(char[][] grid, int x, int y){
        if(x==grid.length-1 && y==grid[0].length-1) return 1;

        if(dp[x][y]!=null) return dp[x][y];

        int ways = 0;

        if(x+1<grid.length && grid[x+1][y]!='*'){
            ways = (int)(((long)ways + solve(grid, x+1, y)) % MOD);
        }
        if(y+1<grid[0].length && grid[x][y+1]!='*'){
            ways = (int)(((long)ways + solve(grid, x, y+1)) % MOD);
        }

        return dp[x][y] = ways;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] grid = new char[n][n];
        for(int i=0; i<n; i++){
            String s = sc.next();
            for(int j=0; j<n; j++){
                grid[i][j] = s.charAt(j);
            }
        }

        if(grid[0][0]=='*' || grid[n-1][n-1]=='*'){
            System.out.println(0);
            return;
        }
        dp = new Integer[n][n];
        int res = solve(grid, 0, 0);
        System.out.println(res);
    }
}