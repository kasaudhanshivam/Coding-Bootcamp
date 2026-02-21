import java.io.*;
import java.util.*;

public class Investigation{
    static int MOD = 1000000007;
    public static void dijkstra(ArrayList<long[]>[] graph, long[] dist, int[] ways, int[] minNodes, int[] maxNodes){
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        boolean[] isFinalized = new boolean[graph.length];
        dist[1] = 0;
        ways[1] = 1;
        minNodes[1] = 0;
        maxNodes[1] = 0;
        pq.offer(new long[]{1, 0});
        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int)curr[0];
            if(isFinalized[u]) continue;
            isFinalized[u] = true;
            
            for(long[] ngbr : graph[u]){
                int v = (int)ngbr[0];
                long w = ngbr[1];
                if(!isFinalized[v] && dist[u]+w < dist[v]){
                    dist[v] = dist[u] + w;
                    ways[v] = ways[u]; // reset
                    minNodes[v] = minNodes[u]+1;
                    maxNodes[v] = maxNodes[u]+1;
                    pq.offer(new long[]{v, dist[v]});
                }else if(dist[u]+w == dist[v]){
                    ways[v] = (ways[u] + ways[v])%MOD;
                    minNodes[v] = Math.min(minNodes[v], minNodes[u]+1);
                    maxNodes[v] = Math.max(maxNodes[v], maxNodes[u]+1);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<long[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            graph[u].add(new long[]{v, w});
        }

        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] ways = new int[n+1];
        int[] minNodes = new int[n+1];
        int[] maxNodes = new int[n+1];
        dijkstra(graph, dist, ways, minNodes, maxNodes);

        System.out.print(dist[n] + " ");
        System.out.print(ways[n] + " ");
        System.out.print(minNodes[n] + " ");
        System.out.print(maxNodes[n] + " ");
    }
}