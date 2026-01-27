import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Assignment3{








    // Question - 4 Leetcode - Permutation II

    public static void recursion(List<Integer> list, List<List<Integer>> result, int i){
        if(i==list.size()){ // digits completed 
            result.add(new ArrayList<>(list)); // add this ans to result
            return;
        }
        List<Integer> temp = new ArrayList<>(list); // save the curr state

        // store the used number in set to avoid duplicates
        HashSet<Integer> used = new HashSet<>();

        int a = temp.get(i); // fixed
        for(int j=i; j<list.size(); j++){
            int b = temp.get(j); // number to swap with position i

            if(used.contains(b)) continue; // if this number is already used => do nothing

            used.add(b); // add this as used

            // swap 
            int c = a;
            list.set(i, b);
            list.set(j, c);

            recursion(list, result, i+1); // call for next 

            list = new ArrayList<>(temp); // restore the state (Backtrack)
        }
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }
        recursion(list, result, 0);
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 4};
        System.out.println(permuteUnique(nums));
    }
}