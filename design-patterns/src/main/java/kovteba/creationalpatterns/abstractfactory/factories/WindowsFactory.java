package kovteba.creationalpatterns.abstractfactory.factories;

import kovteba.creationalpatterns.abstractfactory.button.Button;
import kovteba.creationalpatterns.abstractfactory.checkbox.Checkbox;
import kovteba.creationalpatterns.abstractfactory.button.WindowsButton;
import kovteba.creationalpatterns.abstractfactory.checkbox.WindowsCheckbox;

public class WindowsFactory implements GUIFactory {
   @Override
   public Button createButton() {
      return new WindowsButton();
   }

   @Override
   public Checkbox createCheckbox() {
      return new WindowsCheckbox();
   }
}
