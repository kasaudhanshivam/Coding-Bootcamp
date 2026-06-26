import java.util.*;
import java.io.*;

public class GameRoutes{
    static Long[] dp;
    public static long dfs(List<Integer>[] graph, int i, boolean[] vis){
        if(i==graph.length-1) return 1;
        vis[i] = true;

        long ways = 0;
        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                ways += dfs(graph, ngbr, vis);
            }else{
                ways += dp[ngbr];
            }
        }
        vis[i] = false;
        return dp[i] = ways;
    }
    static int MOD = 1000000000+7;
    public static long topo(List<Integer>[] graph, int[] inDeg){
        long[] ways = new long[inDeg.length];
        ways[1] = 1;
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<inDeg.length; i++){
            if(inDeg[i]==0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int i = q.poll();
            // System.out.println("ways " + i + " " + ways[i]);
            for(int ngbr : graph[i]){
                ways[ngbr] = (ways[ngbr] + ways[i]) % MOD;
                // System.out.println("ways " + ngbr + " " + ways[ngbr]);
                // System.out.println("--------------");
                inDeg[ngbr]--;
                if(inDeg[ngbr]==0){
                    q.offer(ngbr);
                }
            }
        }
        // System.out.println(Arrays.toString(ways));
        return ways[inDeg.length-1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[n+1];
        int[] inDeg = new int[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            inDeg[v]++;
        }
        // for(List<Integer> list : graph){
        //     System.out.println(list);
        // }

        // System.out.println(Arrays.toString(inDeg));
        dp = new Long[n+1];
        // long res = dfs(graph, 1, new boolean[n+1]);
        long res = topo(graph, inDeg);
        System.out.println(res);

    }
}