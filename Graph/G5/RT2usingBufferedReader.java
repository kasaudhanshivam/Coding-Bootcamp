import java.io.*;
import java.util.*;

public class RT2usingBufferedReader {
    public static boolean dfs(ArrayList<Integer>[] graph, int i,
                              boolean[] isVisited, boolean[] inPath,
                              Stack<Integer> path) {

        isVisited[i] = true;
        inPath[i] = true;
        path.push(i);

        for (int ngbr : graph[i]) {
            if (!isVisited[ngbr]) {
                if (dfs(graph, ngbr, isVisited, inPath, path)) {
                    return true;
                }
            } else if (inPath[ngbr]) {
                path.push(ngbr);
                return true;
            }
        }

        inPath[i] = false;
        path.pop();
        return false;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }

        boolean[] isVisited = new boolean[n + 1];
        boolean[] inPath = new boolean[n + 1];

        Stack<Integer> path = new Stack<>();

        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                path = new Stack<>();
                if (dfs(graph, i, isVisited, inPath, path)) {
                    break;
                }
            }
        }

        if (path.isEmpty()) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        int stNode = path.pop();
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(stNode);

        while (!path.isEmpty()) {
            int x = path.pop();
            ans.add(x);
            if (x == stNode) break;
        }

        Collections.reverse(ans);

        System.out.println(ans.size());
        for (int x : ans) System.out.print(x + " ");
    }
}
