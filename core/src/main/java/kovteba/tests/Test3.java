package kovteba.tests;

public class Test3 {
    public static void main(String[] args) {
        boolean b = false;
        if (b == false)
            if (b = false)
                System.out.println("1");
            else
                System.out.println("2");

        System.out.println(b = true);
    }
}
