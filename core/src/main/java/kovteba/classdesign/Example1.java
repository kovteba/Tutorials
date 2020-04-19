package kovteba.classdesign;


class A01 {
    public void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
    }
}

 class B01 extends A01 {
    public void m1() {
        System.out.print(" B_m1");
    }

    public void m2() {
        System.out.print(" B_m2");
        m1();
    }
}


class Main01 {
    public static void main(String[] args) {
        A01 a01 = new B01();
        a01.m2();
    }
    //result : B_m2 B_m1
}
