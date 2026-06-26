import java.io.*;
import java.util.*;

public class LittleAlawnPuzzle {
    static int[] parent;
    static int[] size;
    public static void init(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }
    }
    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    public static void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA==parentB) return;

        if(size[parentA]>=size[parentB]){
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }else{
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int o=0; o<t; o++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            int[][] board = new int[2][n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                board[0][i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                board[1][i] = Integer.parseInt(st.nextToken());
            }

            init(n+1);
            for(int i=0; i<n; i++){
                int u = board[0][i];
                int v = board[1][i];
                union(u, v);
            }

            int count = 0;
            for(int i=1; i<n+1; i++){
                if(parent[i]==i) count++;
            }
            // System.out.println(count);

            long ans = 1;
            for(int i=0; i<count; i++){
                ans = (ans * 2) % (1000000000+7);
            }
            System.out.println(ans);

        }
        
    }
}
