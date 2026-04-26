import java.util.*;

public class BuildingBridges {
    // GFG - Building Bridges
    static Integer[] dp;
    public static int solve(int[][] nums, int i){
        if(dp[i]!=null) return dp[i];
        int ans = 1;
        for(int j=0; j<i; j++){
            if (nums[j][0] < nums[i][0] && nums[j][1] < nums[i][1]) {
                int a = 1 + solve(nums, j);
                ans = Math.max(ans, a);
            }
        }
        return dp[i] = ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] nums = new int[n][2];

        for(int i=0; i<n; i++){
            nums[i][0] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            nums[i][1] = sc.nextInt();
        }


        // solution
        dp = new Integer[n];
        Arrays.sort(nums, (a, b) -> {
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0] - b[0];
        });
        int max = 0;
        for(int i=0; i<n; i++){
            int a = solve(nums, i);
            max = Math.max(max, a);
        }


        System.out.println(max);
    }
}
