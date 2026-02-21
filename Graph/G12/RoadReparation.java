import java.util.*;

public class RoadReparation {
    public static long prims(ArrayList<long[]>[] graph, boolean[] isConnected){
        long cost = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));
        pq.offer(new long[]{-1, 1, 0});
        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int)curr[0];
            int v = (int)curr[1];
            long w = curr[2];

            if(isConnected[v]) continue;
            isConnected[v] = true;
            cost += w;

            for(long[] ngbr : graph[v]){
                pq.offer(new long[]{v, ngbr[0], ngbr[1]});
            }
        }
        return cost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<long[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextInt();

            graph[u].add(new long[]{v, w});
            graph[v].add(new long[]{u, w});
        }


        boolean[] isConnected = new boolean[n+1];
        long cost = prims(graph, isConnected);
        
        for(int i=1; i<n+1; i++){
            if(!isConnected[i]){
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        System.out.println(cost);
    }
}
