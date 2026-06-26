import java.util.*;
import java.io.*;

public class LearningLanguages{
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

        int n = Integer.parseInt(st.nextToken()); // employees
        int m = Integer.parseInt(st.nextToken()); // languages

        boolean flag = true;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            for(int j=0; j<k; j++){
                flag = false;
                int id = Integer.parseInt(st.nextToken());

                List<Integer> list = map.getOrDefault(id, new ArrayList<>());
                list.add(i);
                map.put(id, list);
            }
        }


        init(n); // DSU
        for(int key : map.keySet()){
            List<Integer> list = map.get(key);
            for(int i=0; i<list.size()-1; i++){
                int u = list.get(i);
                int v = list.get(i+1);

                union(u, v);
            }
        }

        int count = 0;
        for(int i=0; i<n; i++){
            if(parent[i]==i){
                count++;
            }
        }
        if(flag){
            System.out.println(count);
            return;
        }
        System.out.println(count-1);

    }
}