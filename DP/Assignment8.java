import java.util.Arrays;

class Assignment8 {



    // 1. Longest Common Substring (GFG)
    static int maxLen;
    public static int recursion(String s1, String s2, int n, int m, int[][] dp){
        // base case : either of string length becomes 0 => return 0 as no subtring possible
        if(n==0 || m==0){
            return 0;
        }
        
        if(dp[n][m]!=-1){ // check if this case is already evaluated?
            return dp[n][m]; // directly return it, if yes
        }
        
        if(s1.charAt(n-1)==s2.charAt(m-1)){ // if chars match increase the substring size by 1
            dp[n][m] = 1 + recursion(s1, s2, n-1, m-1, dp);
            maxLen = Math.max(dp[n][m], maxLen); // take max length of substring till now
        }else{
            dp[n][m] = 0; // if chars doesn't match reset the subtring length to 0 and cpmoute further
        }
        
        // explore other possibilities
        recursion(s1, s2, n - 1, m, dp);
        recursion(s1, s2, n, m - 1, dp);
        
        return dp[n][m];
    }
    
    public static int longestCommonSubstr(String s1, String s2) {
        maxLen = 0;
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n+1][m+1];
        
        
        
        // DP Memoization Approach
        for(int i=0; i<n+1; i++){
            Arrays.fill(dp[i], -1);
        }
        recursion(s1, s2, n, m, dp);
        return maxLen;
        
        
        // DP Tabulation Approach
        
        // // Base case : if either of string's length is 0 => no substring possible 
        // for(int i=0; i<n+1; i++){
        //     dp[i][0] = 0;
        // }
        // for(int j=0; j<m+1; j++){
        //     dp[0][j] = 0;
        // }
        
        // for(int i=1; i<n+1; i++){
        //     for(int j=1; j<m+1; j++){
        //         if(s1.charAt(i-1)==s2.charAt(j-1)){ // if chars match increase the substring size by 1
        //             dp[i][j] = 1 + dp[i-1][j-1];
        //             maxLen = Math.max(maxLen, dp[i][j]); // take max length of substring till now
        //         }else{
        //             // if chars doesn't match reset the subtring length to 0
        //             dp[i][j] = 0;
        //         }
        //     }
        // }
        // return maxLen;
        
    }






    // 7. Wildcard Matching (LeetCode 44)
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // If pattern is empty -> no non-empty string can match it
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }

        // Initialize the first row → this represents cases where the string 's' is empty (i = 0)
        for (int j = 1; j <= m; j++) {
            // If the current pattern character is '*'
            // WHY → because '*' can match zero or more characters.
            // So even if the string is empty, '*' can represent "zero characters".
            // Hence, we carry forward the previous result dp[0][j-1]
            // (i.e., if the pattern up to j-1 could match an empty string, 
            // then including another '*' will also match empty string).
            if (p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 1];
            }
            // Otherwise (if the pattern character is not '*'),
            // WHY → because characters like 'a', 'b', or '?' need at least one character to match.
            // An empty string has no characters to match with, so dp[0][j] = false.
            else{
                dp[0][j] = false;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                char ch1 = s.charAt(i - 1); // current character from string 's'
                char ch2 = p.charAt(j - 1); // current character from pattern 'p'
                if (ch2 == '?' || ch1 == ch2) {
                    // CASE 1: If pattern character is '?' OR both characters are the same
                    // WHY → '?' can match exactly one character of any type,
                    // or if both characters are identical, it's a direct match.
                    // Therefore, we look diagonally back (i-1, j-1),
                    // meaning: if the previous substring and pattern matched, 
                    // then these current characters also match.
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ch2 == '*') {
                    // CASE 2: If pattern character is '*'
                    // WHY → '*' can match:
                    //   (a) Zero characters → ignore '*' → dp[i][j-1]
                    //   (b) One or more characters → let '*' absorb one char from 's' → dp[i-1][j]
                    // Hence, we take OR of both possibilities.
                    boolean option1 = dp[i - 1][j];
                    boolean option2 = dp[i][j - 1];
                    dp[i][j] = option1 || option2;
                } else {
                    // CASE 3: If characters don't match and it's not '?' or '*'
                    // WHY → direct mismatch, so this cell is false.
                    dp[i][j] = false;
                }
            }
        }
        // Final answer → whether full string 's' matches full pattern 'p'
        return dp[n][m];
    }




    public static void main(String[] args) {
        String s1 = "ABCDGH", s2 = "ACDGHR";
        System.out.println(longestCommonSubstr(s1, s2));


        String s = "aa", p = "*";
        System.out.println(isMatch(s, p));
    }
}