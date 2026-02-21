import java.util.*;

// CodeForces
public class TabClosing {
    public static int getMoves(long a, long b, long m){
        if(a==b) return 1;
        if(a>=(b*m)) return 1;
        else return 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            long a = sc.nextInt();
            long b = sc.nextInt();
            long m = sc.nextInt();
            int moves = getMoves(a, b, m);
            System.out.println(moves);
        }
    }
}
