package kovteba.creationalpatterns.abstractfactory.button;

import kovteba.creationalpatterns.abstractfactory.button.Button;

public class WindowsButton implements Button {
   @Override
   public void paint() {
      System.out.println("You have created WindowsButton.");
   }
}
