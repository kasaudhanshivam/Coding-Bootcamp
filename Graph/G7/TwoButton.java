import java.util.Scanner;

// CodeForces - 520 B
public class TwoButton {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        // Strong Observation based problem
        // reversed thinking, try to get m --> n
        int clicks = 0;
        while(n<m){
            if(m%2==0){ // m = even
                m = m/2;
            }else{ // m = odd
                m = m+1;
            }
            clicks++;
        }
        clicks = clicks+(n-m);
        System.out.println(clicks);
    }
}
