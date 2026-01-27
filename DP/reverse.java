public class reverse {
    // public static String reverse(String s, int st, int en){
    //     if(s.length()==1){
    //         return s;
    //     }
    //     char a = s.charAt(st);
    //     // String t = reverse(s.substring(st, en), s++, en);
    //     // return a+t;
    // }
    // public static boolean isPalindrome(String s, int s, int e){
    //     if () {
            
    //     }
    // }

    public static int lucky(int n){
        // brute - O(n)
        // if(n==1){
        //     return 2;
        // }
        // int a = (int)Math.pow(2, n);
        // return a + lucky(n-1);

        // Optimization - O(1)
        return (1<<(n+1)) - 2;
    }

    // public static int TOH(int n, ){

    //     return 1;
    // }

    public static void main(String[] args) {
        int ans = lucky(3);
        System.out.println(ans);
        System.out.println((Math.pow(2, 64)-1)/86400);
    }
}
