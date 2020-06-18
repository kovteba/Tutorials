package kovteba.chesshorse;

import java.util.Scanner;

public class ChessHorse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);


        int[] line = {1, 2, 3, 4, 5, 6, 7, 8};
        String[] collom = {"a", "b", "c", "d", "e", "f", "g", "h"};

        for (int i = 0; i < collom.length; i++) {
            for (int j = 0; j < line.length; j++) {
                System.out.print(collom[j] + line[i] + " ");
            }
            System.out.println();
        }

        System.out.print("Enter start letter position : ");
        String startLetter = scanner.nextLine();

        System.out.print("Enter start number position : ");
        int startNumber = scanner.nextInt();


        System.out.print("Enter end letter position : ");
        String endLetter = scanner1.nextLine();

        System.out.print("Enter end number position : ");
        int endNumber = scanner1.nextInt();

        System.out.println(logicHorse(startLetter, startNumber, endLetter, endNumber));

    }

    public static String logicHorse(String startletter, int startNumber, String endletter, int endNumber) {
        int[] line = {1, 2, 3, 4, 5, 6, 7, 8};
        String[] collom = {"a", "b", "c", "d", "e", "f", "g", "h"};

        int startIndexLetter = 0;
        int startIndexNumber = 0;
        //ищем начальный индек для буквы start
        for (int i = 0; i < collom.length; i++) {
            if (collom[i].equals(startletter)) {
                startIndexLetter = i;
            }
        }
        //ищем начальный индек для цифры start
        for (int i = 0; i < line.length; i++) {
            if (line[i] == startNumber) {
                startIndexNumber = i;
            }
        }
        int endIndexLetter = 0;
        int endIndexNumber = 0;
        //ищем начальный индек для буквы end
        for (int i = 0; i < collom.length; i++) {
            if (collom[i].equals(endletter)) {
                endIndexLetter = i;
            }
        }
        //ищем начальный индек для цифры end
        for (int i = 0; i < line.length; i++) {
            if (line[i] == endNumber) {
                endIndexNumber = i;
            }
        }

        System.out.println("Start position : " + collom[startIndexLetter] + "" + line[startIndexNumber]);
        System.out.println("End position : " + collom[endIndexLetter] + "" + line[endIndexNumber]);
        String ansver = "its not possible";
        //////////
        if (startIndexLetter + 1 == endIndexLetter && startIndexNumber - 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 1 == endIndexLetter && startIndexNumber - 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter + 1 == endIndexLetter && startIndexNumber + 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 1 == endIndexLetter && startIndexNumber + 2 == endIndexNumber) {
            ansver = "its posibble";
        }
        //////////////////
        if (startIndexLetter + 2 == endIndexLetter && startIndexNumber - 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 2 == endIndexLetter && startIndexNumber + 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter + 2 == endIndexLetter && startIndexNumber + 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 2 == endIndexLetter && startIndexNumber - 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        return ansver;
    }


}
