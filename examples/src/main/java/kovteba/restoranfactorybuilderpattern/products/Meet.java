package kovteba.restoranfactorybuilderpattern.products;

public class Meet {


    //for steak
    public static class PorkMeet {

        private final double PRICE_FOR_KILO = 200;

        private String kindMeet;
        private double timeForPreparing;
        private double weight;
        private double priceForDish;

        public PorkMeet(String kindMeet, double weight) {
            this.kindMeet = kindMeet;
            this.weight = weight;

        }



        @Override
        public String toString() {
            return kindMeet;
        }
    }



    public static class Ribs {
        private String ribs;
        private double price;

        public String getRibs() {
            return ribs;
        }

        public void setRibs(String ribs) {
            this.ribs = ribs;
        }

        public Ribs(String ribs) {
            this.ribs = ribs;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
