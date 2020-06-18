package kovteba.restoranfactorybuilderpattern.staff.cooks;


import kovteba.restoranfactorybuilderpattern.dish.Dish;
import kovteba.restoranfactorybuilderpattern.dish.KitchenMenu;

public interface ActionForCooks {
    public Dish initDish(KitchenMenu type);

}
