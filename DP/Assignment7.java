import java.util.Arrays;

class Assignment7 {


    // 1. Perfect Sum Problem (GFG)
    public static int solve(int[] arr, int sum, int i, Integer[][] dp){
        if(sum==0 && i==arr.length){
            return 1; // valid subsets
        }
        if(i>=arr.length){
            return 0; // invalid
        }
        
        if(dp[i][sum]!=null){ // memo step
            return dp[i][sum];
        }
        
        int ans;
        
        if(sum-arr[i]>=0){ // if valid
            int include = solve(arr, sum-arr[i], i+1, dp);
            int exclude = solve(arr, sum, i+1, dp);
            ans = include + exclude;
        }else{ // only option is to exclude
            int exclude = solve(arr, sum, i+1, dp);
            ans = exclude;
        }
        return dp[i][sum] = ans;
    }
    public static int perfectSum(int[] nums, int target) {
        // Integer[][] dp = new Integer[nums.length][target+1];
        // return solve(nums, target, 0, dp);
        
        
        // tabulation - 
        int[][] dp = new int[nums.length+1][target+1];
        
        // number of elements = 0, sum = 0 => 1 subset {}
        dp[0][0] = 1;
        // number of elements = 0, sum = j
        for(int j=1; j<=target; j++){
            dp[0][j] = 0;
        }
        for(int i=1; i<=nums.length; i++){
            dp[i][0] = 0;
        }
        
        // ⭐     important as well as tricky to understand
        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]==0) dp[i][0] = dp[i-1][0]+dp[i-1][0-nums[i-1]];
            else dp[i][0] = dp[i-1][0];
        }
        // one more methode to handle 0 is -
        // calculate count total zeroes in nums 
        // and find the total combinations for that count i.e, (2^n-1)
        // add this ans to final ans after tabulation
        
        // int count = 0;
        // for(int i=0; i<nums.length; i++){
        //     if(nums[i]==0) count++;
        // }
        
        
        int ans;
        for(int i=1; i<=nums.length; i++){ // i -> element
            for(int j=1; j<=target; j++){ // j -> target 
                if(j-nums[i-1]>=0){ // if valid
                    int include = dp[i-1][j-nums[i-1]];
                    int exclude = dp[i-1][j];
                    ans = include + exclude;
                }else{  // only option is to exclude
                    int exclude = dp[i-1][j];
                    ans = exclude;
                }
                dp[i][j] = ans;
            }
        }
        return dp[nums.length][target]; // total subsets when there is n elements and target t
    }



    // 2. Target Sum (LeetCode 494)
    public static int solve(int[] nums, int target, int i, int sum, Integer[][] dp){
        if(target==sum && i>nums.length-1){
            return 1; // valid expression
        }
        if(i>=nums.length) return 0; // invalid

        if(dp[i][sum+2000]!=null){ // memo step
            return dp[i][sum+2000];
        }

        // either add
        int option1 = solve(nums, target, i+1, sum+nums[i], dp);
        // or subtract
        int option2= solve(nums, target, i+1, sum-nums[i], dp);

        return dp[i][sum+2000] = option1 + option2; // total valid expressions
    }
    public static int findTargetSumWays(int[] nums, int target) {
        Integer[][] dp = new Integer[nums.length][4001]; // as per constraints, we are taking 4001 for safe indexing and managing -ive sums
        return solve(nums, target, 0, 0, dp);   

        // indexing is tricky for this problem => dont use tabulation
    }



    
    
    // 3. Frog Jump (Coding Ninjas) - Tabulation
    public int minCost(int[] height) {
        int[] dp = new int[height.length]; // dp[i] represent min cost to reach idx n-1 starting from i
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[height.length-1] = 0; // base initialization
        
        // bottom up
        for(int i=height.length-2; i>=0; i--){
            int option1 = Integer.MAX_VALUE;
            int option2 = Integer.MAX_VALUE;
            
            // Option1 : Move 1 step
            if(i+1<height.length){ // if inside bound
                // calculate curr cost and add next
                option1 = Math.abs(height[i] - height[i+1]) + dp[i+1];
            }
            // Option2 : Move 2 step
            if(i+2<height.length){ // if inside bound
                // calculate curr cost and add next
                option2 = Math.abs(height[i] - height[i+2]) + dp[i+2];
            }
            dp[i] = Math.min(option1, option2);
        }
        
        return dp[0]; // min cost to reach idx n-1 starting from idx 0
    }




    // 4. House Robber (LeetCode 198) - Tabulation
    public static int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // base initialization
        dp[0] = 0; // 0 houses => no money
        dp[1] = nums[0]; // only one house => only money of that house
        for (int i = 2; i <= n; i++) {
            // option 1 - Rob this house
            int option1 = nums[i-1] + dp[i-2]; // take the money and go for i+2
            // option 2 - Do no rob this house
            int option2 = dp[i-1];

            dp[i] = Math.max(option1, option2); // go with the best option
        }

        return dp[n]; // max money can be robbed for n houses
    }




    // 5. House Robber II (LeetCode 213)
    public static int tabulation(int[] nums, int st, int end){
        int len = end-st+1; // number of houses
        int[] dp = new int[len+1];

        // base initialization
        dp[0] = 0; // no house left
        dp[1] = nums[st]; // only house

        for(int i=2; i<=len; i++){
            int option1 = nums[st+i-1] + dp[i-2]; // rob this house and go for i-2
            int option2 = dp[i-1]; // dont rob
            dp[i] = Math.max(option1, option2); // go with best
        }
        return dp[len]; // max robbery for len number of houses
    }
    public static int rob2(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0]; // edge case

        // if we are starting robbery from house 1, we cant rob the house n => they are adjacent
        int robFrom1 = tabulation(nums, 0, n-2); // start from 0 upto n-2 


        // now rob from house 2 upto n
        int robFrom2 = tabulation(nums, 1, n-1); // start from 1 upto n-1
        return Math.max(robFrom1, robFrom2); // take best
    }




    // 7. Unique Paths (LeetCode 62)
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        // last row → you can only move right until the destination ⇒ only 1 way
        for(int i=0; i<m;i++){
            dp[i][n-1] = 1;
        }
        // last column → you can only move down until the destination ⇒ only 1 way
        for(int j=0; j<n; j++){
            dp[m-1][j] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // Moving Right
                int left = dp[i][j+1];
                // Moving Down
                int down = dp[i+1][j];
                dp[i][j] = left + down; // total ways
            }
        }
        return dp[0][0]; // total ways when we start from (0, 0)
    }





    // 8. Minimum Path Sum (LeetCode 64)
    public static int minPathSum(int[][] grid) {
        Integer[][] dp = new Integer[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];

        // Fill first row (can only come from left)
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // Fill first column (can only come from top)
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int i = 1; i<grid.length; i++) {
            for (int j = 1; j <grid[0].length; j++) {
                // Moving Right
                int right = dp[i][j-1];
                // Moving Down
                int down = dp[i-1][j];

                dp[i][j] = grid[i][j] + Math.min(right, down); // take best 
            }
        }
        return dp[grid.length-1][grid[0].length-1]; // ways to reach dest
    }


    public static void main(String[] args) {
        int[] arr = {0, 10, 0};
        // int target = 0;
        // System.out.println(perfectSum(arr, target));
        // System.out.println(findTargetSumWays(arr, target));
    }
}