import java.util.*;

public class FoxAnd2Dots{
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static boolean bfs(char[][] graph, int i, int j, int[][][] parent, boolean[][] isVisited, char ch, Queue<int[]> q){
        isVisited[i][j] = true;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int[] d : dir){
                int x = d[0] + curr[0];
                int y = d[1] + curr[1];
                if(x>=0 && x<graph.length && y>=0 && y<graph[0].length && graph[x][y]==ch){
                    if(!isVisited[x][y]){
                        isVisited[x][y] = true;
                        parent[x][y][0] = curr[0];
                        parent[x][y][1] = curr[1];
                        q.offer(new int[]{x, y});
                    }else if(isVisited[x][y] && parent[curr[0]][curr[1]][0] != x || parent[curr[0]][curr[1]][1] != y){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] graph = new char[n][m];
        for(int i=0; i<n; i++){
            String s = sc.next();
            for(int j=0; j<m; j++){
                graph[i][j] = s.charAt(j);
            }
        }

        boolean[][] isVisited = new boolean[n][m];
        int[][][] parent = new int[n][m][2]; // parent for (x, y) => i, j
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                Arrays.fill(parent[i][j], -1);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!isVisited[i][j]){
                    q.offer(new int[]{i, j});
                    if(bfs(graph, i, j, parent, isVisited, graph[i][j], q)){
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        System.out.println("No");
    }
}