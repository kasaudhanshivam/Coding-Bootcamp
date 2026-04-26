public class Anonymous {
    static int[] parent;
    static int[] size;
    public static void init(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=1; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    public static boolean merge(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if(parentA==parentB) return false;

        if(size[parentA]>size[parentB]){
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }else{
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }
        return true;
    }
    public static int[] solveTradeRings(int n, int[] a) {
        boolean[] isCycle = new boolean[n+1];
        init(n+1);
        // int count = 0;
        for(int i=0; i<n; i++){
            int u = i+1;
            int v = a[i];

            if(!merge(u, v)){
                isCycle[find(u)] = true;
                isCycle[find(v)] = true;
                isCycle[u] = true;
                isCycle[v] = true;

                // count++;
            }
        }
        int max = 0;
        for(int i=1; i<n+1; i++){
            if(parent[i]==i) max++;
        }

        int min = max;
        for(int i=1; i<n+1; i++){
          if(parent[i]==i && !isCycle[i] && size[i]<=2){
                min -= 1;
            }
        }

        return new int[]{max, min};
    }

    public static void main(String[] args) {
        int n = 6;
        int[] a = {2, 1, 4, 3, 6, 5};
        int[] res = solveTradeRings(n, a);

        System.out.println(res[0] + " " + res[1]);
    }
}
