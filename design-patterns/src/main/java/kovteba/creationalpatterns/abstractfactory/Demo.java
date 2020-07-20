package kovteba.creationalpatterns.abstractfactory;

import kovteba.creationalpatterns.abstractfactory.application.Application;
import kovteba.creationalpatterns.abstractfactory.application.ConfigureApplication;
import kovteba.creationalpatterns.abstractfactory.factories.GUIFactory;
import kovteba.creationalpatterns.abstractfactory.factories.MacOSFactory;
import kovteba.creationalpatterns.abstractfactory.factories.WindowsFactory;

public class Demo {
   public static void main(String[] args) {
      Application app = ConfigureApplication.config("win");
      app.paint();
   }
}
