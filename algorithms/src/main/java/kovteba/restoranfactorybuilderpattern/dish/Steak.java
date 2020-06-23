package kovteba.restoranfactorybuilderpattern.dish;


import kovteba.restoranfactorybuilderpattern.products.Meet;
import kovteba.restoranfactorybuilderpattern.products.Pepper;
import kovteba.restoranfactorybuilderpattern.products.Solt;

public class Steak extends Dish {
    private String nameDish = "Steak";
    private double price = 346.0;
    private double dishWeight = 500;

    private Solt solt;
    private Pepper pepper;
    private Meet.PorkMeet meet;

    private Steak(Builder builder) {
        this.solt = builder.solt;
        this.meet = builder.meet;
        this.pepper = builder.pepper;
    }

    @Override
    public String getNameDish() {
        return nameDish;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getDishWeight() {
        return dishWeight;
    }

    @Override
    public void forOrder() {

    }

    /////////////////
    public static class Builder {
        private Solt solt;
        private Pepper pepper;
        private Meet.PorkMeet meet;

        public Builder addSolt(Solt solt) {
            this.solt = solt;
            return this;
        }

        public Builder addPepper(Pepper pepper) {
            this.pepper = pepper;
            return this;
        }

        public Builder addMeet(Meet.PorkMeet meet) {
            this.meet = meet;
            return this;
        }

        public Steak build() {
            return new Steak(this);
        }
    }

    @Override
    public String eat() {
        return super.eat() + "steak";
    }

    @Override
    public String toString() {
        return "STEAK { " + solt + ", " + pepper + ", " + meet + " }.";
    }


}
