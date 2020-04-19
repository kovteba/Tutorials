package kovteba.tests;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList(3);
        list.add(new Integer(100));
        list.add(new Integer(200));
        list.add(new Integer(300));
        list.add(new Integer(400));

        System.out.println(list.size());
    }
}
