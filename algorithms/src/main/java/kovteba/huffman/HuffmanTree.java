package kovteba.huffman;

import java.util.*;

class HuffmanTree implements Comparable<HuffmanTree> {

   private Node root;

   //символ и его частота
   private static Map<Character, Integer> map = new HashMap<>();

   public HuffmanTree(Node root) {
      this.root = root;
   }

   //разбиваем текст на символы и посчитываем их
   public static Map<Character, Integer> characterCount(List<String> text) {
      ArrayList<Character> charList = new ArrayList<>();

      for (String str : text) {
         //разбиение на сомволы
         char[] fingStr = str.toCharArray();
         for (int i = 0; i < fingStr.length; i++) {
            charList.add(fingStr[i]);
         }
         //считаем количество поторений
         for (int i = 0; i < charList.size(); i++) {
            int count = 0;
            for (int j = 0; j < charList.size(); j++) {
               if (charList.get(i).equals(charList.get(j))) {
                  count++;
               }
            }
            map.put(charList.get(i), count);
         }
      }

      return map;
   }

   //Huffman tree
   public static HuffmanTree buildHuffmanTree(List<String> text) {
      Map<Character, Integer> map = characterCount(text);

      PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
      for (Map.Entry<Character, Integer> entry : map.entrySet()) {

         trees.offer(new HuffmanTree(new Node(entry.getValue(), entry.getKey())));
      }
      while (trees.size() > 1) {
         // 4. - 5.
         HuffmanTree a = trees.poll();
         HuffmanTree b = trees.poll();
         trees.offer(new HuffmanTree(new Node(a, b)));
      }
      return trees.poll();
   }

   //save binary code
   public Map<Character, String> coding() {
      Map<Character, String> codeTable = new HashMap<>();
      coding(root, new StringBuilder(), codeTable);
      return codeTable;
   }

   private void coding(Node root, StringBuilder code, Map<Character, String> codeTable) {
      if (root.character != null) {
         codeTable.put(root.character, String.valueOf(code));
      } else {
         coding(root.leftChild, code.append('0'), codeTable);
         code.deleteCharAt(code.length() - 1);
         coding(root.rightChild, code.append('1'), codeTable);
         code.deleteCharAt(code.length() - 1);
      }
   }

   //
   public static void saveBinaryCode(Map<Character, String> codeTable){
      String outputFile = "algorithms/files/output.txt";
      StringBuilder builder = new StringBuilder();
      for (Map.Entry<Character, String> entry: codeTable.entrySet()){
         builder.append(entry.getKey() + " " + entry.getValue() + "\n");
      }

      ReadAndWriteClass.outputToFile(builder.toString(), outputFile, false);
   }

   //
   public static void createCompressFile(Map<Character, String> codeTable){
      String compressFile = "algorithms/files/compressed.txt";
      String compressBinaryFile = "algorithms/files/compressBinaryFile.txt";
      StringBuilder builder = new StringBuilder();
      for (Map.Entry<Character, String> entry: codeTable.entrySet()){
         builder.append(entry.getValue());
      }
      ReadAndWriteClass.outputToFile(builder.toString(), compressBinaryFile, false);
   }

   @Override
   public int compareTo(HuffmanTree tree) {
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

      public Node(HuffmanTree left, HuffmanTree right) {
         frequency = left.root.frequency + right.root.frequency;
         leftChild = left.root;
         rightChild = right.root;
      }

      @Override
      public String toString() {
         return "[id=" + frequency + ", data =" + character + "]";
      }
   }
}