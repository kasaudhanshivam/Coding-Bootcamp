import java.io.*;
import java.util.*;

public class BugLife{
    public static boolean bfs(ArrayList<Integer>[] graph, int[] color, int i){
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        color[i] = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            // System.out.println("---" + color[curr]);
            for(int ngbr : graph[curr]){
                if(color[ngbr]==-1){
                    if(color[curr]==0) color[ngbr] = 1;
                    else color[ngbr] = 0;
                    q.offer(ngbr);
                    // System.out.println(color[ngbr]);
                }else{
                    if(color[ngbr]==color[curr]) return false;
                }
            }
        }
        return true;
    }
    public static boolean isBipartite(ArrayList<Integer>[] graph){
        int n = graph.length;

        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i=1; i<n; i++){
            if(color[i]==-1){
                if(!bfs(graph, color, i)){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        boolean[] res = new boolean[t];

        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] graph = new ArrayList[n+1];
            for(int j=1; j<n+1; j++){
                graph[j] = new ArrayList<>();
            }
            for(int j=0; j<m; j++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            res[i] = isBipartite(graph);

            // System.out.println(res[i]);
        }

        for(int i=0; i<t; i++){
            System.out.println("Scenario #"+(i+1)+":");
            if(res[i]){
                System.out.println("No suspicious bugs found!");
            }else{
                System.out.println("Suspicious bugs found!");
            }
        }
    }
}