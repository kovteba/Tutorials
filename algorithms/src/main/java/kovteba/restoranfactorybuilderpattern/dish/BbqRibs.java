package kovteba.restoranfactorybuilderpattern.dish;


import kovteba.restoranfactorybuilderpattern.products.Meet;
import kovteba.restoranfactorybuilderpattern.products.Pepper;
import kovteba.restoranfactorybuilderpattern.products.Solt;
import kovteba.restoranfactorybuilderpattern.products.Sous;

public class BbqRibs extends Dish {
    private Meet.Ribs ribs;
    private Sous sous;
    private Pepper pepper;
    private Solt solt;

    public BbqRibs(Builder builder) {
        this.ribs = builder.ribs;
        this.sous = builder.sous;
        this.pepper = builder.pepper;
        this.solt = builder.solt;
    }

    public static class Builder {
        private Solt solt;
        private Sous sous;
        private Pepper pepper;
        private Meet.Ribs ribs;

        public Builder addRibs(Meet.Ribs ribs) {
            this.ribs = ribs;
            return this;
        }

        public Builder addSous(Sous sous) {
            this.sous = sous;
            return this;
        }

        public Builder addPepper(Pepper pepper) {
            this.pepper = pepper;
            return this;
        }

        public Builder adSolt(Solt solt) {
            this.solt = solt;
            return this;
        }

        public BbqRibs build() {
            return new BbqRibs(this);
        }
    }

    @Override
    public String eat() {
        return super.eat() + "BBQ Ribs";
    }
}
