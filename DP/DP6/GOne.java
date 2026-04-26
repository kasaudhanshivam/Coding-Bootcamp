import java.util.Scanner;

public class GOne {
    static Integer[][][] dp;
    public static int solve(String s, int i, int tight, int sum){
        if(i>=s.length()){
            if(isPrime(sum)) return 1;
            return 0;
        }

        if(dp[i][tight][sum]!=null) return dp[i][tight][sum];

        int total = 0;
        int ub = Integer.parseInt(s.charAt(i)+"");
        for(int d=0; (tight==1? d<=ub : d<=9); d++){
            if(tight==1){
                if(d==ub){
                    total +=solve(s, i+1, 1, sum+d);
                }else{
                    total +=solve(s, i+1, 0, sum+d);
                }
            }else{
                total +=solve(s, i+1, 0, sum+d);
            }
        }

        return dp[i][tight][sum] = total;
    }
    public static boolean isPrime(int n){
        if(n==0 || n==1) return false;
        for(int i=2; i*i<=n; i++){
            if(n%i==0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        // int[] res = new int[t];
        for(int i=0; i<t; i++){
            
            int l = sc.nextInt();
            int r = sc.nextInt();

            String high = r+"";
            dp = new Integer[high.length()][2][(9*high.length())+1];
            int a = solve(high, 0, 1, 0);
            
            if(l==0){
                System.out.println(a);
                // res[i] = a;
                continue;
            }
            
            String low = (l-1)+"";
            dp = new Integer[low.length()][2][(9*low.length())+1];
            int b = solve(low, 0, 1, 0);
            // res[i] = a-b;
            System.out.println(a-b);
        }


        // for(int r : res){
        //     System.out.println(r);
        // }
    }
}
