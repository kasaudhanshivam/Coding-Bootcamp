import java.io.*;
import java.util.*;

public class MRusingBufferedReader {

    static int levels;
    static int[] parent;

    public static void bfs(ArrayList<Integer>[] graph, boolean[] isVisited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        q.offer(-1); // level marker
        isVisited[1] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr != -1) { // valid node
                for (int ngbr : graph[curr]) {
                    if (!isVisited[ngbr]) {
                        isVisited[ngbr] = true;
                        parent[ngbr] = curr; // store parent for all the nodes so that we can print path later 
                        // it will itself store the parents for the shortest path
                        q.offer(ngbr);
                    }
                }
            }

            if (curr == -1 && q.size() != 0) {
                // level marker encountered
                q.offer(-1); // push marker for next level
                levels++; // increment level
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // used buffered reader so that large inputs can also be handled
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
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[n + 1];
        levels = 1;
        parent = new int[n + 1];
        parent[0] = -1;
        parent[1] = 0;

        bfs(graph, isVisited);

        if (parent[n] == 0) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        ArrayList<Integer> shortestPath = new ArrayList<>();
        int i = n;
        while (parent[i] != -1) {
            shortestPath.add(i); // to print in reverse order 
            i = parent[i];
        }

        System.out.println(shortestPath.size());
        for (int j = shortestPath.size() - 1; j >= 0; j--) {
            System.out.print(shortestPath.get(j) + " "); // shortest path
        }
    }
}
