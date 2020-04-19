package kovteba.classdesign;

class A05 {
    private void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
        m1();
    }
}

class B05 extends A05 {
    public void m1() {
        System.out.print(" B_m1");
    }
}


class Main05 {
    public static void main(String[] args) {
        A05 a = new B05();
        a.m2();
    }
    //result : A_m2 A_m1
}