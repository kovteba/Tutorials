package kovteba.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman implements Comparable<Huffman> {

   private Node root;

   private static String s = "    ";

   //символ и его частота
   private static Map<Character, Integer> map = new HashMap<>();

   public Huffman(Node root) {
      this.root = root;
   }

   public static Map<Character, Integer> charCount(String inputString) {
      char[] chars = inputString.toCharArray();
      for (char c : chars) {
         map.put(c, count(c, chars));
      }
      return map;
   }

   private static Integer count(char c, char[] in) {
      int count = 0;
      for (char value : in) if (value == c) count++;
      return count;
   }

   public static Huffman huffmanTree(String text) {
      Map<Character, Integer> map = charCount(text);
      Queue<Huffman> trees = new PriorityQueue<>();
      System.out.println("----------------");
      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
         System.out.println(entry.getKey() + " " + entry.getValue());
         trees.offer(new Huffman(new Node(entry.getValue(), entry.getKey())));
      }
      System.out.println("------------");
      for (Huffman h : trees) {
         System.out.println(h.root.character + " " + h.root.frequency);
//         System.out.println(h.root.leftChild + " " + h.root.rightChild);
      }
      System.out.println("============");
      while (trees.size() > 1) {
         Huffman a = trees.poll();
         System.out.println("a : " + a.root.toString());
         Huffman b = trees.poll();
         System.out.println("b : " + b.root.toString());
         trees.offer(new Huffman(new Node(a, b)));
      }
      return trees.poll();
   }

   public void print() {
      printTree(root, "");
   }

   private static void printTree(Node current, String indent) {
      if (current == null) {
         return;
      }
      printTree(current.leftChild, indent + s);
      System.out.println(indent + current.toString() + "\n");
      printTree(current.rightChild, indent + s);
   }

   @Override
   public int compareTo(Huffman tree) {
      return root.frequency - tree.root.frequency;
   }

   private static class Node {
      // Частота символа
      private Integer frequency;
      // Символ
      private Character character;
      // Левый потомок узла
      private Node leftChild;
      // Правый потомок узла
      private Node rightChild;

      public Node(Integer frequency, Character character) {
         this.frequency = frequency;
         this.character = character;
      }

      public Node(Huffman left, Huffman right) {
         frequency = left.root.frequency + right.root.frequency;
         leftChild = left.root;
         rightChild = right.root;
      }

      @Override
      public String toString() {
         return "character =\'" + character + "\'";
      }
   }


}
