import java.util.*;
public class Rough
{
    public static boolean evaluate(String exp, int target){
        Stack<Integer> num = new Stack<>();
        int sign = 1;

        int i=0;
        while(i<exp.length()){
            if(Character.isDigit(exp.charAt(i))){
                String number = "";
                while(i<exp.length() && Character.isDigit(exp.charAt(i))){
                    number += exp.charAt(i);
                    i++;
                }
                num.push(Integer.parseInt(number) * sign);
            }else{ // means operator
                if(exp.charAt(i)=='+'){
                    sign = 1;
                    i++;
                }else if (exp.charAt(i)=='-') {
                    sign = -1;
                    i++;
                }else{
                    String number = "";
                    i++;
                    while(i<exp.length() && Character.isDigit(exp.charAt(i))){
                        number += exp.charAt(i);
                        i++;
                    }
                    int n1 = num.pop();
                    int n2 = Integer.parseInt(number);
                    num.push(n1*n2);
                }
            }
        }

        int res = 0;
        while(!num.isEmpty()){
            res += num.pop();
        }

        return res==target;
    }
	public static void main(String[] args) {
		System.out.println(evaluate("2*3*4+5", 29));
	}
}