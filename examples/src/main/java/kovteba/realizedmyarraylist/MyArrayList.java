package kovteba.realizedmyarraylist;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable {

   final private int DEFOULT_SIZE = 0;

   private int size;

   private T[] list;

   public MyArrayList(int size) {
      this.size = size;
      list = (T[]) new Object[size];
   }

   public MyArrayList() {
      this.size = DEFOULT_SIZE;
      list = (T[]) new Object[size];
   }


   /**
    * add to last position
    *
    * @param element
    */
   void add(T element) {

      int index = 0;

      boolean testNull = true;
      //test to empty place in list
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            index = i;
            testNull = false;
            break;
         }
      }
      //add to list.length size
      if (testNull) {
         list = plusSizeList(list);
         //find null position
         for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
               index = i;
               break;
            }
         }
      }
      list[index] = element;
   }

   /**
    * show me size list
    *
    * @return
    */
   int size() {
      return list.length;
   }

   /**
    * print element list in index place
    *
    * @param index
    * @return
    */
   T get(int index) {
      return list[index];
   }

   /**
    * remove element
    *
    * @param index
    * @return
    */
   T[] remove(int index) {

      list = reRecordRemove(list, minusSizeList(list), index);

      return list;
   }

   /**
    * add by index
    *
    * @param index
    * @param element
    */
   void add(int index, T element) {
      list = reRecordAddInCenter(list, plusSizeList(list), index, element);

   }

   /**
    * clear
    */
   void clear() {
      for (int i = 0; i < list.length; i++) {
         list[i] = null;
      }
      System.out.println("list is empty");
   }

   /**
    * expansion size list
    *
    * @param inList
    * @return
    */
   private T[] plusSizeList(T[] inList) {
      list = Arrays.copyOf(inList, inList.length + 1);
      return list;
   }

   /**
    * minus element in index
    *
    * @param inList
    * @return
    */
   private T[] minusSizeList(T[] inList) {
      list = Arrays.copyOf(inList, inList.length - 1);
      return list;
   }

   /**
    * reRecord for input in center list
    *
    * @param in
    * @param out
    * @param removeIndex
    * @return
    */
   private T[] reRecordRemove(T[] in, T[] out, int removeIndex) {
      int i, j;
      for (i = 0, j = 0; j < in.length; i++, j++) {
         if (j == removeIndex) {
            i--;
            continue;
         }
         out[i] = in[j];
      }
      list = out;
      return list;
   }

   /**
    * reRecord for input in center list
    *
    * @param in
    * @param out
    * @param addIndex
    * @param value
    * @return
    */
   private T[] reRecordAddInCenter(T[] in, T[] out, int addIndex, T value) {
      int i, j;
      for (i = 0, j = 0; j < in.length; i++, j++) {
         if (i == addIndex) {
            out[i] = value;
            j--;
            continue;
         }
         out[i] = in[j];
      }
      list = out;
      return list;
   }

   /**
    * print list
    */
   void showList() {
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null) {
            System.out.println(i + " : " + list[i]);
         }
      }
      System.out.println();
   }

   @Override
   public Iterator iterator() {
      return null;
   }
}
