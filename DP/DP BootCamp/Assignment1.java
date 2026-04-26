class Assignment1 {
    // 1. Count the Number of Digits in a Number
    public static int countDig(int n) {
        if (n == 0) { // when n = 0 => digits = 0
            return 0;
        }
        return countDig(n / 10) + 1; // count this digit + call for remainings
    }

    // 2. Find the Maximum Element in an Array using Recursion
    public static int findMax(int[] arr, int i, int max) {
        if (i == arr.length) {
            return max;
        }
        max = Math.max(arr[i], max);
        return findMax(arr, i + 1, max);
    }

    // 3. Check if an Array is Sorted (Strictly Increasing) using Recursion
    public static boolean isIncreasing(int[] arr, int i) {
        if (i + 1 == arr.length) {
            return true;
        }
        if (arr[i] >= arr[i + 1]) {
            return false;
        }
        return isIncreasing(arr, i + 1);
    }

    // 4. Check if a String contains only Digits using Recursion
    public static boolean isDigits(String s, int i) {
        if (i == s.length()) {
            return true;
        }
        int ascci = (int) s.charAt(i);
        if (!(ascci >= 48 && ascci <= 57)) {
            return false;
        }
        return isDigits(s, i + 1);
    }

    // 5. Count the Number of Zeros in a Number using Recursion
    public static int countZeroes(int n) {
        if (n == 0)
            return 0;

        if (n % 10 == 0) {
            return 1 + countZeroes(n / 10);
        } else {
            return countZeroes(n / 10);
        }
    }

    // 6. Convert a Decimal Number to Binary using Recursion

    // 7. Reverse the Digits of a Number using Recursion
    public static int reverseDig(int n, int ans) {
        if (n == 0) {
            return ans;
        }
        int dig = n % 10;
        return reverseDig(n / 10, ans * 10 + dig);
    }

    // 8. Reverse a Linked List using recursion
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    // public static Node reverseLL(Node head){

    // }

    // 9. Reverse an Array using recursion
    public static void reverseArray(int[] arr, int i) {
        if (i == arr.length) {
            return;
        }

        reverseArray(arr, i + 1);
        System.out.print(arr[i] + " ");
    }


    // 10. Merge Two Sorted Lists using recursion
    // public static void merge()

    public static void main(String[] args) {
        // System.out.println(countDig(243764));

        int[] arr = { 1, 3, 45, 7, 8, 9 };
        int max = findMax(arr, 0, Integer.MIN_VALUE);
        // System.out.println(max);

        // System.out.println(isIncreasing(arr, 0));

        // System.out.println(isDigits("53264", 0));

        // System.out.println(countZeroes(502006));

        // System.out.println(reverseDig(502006, 0));

        reverseArray(arr, 0);
    }
}