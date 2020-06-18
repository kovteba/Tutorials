package kovteba.binarytree;

public class Lesson20 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);

        tree.add(5);
        tree.add(1);
        tree.add(4);
        tree.add(35);
        tree.add(20);
        tree.add(31);
        tree.add(99);
        tree.add(17);

        System.out.println(tree.ascending());
        System.out.println(tree.descending());

        System.out.println("max : " + tree.max());
        System.out.println("min : " + tree.min());
    }
}
