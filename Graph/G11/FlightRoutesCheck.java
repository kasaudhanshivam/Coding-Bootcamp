import java.io.*;
import java.util.*;

public class FlightRoutesCheck {
    // CSES - Flight Routes Check

    public static void dfs(ArrayList<Integer>[] graph, boolean[] isVisited, int i){
        isVisited[i] = true;
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                dfs(graph, isVisited, ngbr);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graphR = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
            graphR[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);   // directed
            graphR[v].add(u); // reversed graph
        }

        // DFS Traversal on original graph
        boolean[] vis = new boolean[n+1];
        dfs(graph, vis, 1);
        for(int i=1; i<=n; i++){
            if(!vis[i]){ // if any node is not reachable
                System.out.println("NO");
                System.out.println(1 + " " + i);
                return;
            }
        }

        //DFS Traversal on reversed graph
        boolean[] visR = new boolean[n+1];
        dfs(graphR, visR, 1);
        for(int i=1; i<=n; i++){
            if(!visR[i]){ // if any node is not reachable
                System.out.println("NO");
                System.out.println(i + " " + 1);
                return;
            }
        }

        System.out.println("YES");
    }
}
