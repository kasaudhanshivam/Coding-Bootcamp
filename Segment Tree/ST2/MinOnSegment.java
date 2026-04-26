import java.util.*;
import java.io.*;
public class MinOnSegment{
    static int[] nums;
    static int[][] sTree;
    public static void build(int i, int l, int r){
        if(l==r){
            sTree[i][0] = nums[l];
            sTree[i][1] = 1;
            return;
        }
        int mid = l + (r-l)/2;
        build(2*i+1, l, mid);
        build(2*i+2, mid+1, r);
        int left = sTree[2*i+1][0];
        int right = sTree[2*i+2][0];
        if(left==right){
            sTree[i][0] = left;
            sTree[i][1] = sTree[2*i+1][1] + sTree[2*i+2][1];
        }else if(left < right){
            sTree[i][0] = left;
            sTree[i][1] = sTree[2*i+1][1];
        }else{
            sTree[i][0] = right;
            sTree[i][1] = sTree[2*i+2][1];
        }
    }
    public static void update(int idx, int val, int i, int l, int r){
        if(l==r){
            sTree[i][0] = val;
            sTree[i][1] = 1;
            return;
        }
        int mid = l+(r-l)/2;
        if(idx<=mid){
            update(idx, val, 2*i+1, l, mid);
        }else{
            update(idx, val, 2*i+2, mid+1, r);
        }
        int left = sTree[2*i+1][0];
        int right = sTree[2*i+2][0];
        if(left==right){
            sTree[i][0] = left;
            sTree[i][1] = sTree[2*i+1][1] + sTree[2*i+2][1];
        }else if(left < right){
            sTree[i][0] = left;
            sTree[i][1] = sTree[2*i+1][1];
        }else{
            sTree[i][0] = right;
            sTree[i][1] = sTree[2*i+2][1];
        }
    }
    public static int[] query(int i, int ql, int qr, int L, int R){
        if(ql<=L && R<=qr) return sTree[i];
        if(qr<L || R<ql) return new int[]{Integer.MAX_VALUE, 0};
        int mid = L+(R-L)/2;
        int[] left = query(2*i+1, ql, qr, L, mid);
        int[] right = query(2*i+2, ql, qr, mid+1, R);

        int min = Integer.MAX_VALUE;
        int count = 0;
        if(left[0]==right[0]){
            min = Math.min(min, left[0]);
            count += left[1] + right[1];
        }else if(left[0] < right[0]){
            min = Math.min(min, left[0]);
            count += left[1];
        }else{
            min = Math.min(min, right[0]);
            count += right[1];
        }
        return new int[]{min, count};
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        sTree = new int[4*n][2];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        build(0, 0, n-1);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type==1){
                int idx = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                update(idx, val, 0, 0, n-1);
            }else{
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int[] res = query(0, l, r-1, 0, n-1);
                sb.append(res[0]).append(" ").append(res[1]);
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}