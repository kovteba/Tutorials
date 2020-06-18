package kovteba.realizedmyarraylist;

public class Application{
    public static void main(String[] args) {
        MyArrayList<Integer> testList = new MyArrayList<Integer>();



        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        System.out.println("Basic list");
        testList.showList();
        /////
        System.out.println("Test add to index");
        testList.add(3, 12);
        testList.showList();
        /////
        System.out.println("Test just add");
        testList.add(12);
        testList.showList();
        /////
        System.out.print("Test show element in index : " + testList.get(0) + "\n");
        /////
        System.out.println("\n" + "Test remove element by index (3)");
        testList.remove(3);
        testList.showList();
        /////
        System.out.print("Test clear list : ");
        testList.clear();
        testList.showList();

        System.out.println("-----------------DOUBLE---------------------");

        MyArrayList<Double> testListDouble = new MyArrayList<>();

        testListDouble.add(1.0);
        testListDouble.add(2.0);
        testListDouble.add(3.0);
        testListDouble.add(4.0);
        testListDouble.add(5.0);
        testListDouble.add(6.0);

        System.out.println("Basic list");
        testListDouble.showList();

        System.out.println("Test remove element by index (3)");
        testListDouble.remove(3);
        testListDouble.showList();
        ///
        System.out.println("Test add to index (0)");
        testListDouble.add(0, 12.0);
        testListDouble.showList();
        /////
        System.out.println("Test just add");
        testListDouble.add(12.0);
        testListDouble.showList();
        /////
        System.out.print("Test show element in index : " + testListDouble.get(0) + "\n");
        /////
        System.out.print("Test clear list : ");
        testListDouble.clear();
        testListDouble.showList();
























        //System.out.println(testList.size());

//
//        ArrayList list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(0, 1);
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        //System.arraycopy();
    }
}
