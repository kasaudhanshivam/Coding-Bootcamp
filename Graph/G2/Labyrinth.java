import java.util.*;

public class Labyrinth {

    static int[][] dir = {{-1, 0, 'U'}, {0, 1, 'R'}, {1, 0, 'D'}, {0, -1, 'L'}};
    public static String getPath(char[][] grid, int x, int y, boolean[][] isVisited) {
        if (grid[x][y] == 'B')
            return "";

        String ans = "";
        int currSize = Integer.MAX_VALUE;
        String currAns = "";
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && !isVisited[nx][ny]
                    && (grid[nx][ny] == '.' || grid[nx][ny] == 'B')) {
                isVisited[nx][ny] = true;
                String s = (char) d[2] + getPath(grid, nx, ny, isVisited);
                if (currSize > s.length()) {
                    currAns = s;
                    currSize = s.length();
                }
                isVisited[nx][ny] = false; // backtrack
            }
        }
        ans += currAns;
        return ans;
    }

    // getPath using BFS (as above dfs will give TLE)
    public static void bfs(char[][] grid, int x, int y, boolean[][] isVisited, char[][] parent) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y });
        isVisited[x][y] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dir) {
                int i = curr[0] + d[0];
                int j = curr[1] + d[1];
                if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !isVisited[i][j]
                        && (grid[i][j] == '.' || grid[i][j] == 'B')) {
                    q.offer(new int[] { i, j });
                    // store the parent direction to print the path in future
                    parent[i][j] = (char) d[2];
                    isVisited[i][j] = true;
                    if (grid[i][j] == 'B') return; // immediately return when we reach to destination
                }
            }
        }
    }

    public static void main(String[] args) {

        // 5 8
        // ########
        // #.A#...#
        // #.##.#B#
        // #......#
        // ########

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // starting point
        int x = 0;
        int y = 0;

        // There is exactly one A and one B in the input.

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        // destination point
        int dx = -1;
        int dy = -1;
        char[][] mat = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = arr[i].charAt(j);
                if (mat[i][j] == 'A') {
                    x = i;
                    y = j;
                } else if (mat[i][j] == 'B') {
                    dx = i;
                    dy = j;
                }
            }
        }

        boolean[][] isVisited = new boolean[n][m];
        // . (floor), # (wall), A (start), or B (end)
        isVisited[x][y] = true;
        char[][] parent = new char[n][m];
        parent[x][y] = 'S';
        parent[x][y] = 'S';
        bfs(mat, x, y, isVisited, parent);

        if(!isVisited[dx][dy]){
            System.out.println("NO");
            sc.close();
            return;
        }

        ArrayList<Character> path = new ArrayList<>();
        while (parent[dx][dy] != 'S') {
            char c = parent[dx][dy];
            path.add(c);

            // as we have to print path with respect to starting point, not w.r.t destination
            if (c == 'U') dx++; // opposite of U -> D
            else if (c == 'D') dx--; // opposite of D -> U
            else if (c == 'L') dy++; // opposite of L -> R
            else if (c == 'R') dy--; // opposite of R -> L
        }
        System.out.println("YES");
        System.out.println(path.size());
        while(!path.isEmpty()){
            System.out.print(path.remove(path.size()-1));
        }

        sc.close();
    }
}
