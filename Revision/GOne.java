import java.util.*;

public class GOne{
    static Integer[][][] dp;
    public static boolean isPrime(int n){
        if(n<2) return false;
        for(int i=2; i<n; i++){
            if(n%i==0) return false;
        }
        return true;
    }
    public static int solve(String s, int i, int rest, int sum){
        if(i>=s.length()){
            if(isPrime(sum)){
                return 1;
            }
            return 0;
        }


        if(dp[i][rest][sum]!=null) return dp[i][rest][sum];

        int total = 0;
        int ub = Integer.parseInt(s.charAt(i)+"");
        for(int d=0; (rest==0? d<=9 : d<=ub); d++){
            if(rest==1){
                if(d==ub){
                    total += solve(s, i+1, 1, sum+d);
                }else{
                    total += solve(s, i+1, 0, sum+d);
                }
            }else{
                total += solve(s, i+1, 0, sum+d);
            }
        }

        return dp[i][rest][sum] = total;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        // int[] res = new int[t];
        for(int o=0; o<t; o++){

            int l = sc.nextInt();
            int r = sc.nextInt();

            String R = r+"";
            dp = new Integer[R.length()][2][9*R.length()];
            int a = solve(R, 0, 1, 0);
            
            if(l==0){
                // res[o] = a;
                System.out.println(a);
                continue;
            }
            
            String L = (l-1)+"";
            dp = new Integer[L.length()][2][9*L.length()];
            int b = solve(L, 0, 1, 0);

            // res[o] = a-b;
            System.out.println(a-b);

        }



        // for(int i=0; i<t; i++){
        //     System.out.println(res[i]);
        // }
    }
}