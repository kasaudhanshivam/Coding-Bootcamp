import java.util.*;

public class Lucifer{
    static Integer[][][][] dp;
    static boolean[] isPrime = new boolean[100];
    static {
        isPrime[2] = isPrime[3] = isPrime[5] = isPrime[7] = isPrime[11] = 
        isPrime[13] = isPrime[17] = isPrime[19] = isPrime[23] = isPrime[29] = 
        isPrime[31] = isPrime[37] = isPrime[41] = isPrime[43] = isPrime[47] = true;
    }
    public static int solve(String s, int i, int rest, int evenSum, int oddSum){
        if(i>=s.length()){
            int diff = evenSum-oddSum;
            if(diff<0) return 0;
            if(isPrime[diff]){
                return 1;
            }
            return 0;
        }

        if(dp[i][rest][evenSum][oddSum]!=null) return dp[i][rest][evenSum][oddSum];

        int total = 0;
        int ub = s.charAt(i)-'0';
        for(int d=0; (rest==0? d<=9 : d<=ub); d++){
            if(rest==1){
                if(d==ub){
                    if((s.length()-i)%2==0){ // even idx
                        total += solve(s, i+1, 1, evenSum+d, oddSum);
                    }else{ // odd idx
                        total += solve(s, i+1, 1, evenSum, oddSum+d);
                    }
                }else{
                    if((s.length()-i)%2==0){
                        total += solve(s, i+1, 0, evenSum+d, oddSum);
                    }else{
                        total += solve(s, i+1, 0, evenSum, oddSum+d);
                    }
                }
            }else{
                if((s.length()-i)%2==0){
                    total += solve(s, i+1, 0, evenSum+d, oddSum);
                }else{
                    total += solve(s, i+1, 0, evenSum, oddSum+d);
                }
            }
        }

        dp[i][rest][evenSum][oddSum] = total;
        return total;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int o=0; o<t; o++){

            int l = sc.nextInt();
            int r = sc.nextInt();

            String R = r+"";
            // resetDp(R.length());
            dp = new Integer[12][2][90][90];
            // dp = new HashMap<>();
            int a = solve(R, 0, 1, 0, 0);
            
            if(l==0){
                System.out.println(a);
                continue;
            }
            
            String L = (l-1)+"";
            // resetDp(L.length());
            dp = new Integer[12][2][90][90];
            // dp = new HashMap<>();
            int b = solve(L, 0, 1, 0, 0);

            System.out.println(a-b);

        }
        sc.close();
    }
}