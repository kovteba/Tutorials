package kovteba;



public class Test {
   public static void main(String[] args) {



      CarFilter<Car> carFilter = car -> car.getYear() >= 2010;

      Car car = new Car();
      car.setYear(2009);
      System.out.println(carFilter.test(car));


   }
}


interface MyInterface {
   void Test();

   static void Test1() {
   }

   default void Test2() {
      System.out.println("!");
   }
}

class TestClass implements MyInterface {
   @Override
   public void Test() {

   }

   @Override
   public void Test2() {

   }
//   public void Test() {
//      MyInterface.super.Test2();
//   }


}


@FunctionalInterface
interface CarFilter<T> {
   boolean test(T car);
}

class Car {
   int year;
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
   }
}

