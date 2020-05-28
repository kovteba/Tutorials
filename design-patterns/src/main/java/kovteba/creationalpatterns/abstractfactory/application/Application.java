package kovteba.creationalpatterns.abstractfactory.application;

import kovteba.creationalpatterns.abstractfactory.button.Button;
import kovteba.creationalpatterns.abstractfactory.checkbox.Checkbox;
import kovteba.creationalpatterns.abstractfactory.factories.GUIFactory;

public class Application {

   private Button button;
   private Checkbox checkbox;

   public Application(GUIFactory factory) {
      button = factory.createButton();
      checkbox = factory.createCheckbox();
   }

   public void paint() {
      button.paint();
      checkbox.paint();
   }

}
