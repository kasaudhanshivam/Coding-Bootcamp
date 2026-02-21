import java.util.*;

public class TheTwoRoutes {
    public static int bfs(HashSet<Integer>[] graph, int src, boolean[] isVisited){
        int levels = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        q.offer(-1);
        isVisited[src] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr==graph.length-1) return levels; // destination reached
            if(curr==-1){
                levels++;
                if(!q.isEmpty() && q.peek()!=-1) q.offer(-1);
                continue;
            }
            for(int ngbr : graph[curr]){
                if(!isVisited[ngbr]){
                    isVisited[ngbr] = true;
                    q.offer(ngbr);
                }
            }
        }
        return levels;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        @SuppressWarnings("unchecked")
        HashSet<Integer>[] graph1 = new HashSet[n+1]; // railways
        for(int i=1; i<n+1; i++){
            graph1[i] = new HashSet<>();
        }
        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph1[u].add(v);
            graph1[v].add(u);
        }
        
        // two cities that are not connected by rail, are connected by roads
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] graph2 = new HashSet[n+1]; // roadways
        for(int i=1; i<n+1; i++){
            graph2[i] = new HashSet<>();
        }
        for(int i=1; i<n+1; i++){
            for(int j=i+1; j<n+1; j++){
                if(!graph1[i].contains(j)){
                    graph2[i].add(j);
                    graph2[j].add(i);
                }
            }
        }

        boolean[] vis1 = new boolean[n+1]; // by train
        boolean[] vis2 = new boolean[n+1]; // by bus

        int time1 = bfs(graph1, 1, vis1);
        int time2 = bfs(graph2, 1, vis2);

        if(vis1[n] && vis2[n]){
            int max = Math.max(time1, time2); // max time to reach dest
            System.out.println(max);
            return;
        }

        System.out.println(-1);
    }
}
