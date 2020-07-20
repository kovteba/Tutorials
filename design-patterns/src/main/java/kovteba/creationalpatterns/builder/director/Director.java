package kovteba.creationalpatterns.builder.director;

import kovteba.creationalpatterns.builder.builders.Builder;
import kovteba.creationalpatterns.builder.cars.Type;
import kovteba.creationalpatterns.builder.components.Engine;
import kovteba.creationalpatterns.builder.components.GPSNavigator;
import kovteba.creationalpatterns.builder.components.Transmission;
import kovteba.creationalpatterns.builder.components.TripComputer;

public class Director {

   public void constructSportsCar(Builder builder) {
      builder.setType(Type.SPORTS_CAR);
      builder.setSeats(2);
      builder.setEngine(new Engine(3.0, 0));
      builder.setTransmission(Transmission.SEMI_AUTOMATIC);
      builder.setTripComputer(new TripComputer());
      builder.setGPSNavigator(new GPSNavigator());
   }

   public void constructCityCar(Builder builder) {
      builder.setType(Type.CITY_CAR);
      builder.setSeats(2);
      builder.setEngine(new Engine(1.2, 0));
      builder.setTransmission(Transmission.AUTOMATIC);
      builder.setTripComputer(new TripComputer());
      builder.setGPSNavigator(new GPSNavigator());
   }

   public void constructSUV(Builder builder) {
      builder.setType(Type.SUV);
      builder.setSeats(4);
      builder.setEngine(new Engine(2.5, 0));
      builder.setTransmission(Transmission.MANUAL);
      builder.setGPSNavigator(new GPSNavigator());
   }
}
