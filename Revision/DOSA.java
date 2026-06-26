import java.io.*;
import java.util.*;

public class DOSA {
    static Integer[] dp;
    public static int solve(int[] nums, int i){
        if(dp[i]!=null) return dp[i];
        int res = 1;
        for(int j=0; j<i; j++){
            if(nums[j]!=0 && nums[i]>=nums[j]){
                int a = 1 + solve(nums, j);
                res = Math.max(res, a);
            }
        }

        return dp[i] = res;
    }
    // public static int solve2(int[] nums){
    //     int count = 0;
    //     int prev = -1;
    //     for(int i=0; i<nums.length; i++){
    //         if(nums[i]<=prev){
    //             count++;
    //             prev++;
    //         }else{
    //             prev = nums[i];
    //         }
    //     }
    //     return count;
    // }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken())-i;
        }


        // Approach-1 : Failed
        // int res = 1;
        // for(int i=0; i<n; i++){
            //     int a = solve(nums, i);
            //     res = Math.max(res, a);
            // }
            // System.out.println(n - res);
            
        // Approach-2 : Failed
        // int res = solve2(nums);
        // System.out.println(res);


        // Approach-3 : 
        // int[] arr = new int[n];
        // for(int i=0; i<n; i++){
        //     arr[i] = nums[i]-i;
        // }

        // int res = 1;
        // dp = new Integer[n];
        // for(int i=0; i<n; i++){
        //     int ans = solve(nums, i);
        //     res = Math.max(res, ans);
        // }
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(list.isEmpty() || (nums[i]!=0 && nums[i]>=list.get(list.size()-1))){
                list.add(nums[i]);
            }else{
                int idx = Collections.binarySearch(list, nums[i]);
                System.out.println(idx);
                if(idx<0) idx = -idx+1;
                System.out.println(idx);
                list.set(idx, nums[i]);
            }
        }
        
        System.out.println(n-list.size());
    }
}