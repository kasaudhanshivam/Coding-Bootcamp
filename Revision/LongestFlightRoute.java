import java.io.*;
import java.util.*;

public class LongestFlightRoute {
    static boolean[] vis;
    public static void dfs(List<Integer>[] graph, int i){
        vis[i] = true;
        for(int ngbr : graph[i]){
            if(!vis[ngbr]){
                dfs(graph, ngbr);
            }
        }
    }
    public static int topo(List<Integer>[] graph, int[] inDeg, int[] parent){
        int[] ways = new int[inDeg.length];
        ways[1] = 1;
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<inDeg.length; i++){
            if(inDeg[i]==0) q.offer(i);
        }
        while(!q.isEmpty()){
            int u = q.poll();

            for(int v : graph[u]){
                if(ways[u]+1>ways[v] && vis[v]){
                    ways[v] = ways[u]+1;
                    parent[v] = u;
                }
                inDeg[v]--;
                if(inDeg[v]==0){
                    q.offer(v);
                }
            }
        }

        return ways[inDeg.length-1]==0? -1 : ways[inDeg.length-1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        int[] inDeg = new int[n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            inDeg[v]++;
        }

        int[] parent = new int[n+1];
        // Arrays.fill(parent, -1);
        parent[1] = -1;
        vis = new boolean[n+1];
        dfs(graph, 1);
        int ways = topo(graph, inDeg, parent);
        // System.out.println(Arrays.toString(parent));

        if(ways==-1){
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(ways);

        List<Integer> list = new ArrayList<>();
        int x = n;
        while(x!=-1){
            list.add(x);
            x = parent[x];
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=list.size()-1; i>=0; i--){
            sb.append(list.get(i));
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
