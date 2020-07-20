package kovteba.creationalpatterns.abstractfactory.application;

import kovteba.creationalpatterns.abstractfactory.factories.GUIFactory;
import kovteba.creationalpatterns.abstractfactory.factories.MacOSFactory;
import kovteba.creationalpatterns.abstractfactory.factories.WindowsFactory;

public class ConfigureApplication {
   public static Application config(String osName) {
      Application app;
      GUIFactory factory;
      if (osName.contains("mac"))
         factory = new MacOSFactory();
      else
         factory = new WindowsFactory();
      return new Application(factory);
   }
}
