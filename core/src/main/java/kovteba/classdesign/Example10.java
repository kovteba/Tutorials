package kovteba.classdesign;

class A10 {
}

class B10 extends A10 {
}

class C10 extends B10{
}

class Main10 {
    static void m1(A10 a){
        System.out.println(" A");
    }
    static void m1(B10 b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(new C10());
    }
    //result : B
}
