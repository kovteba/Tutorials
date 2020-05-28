package kovteba.creationalpatterns.factorymethod;

import kovteba.creationalpatterns.factorymethod.donuts.Donut;
import kovteba.creationalpatterns.factorymethod.factory.DonutFactory;

public class Application {
   public static void main(String[] args) {
      Donut cherryDonut = DonutFactory.getDonut("cherry");
      cherryDonut.nameDonut();
      Donut appleDonut = DonutFactory.getDonut("apple");
      appleDonut.nameDonut();
      Donut vanillaDonut = DonutFactory.getDonut("vanilla");
      vanillaDonut.nameDonut();
   }
}
