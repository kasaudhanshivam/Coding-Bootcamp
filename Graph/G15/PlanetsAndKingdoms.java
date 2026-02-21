import java.io.*;
import java.util.*;

public class PlanetsAndKingdoms {

    static Stack<Integer> sorted;
    public static void dfs1(ArrayList<Integer>[] graph, boolean[] isVisited, int i){
        isVisited[i] = true;
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                dfs1(graph, isVisited, ngbr);
            }
        }
        sorted.push(i); // finishing time order
    }

    public static void dfs2(ArrayList<Integer>[] graph, boolean[] isVisited, int i, int[] ass, int o){
        isVisited[i] = true;
        ass[i] = o; // assigned this node to component 'oth'
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                dfs2(graph, isVisited, ngbr, ass, o);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer ST = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(ST.nextToken());
        int m = Integer.parseInt(ST.nextToken());
        

        // Kosaraju Algorithm
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] revGraph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            ST = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(ST.nextToken());
            int v = Integer.parseInt(ST.nextToken());

            graph[u].add(v);
            revGraph[v].add(u);
        }

        sorted = new Stack<>(); // finishing time order
        boolean[] isVis1 = new boolean[n+1];
        for(int i=1; i<n+1; i++){
            if(!isVis1[i]){
                dfs1(graph, isVis1, i);
            }
        }
        

        boolean[] isVis = new boolean[n+1];
        int[] ass = new int[n+1]; // assigned 'i' node to component 'o'
        int o = 0; // SCC's count
        for(int i=1; i<n+1; i++){
            int src = sorted.pop();
            if(!isVis[src]){
                o++;
                dfs2(revGraph, isVis, src, ass, o);
            }
        }

        System.out.println(o);
        // Most Important thing to remember
        // This was causing TLE, so we must have to use String Builder to first store the output then we will print at once
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(ass[i]).append(' ');
        }
        System.out.println(sb);

    }
}