import java.io.*;
import java.util.*;

public class JzzhuAndCities {
    public static void dijkstra(List<int[]>[] graph, long[] dist, int[] parent){
        boolean[] isFin = new boolean[graph.length];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{1, 0});

        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int)curr[0];
            if(isFin[u]) continue;
            isFin[u] = true;

            for(int[] ngbr : graph[u]){
                int v = ngbr[0];
                int d = ngbr[1];
                if(!isFin[v] && dist[u]+d<dist[v]){
                    dist[v] = dist[u] + d;
                    if(ngbr[2]==1) parent[v] = 1;
                    else parent[v] = 0;
                    pq.offer(new long[]{v, dist[v]});
                }
                else if(!isFin[v] && dist[u]+d==dist[v] && ngbr[2]==0){
                    parent[v] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        // 0 = Roads, 1 = Train
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w, 0}); // road
            graph[v].add(new int[]{u, w, 0});
        }
        
        for(int j=0; j<k; j++){
            st = new StringTokenizer(br.readLine());
            int u = 1; // trains from capital
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w, 1}); // train
            graph[v].add(new int[]{u, w, 1});
        }

        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        int[] parent = new int[n+1];
        parent[1] = -1;
        dijkstra(graph, dist, parent);

        int count = 0;
        for(int i=1; i<n+1; i++){
            if(parent[i]==1) count++;
        }
        System.out.println(k-count);
        
    }
}
