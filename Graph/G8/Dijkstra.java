import java.util.*;

public class Dijkstra {
    // Similar to CSES - Labyrinth
    public static void dijkstra(ArrayList<int[]>[] graph, boolean[] isFinalised, int[] dist, PriorityQueue<int[]> q, int[] parent){
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int u = curr[0];
            if(isFinalised[u]) continue;
            isFinalised[u] = true;
            for(int[] ngbr : graph[u]){
                int v = ngbr[0];
                int w = ngbr[1];
                if(!isFinalised[v] && dist[u]+w < dist[v]){
                    dist[v] = dist[u] + w;
                    parent[v] = u; // store the parent of nodes in parent array
                    q.offer(new int[]{v, dist[v]});
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        boolean[] isFinalised = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        q.offer(new int[]{1, dist[1]});
        int[] parent = new int[n+1];
        parent[1] = -1;
        dijkstra(graph, isFinalised, dist, q, parent);
        if(dist[n]==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        // print path
        ArrayList<Integer> path = new ArrayList<>();
        int i = n;
        // traverse parent array from destination (n) --> source (1)
        while(parent[i]!=-1){
            path.add(i);
            i = parent[i];
        }
        path.add(1);
        for(int j=path.size()-1; j>=0; j--){
            // now print the path from source (1) --> destionation (n)
            System.out.print(path.get(j) + " ");
        }
    }
}
