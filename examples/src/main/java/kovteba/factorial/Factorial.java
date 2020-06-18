package factorial;

public class Factorial {

    public static void main(String[] args) {

        System.out.println(factorial(5));

    }

    public static long factorial(int n){

        long result = 1;

        if (n == 1 || n == 0) {
            return result;
        }
        result = n * factorial(n - 1);

        return result;
    }
}
