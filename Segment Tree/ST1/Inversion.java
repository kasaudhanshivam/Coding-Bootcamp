import java.util.*;

public class Inversion {
    static int[] st;
    public static void build(int i, int l, int r){
        if(l==r){
            st[i] = 0;
            return;
        }

        int mid = l+(r-l)/2;
        build(2*i+1, l, mid);
        build(2*i+2, mid+1, r);
        st[i] = st[2*i+1] + st[2*i+2];
    }
    public static void update(int i, int l, int r, int idx, int val){
        if(l==r){
            st[i] += val;
            return;
        }
        
        int mid = l+(r-l)/2;
        if(idx<=mid){
            update(2*i+1, l, mid, idx, val);
        }else{
            update(2*i+2, mid+1, r, idx, val);
        }
        st[i] = st[2*i+1] + st[2*i+2];
    }
    public static int query(int i, int L, int R, int ql, int qr){
        if(qr<L || R<ql) return 0;
        if(ql<=L && R<=qr){ // complete overlapping
            return st[i];
        }
        int mid = L+(R-L)/2;
        int left = query(2*i+1, L, mid, ql, qr);
        int right = query(2*i+2, mid+1, R, ql, qr);
        return left + right;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        st = new int[4*(n+1)];
        // build(0, 0, n);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int num = sc.nextInt();
            int res = query(0, 0, n, num+1, n);
            sb.append(res).append(" ");
            update(0, 0, n, num, 1);
        }
        System.out.println(sb.toString());
    }
}