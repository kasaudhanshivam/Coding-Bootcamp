import java.io.*;
import java.util.*;

public class CoinCollector {
    // CSES - Coin Collector

    // Very important this was causing TLE
    static ArrayDeque<Integer> order; // alternative of stack 
    public static void dfs1(ArrayList<Integer>[] graph, boolean[] vis, int i, int[] par){
        vis[i] = true;
        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                dfs1(graph, vis, ngbr, par);
            }
        }
        order.push(i);
    }


    public static long dfs2(ArrayList<Integer>[] graph, boolean[] vis, int i, long[] coins, int[] ass, int o){
        vis[i] = true;
        ass[i] = o;
        long ans = 0;
        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                ans += dfs2(graph, vis, ngbr, coins, ass, o);
            }
        }
        return coins[i] + ans;
    }



    public static long getCoins(ArrayList<Integer>[] G, int i, ArrayList<Long> coins, Long[] dp){
        if(dp[i]!=null) return dp[i];

        long ans = 0;
        for(int ngbr : G[i]){
            long a = getCoins(G, ngbr, coins, dp);
            ans = Math.max(a, ans);
        }
        return dp[i] = ans + coins.get(i);
    }


    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] coins = new long[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] revGraph = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            coins[i] = Long.parseLong(st.nextToken());
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            revGraph[v].add(u);
        }


        // 1. Using Kosaraju Algorithm find SCC, and assign components to each of the nodes they belong to

        order = new ArrayDeque<>(); // finishing time of nodes
        int parent[] = new int[n+1];
        boolean[] vis1 = new boolean[n+1];
        for(int i=1; i<n+1; i++){
            if(!vis1[i]){
                dfs1(graph, vis1, i, parent);
            }
        }



        // store all the components along with their cost
        boolean[] vis2 = new boolean[n+1];
        int[] ass = new int[n+1]; // assign it a component
        ArrayList<Long> scc_coins = new ArrayList<>();
        scc_coins.add(0L); // to adjust indexing with o
        int o=0;
        while(!order.isEmpty()){
            int src = order.pop();
            if(!vis2[src]){
                o++;
                long a = dfs2(revGraph, vis2, src, coins, ass, o); // total coins in this scc
                scc_coins.add(a);
            }
        }

        // 2. Make new compressed graph, each SCC ans a node forms a DAG
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] G = new ArrayList[o+1]; // new condensed graph
        long[] inDeg = new long[o+1];
        for(int i=1; i<=o; i++){
            G[i] = new ArrayList<>();
        }
        for(int i=1; i<n+1; i++){
            int u = ass[i];
            for(int ngbr : graph[i]){
                int v = ass[ngbr];
                if(v==u) continue; // dont make self loop
                inDeg[v]++;
                G[u].add(v);
            }
        }

        // 3. Start collecting coin from nodes with inDeg = 0
        long max = Long.MIN_VALUE;
        Long[] dp = new Long[o+1]; // use memoization to optimize
        for(int i=1; i<=o; i++){
            if(inDeg[i]==0){ // start from a node with indegree = 0
                long curr = getCoins(G, i, scc_coins, dp);
                max = Math.max(max, curr); // store max
            }
        }

        System.out.println(max);

    }    
}