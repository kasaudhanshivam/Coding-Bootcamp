import java.io.*;
import java.util.*;

public class FragileBridges{
    public static int solve(int[] nums, int i){
        int res = 0;

        // left
        if(i>=0 && i<nums.length && nums[i]>0){
            nums[i]--;
            int a = 1 + solve(nums, i+1);
            res = Math.max(a, res);
            nums[i]++;
        }

        // right
        if(i+1>=0 && i+1<nums.length && nums[i+1]>0){
            nums[i+1]--;
            int b = 1 + solve(nums, i-1);
            res = Math.max(b, res);
            nums[i+1]++;
        }

        return res;
    }
    public static int solve2(List<int[]>[] graph, int i){
        int res = 0;

        for(int j=0; j<graph[i].size(); j++){
            int v = graph[i].get(j)[0];
            int w = graph[i].get(j)[1];
            if(w>0){
                graph[i].get(j)[1]--;
                int a = 1 + solve2(graph, v);
                graph[i].get(j)[1]++;
                res = Math.max(res, a);
            }
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());

        // int[] nums = new int[n+1];

        @SuppressWarnings("unchecked")
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int u=1; u<n; u++){
            int v = u+1;
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        for(List<int[]> g : graph){
            for(int[] d : g){
                System.out.print(Arrays.toString(d));
            }
            System.out.println();
        }

        // int res = 0;
        // for(int i=1; i<n+1; i++){
        //     int a = solve2(graph, i);
        //     res = Math.max(res, a);
        // }

        // System.out.println(res);

    }
}