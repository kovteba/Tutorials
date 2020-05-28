package kovteba.creationalpatterns.abstractfactory.checkbox;

import kovteba.creationalpatterns.abstractfactory.checkbox.Checkbox;

public class MacOSCheckbox implements Checkbox {
   @Override
   public void paint() {
      System.out.println("You have created MacOSCheckbox.");
   }
}
