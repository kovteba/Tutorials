package kovteba.streamapi.examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Example2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 5, 1, 3, 7);

        int max = list.stream()
                .filter(a -> a < 7)
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println(max);
    }
}
