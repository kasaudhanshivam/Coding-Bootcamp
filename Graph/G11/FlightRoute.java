import java.io.*;
import java.util.*;

public class FlightRoute{
    // CSES - Flight Routes
    public static void dijkstra(ArrayList<long[]>[] graph, PriorityQueue<Long>[] dist, int k){
        int[] pCount = new int[graph.length]; // processed count
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        dist[1].offer(0L);
        pq.offer(new long[]{1, 0});
        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int)curr[0];
            long d = curr[1];
            
            // if(pCount[u]==k) continue;
            // pCount[u]++;
            
            for(long[] ngbr : graph[u]){
                int v = (int)ngbr[0];
                long w = ngbr[1];
                long newDist = d + w;
                
                if(dist[v].size()<k){
                    dist[v].offer(newDist);
                    pq.offer(new long[]{v, newDist});
                }else if(dist[v].size()==k){
                    if(dist[v].peek()>newDist){
                        dist[v].poll();
                        dist[v].offer(newDist);
                        pq.offer(new long[]{v, newDist});
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<long[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Integer.parseInt(st.nextToken());

            graph[u].add(new long[]{v, w});
        }

        @SuppressWarnings("unchecked")
        PriorityQueue<Long>[] dist = new PriorityQueue[n+1];
        for(int i=1; i<n+1; i++){
            dist[i] = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        }
        dijkstra(graph, dist, k);
        
        // print
        ArrayList<Long> list = new ArrayList<>();
        while(!dist[n].isEmpty()){
            long a = dist[n].poll();
            list.add(0, a);
        }

        for(long a : list){
            System.out.print(a + " ");
        }

    }
}
