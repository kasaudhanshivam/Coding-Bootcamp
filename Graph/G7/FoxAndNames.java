import java.util.*;

// CodeForces (Exactly similar to Alien Dictionary-GFG)
public class FoxAndNames {
    static String ans;
    public static boolean dfs(HashMap<Character, ArrayList<Character>> graph, char curr, HashMap<Character, Boolean> isVisited, HashMap<Character, Boolean> inPath){
        isVisited.put(curr, true);
        inPath.put(curr, true);

        for(char ngbr : graph.get(curr)){
            if(!isVisited.get(ngbr)){
                if(dfs(graph, ngbr, isVisited, inPath)) return true;
            }else if(inPath.get(ngbr)){
                return true; // cycle present
            }
        }

        inPath.put(curr, false); // backtrack
        ans = curr + ans; // topo order
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n =  sc.nextInt();
        String[] words = new String[n];
        for(int i=0; i<n; i++){
            words[i] = sc.next();
        }

        HashMap<Character, ArrayList<Character>> graph = new HashMap<>();
        HashMap<Character, Boolean> inPath = new HashMap<>();
        HashMap<Character, Boolean> isVisited = new HashMap<>();
        for(char ch='a'; ch<='z'; ch++){ // question says the order contains all alphabet
            graph.put(ch, new ArrayList<>());
            inPath.put(ch, false);
            isVisited.put(ch, false);
        }
        for(int i=0; i<n-1; i++){
            String w1 = words[i];
            String w2 = words[i+1];
            boolean edgeFound = false;
            for(int j=0; j<Math.min(w1.length(), w2.length()); j++){
                if(w1.charAt(j)!=w2.charAt(j)){
                    // edge found, first unmatched
                    ArrayList<Character> list = graph.get(w1.charAt(j));
                    list.add(w2.charAt(j)); // edge : c1 --> c2
                    graph.put(w1.charAt(j), list);
                    edgeFound = true;
                    break;
                }
            }
            if(!edgeFound && w1.length()>w2.length()){ //invalid case
                System.out.println("Impossible");
                return;
            }
        }

        ans = "";
        for(char ch : graph.keySet()){
            if(!isVisited.get(ch)){
                if(dfs(graph, ch, isVisited, inPath)){ // of topo not possible, cycle present
                    System.out.println("Impossible");
                    return;
                }
            }
        }
        System.out.println(ans);
    }
}
