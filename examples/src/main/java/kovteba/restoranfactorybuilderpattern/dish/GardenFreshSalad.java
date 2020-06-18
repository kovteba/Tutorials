package kovteba.restoranfactorybuilderpattern.dish;


import kovteba.restoranfactorybuilderpattern.products.Cuccumber;
import kovteba.restoranfactorybuilderpattern.products.Oil;
import kovteba.restoranfactorybuilderpattern.products.Tommato;

public class GardenFreshSalad extends Dish {
    private Tommato tommato;
    private Cuccumber cuccumber;
    private Oil oil;

    public GardenFreshSalad(Builder builder) {
        this.oil = builder.oil;
        this.cuccumber = builder.cuccumber;
        this.tommato = builder.tommato;
    }

    public static class Builder {
        private Tommato tommato;
        private Cuccumber cuccumber;
        private Oil oil;

        public Builder addTommato(Tommato tommato) {
            this.tommato = tommato;
            return this;
        }

        public Builder addCuccumber(Cuccumber cuccunber) {
            this.cuccumber = cuccunber;
            return this;
        }

        public Builder addOil(Oil oil) {
            this.oil = oil;
            return this;
        }

        public GardenFreshSalad build() {
            return new GardenFreshSalad(this);
        }
    }
}
