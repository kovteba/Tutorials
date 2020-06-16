package kovteba;

public class Encapsulation {
   public static void main(String[] args) {
      Car car = new Car();
      //user does not know how, but it works
      car.accelerator();
      car.brake();
   }
}

class Car {

   //Data hiding
   private String carBrand;

   public String getCarBrand() {
      return carBrand;
   }

   public void setCarBrand(String carBrand) {
      this.carBrand = carBrand;
   }

   //Concealment of implementation
   public void accelerator() {
      //Some kind of complex and incomprehensible process
   }

   public void brake() {
      //Some kind of complex and incomprehensible process
   }
}


