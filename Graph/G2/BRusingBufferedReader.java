import java.io.*;
import java.util.*;

public class BRusingBufferedReader {
    public static void bfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);
        isVisited[i] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int ngbr : graph[curr]) {
                if (!isVisited[ngbr]) {
                    isVisited[ngbr] = true;
                    q.offer(ngbr);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // using buffered reader so that large input can also be handled
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] isVisited = new boolean[V + 1];
        ArrayList<Integer> notVisited = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            if (!isVisited[i]) {
                notVisited.add(i); // accumulate total number of components (their start or any node)
                bfs(graph, i, isVisited);
            }
        }

        System.out.println(notVisited.size() - 1);
        for (int i = 1; i < notVisited.size(); i++) {
            // we need to connect only those accumulated cities
            System.out.println(notVisited.get(i - 1) + " " + notVisited.get(i));
        }
    }
}
