import java.io.*;
import java.util.*;

public class LRusingBufferedReader {
    static int[][] dir = {{-1, 0, 'U'}, {0, 1, 'R'}, {1, 0, 'D'}, {0, -1, 'L'}};

    public static void bfs(char[][] grid, int x, int y, boolean[][] isVisited, char[][] parent) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dir) {
                int i = curr[0] + d[0];
                int j = curr[1] + d[1];
                if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length &&
                    !isVisited[i][j] &&
                    (grid[i][j] == '.' || grid[i][j] == 'B')) {

                    q.offer(new int[]{i, j});
                    parent[i][j] = (char) d[2];
                    isVisited[i][j] = true;

                    if (grid[i][j] == 'B') return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int x = 0, y = 0, dx = -1, dy = -1;
        char[][] mat = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = arr[i].charAt(j);
                if (mat[i][j] == 'A') {
                    x = i; y = j;
                } else if (mat[i][j] == 'B') {
                    dx = i; dy = j;
                }
            }
        }

        boolean[][] isVisited = new boolean[n][m];
        char[][] parent = new char[n][m];
        parent[x][y] = 'S';

        bfs(mat, x, y, isVisited, parent);

        if (!isVisited[dx][dy]) {
            System.out.println("NO");
            return;
        }

        ArrayList<Character> path = new ArrayList<>();
        while (parent[dx][dy] != 'S') {
            char c = parent[dx][dy];
            path.add(c);

            if (c == 'U') dx++;
            else if (c == 'D') dx--;
            else if (c == 'L') dy++;
            else if (c == 'R') dy--;
        }

        System.out.println("YES");
        System.out.println(path.size());
        StringBuilder sb = new StringBuilder();

        while (!path.isEmpty()) {
            sb.append(path.remove(path.size() - 1));
        }

        System.out.print(sb.toString());

    }
}
