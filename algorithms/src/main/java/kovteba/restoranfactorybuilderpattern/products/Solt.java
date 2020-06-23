package kovteba.restoranfactorybuilderpattern.products;

public class Solt {
    private String solt;
    private double weight;

    public Solt(String solt, double weight) {
        this.solt = solt;
        this.weight = weight;
    }

    public String getSolt() {
        return solt;
    }

    public void setSolt(String solt) {
        this.solt = solt;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return solt;
    }

}
