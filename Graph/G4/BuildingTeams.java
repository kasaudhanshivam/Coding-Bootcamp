import java.util.*;

public class BuildingTeams {
    public static boolean bfs(ArrayList<Integer>[] graph, int i, int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int ngbr : graph[curr]){
                if(color[ngbr]==-1){
                    if(color[curr]==1) color[ngbr] = 2;
                    else color[ngbr] = 1;
                    q.offer(ngbr);
                }else if(color[ngbr]==color[curr]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int e = sc.nextInt();
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[V+1];
        for(int i=1; i<V+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] color = new int[V+1];
        Arrays.fill(color, -1);
        for(int i=1; i<V+1; i++){
            if(color[i]==-1){
                color[i] = 1;
                if(!bfs(graph, i, color)){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        for(int i=1; i<V+1; i++){
            System.out.print(color[i] + " ");
        }
        sc.close();
    }
}
