import java.util.*;

public class Lect2 {

    // 1. Sort an arraylist using recursion
    public static void insert(ArrayList<Integer> list, int el, int idx){
        if(list.get(idx)>=el || idx==list.size()){
            list.add(idx, el);
            return;
        }
        insert(list, el, idx+1);
    }
    public static void sort(ArrayList<Integer> list, int n){
        if(n==1){
            return;
        }
        int last = list.remove(n-1);
        sort(list, n-1);
        insert(list, last, 0);
    }

    // 2. Sort a stack using recursion
    public static void insertStack(Stack<Integer> st, int el){
        if((!st.isEmpty() && st.peek()<=el) || st.isEmpty()){
            st.push(el);
            return;
        }
        int temp = st.pop();
        insertStack(st, el);
        st.push(temp);
    }
    public static void sortStack(Stack<Integer> st){
        if(st.size()==1){
            return;
        }
        int last = st.pop();
        sortStack(st);
        insertStack(st, last);
    }


    // 3. Reverse a stack using recursion
    public static void push(Stack<Integer> st, int el){
        if(st.isEmpty()){
            st.push(el);
            return;
        }
        int temp = st.pop();
        push(st, el);
        st.push(temp);
    }
    public static void reverseStack(Stack<Integer> st){
        if(st.isEmpty()){
            return;
        }
        int temp = st.pop();
        reverseStack(st);
        push(st, temp);
    }

    // 4. Find all the subset of a string
    public static void findSubset(String s, int i, String ans){
        if(i==s.length()){
            System.out.print(ans + "|");
            return;
        }

        char ch = s.charAt(i);
        String temp = ans;
        // pick 
        findSubset(s, i+1, ans+ch);
        // not pick
        findSubset(s, i+1, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(4);
        // list.add(3);
        // list.add(0);
        // list.add(5);
        // sort(list, list.size()-1);
        // System.out.println(list);

        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(2);
        st.push(1);
        st.push(0);
        st.push(9);

        // sortStack(st);
        // reverseStack(st);
        // System.out.println(st);


        String s = "abc";
        findSubset(s, 0, "");
    }
}
