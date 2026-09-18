package week1.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    static boolean isPalindromeRecursive(String text, int left, int right) {

        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindromeRecursive(text, left + 1, right - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {

        char[] arr = text.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        String reversed = new String(arr);

        return text.equals(reversed);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = sc.nextLine();

        System.out.println("\nIterative Method: " +
                (isPalindromeIterative(text) ?
                        "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive Method: " +
                (isPalindromeRecursive(text, 0, text.length() - 1) ?
                        "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal Method: " +
                (isPalindromeArrayReversal(text) ?
                        "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}