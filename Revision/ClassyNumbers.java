import java.io.*;
import java.util.StringTokenizer;

public class ClassyNumbers{
    static Long[][][] dp;
    public static long solve(String s, int i, int rest, int cnt){
        if(i>=s.length()) return 1;

        if(dp[i][rest][cnt]!=null) return dp[i][rest][cnt];

        int ub = Integer.parseInt(s.charAt(i)+"");
        long total = 0;

        for(int d=0; d<=(rest==1? ub : 9); d++){
            if(rest==1){
                if(d==ub){
                    if(d!=0 && cnt<3) total += solve(s, i+1, 1, cnt+1);
                    if(d==0) total += solve(s, i+1, 1, cnt);
                }else{
                    if(d!=0 && cnt<3) total += solve(s, i+1, 0, cnt+1);
                    if(d==0) total += solve(s, i+1, 0, cnt);
                }
            }else{
                if(d!=0 && cnt<3) total += solve(s, i+1, rest, cnt+1);
                if(d==0) total += solve(s, i+1, rest, cnt);
            }
        }

        return dp[i][rest][cnt] = total;
    }
    public static void main(String[] args) throws IOException {


        // 4
        // 1 1000
        // 1024 1024
        // 65536 65536
        // 999999 1000001


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        
        for(int o=0; o<t; o++){
            st = new StringTokenizer(br.readLine());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());


            String r = R+"";
            dp = new Long[r.length()][2][4];
            long a = solve(r, 0, 1, 0);
            
            if(L==0){
                System.out.println(a);
                continue;
            }
            
            String l = (L-1)+"";
            dp = new Long[l.length()][2][4];
            long b = solve(l, 0, 1, 0);


            System.out.println(a-b);
        }

    }
}