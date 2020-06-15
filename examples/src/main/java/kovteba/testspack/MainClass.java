package kovteba.testspack;

public class MainClass {
   public static void main(String args[]) {
      System.out.println(TestClass.v);
      System.out.println("-");
      new TestClass().a();
      System.out.println("-");
      System.out.println(TestClass.v);
   }
}
class TestClass {

   public static String v = "Initial val";
   {
      System.out.println("!!! Non-static initializer");
      v = "Val from non-static";
   }
   static {
      System.out.println("!!! Static initializer");
      v = "Some val";
   }
   public void a() {
      System.out.println("!!! a() called");
   }
}