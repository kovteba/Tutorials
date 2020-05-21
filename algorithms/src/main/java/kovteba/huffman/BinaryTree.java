package kovteba.huffman;

class BinaryTree {
   private Node root;

   public BinaryTree() {
      root = new Node();
   }

   public BinaryTree(Node root) {
      this.root = root;
   }

   public int getFrequence() {
      return root.getFrequence();
   }

   public Node getRoot() {
      return root;
   }
}
