import java.util.*;

// CodeForces - 639B Bear And Forgotten Tree 3
public class BearAndForgottenTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int d = sc.nextInt();
        int h = sc.nextInt();
        int ht = h; // temp in case h will be modified further

        int reqD = d-h; // diameter me se height minus krdo and firr agr wo height se bhi jada ho to tree not possible wrna height aur badh jayegi given se
        if(reqD>h || d<h || (d==1 && h==1 && n>2)){ // kabhi bhi height bada nhi ho skta diameter se
            // agar h==1 ho aur d==1 ho aur nodes 2 se jada ho to tree possible nhi h qki h==1 aur d==1 k liye sirf 2 nodes hi hone chahiye
            // cases where tree is not possible
            System.out.println(-1);
            return;
        }

        ArrayList<int[]> edges = new ArrayList<>();
        boolean[] isUsed = new boolean[n+1]; // to track nodes which are used to make graph
        
        // create height
        int i = 1; // root
        isUsed[1] = true; // mark it as used
        while(h!=0){ // pehle tree ki height bna rhe
            edges.add(new int[]{i, i+1}); // 1 --> 2, 2--> 3, ......so on.
            isUsed[i+1] = true; // mark them as used
            i++;
            h--;
        }
        i++;
        
        // create diameter
        int j = 1; // root
        while(reqD!=0){ // diameter bna rhe h ab
            edges.add(new int[]{j, i});
            isUsed[i] = true; // mark it as used
            j = i; // connect other with next node
            i++;
            reqD--;
        }
        

        // connect remaining nodes in the tree
        if(d==ht){ // edge cases, we can't increase the height so we will connect remaining nodes with 2th node
            for(int a=1; a<n+1; a++){
                if(!isUsed[a]){
                    edges.add(new int[]{a, 2});
                }
            }
        }else{ // other wise simply connect all the remaining nodes with 2st
            for(int a=1; a<n+1; a++){
                if(!isUsed[a]){
                    edges.add(new int[]{a, 1});
                }
            }
        }


        for(int[] e : edges){
            System.out.println(e[0] + " " + e[1]);
        }
    }
}
