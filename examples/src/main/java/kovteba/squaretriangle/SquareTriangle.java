package kovteba.squaretriangle;

public class SquareTriangle {

    public static void main(String[] args) {

        System.out.println("Square triangle = " + triangle(1, 3, 2, -5, -8, 4));

    }

    public static double triangle(int x1, int y1, int x2, int y2, int x3, int y3) {

        double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        double b = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));

        double c = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));

        double p = (a + b + c) / 2;

        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println(a + " " + b + " " + c);

        return s;

    }

}
