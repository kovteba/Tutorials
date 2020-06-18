package kovteba.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private Node root;

    public  BinaryTree(int root){
        this.root = new Node(root);
    }

    public boolean add(int value) {
        return add(value, root);
    }

    private boolean add(int value, Node root) {
        final int rootVal = root.value;
        final boolean added;
        if (rootVal == value) {
            added = false;
        } else if (value < rootVal) {
            if (root.left == null) {
                root.left = new Node(value);
                added = true;
            } else {
                added = add(value, root.left);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(value);
                added = true;
            } else {
                added = add(value, root.right);
            }
        }
        return added;
    }

    public boolean contains(int value) {
        return contains(value, root);
    }

    private boolean contains(int value, Node root) {
        final int rootVal = root.value;
        final boolean contains;
        if (value == rootVal) {
            contains = true;
        } else if (value < rootVal) {
            if (root.left == null) {
                contains = false;
            } else {
                contains = contains(value, root.left);
            }
        } else {
            if (root.right == null) {
                contains = false;
            } else {
                contains = contains(value, root.right);
            }
        }
        return contains;
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        final int min;
        if (root.left == null) {
            min = root.value;
        } else {
            min = min(root.left);
        }
        return min;
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {
        final int max;
        if (root.right == null) {
            max = root.value;
        } else {
            max = max(root.right);
        }
        return max;
    }

    //////////////////////
    public List<Integer> ascending(){
        List<Integer> list = new ArrayList<>();
        ascending(root, list);
        return list;
    }

    private void ascending(Node root, List<Integer> list){
        if (root.left != null)
            ascending(root.left, list);

        list.add(root.value);

        if (root.right != null)
            ascending(root.right, list);


    }

    //////////////////////////
    public List<Integer> descending(){
        List<Integer> list = new ArrayList<>();
        descending(root, list);
        return list;
    }

    private void descending(Node root, List<Integer> list){
        if (root.right != null)
            descending(root.right, list);

        list.add(root.value);

        if (root.left != null)
            descending(root.left, list);
    }

    private static class Node {

        int value;

        Node left;

        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
