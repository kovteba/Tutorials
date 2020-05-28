package kovteba.creationalpatterns.factorymethod.factory;

import kovteba.creationalpatterns.factorymethod.donuts.AppleDonut;
import kovteba.creationalpatterns.factorymethod.donuts.CherryDonut;
import kovteba.creationalpatterns.factorymethod.donuts.Donut;
import kovteba.creationalpatterns.factorymethod.donuts.VanillaDonut;

public class DonutFactory {
   public static Donut getDonut(String nameDonut){
      if (nameDonut.equals("apple")){
         return new AppleDonut();
      } else if (nameDonut.equals("vanilla")){
         return new VanillaDonut();
      } else if(nameDonut.equals("cherry")){
         return new CherryDonut();
      } else {
         return null;
      }
   }
}
