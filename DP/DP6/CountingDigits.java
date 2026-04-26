import java.util.*;
public class CountingDigits{

    // CSES - Counting Digits
    static Long[][][][] dp;
    public static long solve(String s, int i, int tight, int prev, int started){
        if(i>=s.length()) return 1;

        if(dp[i][tight][prev][started]!=null) return dp[i][tight][prev][started];

        long total = 0;
        int ub = Integer.parseInt(s.charAt(i)+"");

        for(int d=0; (tight==1? (d<=ub) : (d<=9)); d++){
            if(d==prev && started==1) continue;
            if(tight==1){
                if(d==ub){
                    if(d!=0){
                        total += solve(s, i+1, 1, d, 1);
                    }else{
                        total += solve(s, i+1, 1, d, started);
                    }
                }else{
                    if(d!=0){
                        total += solve(s, i+1, 0, d, 1);
                    }else{
                        total += solve(s, i+1, 0, d, started);
                    }
                }
            }else{
                if(d!=0){
                    total += solve(s, i+1, 0, d, 1);
                }else{
                    total += solve(s, i+1, 0, d, started);
                }
            }
        }

        return dp[i][tight][prev][started] = total;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long l = sc.nextLong();
        long r = sc.nextLong();

        
        String high = r+"";
        dp = new Long[high.length()][2][11][2];
        long a = solve(high, 0, 1, 10, 0);
        
        if(l==0){
            System.out.println(a);
            return;
        }
        
        String low = (l-1)+"";
        dp = new Long[high.length()][2][11][2];
        long b = solve(low, 0, 1, 10, 0);
        
        System.out.println(a-b);
    }
}