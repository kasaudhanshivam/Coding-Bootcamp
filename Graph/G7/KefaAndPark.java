

import java.util.*;

public class KefaAndPark {
    public static int dfs(ArrayList<Integer>[] graph, boolean[] isVisited, int curr, int m, int currConsecCats, int[] cat_vertices){
        if(graph[curr].size()==1 && curr!=1){
            // System.out.println(curr + "--" + currConsecCats);
            if(currConsecCats<=m) return 1;
        }
        if(currConsecCats>m) return 0;
        isVisited[curr] = true;
        int ans = 0;
        for(int ngbr : graph[curr]){
            if(!isVisited[ngbr]){
                if(cat_vertices[ngbr]==1){
                    ans += dfs(graph, isVisited, ngbr, m, currConsecCats+1, cat_vertices);
                }else{
                    ans += dfs(graph, isVisited, ngbr, m, 0, cat_vertices);
                }
            }
        }
        isVisited[curr] = false;
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 4 1
        // 1 1 0 0
        // 1 2
        // 1 3
        // 1 4


        // 3 2
        // 1 1 1
        // 1 2
        // 2 3


        int[] cat_vertices = new int[n+1];
        for(int i=1; i<n+1; i++){
            cat_vertices[i] = sc.nextInt();
        }

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[n+1];
        int count;
        if(cat_vertices[1]==1){
            count = dfs(graph, isVisited, 1, m, 1, cat_vertices);
        }else{
            count = dfs(graph, isVisited, 1, m, 0, cat_vertices);
        }
        System.out.println(count);
    }
}
