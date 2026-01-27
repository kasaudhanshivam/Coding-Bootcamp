import java.util.*;

public class RoundTrip {
    public static boolean dfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited, int[] parent, Stack<Integer> path){
        isVisited[i] = true;
        path.push(i); // store vertices in curr path
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                parent[ngbr] = i;
                if(dfs(graph, ngbr, isVisited, parent, path)) return true;
            }else if(isVisited[ngbr] && parent[i]!=ngbr){
                // cycle found
                path.push(ngbr); // again push the node at which we got cycle so that cycle can be formed
                return true;
            }
        }
        path.pop(); // backtrack
        return false;
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
        for(int i=0; i<m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);


        Stack<Integer> path = new Stack<>();
        for(int i=1; i<n+1; i++){
            if(!isVisited[i]){
                path = new Stack<>(); // will store curr path
                if(dfs(graph, i, isVisited, parent, path)){
                    break;
                }
            }
        }
        if(path.isEmpty()){
            System.out.println("IMPOSSIBLE");
            return;
        }

        int st = path.pop(); // starting point
        ArrayList<Integer> ans = new ArrayList<>();
        while(!path.isEmpty() && st!=path.peek()){
            ans.add(path.pop()); // store ans
        }
        System.out.println(ans.size()+2); // 
        System.out.print(st + " "); // start
        for(int c : ans){
            System.out.print(c + " "); // print path
        }
        System.out.print(st); // again reached to start
        sc.close();
    }
}
