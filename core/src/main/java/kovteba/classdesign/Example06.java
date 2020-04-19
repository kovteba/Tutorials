package kovteba.classdesign;

class A06 {
    public void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}

class B06 extends A06 {
    public /*static*/ void m1() { // <-- cant be static
        System.out.print(" B_m1");
    }
}


class Main06 {
    public static void main(String[] args) {
        A06 a = new B06();
        a.m1();
    }
    //result : compile error
}
