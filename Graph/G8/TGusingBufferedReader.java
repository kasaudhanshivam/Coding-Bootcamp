
import java.util.*;
import java.io.*;

public class TGusingBufferedReader {
    public static void bfs(ArrayList<Integer>[] graph, int i, int[] dist){
        boolean[] isVisited = new boolean[graph.length+1];
        isVisited[i] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        dist[i] = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int ngbr : graph[curr]){
                if(!isVisited[ngbr]){
                    dist[ngbr] = dist[curr] + 1; // update dist for ngbrs
                    isVisited[ngbr] = true;
                    q.offer(ngbr);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // total vertices
        int m = Integer.parseInt(st.nextToken()); // B's Node

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u); // undirected
        }
        

        // do find dist to each node from Alice's node
        int[] distA = new int[n+1];
        bfs(graph, 1, distA);
        // for(int i=1; i<n+1; i++){
        //     System.out.print(distA[i]+" ");
        // }




        // do find dist to each node from Bob's node
        int[] distB = new int[n+1];
        System.out.println();
        bfs(graph, m, distB);
        // for(int i=1; i<n+1; i++){
        //     System.out.print(distB[i]+" ");
        // }


        // Get the max dist of Alice to any node which is max and > Bob's dist
        // matlb alice ko jyada time lagega us node tak pahuchne me to bob safe rhega
        int dist = Integer.MIN_VALUE;
        for(int i=1; i<n+1; i++){
            if(distA[i]>distB[i]){
                dist = Math.max(dist, distA[i]);
            }
        }

        int moves = 2 * dist;
        System.out.println(moves);
    }    
}
