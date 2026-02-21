import java.util.*;

public class RingRoad {
    static int min;
    public static void dfs(ArrayList<int[]>[] graph, int curr, int parent, int start, int currCost){
        if(curr==start){
            min = Math.min(currCost, min);
            return;
        }
        
        for(int[] ngbr : graph[curr]){
            int v = ngbr[0];
            int w = ngbr[1];
            if(v!=parent){ // ngbr != its current parent
                dfs(graph, v, curr, start, currCost+w);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new int[]{v, 0}); // u --> v (already road exists)
            graph[v].add(new int[]{u, w}); // v --> u (reversed road with cost)
        }

        min = Integer.MAX_VALUE;
        dfs(graph, graph[1].get(0)[0], 1, 1, graph[1].get(0)[1]); // clockwise cost
        dfs(graph, graph[1].get(1)[0], 1, 1, graph[1].get(1)[1]); // anticlockwise cost
        System.out.println(min);
    }
}