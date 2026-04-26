class Lect8{

    // Print LCS 
    public static String recursion(String s1, String s2, int n, int m, String[][] dp){
        if(n==0 || m==0){
            return "";
        }
        
        if(dp[n][m]!=null) return dp[n][m];
        
        String ans;
        if(s1.charAt(n-1)!=s2.charAt(m-1)){
            // remove from s1
            String a = recursion(s1, s2, n-1, m, dp);
            // remove from s2
            String b = recursion(s1, s2, n, m-1, dp);
            ans = a.length()>b.length()? a : b;
        }else{
            ans = recursion(s1, s2, n-1, m-1, dp) + s1.charAt(n-1);
        }
        return dp[n][m] = ans;
    }
    public static String longestCommonSubsequence(String text1, String text2) {
        String[][] dp = new String[text1.length()+1][text2.length()+1];
        // return recursion(text1, text2, text1.length(), text2.length(), dp);

        



        // tabulation - 

        int n = text1.length();
        int m = text2.length();

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                dp[i][j] = "";
            }
        }


        
        // // base initialization
        for(int i=0; i<=n; i++){
            dp[i][0] = "";
        }
        for(int j=0; j<=m; j++){
            dp[0][j] = "";
        }
        
        
        // // transition
        for(int i=n-1; i>=0; i--){
            for(int j=m-1; j>=0; j--){
                char ch1 = text1.charAt(i);
                char ch2 = text2.charAt(j);
                String ans;
                if(ch1!=ch2){
                    // remove from s1
                    String a = dp[i+1][j];
                    // remove from s2
                    String b = dp[i][j+1];
                    ans = a.length()>b.length()? a : b;
                }else{
                    ans = ch1 + dp[i+1][j+1];
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        String s1 = "AZT";
        String s2 = "AXZY";
        System.out.println(longestCommonSubsequence(s1, s2));
    }
}