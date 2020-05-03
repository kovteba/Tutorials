import java.util.Arrays;
import java.util.List;

public class Exapmle1 {
    public static void main(String[] args) {

        Exapmle1 t = new Exapmle1();
        List<Integer> integers = Arrays.asList(4, 10, 7, 7);
        myIn m = t::print;
        m.out(integers);

    }
    void print(List<Integer> list){
        list.stream().map(x -> 2 * x)
                .distinct().sorted()
                .forEach(System.out::print);
    }

    interface myIn{
        void out(List<Integer> list);
    }
}
