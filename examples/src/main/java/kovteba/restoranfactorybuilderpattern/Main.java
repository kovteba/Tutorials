package kovteba.restoranfactorybuilderpattern;


import kovteba.restoranfactorybuilderpattern.dish.Dish;
import kovteba.restoranfactorybuilderpattern.dish.KitchenMenu;
import kovteba.restoranfactorybuilderpattern.order.Order;
import kovteba.restoranfactorybuilderpattern.staff.Waiter;
import kovteba.restoranfactorybuilderpattern.staff.cooks.CooksLIst;
import kovteba.restoranfactorybuilderpattern.staff.cooks.CooksStaffFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Order myOrger = new Order();
        Dish myDish;

        Scanner scanner = new Scanner(System.in);
        Waiter waiter = new Waiter();
        KitchenMenu[] kitchenMenus = KitchenMenu.values();
        int count = 1;
        for (KitchenMenu s : kitchenMenus) {
            System.out.println(count + " " + s);
            count++;
        }

        boolean exitWhile = true;
        while (exitWhile) {

            System.out.print("Select the desired number : ");
            int answer = scanner.nextInt();

            CooksStaffFactory cooksStaffFactory = new CooksStaffFactory();
            switch (answer) {
                case 1:
                    myDish = cooksStaffFactory
                            .setupCook(CooksLIst.COOK_HOT_POSITION)
                            .initDish(KitchenMenu.STEAK);
                    System.out.println(myDish.eat());
                    exitWhile = false;
                    break;
                case 2:
                    System.out.println(cooksStaffFactory
                            .setupCook(CooksLIst.COOK_HOT_POSITION)
                            .initDish(KitchenMenu.BBQ_RIBS).eat());
                    exitWhile = false;
                    break;
                case 3:
                    System.out.println(cooksStaffFactory
                            .setupCook(CooksLIst.COOK_HOT_POSITION)
                            .initDish(KitchenMenu.GREEK_SALAD).eat());
                    exitWhile = false;
                    break;
                case 4:
                    System.out.println(cooksStaffFactory
                            .setupCook(CooksLIst.COOK_HOT_POSITION)
                            .initDish(KitchenMenu.GARDEN_FRESH_SALAD).eat());
                    exitWhile = false;
                    break;
                default:
                    System.out.println("Choose correct option: ");
                    break;
            }
        }
        System.out.println(myOrger.getYouOrder());


    }
}
