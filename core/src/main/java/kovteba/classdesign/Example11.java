package kovteba.classdesign;

class A11 {
}

class B11 extends A11 {
}

class Main11 {
    static void m1(A11 a){
        System.out.println(" A");
    }
    static void m1(B11 b){
        System.out.println(" B");
    }
    public static void main(String[] args) {
        m1(null);
    }
    //result : B
}
