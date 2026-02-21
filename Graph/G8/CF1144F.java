import java.util.*;

public class CF1144F {
    public static boolean dfs(ArrayList<Integer>[] graph, int[] color, int curr){
        for(int ngbr : graph[curr]){
            if(color[ngbr]==-1){
                if(color[curr]==1) color[ngbr] = 0;
                else color[ngbr] = 1;
                if(!dfs(graph, color, ngbr)) return false;
            }else if(color[ngbr]==color[curr]){
                return false;
            }
        }
        return true;
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
        int[][] edges = new int[m][2];
        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v); // initially directed
            graph[v].add(u);
            edges[i][0] = u;
            edges[i][1] = v;
        }

        int[] color = new int[n+1];
        Arrays.fill(color, -1);
        for(int i=1; i<n+1; i++){
            if(color[i]==-1){
                color[i] = 0;
                if(!dfs(graph, color, i)){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            if(color[u]==0 && color[v]==1){
                System.out.print(1);
            }else{
                System.out.print(0);
            }
        }
    }
}
