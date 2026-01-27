import java.util.*;
public class RoundTrip2 {
    public static boolean dfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited, boolean[] inPath, Stack<Integer> path){
        isVisited[i] = true;
        inPath[i] = true; // mark that it is in curr path
        path.push(i); // store to curr path
        for(int ngbr : graph[i]){
            if(!isVisited[ngbr]){
                if(dfs(graph, ngbr, isVisited, inPath, path)){
                    return true; // we have got the cycle
                }
            }else if(inPath[ngbr]){ // already in same path
                path.push(ngbr); // psuh to curr path to form cycle
                return true; // immediately return true as we already got cycle
            }
        }
        inPath[i] = false; // backtrack
        path.pop(); // backtrack
        return false; // if no cycle found
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
            graph[u].add(v); // directed
        }

        boolean[] isVisited = new boolean[n+1];
        boolean[] inPath = new boolean[n+1]; // to track[T/F] curr path


        Stack<Integer> path = new Stack<>();
        for(int i=1; i<n+1; i++){
            if(!isVisited[i]){
                path = new Stack<>(); // will store curr path
                if(dfs(graph, i, isVisited, inPath, path)){
                    break;
                }
            }
        }
        if(path.isEmpty()){
            System.out.println("IMPOSSIBLE");
            return;
        }

        int st = path.pop(); // starting node
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(st);

        while (!path.isEmpty()) {
            int x = path.pop();
            ans.add(x); // store in final paht trip
            if (x == st) break; // till we reach to the same node from where we have start
        }

        Collections.reverse(ans); // start to end

        System.out.println(ans.size());
        for (int x : ans) System.out.print(x + " "); // print path

        sc.close();
    }
}
