import java.io.*;
import java.util.*;

public class SalaryQueries {
    static long[] sTree;
    static long[] nums;
    static long[] freq;
    static HashMap<Long, Integer> hashed;
    public static void build(int i, int l, int r){
        if(l==r){
            sTree[i] = freq[l];
            return;
        }

        int mid = l+(r-l)/2;
        build(2*i+1, l, mid);
        build(2*i+2, mid+1, r);
        sTree[i] = sTree[2*i+1] + sTree[2*i+2];
    }
    public static void update(int i, int l, int r, int idx, int val){
        if(l==r){
            sTree[i] += val;
            return;
        }

        int mid = l+(r-l)/2;
        if(idx<=mid){
            update(2*i+1, l, mid, idx, val);
        }else{
            update(2*i+2, mid+1, r, idx, val);
        }
        sTree[i] = sTree[2*i+1] + sTree[2*i+2];
    }
    public static long query(int i, int L, int R, int l, int r){
        if(l<=L && R<=r) return sTree[i]; // complete overlapping
        if(r<L || R<l) return 0; // no overlapping

        int mid = L+(R-L)/2;
        long left = query(2*i+1, L, mid, l, r);
        long right = query(2*i+2, mid+1, R, l, r);
        return left + right;
    }
    public static void hashing(long[][] queries){
        HashSet<Long> set = new HashSet<>();
        ArrayList<Long> list = new ArrayList<>();
        for(long num : nums){
            if(!set.contains(num)){
                set.add(num);
                list.add(num);
            }
        }
        for(long[] q : queries){
            long a = q[1];
            long b = q[2];
            if(!set.contains(a)){
                set.add(a);
                list.add(a);
            }
            if(!set.contains(b)){
                set.add(b);
                list.add(b);
            }
        }
        Collections.sort(list);
        int i=0;
        for(long num : list){
            hashed.put(num, i);
            i++;
        }
        freq = new long[hashed.size()];
        for(long num : nums){
            freq[hashed.get(num)] += 1;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        nums = new long[n];
        st = new StringTokenizer(br.readLine());
        
        
        for(int i=0; i<n; i++){
            nums[i] = Long.parseLong(st.nextToken());
        }
        
        long[][] queries = new long[q][3];
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            if(st.nextToken().charAt(0)=='?'){ // ? ---> 0
                queries[i][0] = 0;
            }else{ // ! ----> 1
                queries[i][0] = 1;
            }
            queries[i][1] = Long.parseLong(st.nextToken());
            queries[i][2] = Long.parseLong(st.nextToken());
        }

        hashed = new HashMap<>();
        hashing(queries);
        
        // build the segment tree
        sTree = new long[4*freq.length];
        build(0, 0, freq.length-1);

        StringBuilder sb = new StringBuilder();
        for(long[] qu : queries){
            // process queries
            if(qu[0]==1){ // update query
                int idx = (int)qu[1];
                int newVal = hashed.get(qu[2]);
                int oldVal = hashed.get(nums[idx-1]); // as 1-based
                nums[idx-1] = qu[2];
                update(0, 0, freq.length-1, oldVal, -1);
                update(0, 0, freq.length-1, newVal, +1);
            }else{ // range query
                int a = hashed.get(qu[1]);
                int b = hashed.get(qu[2]);
                long res = query(0, 0, freq.length-1, a, b);
                sb.append(res);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}