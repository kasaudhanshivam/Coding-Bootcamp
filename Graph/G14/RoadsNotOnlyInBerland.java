import java.util.*;
public class RoadsNotOnlyInBerland{

    static int[] parent;
    static int[] size;
    public static void init(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    public static boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA==parentB) return false;

        if(size[parentA]>=size[parentB]){
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }else{
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        init(n+1);

        // 1. Collect edges that are redundant
        ArrayList<int[]> oldR = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(!union(u, v)){
                oldR.add(new int[]{u, v});
            }
        }

        // No redundant edges
        if(oldR.isEmpty()){
            System.out.println(0);
            sc.close();
            return;
        }

        // 2. Now collect connection to be made, to connect all cities with every other cities
        ArrayList<int[]> newR = new ArrayList<>();
        int prev = 0;
        for(int i=1; i<n+1; i++){
            if(parent[i]==i){
                if(prev!=0){
                    newR.add(new int[]{prev, i});
                    prev = i;
                }else{
                    prev = i;
                }
            }
        }

        System.out.println(newR.size()); // total days
        for(int o=0; o<Math.min(oldR.size(), newR.size()); o++){

            // old roads
            int i = oldR.get(o)[0];
            int j = oldR.get(o)[1];

            // new roads
            int u = newR.get(o)[0];
            int v = newR.get(o)[1];

            System.out.println(i + " " + j + " " + u + " " + v);
        }

        sc.close();
    }
}