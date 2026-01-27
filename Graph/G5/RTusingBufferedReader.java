import java.io.*;
import java.util.*;

public class RTusingBufferedReader {
    public static boolean dfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited, int[] parent, Stack<Integer> path){
        isVisited[i] = true;
        path.push(i);
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                parent[ngbr] = i;
                if(dfs(graph, ngbr, isVisited, parent, path)) return true;
            }else if(isVisited[ngbr] && parent[i]!=ngbr){
                // cycle found
                path.push(ngbr);
                return true;
            }
        }
        path.pop();
        return false;
    }
    public static void main(String[] args) throws Exception {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(sc.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(sc.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        Stack<Integer> path = new Stack<>();
        for(int i=1; i<n+1; i++){
            if(!isVisited[i]){
                path = new Stack<>(); // curr path
                if(dfs(graph, i, isVisited, parent, path)){
                    break;
                }
            }
        }
        if(path.isEmpty()){
            System.out.println("IMPOSSIBLE");
            return;
        }

        int stt = path.pop();
        ArrayList<Integer> ans = new ArrayList<>();
        while(!path.isEmpty() && stt!=path.peek()){
            ans.add(path.pop());
        }
        System.out.println(ans.size()+2);
        System.out.print(stt + " "); // start
        for(int c : ans){
            System.out.print(c + " ");
        }
        System.out.print(stt); // again reached to start
    }
}
