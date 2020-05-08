package kovteba.tests;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindromeString("12222"));
        System.out.println(isPalindromeChar("12321"));
        System.out.println(palindrome(12321));
        System.out.println(isPalindrome(122222));
    }

    private static boolean isPalindromeString(String str) {
        if (str == null)
            return false;
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.reverse();
        return strBuilder.toString().equals(str);
    }

    private static boolean isPalindromeChar(String str) {
        if (str == null)
            return false;
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1))
                return false;
        }
        return true;
    }

    private static boolean isPalindrome(int inputNumber) {
        if (countNumber(inputNumber)) {
            return String.valueOf(inputNumber)
                    .equals(String.valueOf(new StringBuffer(String.valueOf(inputNumber)).reverse()));
        } else {
            return false;
        }
    }

    private static boolean countNumber(int inputNumber) {
        int count = 0;
        while (inputNumber != 0) {
            count++;
            inputNumber /= 10;
        }
        return count % 2 != 0;
    }

    private static boolean palindrome(int inputNumber) {
        if (countNumber(inputNumber)) {
            int origin = inputNumber;
            int reverse = 0;
            while (inputNumber > 0) {
                reverse = (reverse * 10) + (inputNumber % 10);
                inputNumber /= 10;
            }
            return reverse == origin;
        } else {
            return false;
        }
    }
}
