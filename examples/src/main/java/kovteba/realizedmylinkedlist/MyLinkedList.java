package kovteba.realizedmylinkedlist;

public class MyLinkedList<T> {


    //count elements
    private int size = 0;

    //first element in mas
    private Node<T> firstNode;

    //last element in list
    private Node<T> lastNode;

//    public MyLinkedList() {
//        this.firstNode = new Node<>();
//        this.lastNode = firstNode;
//        this.size++;
//    }

    /**
     * add element in last position
     *
     * @param element
     */
    public void add(T element) {
        Node<T> newNode;
        if (chechThePositionIsEmpty()) {
            newNode = new Node<>(element);
            this.firstNode = newNode;
            this.lastNode = firstNode;
        } else {
            newNode = new Node<>(navigate(size - 1), element);
            navigate(size - 1).linkNext(newNode);
            lastNode = newNode;
        }
        this.size++;
    }

    /**
     * find last not null position
     *
     * @return
     */
    private Node<T> findLastPosition() {
        Node<T> n = firstNode;
        while (n.getNext() != null) {
            n = n.getNext();
        }
        return n;
    }

    /**
     * size list
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add element in LinkedList by index
     *
     * @param index
     * @param element
     */
    public void add(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        Node<T> newNode;
        Node<T> previous;
        Node<T> next;

        if (index == 0) {
            newNode = new Node<>(null, element, firstNode);
            this.firstNode.linkPrevious(newNode);
            this.firstNode = newNode;
        } else {
            previous = navigate(index - 1);
            next = navigate(index);
            newNode = new Node<>(previous, element, next);
            previous.linkNext(newNode);
            next.linkPrevious(newNode);
        }
        this.size++;
    }

    /**
     * remove element List by index
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index = " + index);
        }

        if (index == 0) {
            this.firstNode = navigate(index + 1);
            this.firstNode.linkPrevious(null);
            this.firstNode.linkNext(navigate(index + 1));
        } /*else if (index == size) {
            this.lastNode.linkNext(null);
            this.lastNode = navigate(index - 1);

        }*/ else {
            Node<T> previous = navigate(index - 1);
            Node<T> next = navigate(index + 1);
            navigate(index).setValue(null);
            previous.linkNext(next);
            next.linkPrevious(previous);
        }

        this.size--;

    }

    /**
     * clear
     */
    public void clear() {
        this.firstNode = null;
        this.size = 0;
    }

    public T get(int index) {
        try {
            return navigate(index).getValue();
        } catch (Exception e) {
            throw new IllegalArgumentException("method get");
        }
    }

    /**
     * check firstNode position == null
     *
     * @return
     */
    private boolean chechThePositionIsEmpty() {
        return firstNode == null;
    }

    /**
     * find Node by index
     *
     * @param index
     * @return
     */
    public Node<T> navigate(int index) {
        Node<T> n = firstNode;
//        for (int i = 1; i <= index; i++) {
        for (int i = 1; i <= index; i++) {
            n = n.getNext();
        }
        return n;
    }

    public Node<T> reverseNavigate(int index) {
        Node<T> n = lastNode;
        for (int i = 1; i <= index; i++) {
            n = n.getPrevious();
        }
        return n;
    }

    public Node<T> navigateReverse(int index) {
        Node<T> n = navigate(size - 1);
        for (int i = index; i >= 1; i--) {
            n = n.getPrevious();
        }
        return n;
    }

    public void testPreviousLink() {
        Node<T> n = lastNode;
        System.out.print(n.getValue() + ", ");
        while (n.getPrevious() != null) {
            n = n.getPrevious();
            System.out.print(n.getValue() + ", ");
        }
    }


    @Override
    public String toString() {
        if (chechThePositionIsEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        sb.append(firstNode.getValue());

        Node<T> n = firstNode;
        while (n.getNext() != null) {
            n = n.getNext();
            sb.append(", ").append(n.getValue());
        }

        return sb.append("]").toString();
    }

    private static final class Node<E> {

        private E value;

        //link on the next element
        private Node<E> next = null;

        //link on the previous element
        private Node<E> previous = null;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node<E> previous, E value) {
            this.value = value;
            this.previous = previous;
        }


        public Node(Node<E> previous, E value, Node<E> next) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void linkNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void linkPrevious(Node<E> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    ", previous=" + previous +
                    '}';
        }
    }


}
