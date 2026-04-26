import java.util.*;

class Lect6{
    public static int solve(int[] dice, int target, int i, int[][] dp){
        if (target==0 || i>=6) {
            return 0;
        }

        if(dp[target][i]!=-1){
            return dp[target][i];
        }

        int minRolls;

        if(target-dice[i]>=0){
            // either choose
            int option1 = 1 + solve(dice, target-dice[i], i, dp);
            // or skip
            int option2 = solve(dice, target, i+1, dp);

            minRolls = Math.max(option1, option2);
        }else{
            // olny option to skip
            minRolls = solve(dice, target, i+1, dp);
        }
        return minRolls;

    }
    public static int diceRolls(int n){
        int[] dice = {2, 3, 4, 5, 6, 7};
        int[][] dp = new int[n+1][6];
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(dice, n, 0, dp);
    }

    public static void main(String[] args) {
        // int n = 100;
        // System.out.println(diceRolls(n));

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            System.out.println(diceRolls(arr[i]));
        }
    }
}