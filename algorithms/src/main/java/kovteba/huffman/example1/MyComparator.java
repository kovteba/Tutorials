package kovteba.huffman.example1;

import java.util.Comparator;

public class MyComparator implements Comparator<HuffmanNode> {
   public int compare(HuffmanNode x, HuffmanNode y)
   {

      return x.data - y.data;
   }
}
