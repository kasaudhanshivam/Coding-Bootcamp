public class DSU {
    static int[] parent;
    static int[] size;
    public static void intit(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public static int findParent(int x){
        if(parent[x]==x){
            return x;
        }
        return findParent(parent[x]);
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);

        if(size[parentA]>=size[parentB]){
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }else if(size[parentA]<size[parentB]){
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }
        // else{
        //     parent[a] = parentB;
        //     size[parentB] += size[parentA];
        // }
    }
    public static void main(String[] args) {
        int n = 7;
        intit(n);

        union(1, 2);
        union(2, 3);
        union(4, 5);
        union(0, 6);
        union(0, 1);
        union(6, 4);


        System.out.println("Parent : ");
        for(int i=0; i<n; i++){
            System.out.print(parent[i] + " ");
        }

        System.out.println();

        System.out.println("Size : ");
        for(int i=0; i<n; i++){
            System.out.print(size[i] + " ");
        }
    }
}
