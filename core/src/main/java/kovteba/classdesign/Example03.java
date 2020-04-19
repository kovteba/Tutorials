package kovteba.classdesign;

class A03 {
    public void m1() {
        System.out.print(" A_m1");
    }

    public void m2() {
        System.out.print(" A_m2");
    }
}

class B03 extends A03 {
    /*private*/ public void m1() { // <----
        System.out.print(" B_m1");
    }

    public void m2() {
        System.out.print(" B_m2");
        m1();
    }
}


class Main03 {
    public static void main(String[] args) {
        A03 a03 = new B03();
        a03.m2();
        a03.m1();
    }
    //result : compile error
    //попытка сужения уровня доступа
}