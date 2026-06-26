import java.io.*;
import java.util.*;

public class RPLA {
    public static int topo(List<Integer>[] graph, HashMap<Integer, List<Integer>> rank, int[] inDeg){
        int n = inDeg.length;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            if(inDeg[i]==0) {
                q.offer(i);
            }
        }

        int rnk = 1;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = rank.getOrDefault(rnk, new ArrayList<>());
            for(int i=0; i<size; i++){
                int u = q.poll();
                list.add(u);
                
                for(int v : graph[u]){
                    inDeg[v]--;
                    if(inDeg[v]==0){
                        q.offer(v);
                    }
                }
            }
            rank.put(rnk, list);
            rnk++;
        }
        return rnk-1;
    }
    
    public static void main(String[] args) throws IOException {
        
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        
        in.nextToken();
        int t = (int) in.nval;

        StringBuilder sb = new StringBuilder();

        for(int o=0; o<t; o++){

            in.nextToken();
            int n = (int) in.nval;
            
            in.nextToken();
            int m = (int) in.nval;

            @SuppressWarnings("unchecked")
            List<Integer>[] graph = new ArrayList[n];
            for(int i=0; i<n; i++){
                graph[i] = new ArrayList<>();
            }
            int[] inDeg = new int[n];
            
            for(int i=0; i<m; i++){
                in.nextToken();
                int v = (int) in.nval;
                
                in.nextToken();
                int u = (int) in.nval;

                graph[u].add(v);
                inDeg[v]++;
            }

            HashMap<Integer, List<Integer>> rank = new HashMap<>();
            int maxRank = topo(graph, rank, inDeg);
            
            sb.append("Scenario #").append(o+1).append(":\n");

            for(int i=1; i<=maxRank; i++){
                List<Integer> list = rank.get(i);
                Collections.sort(list);
                for(int node : list){
                    sb.append(i).append(" ").append(node).append("\n");
                }
            }
        }
        // Print everything once at the end
        System.out.print(sb.toString());
    }
}