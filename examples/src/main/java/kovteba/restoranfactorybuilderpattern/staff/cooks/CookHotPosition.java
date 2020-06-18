package kovteba.restoranfactorybuilderpattern.staff.cooks;


import kovteba.restoranfactorybuilderpattern.dish.*;
import kovteba.restoranfactorybuilderpattern.products.*;

public class CookHotPosition implements InitCooks {


    @Override
    public Dish initDish(KitchenMenu type) {

        switch (type) {
            case STEAK:
                Steak steak = new Steak.Builder()
                        .addMeet(new Meet.PorkMeet("pork", 500))
                        .addPepper(new Pepper("pepperCheel111"))
                        .addSolt(new Solt("solt+", 0.3))
                        .build();

                return steak;
            case GARDEN_FRESH_SALAD:
                GardenFreshSalad gardenFreshSalad = new GardenFreshSalad.Builder()
                        .addTommato(new Tommato("tommato"))
                        .addCuccumber(new Cuccumber("cuccumber"))
                        .addOil(new Oil("oil"))
                        .build();
                return gardenFreshSalad;
            case GREEK_SALAD:
                GreekSalad greekSalad = new GreekSalad.Builder()
                        .addCuccumber(new Cuccumber("cuccumber"))
                        .addTommato(new Tommato("tommato"))
                        .addOil(new Oil("OliveOile"))
                        .addMozzarella(new Mozzarella("mozzarella"))
                        .build();
                return greekSalad;
            case BBQ_RIBS:
                BbqRibs bbqRibs = new BbqRibs.Builder()
                        .addRibs(new Meet.Ribs("pork"))
                        .addPepper(new Pepper("Pepper"))
                        .addSous(new Sous("sous"))
                        .adSolt(new Solt("solt", 0.3))
                        .build();
                return bbqRibs;
            default:
                throw new IllegalArgumentException("Wrong staff type:" + type);
        }
    }
}
