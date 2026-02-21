import java.util.*;
import java.io.*;

class DarkRoads {

    public static int prims(ArrayList<long[]>[] graph) {
        int cost = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));
        boolean[] isConnected = new boolean[graph.length];
        pq.offer(new long[] { -1, 0, 0 });

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            int v = (int) curr[1];
            long w = curr[2];

            if (isConnected[v]) continue;
            isConnected[v] = true;
            cost += w;

            for (long[] ngbr : graph[v]) {
                pq.offer(new long[] { v, ngbr[0], ngbr[1] });
            }
        }
        return cost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Integer> ans = new ArrayList<>();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            @SuppressWarnings("unchecked")
            ArrayList<long[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            int totalCost = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());

                totalCost += w;
                graph[u].add(new long[] { v, w });
                graph[v].add(new long[] { u, w });
            }

            int minCost = prims(graph);
            ans.add(totalCost - minCost);
        }

        for (int a : ans) {
            System.out.println(a);
        }
    }
}
