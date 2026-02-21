import java.util.*;
import java.io.*;

public class Checkposts{
    // CodeForces - 627C Checkposts
    static int MOD = 1000000007;
    static Stack<Integer> order;
    public static void dfs1(ArrayList<Integer>[] graph, int i, boolean[] vis){
        vis[i] = true;
        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                dfs1(graph, ngbr, vis);
            }
        }
        order.push(i); // finishing time order
    }
    static long minCost;
    static long minCount;
    public static void dfs2(ArrayList<Integer>[] graph, int i, boolean[] vis, int[] cost){
        vis[i] = true;

        if(minCost>cost[i]){
            minCost = cost[i]; // update minCost
            minCount = 1; // and reset count to 1 again
        }else if(minCost==cost[i]){
            minCount++; // +1
        }

        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                dfs2(graph, ngbr, vis, cost);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] cost = new int[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] revGraph = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            cost[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        

        // Kosaraju Algorithm
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            revGraph[v].add(u);
        }

        order = new Stack<>(); // finishing time order
        boolean[] vis1 = new boolean[n+1];
        for(int i=1; i<n+1; i++){
            if(!vis1[i]){
                dfs1(graph, i, vis1);
            }
        }


        long totalCost = 0; // total min cost
        long ways = 1; // total ways with min cost
        boolean[] vis2 = new boolean[n+1];
        while(!order.isEmpty()){
            int src = order.pop();
            if(!vis2[src]){
                minCost = Long.MAX_VALUE;
                minCount = 0;
                dfs2(revGraph, src, vis2, cost);
                totalCost = (totalCost + minCost); // add this cost to total
                ways *= minCount; // update ways
            }
        }

        ways = ways % MOD; // as ques demands

        System.out.println(totalCost + " " + ways);
    }
}