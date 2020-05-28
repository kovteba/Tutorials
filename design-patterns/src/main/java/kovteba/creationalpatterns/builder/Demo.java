package kovteba.creationalpatterns.builder;

import kovteba.creationalpatterns.builder.builders.CarBuilder;
import kovteba.creationalpatterns.builder.builders.CarManualBuilder;
import kovteba.creationalpatterns.builder.cars.Car;
import kovteba.creationalpatterns.builder.cars.Manual;
import kovteba.creationalpatterns.builder.director.Director;

public class Demo {

   public static void main(String[] args) {
      Director director = new Director();

      // Директор получает объект конкретного строителя от клиента
      // (приложения). Приложение само знает какой строитель использовать,
      // чтобы получить нужный продукт.
      CarBuilder builder = new CarBuilder();
      director.constructSportsCar(builder);

      // Готовый продукт возвращает строитель, так как Директор чаще всего не
      // знает и не зависит от конкретных классов строителей и продуктов.
      Car sportCar = builder.getResult();
      System.out.println("Car built:\n" + sportCar.getType());

      CarManualBuilder manualBuilder = new CarManualBuilder();
      // Директор может знать больше одного рецепта строительства.
      director.constructSportsCar(manualBuilder);
      Manual carManual = manualBuilder.getResult();
      System.out.println("\nCar manual built:\n" + carManual.print());

   }

}
