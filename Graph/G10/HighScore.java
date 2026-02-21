import java.util.*;

public class HighScore {

    public static void bellman(long[][] edges, long[] dist, int V, long[] parent){
        for(int i=0; i<V-1; i++){
            for(long[] e : edges){
                long u = e[0];
                long v = e[1];
                long w = e[2];
                if(dist[(int)u]!=Long.MIN_VALUE && dist[(int)u]+w > dist[(int)v]){
                    dist[(int)v] = dist[(int)u] + w; // relax the edges 
                    parent[(int)v] = u; // and also store their parent
                }
            }
        }
    }

    public static boolean dfs(ArrayList<long[]>[] graph, boolean[] isVisited, int i, int target){
        if(i==target) return true; // conncted with n
        isVisited[i] = true;
        for(long[] ngbr : graph[i]){
            if(!isVisited[(int)ngbr[0]]){
                if(dfs(graph, isVisited, (int)ngbr[0], target)) return true;
            }
        }
        return false;
    }

    // public static boolean comeThrough(long[] parent, long curr, long n){
    //     boolean[] isVisited = new boolean[(int)n+1];
    //     while(curr!=-1 && !isVisited[(int)curr]){
    //         isVisited[(int)curr] = true;
    //         if(curr==n) return true;
    //         curr = parent[(int)curr];
    //     }
    //     return false;
    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt(); // rooms = nodes
        int m = sc.nextInt(); // tunnels = edges

        @SuppressWarnings("unchecked")
        ArrayList<long[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        

        long[][] edges = new long[m][3];
        for(int i=0; i<m; i++){
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
            graph[(int)edges[i][0]].add(new long[]{edges[i][1], edges[i][2]});
        }

        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[1] = 0;

        long[] parent = new long[n+1];
        Arrays.fill(parent, -1);

        bellman(edges, dist, n, parent);

        // -ive wt cycle detection
        for(long[] e : edges){
            long u = e[0];
            long v = e[1];
            long w = e[2];
            if(dist[(int)u]!=Long.MIN_VALUE && dist[(int)u]+w > dist[(int)v]){
                // if(dfs(graph, new boolean[n+1], (int)v, n) || comeThrough(parent, v, n)){
                if(dfs(graph, new boolean[n+1], (int)v, n)){ // check if n is connected with v
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(dist[n]);
        sc.close();

    }
}
