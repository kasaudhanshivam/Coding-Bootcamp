import java.util.*;

// CodeForces - 638B Making Genome in Berland
public class CF638B {
    static String ans;
    static HashSet<Character> set;
    public static void bfs(HashMap<Character, ArrayList<Character>> graph, HashMap<Character, Integer> inDeg, char ch){
        // Kahn's Algorithm
        Queue<Character> q = new LinkedList<>();
        q.offer(ch);
        while(!q.isEmpty()){
            char curr = q.poll();
            if(!set.contains(curr)){
                ans = ans + curr;
                set.add(curr);
            }
            for(char ngbr : graph.get(curr)){
                inDeg.put(ngbr, inDeg.get(ngbr)-1);
                if(inDeg.get(ngbr)==0) q.offer(ngbr);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<Character, ArrayList<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> inDeg = new HashMap<>();

        int n = sc.nextInt();
        String[] words = new String[n];
        for(int i=0; i<n; i++){
            words[i] = sc.next();
        }

        for(String word : words){
            for(char ch : word.toCharArray()){
                graph.put(ch, new ArrayList<>());
                inDeg.put(ch, 0);
            }
        }

        // each char will be a node
        for(String word : words){ // word = bcd
            for(int i=0; i<word.length()-1; i++){
                char c1 = word.charAt(i); // b --> c --> d
                char c2 = word.charAt(i+1);
                ArrayList<Character> list = graph.get(c1);
                list.add(c2);
                inDeg.put(c2, inDeg.get(c2)+1); // + 1 indegree
                graph.put(c1, list);
            }
        }

        ans = ""; // topo order for each component indiviually
        set = new HashSet<>(); // to avoid repeating chars
        for(char ch : graph.keySet()){
            if(inDeg.get(ch)==0){
                bfs(graph, inDeg, ch);
            }
        }

        System.out.println(ans);

    }
}
