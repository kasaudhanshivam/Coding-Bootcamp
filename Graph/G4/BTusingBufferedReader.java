import java.io.*;
import java.util.*;

public class BTusingBufferedReader {
    public static boolean bfs(ArrayList<Integer>[] graph, int i, int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int ngbr : graph[curr]){
                if(color[ngbr] == -1){
                    if(color[curr] == 1) color[ngbr] = 2;
                    else color[ngbr] = 1;
                    q.offer(ngbr);
                } else if(color[ngbr] == color[curr]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] color = new int[V + 1];
        Arrays.fill(color, -1);

        for(int i = 1; i <= V; i++){
            if(color[i] == -1){
                color[i] = 1;
                if(!bfs(graph, i, color)){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            sb.append(color[i]).append(" ");
        }
        System.out.print(sb.toString());
    }
}
