package kovteba.huffman;

import java.util.List;
import java.util.Map;

public class Main {
   public static void main(String[] args) {
      String inputFile = "input.txt";

//      List<String> test = ReadAndWriteClass.inputFromFile(inputFile);
//
//      HuffmanTree tree = HuffmanTree.buildHuffmanTree(test);
//
//      Map<Character, String> codeTable = tree.coding();
//
//      for (Map.Entry entry : codeTable.entrySet()){
//         System.out.println(entry.getKey() + " " + entry.getValue());
//      }
//
//      HuffmanTree.saveBinaryCode(codeTable);
//
//      HuffmanTree.createCompressFile(codeTable);
//
//      System.out.println("---------------------------");

      String s = ReadAndWriteClass.readFromFile(inputFile);

      Map<Character, Integer> map = Huffman.charCount(s);
      for (Map.Entry entry : map.entrySet())
         System.out.println(entry.getKey() + " " + entry.getValue());

      Huffman tree1 = Huffman.huffmanTree(s);
      tree1.print();

   }


}
