import java.util.*;

public class CyclicComponents {
    static boolean isCycle;
    public static void dfs(ArrayList<Integer>[] graph, boolean[] isVisited, int curr, int[] inDeg){
        isVisited[curr] = true;

        if(inDeg[curr]!=2) isCycle = false; // flag it as false

        for(int ngbr : graph[curr]){
            if(!isVisited[ngbr]){
                // travere the complete component and mark it as visited so that it will be separated and we do not process it again unnecessarily
                // we just play with isCycle flag and make all the nodes of a comoonent visited
                dfs(graph, isVisited, ngbr, inDeg);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        int[] inDeg = new int[n+1];
        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
            inDeg[u]++;
            inDeg[v]++;
        }

        // Note as per given cyclic condition inDeg for any node must be exactly 2
        boolean[] isVisited = new boolean[n+1];
        int count = 0; // total cyclic components
        for(int i=1; i<n+1; i++){
            if(!isVisited[i]){
                isCycle = true;
                dfs(graph, isVisited, i, inDeg); // cycle count
                if(isCycle) count++;
            }
        }

        System.out.println(count);
    }
}
