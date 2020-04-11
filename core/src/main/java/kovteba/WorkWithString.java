package kovteba;

import javax.swing.*;
import java.util.Arrays;

public class WorkWithString {

    private static String str = "123";
    private static String url = "www.journaldev.com";

    public static void main(String[] args) {

        //String to char array
        System.out.println("String to char array : " + Arrays.toString(stringToCharArray(str)) + "\n");

        //String to char
        System.out.println("String to char: " + stringTiCharByIndex(str, 1) + "\n");

        //char to String
        System.out.println("Char to String: " + Character.toString(stringTiCharByIndex(str, 1)) + "\n");

        //удалить все заданные символы из строки
        System.out.println("removing all chars from String: " + removeCharFromString("1ABCD12DW", 'A'));

        //преобразование String в byte array
        byte[] byteArr = stringToByte(url);
        System.out.println("String to byte array : " + Arrays.toString(byteArr) + "\n");

        //преобразование byte array и String
        String str1 = new String(byteArr);
        System.out.println("byte array to String : " + str1 + "\n");

        //посмотрим, str и str1 одинаковые или нет
        System.out.println("str == str1? " + (url == str1));
        System.out.println("str.equals(str1)? " + (url.equals(str1)) + "\n");

    }

    private static char[] stringToCharArray(String input) {
        return input.toCharArray();
    }

    private static char stringTiCharByIndex(String input, int index) {
        return input.charAt(index);
    }

    private static String removeCharFromString(String str, char c) {
        return str.replaceAll(Character.toString(c), "");
    }

    private static byte[] stringToByte(String input) {
        return input.getBytes();
    }

}
