class Lect3{
    // No consecutive ones
    public static void noOnes(int n, int i, String ans, char lastCh){
        if(i==n){
            System.out.println(ans);
            return;
        }

        String temp = ans;
        if(lastCh=='0'){
            char[] a = ans.toCharArray();
            a[i] = '1';
            ans = a.toString();
            noOnes(n, i+1, ans, '1');
        }else{
            // pick 0
            char[] a = ans.toCharArray();
            a[i] = '1';
            ans = a.toString();
            ans = ans + '1';
            noOnes(n, i+1, ans, '1');

            // pick 1
            char[] b = ans.toCharArray();
            b[i] = '0';
            ans = b.toString();
            noOnes(n, i+1, temp, '0');
        }
    }

    // Can be reach to destination
    public static boolean isReachable(int[][] grid, int i, int j){
        // System.out.println(i+ " "+j);
        if(i==grid.length-1 && j==grid[0].length-1){
            // destination reached 
            return true;
        }

        boolean flag1 = false;
        boolean flag2 = false;

        // move right
        if(j+1<grid[0].length && grid[i][j+1]==0){
            flag1 = isReachable(grid, i, j+1);
        }
        // move down
        if(i+1<grid.length && grid[i+1][j]==0){
            flag2 = isReachable(grid, i+1, j);
        }

        return flag1 || flag2;
    }

    public static void main(String[] args) {
        // noOnes(3, 0, "000", '1');

        int[][] grid = {
            {0, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };


        System.out.println(isReachable(grid, 0, 0));
    }
}