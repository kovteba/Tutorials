package kovteba.classdesign;

class A08 {
    int i = 1;
    public void m1() {
        System.out.print(" A_m1:" + i);
    }
}

class B08 extends A08 {
    double i = 1.1;
    public void m1() { // <-- cant be static
        System.out.print(" B_m1:" + i);
    }
}


class Main08 {
    public static void main(String[] args) {
        A08 a = new B08();
        a.m1();
        System.out.println(" a.i=" + a.i);
    }
    //result : B_m1:1.1 a.i=1
}