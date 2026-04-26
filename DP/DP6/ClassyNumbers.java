import java.util.Scanner;

public class ClassyNumbers {
    static Long[][][] dp;
    public static long solve(String s, int i, int tight, int nonZero){
        if(i>=s.length()) return 1;

        if(dp[i][tight][nonZero]!=null) return dp[i][tight][nonZero];

        long total = 0;
        int ub = Integer.parseInt(s.charAt(i)+"");
        for(int d=0; (tight==1? d<=ub : d<=9); d++){
            if(tight==1){
                if(d==ub){
                    if(d!=0){
                        if(nonZero<3){
                            total += solve(s, i+1, 1, nonZero+1);
                        }else break;
                    }else{
                        total += solve(s, i+1, 1, nonZero);
                    }
                }else{
                    if(d!=0){
                        if(nonZero<3){
                            total += solve(s, i+1, 0, nonZero+1);
                        }else break;
                    }else{
                        total += solve(s, i+1, 0, nonZero);
                    }
                }
            }else{
                if(d!=0){
                    if(nonZero<3){
                        total += solve(s, i+1, 0, nonZero+1);
                    }else break;
                }else{
                    total += solve(s, i+1, 0, nonZero);
                }
            }
        }

        return dp[i][tight][nonZero] = total;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        long[] res = new long[t];
        for(int i=0; i<t; i++){
            long l = sc.nextLong();
            long r = sc.nextLong();

            String high = r+"";
            dp = new Long[high.length()][2][high.length()+1];
            long a = solve(high, 0, 1, 0);
            
            if(l==0){
                res[i] = a;
                continue;
            }
            
            String low = (l-1)+"";
            dp = new Long[low.length()][2][low.length()+1];
            long b = solve(low, 0, 1, 0);
            
            res[i] = a-b;
        }



        for(long r : res){
            System.out.println(r);
        }
    }
}
