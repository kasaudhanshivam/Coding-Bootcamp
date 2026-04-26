import java.util.ArrayList;
import java.util.List;

class Assignment2 {

    // 17. Letter Combinations of a Phone Number

    static String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static void recursion(String digits, int i, String ans, List<String> result){
        if(i>digits.length()-1){
            result.add(ans);
            return;
        }

        int number = digits.charAt(i) - '0';
        String mappedValue = mapping[number];

        for(int k=0; k<mappedValue.length(); k++){
            String temp = ans; // save the state for curr ans
            recursion(digits, i+1, ans + mappedValue.charAt(k), result);
            ans = temp; // backtrack => restore the state
        }
    }
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        recursion(digits, 0, "", result);
        return result;
    }

    public static void main(String[] args) {
        String d = "23";
        System.out.println(letterCombinations(d));
    }
}