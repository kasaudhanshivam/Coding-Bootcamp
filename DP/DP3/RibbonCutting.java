import java.io.*;
import java.util.StringTokenizer;

public class RibbonCutting {
    static Integer[][] dp;
    public static int solve(int[] cuts, int len, int i){
        if(i==cuts.length){
            return len==0? 0 : Integer.MIN_VALUE;
        }

        if(dp[i][len]!=null) return dp[i][len];

        int a = Integer.MIN_VALUE;
        if(len-cuts[i]>=0){
            a = 1 + solve(cuts, len-cuts[i], i);
        }
        int b = solve(cuts, len, i+1);

        int ans = Math.max(a, b);
        return dp[i][len] = ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] cuts = new int[3];
        cuts[0] = Integer.parseInt(st.nextToken());
        cuts[1] = Integer.parseInt(st.nextToken());
        cuts[2] = Integer.parseInt(st.nextToken());

        dp = new Integer[3][n+1];
        int ans = solve(cuts, n, 0);
        System.out.println(ans);
    }
}
