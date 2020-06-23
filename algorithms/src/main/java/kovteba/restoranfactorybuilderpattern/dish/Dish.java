package kovteba.restoranfactorybuilderpattern.dish;


import kovteba.restoranfactorybuilderpattern.visitor.ActionForVisitor;

public class Dish implements ActionForVisitor {

    protected String nameDish;
    protected double price;
    protected double dishWeight;

    public String getNameDish() {
        return nameDish;
    }

    public double getPrice() {
        return price;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    public void forOrder(){
    }

    @Override
    public String eat() {
        return "You are eat ";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
