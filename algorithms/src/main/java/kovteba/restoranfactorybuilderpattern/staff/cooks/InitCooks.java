package kovteba.restoranfactorybuilderpattern.staff.cooks;


import kovteba.restoranfactorybuilderpattern.dish.Dish;
import kovteba.restoranfactorybuilderpattern.dish.KitchenMenu;

public interface InitCooks {
    public Dish initDish(KitchenMenu type);
}
