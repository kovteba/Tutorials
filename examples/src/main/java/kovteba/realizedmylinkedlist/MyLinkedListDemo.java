package kovteba.realizedmylinkedlist;

public class MyLinkedListDemo {

    public static void main(String[] args) {


        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);//0
        list.add(2);//index 1
        list.add(3);//2
        list.add(4);//3
        list.add(5);//4
        list.add(6);//5
        list.add(7);//6
        System.out.println("Basic list  " + "\n" + list.toString() + "\n");

        System.out.println("Size list : " + list.size() + "\n");

        int indexForRemove = 5;
        list.remove(indexForRemove);
        System.out.println("Test remove element by index : " + indexForRemove + " " + "\n" + list.toString() + "\n");

        int indexForAdd = 5;
        list.add(indexForAdd, 55);
        System.out.println("Test add element by index : " + indexForAdd+ "\n" + list.toString() + "\n");

        int indexForTestGetByIndex = 6;
        System.out.println("Test get element by index : " + indexForTestGetByIndex + "\n" + list.get(indexForTestGetByIndex) + "\n");


        System.out.println("TestPreviousLink : " + "\n");
        list.testPreviousLink();

    }

}
