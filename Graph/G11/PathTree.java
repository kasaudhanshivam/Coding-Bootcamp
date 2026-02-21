import java.io.*;
import java.util.*;

public class PathTree {
    // CodeForces - 2193G Paths in a Tree

    static BufferedReader br;
    static StringTokenizer st;

    // for input using Buffered Reader
    static int nextInt() throws IOException {
        while (st == null || !st.hasMoreElements()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }

    public static void dfs(ArrayList<Integer>[] graph, ArrayList<Integer> list, boolean[] isVisited, int i){
        isVisited[i] = true;
        list.add(i);
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                dfs(graph, list, isVisited, ngbr);
            }
        }
    }

    public static int solve(ArrayList<Integer>[] graph) throws IOException {
        ArrayList<Integer> list = new ArrayList<>(); // list of the nodes (adjacent) using DFS
        dfs(graph, list, new boolean[graph.length], 1);

        for(int i=0; i<list.size()-1; i+=2){
            System.out.println("? " + list.get(i) + " " + list.get(i+1)); // query
            System.out.flush();
            int res = nextInt(); // response

            if(res==1){ // if jury says 1, means either of node will be ans (i/i+1)
                System.out.println("? " + list.get(i) + " " + list.get(i));
                System.out.flush();
                int res1 = nextInt();
                if(res1==1) return list.get(i);
                else return list.get(i+1);
            }
        }

        if(list.size()%2!=0){ // odd number of nodes
            return list.get(list.size()-1); // last node will be ans
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int t = nextInt();

        for(int i=0; i<t; i++){
            int n = nextInt();

            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] graph = new ArrayList[n+1];
            for(int j=1; j<n+1; j++){
                graph[j] = new ArrayList<>();
            }

            for(int j=0; j<n-1; j++){
                int u = nextInt();
                int v = nextInt();
                graph[u].add(v);
                graph[v].add(u);
            }

            int ans = solve(graph);
            System.out.println("! " + ans);
            System.out.flush();
        }
    }
}
