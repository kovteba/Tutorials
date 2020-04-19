package kovteba.classdesign;

class A07 {
    public static void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}

class B07 extends A07 {
    public static void m1() { // <-- cant be static
        System.out.print(" B_m1");
    }
}


class Main07 {
    public static void main(String[] args) {
        B07 a = new B07();
        a.m2();
        a.m1();
    }
    //result : A_m2 A_m1 B_m1
}