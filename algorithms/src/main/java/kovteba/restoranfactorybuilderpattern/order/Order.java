package kovteba.restoranfactorybuilderpattern.order;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<String, Double> youOrder = new HashMap<String, Double>();

    public Map<String, Double> getYouOrder() {
        return youOrder;
    }

    private double finalScore;

    public double getFinalScore() {
        return finalScore;
    }

    public double addToFinalScore(double price){
        return finalScore += price;
    }



    public void addToYouOrder(String name, double price){
        youOrder.put(name, price);
    }

}
