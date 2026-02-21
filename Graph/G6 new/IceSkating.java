import java.util.*;
public class IceSkating {
    // CodeForces -  Ice Skating (Incomplete code)
    public static void dfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited){
        isVisited[i] = true;
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                dfs(graph, ngbr, isVisited);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] edges = new int[n][2];
        for(int i=0; i<n; i++){ // store coordinates
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph =  new ArrayList[n];
        for(int i=0 ; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        // treat coordinates as a node 
        // they will be connected by edge when any of the coordinate matched
        // with x or y cordinate of other node
        for(int i=0; i<n-1; i++){ // node u
            for(int j=i+1; j<n; j++){ // node v

                int x1 = edges[i][0]; // node U
                int y1 = edges[i][1]; // node U

                int x2 = edges[j][0]; // node V
                int y2 = edges[j][1]; // node V

                if(x1==x2 || y1==y2){ // condition
                    // connections edges
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        int count = 0; // number of components
        boolean[] isVisited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!isVisited[i]){
                dfs(graph, i, isVisited);
                count++;
            }
        }

        System.out.println(count-1); // edges required will be count -1
    }
}