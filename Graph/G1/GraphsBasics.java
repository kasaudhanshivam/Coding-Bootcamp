import java.util.*;

class GraphBasics{
    public static void bfs(ArrayList<Integer>[] graph, int V){
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisisted = new boolean[V];
        q.offer(0);
        while(!q.isEmpty()){
            int curr = q.poll();
            if(!isVisisted[curr]){
                System.out.print(curr + " ");
                isVisisted[curr] = true;
            }
            for(int ngbr : graph[curr]){
                if(!isVisisted[ngbr]){
                    q.offer(ngbr);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {1, 0}, {0, 2, 3}};
    }
}