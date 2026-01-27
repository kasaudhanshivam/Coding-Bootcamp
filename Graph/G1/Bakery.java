import java.util.*;

public class Bakery {
    public static void main(String[] args) {
        // 5 4 2
        // 1 2 5
        // 1 2 3
        // 2 3 4
        // 1 4 10
        // 1 5

        // 3 1 1
        // 1 2 3
        // 3

        // 2 3 1
        // 1 2 3
        // 1 2 18
        // 1 2 13
        // 2

        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // vertices
        int n = sc.nextInt(); // edges
        int k = sc.nextInt(); // no. of storage cities

        // @SuppressWarnings("unchecked")
        // ArrayList<int[]>[] graph = new ArrayList[m+1];
        // for(int i=1; i<m+1; i++){
        //     graph[i] = new ArrayList<>();
        // }

        int[][] edges = new int[n][3];
        for(int i=0; i<n; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            // graph[u].add(new int[]{v, w});
            // graph[v].add(new int[]{u, w});

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;
        }
        
        Set<Integer> set = new HashSet<>(); // storage cities
        for(int i=0; i<k; i++){
            int t = sc.nextInt();
            set.add(t);
        }

        int ans = Integer.MAX_VALUE;
        

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if((set.contains(u) && !set.contains(v)) || (set.contains(v) && !set.contains(u))){
                ans = Math.min(ans, w);
            }
        }

        System.out.println(ans==Integer.MAX_VALUE? -1: ans);
    }   
}
