import java.io.*;
import java.util.*;

public class RemovingDigits{

    // CSES - Removing Digits

    // static Integer[] dp;
    // public static int solve(int n){
    //     if(n==0) return 0;

    //     if(dp[n]!=null) return dp[n];

    //     int min = Integer.MAX_VALUE;
    //     int temp = n;
    //     while(temp>0){
    //         int dig = temp % 10;
    //         int ans = Integer.MAX_VALUE;
    //         if(dig!=0){
    //             ans = 1 + solve(n-dig);
    //         }
    //         temp = temp/10;
    //         min = Math.min(ans, min);
    //     }

    //     return dp[n] = min;
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // dp = new Integer[n+1];

        // int ans = solve(n);

        int[] dp = new int[n+1];
        dp[0] = 0;

        for(int i=1; i<=n; i++){
            int min = Integer.MAX_VALUE;
            int temp = i;
            while(temp>0){
                int dig = temp % 10;
                int ans = Integer.MAX_VALUE;
                if(dig!=0){
                    ans = 1 + dp[i-dig];
                }
                temp = temp/10;
                min = Math.min(ans, min);
            }
            dp[i] = min;
        }

        System.out.println(dp[n]);

    }
}