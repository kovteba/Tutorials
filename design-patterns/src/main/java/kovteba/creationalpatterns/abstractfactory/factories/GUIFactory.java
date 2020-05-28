package kovteba.creationalpatterns.abstractfactory.factories;

import kovteba.creationalpatterns.abstractfactory.button.Button;
import kovteba.creationalpatterns.abstractfactory.checkbox.Checkbox;

// ABSTRACT FACTORY

public interface GUIFactory {
   Button createButton();
   Checkbox createCheckbox();
}
