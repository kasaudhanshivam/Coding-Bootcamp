import java.util.*;
// CodeForces
public class Party {
    public static int bfs(ArrayList<Integer>[] graph, int i, boolean[] isVisited){
        Queue<Integer> q = new LinkedList<>();
        int level = 0;
        q.offer(i);
        isVisited[i] = true;
        q.offer(-1); // level marker
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr==-1){
                level++;
                if(!q.isEmpty() && q.peek()!=-1) q.offer(-1); // next level marker
            }else{
                for(int ngbr : graph[curr]){
                    if(!isVisited[ngbr]){
                        isVisited[ngbr] = true;
                        q.offer(ngbr);
                    }
                }
            }
        }
        return level;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inDeg = new int[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1; i<n+1; i++){
            int u = sc.nextInt();
            if(u==-1) continue;
            int v = i;
            graph[u].add(v);
            inDeg[v]++;
        }

        int maxLevel = Integer.MIN_VALUE;
        boolean[] isVisited = new boolean[n+1];
        for(int i=1; i<n+1; i++){
            if(inDeg[i]==0 && !isVisited[i]){
                int level = bfs(graph, i, isVisited);
                maxLevel = Math.max(maxLevel, level);
            }
        }

        System.out.println(maxLevel);

    }
}
