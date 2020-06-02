package kovteba.datatypes.string;

public class ImmutableString {
   public static void main(String[] args) {

      String s = "один"; // создали объект "один" типа String
      System.out.println(s); // вывели его на экран
      change(s); // пробуем изменить ЭТОТ ЖЕ объект
      System.out.println(s);

      System.out.println();

      StringBuilder builder = new StringBuilder("один"); //создали объект "один" типа StringBuilder
      System.out.println(builder); // вывели его на экран
      change(builder); // пробуем изменить ЭТОТ ЖЕ объект
      System.out.println(builder);

   }

   static void change(String s) {
      s = s + " два";
   }

   static void change(StringBuilder s) {
      s.append(" два");
   }
}
