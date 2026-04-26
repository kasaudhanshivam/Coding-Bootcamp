import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Assignment5{

    // 1. Calculate nCr (GeeksforGeeks)
    public static int recursion(int n, int r, int[][] dp){
        if(r>n || r<0 || n<0) return 0; // base case
        if(r==n || r==0){ // base case
            return 1;
        }
        
        if(dp[n][r]!=-1){ // if this case is already evaluated
            return dp[n][r]; // immediately return it
        }
        
        // ways = either we choose this item + not choose this item
        int ways = recursion(n-1, r-1, dp) + recursion(n-1, r, dp);
        return dp[n][r] = ways; // total ways 
    }
    
    public static int nCr(int n, int r) {
        // the number of ways to choose r objects from a set of n objects
        
        int[][] dp = new int[n+1][r+1];
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], -1);
        }
        return recursion(n, r, dp);
    }



    // 2. Tribonacci Number (LeetCode 1137)
    public static int tribonacci(int n) {
        // DP using Tabulation
        if(n==0) return 0;
        if(n==1 || n==2) return 1;

        int[] dp = new int[n+1]; // to store the evaluated i-th tribonacci number
        // Base cases :
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i=3; i<n+1; i++){
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1]; // evaluate i-th tribonacci number
        }
        return dp[n]; // contains n-th tribonacci number
    }



    // 3. Min Cost Climbing Stairs (LeetCode 746)
    public static int recursion(int[] cost, int i, int[] dp){
        if(i>=cost.length) return 0;
        if(dp[i]!=-1) return dp[i];
        int option1 = cost[i] + recursion(cost, i+1, dp);
        int option2 = cost[i] + recursion(cost, i+2, dp);
        return dp[i] = Math.min(option1, option2);
    }
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        Arrays.fill(dp, -1);
        int startFrom0 = recursion(cost, 0, dp);
        Arrays.fill(dp, -1);
        int startFrom1 = recursion(cost, 1, dp);
        return Math.min(startFrom0, startFrom1);
    }









    // 4. Boredom (Codeforces 455A)
    public static long maxPoints(List<Integer> list, int n) {
        int[] arr = new int[n + 1];
        for (int num : list) {
            arr[num]++;
        }
        long[] dp = new long[n + 1];

        // tabulation

        dp[0] = 0;
        if (n >= 1) dp[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            // choose this num
            long option1 = (long)i * arr[i] + dp[i - 2];
            // dont choose this num
            long option2 = dp[i - 1];

            dp[i] = Math.max(option1, option2);
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(nCr(5, 2));
        System.out.println(tribonacci(5));



        List<Integer> list = new ArrayList<>();
        // 1 2 1 3 2 2 2 2 3
        // int n = 4
        // list.add(1);
        // list.add(2);
        // list.add(1);
        // list.add(3);
        // list.add(2);
        // list.add(2);
        // list.add(2);
        // list.add(2);
        // list.add(3);

        // 1 2
        // list.add(1);
        // list.add(2);
        // int n = 2;

        // 1 2 3
        list.add(1);
        list.add(2);
        list.add(3);
        int n = 3;

        System.out.println(maxPoints(list, 3));


        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost));
    }
}