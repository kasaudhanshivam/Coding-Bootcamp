import java.io.*;
import java.util.*;

public class StringColoring {
    public static boolean isBipartite(List<Integer>[] graph, int i, int[] color){
        for(int ngbr : graph[i]){
            if(color[ngbr]==-1){
                if(color[i]==0) color[ngbr] = 1;
                else color[ngbr] = 0;
                if(!isBipartite(graph, ngbr, color)) return false;
            }else if(color[ngbr]==color[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] original = s.toCharArray();
        // char[] sorted = s.toCharArray();
        // Arrays.sort(sorted);

        // HashMap<Character, List<Integer>> map = new HashMap<>();
        // for(int i=0; i<n; i++){
        //     List<Integer> list = map.getOrDefault(original[i], new ArrayList<>());
        //     list.add(i);
        //     map.put(original[i], list);
        // }
        
        @SuppressWarnings("unchecked")
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        // boolean[] vis = new boolean[n];
        // for(int i=0; i<n; i++){
        //     if(original[i]==sorted[i]){
        //         vis[i] = true;
        //     }else{
        //         List<Integer> list = map.get(sorted[i]);
        //         int idx = list.get(0);
        //         int k = 0;
        //         while(k<list.size() && vis[idx]){
        //             idx = list.get(k);
        //             k++;
        //         }

        //         int st = Math.min(i, idx);
        //         int end = Math.max(i, idx);

        //         for(int j=st; j<=end; j++){
        //             // changed
        //             // if(j < idx && original[j] > original[idx]){
        //                 graph[j].add(idx);
        //                 graph[idx].add(j);
        //             // } else if(j > idx && original[idx] > original[j]){
        //             //     graph[j].add(idx);
        //             //     graph[idx].add(j);
        //             // }
        //         }
        //         vis[idx] = true;
        //     }
        // }


        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(original[i]>original[j]) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        // for(int i=0; i<n; i++){
        //     System.out.println(graph[i]);
        // }

        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i=0; i<n; i++){
            if(color[i]==-1){
                color[i] = 0;
                if(!isBipartite(graph, i, color)){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
        StringBuilder sb = new StringBuilder();
        for(int c : color){
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
