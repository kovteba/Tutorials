package kovteba.generics;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
   public static void main(String[] args) {
      List<String> list = new ArrayList<>();


      Cell<? super Toyota> superCell = new Cell<Car>();
      superCell.setT(new Toyota());
      superCell.setT(new Corola());
//      superCell.setT(new Car());
//      Toyota car = superCell.getT();

      Cell<? extends Toyota> extendsCell = new Cell<>();
      Toyota t = extendsCell.getT();


   }

}

// EXTENDS
class Parent{}
class GenParent<T> extends Parent {}
class Child1 extends GenParent {}
class Child2 extends GenParent<String> {}
class Child3<T> extends GenParent<T> {}
class Child4<T, X, Y> extends GenParent<Y> {}
class Child5<T, X, Y> extends GenParent {}
class Child6<T, X, Y> extends Child5<Integer, X, String> {}

interface GenInterface<T> {
   T getT();
}

class One implements GenInterface{
   @Override
   public Object getT() {
      return null;
   }
}

class Two implements GenInterface<String>{
   @Override
   public String getT() {
      return null;
   }
}

class Three<T> implements GenInterface<T>{
   @Override
   public T getT() {
      return null;
   }
}

// METHODS
class GenClass<T> {
   T t;
   <X> GenClass(X x){}

   public void setT(T t) {
      this.t = t;
   }

   <E> E GetE(E e){
      return e;
   }
}

// WOLDCARD
class Car{}
class Toyota extends Car{}
class Corola extends Toyota{}

class Cell<T> {
   T t;

   public T getT() {
      return t;
   }

   public void setT(T t) {
      this.t = t;
   }
}


