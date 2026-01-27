import java.util.*;
public class MessageRoute {
    public static void bfs(ArrayList<Integer>[] graph, boolean[] isVisited){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        q.offer(-1);
        isVisited[1] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr!=-1){
                for(int ngbr : graph[curr]){
                    if(!isVisited[ngbr]){
                        isVisited[ngbr] = true;
                        parent[ngbr] = curr;
                        q.offer(ngbr);
                    }
                }
            }
            if(curr==-1 && q.size()!=0){
                q.offer(-1);
                levels++;
            }
        }
    }
    static int levels;
    static int[] parent;
    public static void main(String[] args) {
        // 5 5
        // 1 2
        // 1 3
        // 1 4
        // 2 3
        // 5 4

        // 10 10
        // 5 7
        // 3 5
        // 7 9
        // 5 9
        // 3 7
        // 6 8
        // 2 6
        // 6 10
        // 2 4
        // 4 8

        // 10 20
        // 1 2
        // 4 5
        // 3 6
        // 8 10
        // 5 6
        // 6 7
        // 7 9
        // 1 4
        // 2 3
        // 9 10
        // 4 6
        // 7 8
        // 8 9
        // 3 7
        // 7 10
        // 6 10
        // 5 9
        // 3 4
        // 5 7
        // 2 6

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[n+1];
        levels = 1;
        parent = new int[n+1];
        parent[0] = -1;
        parent[1] = 0;
        bfs(graph, isVisited);
        if(parent[n]==0){
            System.out.println("IMPOSSIBLE");
            return;
        }
        ArrayList<Integer> shortestPath = new ArrayList<>();
        int i = n;
        while(parent[i]!=-1){
            shortestPath.add(i);
            i = parent[i];
        }
        System.out.println(shortestPath.size());
        for(int j=shortestPath.size()-1; j>=0; j--){
            System.out.print(shortestPath.get(j) + " ");
        }
    }




    // using buffered reader
    
}
