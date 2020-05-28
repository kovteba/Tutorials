import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
   public static void main(String[] args) {








   }

}

interface MyInterface {
//   void Test();
   static void Test1() {
   }
   default void Test2() {
      System.out.println("!");
   }
}

class TestClass implements MyInterface {
   public void Test() {
      MyInterface.super.Test2();
   }
}





