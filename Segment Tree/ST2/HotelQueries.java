import java.io.*;
import java.util.StringTokenizer;

public class HotelQueries {
    static int[] sTree;
    static int[] nums;
    public static void build(int i, int l, int r){
        if(l==r){
            sTree[i] = nums[l];
            return;
        }

        int mid = l+(r-l)/2;
        build(2*i+1, l, mid);
        build(2*i+2, mid+1, r);
        sTree[i] = Math.max(sTree[2*i+1], sTree[2*i+2]);
    }
    public static int query(int i, int l, int r, int needed){
        if(sTree[i]<needed) return Integer.MAX_VALUE;

        if(l==r){
            if(needed<=sTree[i]){
                sTree[i] -= needed;
                return l;
            }
        }

        int mid = l+(r-l)/2;
        int left = query(2*i+1, l, mid, needed);
        if(left!=Integer.MAX_VALUE){
            sTree[i] = Math.max(sTree[2*i+1], sTree[2*i+2]);
            return left;
        }
        int right = query(2*i+2, mid+1, r, needed);
        sTree[i] = Math.max(sTree[2*i+1], sTree[2*i+2]);
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        sTree = new int[4*n];

        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        build(0, 0, n-1);
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            int needed = Integer.parseInt(st.nextToken());
            int res = query(0, 0, n-1, needed);
            if(res==Integer.MAX_VALUE){
                res = -1;
            }
            sb.append(res+1);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}