package kovteba.restoranfactorybuilderpattern.dish;


import kovteba.restoranfactorybuilderpattern.products.Cuccumber;
import kovteba.restoranfactorybuilderpattern.products.Mozzarella;
import kovteba.restoranfactorybuilderpattern.products.Oil;
import kovteba.restoranfactorybuilderpattern.products.Tommato;

public class GreekSalad extends Dish {
    private Cuccumber cuccumber;
    private Tommato tommato;
    private Oil oil;
    private Mozzarella mozzarella;

    public GreekSalad(Builder builder) {
        this.mozzarella = builder.mozzarella;
        this.cuccumber = builder.cuccumber;
        this.oil = builder.oil;
        this.tommato = builder.tommato;
    }

    public static class Builder {

        private Cuccumber cuccumber;
        private Tommato tommato;
        private Oil oil;
        private Mozzarella mozzarella;

        public Builder addCuccumber(Cuccumber cuccunber) {
            this.cuccumber = cuccunber;
            return this;
        }

        public Builder addTommato(Tommato tommato) {
            this.tommato = tommato;
            return this;
        }

        public Builder addOil(Oil oil) {
            this.oil = oil;
            return this;
        }

        public Builder addMozzarella(Mozzarella mozzarella) {
            this.mozzarella = mozzarella;
            return this;
        }

        public GreekSalad build() {
            return new GreekSalad(this);
        }
    }
}
