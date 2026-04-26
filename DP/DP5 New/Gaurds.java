import java.io.*;
import java.util.*;

public class Gaurds{
    static long[] prefSum;
    static Long[][] dp;
    public static long solve(int[] nums, int i, int g){
        if(g<=0 && i!=nums.length) return Long.MAX_VALUE;
        if(i==nums.length){
            return g==0? 0 : Long.MAX_VALUE;
        }

        if(dp[i][g]!=null) return dp[i][g];

        long ans = Long.MAX_VALUE;
        for(int k=i; (k-i+1<=3 && k<nums.length); k++){
            long curr = (k-i+1) * (prefSum[k] - ((i-1<0)? 0 : prefSum[i-1]));
            long next = solve(nums, k+1, g-1);
            if(next!=Long.MAX_VALUE){
                ans = Math.min(ans, curr+next);
            }
        }
        return dp[i][g] = ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // prisoners
        int g = Integer.parseInt(st.nextToken()); // gaurds

        int[] s = new int[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
        }

        prefSum = new long[n];
        prefSum[0] = s[0];
        for(int i=1; i<n; i++){
            prefSum[i] = s[i] + prefSum[i-1];
        }


        dp = new Long[n][g+1];
        long res = solve(s, 0, g);
        System.out.println(res);
    }
}