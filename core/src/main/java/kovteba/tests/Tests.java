package kovteba.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kovteba
 */
public class Tests {

    public static void main(String[] args) {

//        Test20 test20 = new Test20();
//        test20.test();

        Test23 test23 = new Test23();

    }
}

class Test20 {
    class MedicalStaff {
    }

    class Doctor extends MedicalStaff {
    }

    class Nurse extends MedicalStaff {
    }

    class HeadDoctor extends Doctor {
    }

    public void test() {
        MedicalStaff medic = new HeadDoctor();
        if (medic instanceof Nurse) {
            System.out.println("Nurse");
        } else if (medic instanceof Doctor) {
            System.out.println("Doctor");
        } else if (medic instanceof HeadDoctor) {
            System.out.println("HeadDoctor");
        }
    }
}

class Test22 {
    public static int method() {
        final int loc;
//        System.out.println(loc);//1 <----- error compile
        loc = 4;//2
        return loc + 1;//3
    }

    public static void main(String[] args) {
        method();
        method();
        method();
        System.out.println(method());
    }
}

class Test23 {
    public Test23() {
        System.out.println("Empty");
    }

    public Test23(int i) {
        this(i, i);
        System.out.println("One");
    }

    public Test23(int i, int j) {
        this();
        System.out.println("Two");
    }

    public static void main(String[] args) {

        Test23 q = new Test23(2, 3); //1
    }
}

class Test24 {
    public void meth(Number obj) {
        System.out.print("1");
    }

    public void meth(Character obj) {
        System.out.print("2");
    }

    private static void meth(Integer obj) {
        System.out.print("3");
    }

    public void meth(int i) {
        System.out.print("4");
    }

    public void meth(double d) {
        System.out.print("5");
    }

    public static void main(String[] args) {
        Test24 q = new Test24();
        Number n = 67;
        Integer i = 78;
        q.meth(n);
        q.meth(i);
    }
}

class Test26 {
    enum Numbers {ONE, TWO, THREE, FOUR, FIVE}

    ;

    public static void main(String[] args) {
        Numbers n1 = Numbers.ONE;
        Numbers n2 = Numbers.ONE;//1
        if (n1 == n2) {
            System.out.print("true");
        } else {
            System.out.print("false");
        }
        System.out.println(Numbers.FIVE.ordinal());//2
    }
}

class Test28 {
    public class Quest43 {
        private final void method() {
        } //1
    }

    class Quest431 extends Quest43 {
        public void method() {
        } //2
    }
}

class Test29 {
    public class Quest51 {
        public String toString() {
            return getClass().getSimpleName();
        }

        class Quest52 extends Quest51 {
        }

        class Quest53 extends Quest52 {
        }

        public void main(String[] args) {
            Quest53 q = new Quest53();
            System.out.println(q.toString());
        }
    }
}

class Test30 {
    class Item {
        public int item;

        Item(int item) {
            this.item = item;
        }
    }

    public class Quest61 {
        public void main(String[] args) {
            Item ar1[] = {new Item(1), new Item(2), new Item(3)};
            Item ar2[] = ar1.clone();
            ar2[0].item = 4;
            System.out.println(ar1[0].item + " " + ar1[1].item + " " + ar1[2].item);
        }
    }
}

class Test31 {
    public class Quest1 {
        public /*static*/ void main(String[] args) {
            for (Numbers num : Numbers.values()) {
                System.out.print(num.getNumber());
            }
        }
    }

    enum Numbers {
        ONE(1), TWO(2) {
            public int getNumber() {
                return x + x;
            }
        }, THREE(3) {
            public int getNumber() {
                return x + x + x;
            }
        }, FOUR(4), FIVE(5);
        int x;

        Numbers(int x) {

            this.x = x;
        }

        public int getNumber() {

            return x;
        }
    }
}

class Test32 {
    public class Quest3 {
        public /*static*/ void main(String[] args) {
            Outer obj = new Outer().new Inner1();

            obj.print();
        }
    }

    class Outer {
        public void print() {
        }

        class Inner1 /*-----------*/ extends Outer { //<------
            public void print() {

                System.out.println("In inner.");

            }
        }
    }
}

class Test35 {
    interface Quest10 {
        Number returner();
    }

    abstract class Quest100 {
        public abstract Integer returner();
    }

    public class Quest1 extends Quest100 implements Quest10 {//line 1

        @Override //line 2
        public Integer returner() {// line 3
            return new Integer(6);
        }

        public void main(String[] args) {
            Quest1 quest = new Quest1();
            Quest10 quest10 = quest;
            Quest100 quest100 = quest;
            System.out.println(quest10.returner() + "" + quest100.returner());
        }
    }
}

class Test41 {
    public static void main(String[] args) {
        try {
            FileReader fr1 = new FileReader("kovteba/tests/test1.txt");
            try {
                FileReader fr2 = new FileReader("test2.txt");
            } catch (IOException e) {

                System.out.print("test2");
            }
            System.out.print("+");
        } catch (FileNotFoundException e) {
            System.out.print("test1");
        }
        System.out.print("+");
    }
}

class Test42 {
    private int qQ;
    public Test42(int q) {
        qQ = 12 / q;//1
    }
    public int getQQ() {
        return qQ;//2
    }
    public static void main(String[] args) {
        Test42 test42 = null;
        try {
            test42 = new Test42(0);//3
        } catch (Exception e) {//4
        }
        System.out.println(test42.getQQ());//5
    }
}
























