package kovteba.java8.optional.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class OptionalExamples {
   public static void main(String[] args) {

   }
}

class OptionalExample1{
   public static void main(String[] args) {
      List<Integer> list = Arrays.asList(null, 1, 12, 14);
      Optional<Integer> optional = Optional.ofNullable(list.get(1));
      if (optional.isPresent()){
         System.out.println(optional.get());
      }
   }
}

class OptionalExample2{
   public static void main(String[] args) {
      List<Integer> list = Arrays.asList(null, 1, 12, 14);
      Optional<Integer> optional = Optional.ofNullable(list.get(1));
      optional.ifPresent(System.out::println);
   }
}

class OptionalExample3{
   public static void main(String[] args) {
      List<Integer> list = Arrays.asList(null, 1, 12, 14);
      Optional<Integer> optional = Optional.ofNullable(list.get(1));
      if (optional.isPresent()){
         System.out.println(optional.get());
      }
   }
}

class OptionalExample4{
   public static void main(String[] args) {
      ExampleClass exampleClass = null;

      Scanner scanner = new Scanner(System.in);

      String answer = scanner.next();
      if (answer.equals("1")){
         exampleClass = new ExampleClass("EXAMPLE");
      }

      Optional<ExampleClass> optionalFinalClass = Optional.ofNullable(exampleClass);

      System.out.println(optionalFinalClass.isPresent() ? optionalFinalClass.get() : "NOPE");
   }

   static class ExampleClass {
      private String value;

      public ExampleClass(String value) {
         this.value = value;
      }

      @Override
      public String toString() {
         return "ExampleClass{" +
             "value='" + value + '\'' +
             '}';
      }
   }
}
