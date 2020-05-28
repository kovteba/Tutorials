package kovteba.creationalpatterns.builder.builders;

import kovteba.creationalpatterns.builder.cars.Type;
import kovteba.creationalpatterns.builder.components.Engine;
import kovteba.creationalpatterns.builder.components.GPSNavigator;
import kovteba.creationalpatterns.builder.components.Transmission;
import kovteba.creationalpatterns.builder.components.TripComputer;

public interface Builder {
   void setType(Type type);
   void setSeats(int seats);
   void setEngine(Engine engine);
   void setTransmission(Transmission transmission);
   void setTripComputer(TripComputer tripComputer);
   void setGPSNavigator(GPSNavigator gpsNavigator);
}
