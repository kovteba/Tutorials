package kovteba.classdesign;

class A09 {
}

class B09 extends A09 {
}

class C09 extends A09{
}

class Main09 {
    static void m1(A09 a){
        System.out.println(" A");
    }
    static void m1(B09 b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(new C09());
    }
    //result : A
}
