package kovteba;

import java.util.Scanner;

public class Binding {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      do {
         Run run = () -> System.out.println("Default RUN");

         System.out.print("Enter value (dog, cat, exit) : ");
         String answer = scanner.next();
         if (answer.equals("exit")) break;
         if (answer.equals("dog")) run = new Dog();
         if (answer.equals("cat")) run = new Cat();
         assert run != null;
         run.run();
      } while (true);
   }
}

interface Run {
   void run();
}

class Cat implements Run {

   @Override
   public void run() {
      System.out.println("Cat run");
   }
}

class Dog implements Run {

   @Override
   public void run() {
      System.out.println("Dog Run");
   }
}


 class Main {
   public static void main(String[] args) {

      // Пример статического и динамического связывания в Java
      Insurance current = new CarInsurance();

      // Динамическое связывание на основе объекта
      int premium = current.premium();

      // Статическое связывание на основе класса
      String category = current.category();

      System.out.println("premium : " + premium);
      System.out.println("category : " + category);
   }
}

class Insurance{
   public static final int LOW = 100;

   public int premium(){
      return LOW;
   }

   public static String category(){
      return "Insurance";
   }

}

class CarInsurance extends Insurance{
   public static final int HIGH = 200;

   public int premium(){
      return HIGH;
   }

   public static String category(){
      return "Car Insurance";
   }

}