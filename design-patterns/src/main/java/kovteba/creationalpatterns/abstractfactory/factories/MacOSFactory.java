package kovteba.creationalpatterns.abstractfactory.factories;

import kovteba.creationalpatterns.abstractfactory.button.Button;
import kovteba.creationalpatterns.abstractfactory.button.MacOSButton;
import kovteba.creationalpatterns.abstractfactory.checkbox.MacOSCheckbox;
import kovteba.creationalpatterns.abstractfactory.checkbox.Checkbox;

public class MacOSFactory implements GUIFactory {
   @Override
   public Button createButton() {
      return new MacOSButton();
   }

   @Override
   public Checkbox createCheckbox() {
      return new MacOSCheckbox();
   }
}
