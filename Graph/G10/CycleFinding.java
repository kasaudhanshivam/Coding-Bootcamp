import java.util.*;

public class CycleFinding{

    public static void bellman(long[][] edges, long V, long[] dist, long[] parent){
        for(int i=0; i<V-1; i++){
            for(long[] e : edges){
                long u = e[0];
                long v = e[1];
                long w = e[2];
                if(dist[(int)u]+w<dist[(int)v]){
                    // relax the edges
                    dist[(int)v] = dist[(int)u] + w;
                    parent[(int)v] = u; // also store the parent of each node
                }
            }
        }
    }

    public static void printCycle(long[] parent, long u){
        System.out.println("YES");
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] isVisited = new boolean[parent.length];
        
        // parent arr ka use krke peeche jate jao jab tak cycle wala node na mil jaye
        int curr = (int)u;
        while(parent[curr]!=-1 && !isVisited[curr]){
            isVisited[curr] = true;
            list.add(curr);
            curr = (int)parent[curr];
        }

        int start = curr; // this is the node that forms cycle

        // ab aagain start from the node where we got the cycle
        // and form the cycle
        System.out.print(start + " ");
        if(!list.isEmpty()){
            curr = list.remove(list.size()-1);
            while(curr!=start){
                System.out.print(curr + " ");
                curr = list.remove(list.size()-1);
            }
        }
        System.out.print(curr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        long[][] edges = new long[E][3];
        for(int i=0; i<E; i++){
            edges[i][0] = sc.nextLong();
            edges[i][1] = sc.nextLong();
            edges[i][2] = sc.nextLong();
        }

        long[] dist = new long[V+1];
        Arrays.fill(dist, 0);
        dist[1] = 0;

        long[] parent = new long[V+1];
        Arrays.fill(parent, -1);
        bellman(edges, V, dist, parent);




        // -ive wt cycle detection
        for(long[] e : edges){
            long u = e[0];
            long v = e[1];
            long w = e[2];
            if(dist[(int)u]+w < dist[(int)v]){
                // cycle exits
                printCycle(parent, v);
                return;
            }
        }


        System.out.println("NO");

    }
}