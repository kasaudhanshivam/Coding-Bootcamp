import java.util.*;
public class Lect5 {
    public static int recursion(int[] height, int i, int k, int[] dp) {
        if (i == height.length - 1) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int min = Integer.MAX_VALUE;

        for (int steps = 1; steps <= k; steps++) {
            if (i + steps < height.length) {
                int cost = Math.abs(height[i] - height[i + steps]);
                int op = cost + recursion(height, i + steps, k, dp);
                min = Math.min(min, op);
            }
        }

        return dp[i] = min;
    }

    public static int minCost(int[] height, int k) {
        int[] dp = new int[height.length];
        Arrays.fill(dp, -1);
        return recursion(height, 0, k, dp);
    }



    // CodeForces - 455 A
    public static long maxPoints(List<Integer> list, int n) {
        int[] arr = new int[n + 1];
        for (int num : list) {
            arr[num]++;
        }
        long[] dp = new long[n + 1];
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
        int[] height = { 20, 30, 40, 20 };

        // System.out.println(minCost(height, 3));

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

        // Scanner sc = new Scanner(System.in);

        // int n = sc.nextInt();

        // int maxVal = 0;
        // for(int i = 0; i < n; i++){
        // int x = sc.nextInt();
        // list.add(x);
        // maxVal = Math.max(maxVal, x);
        // }

        // System.out.println(maxPoints(list, maxVal));
    }
}
