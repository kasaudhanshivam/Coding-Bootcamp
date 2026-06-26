import java.util.*;

public class CountingTiling {
    static List<Integer> list;
    public static void getNext(int currMask, int newMask, int i, int n){
        if(i==n){
            list.add(newMask);
            return;
        }
        if(i>n) return;

        if(((currMask>>(n-i-1))&1)!=0){ // bit already set
            getNext(currMask, newMask, i+1, n);
        }else{ // bit not set => two options (vertical, horizontal)

            // vertical
            // check next if it is 0 then only we can put vertical
            int next = i+1;
            if(next<n && ((currMask>>(n-next-1))&1)==0) {
                getNext(currMask, newMask, i+2, n);
            }

            // horizontal
            newMask = (1<<(n-i-1)) | newMask;
            getNext(currMask, newMask, i+1, n);
        }
    }
    static Integer[][] dp;
    static int MOD = 1000000007;
    public static int solve(int col, int mask, int n, int m){
        if(col==m){
            return (mask==0)? 1 : 0;
        }

        if(dp[col][mask]!=null) return dp[col][mask];

        int ways = 0;
        list = new ArrayList<>();
        getNext(mask, 0, 0, n);
        // System.out.println(list);
        for(int nextMask : list){
            ways = (int)((ways + (long)solve(col+1, nextMask, n, m)) % MOD);
        }
        return dp[col][mask] = ways;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        dp = new Integer[m][(1<<n)];
        int res = solve(0, 0, n, m);
        System.out.println(res);
    }
}
