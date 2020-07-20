package kovteba.testspack;

public class TestMemory {
   public static void main(String[] args) {
      System.out.println("TOTAL MEMORY : " + Runtime.getRuntime().totalMemory());
      long before = Runtime.getRuntime().freeMemory();
      System.out.println("FREE MEMORY : " + before);
      String s = "a";
      String s1 = "a";
      String s2 = "a";
      String s3 = "a";
      String s4 = "a";
      String s5 = "a";
      String s7 = "a";
      String s8 = "a";
      String s9 = "a";
      String s12 = "a";
      String s13 = "a";
      long after = Runtime.getRuntime().freeMemory();
      System.out.println("FREE MEMORY : " + after);
      System.out.println("STRING : " + (before - after));
   }
}
