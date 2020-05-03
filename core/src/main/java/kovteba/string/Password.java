package kovteba.string;

import javax.swing.*;
import java.util.Arrays;

public class Password {

    public static void main(String[] args) {

        JPasswordField passwordField = new JPasswordField("KOvTEBA");

        char[] correctPass = passwordField.getPassword();

        System.out.println(String.copyValueOf(correctPass));

        System.out.println(Arrays.toString(correctPass));

        Arrays.fill(correctPass, '0');

        System.out.println(Arrays.toString(correctPass));

    }

}
