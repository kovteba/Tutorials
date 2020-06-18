package kovteba.restoranfactorybuilderpattern.products;

public class Pepper {
    private String peppr;

    public Pepper(String peppr) {
        this.peppr = peppr;
    }

    public String getPeppr() {
        return peppr;
    }

    public void setPeppr(String peppr) {
        this.peppr = peppr;
    }

    @Override
    public String toString() {
        return peppr;
    }
}
