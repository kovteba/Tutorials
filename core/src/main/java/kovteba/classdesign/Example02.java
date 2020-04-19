package kovteba.classdesign;


class A02 {
    public void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}

class B02 extends A02 {
    public void m1() {
        System.out.print(" B_m1");
    }
}


class Main {
    public static void main(String[] args) {
        A02 a02 = new B02();
        a02.m2();
    }
    //result : A_m2 B_m1
}
