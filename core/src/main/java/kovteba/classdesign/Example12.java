package kovteba.classdesign;

class Outher {
    public static int field1 = 1;
    public int field2 = 2;
    class Inner{
        private static final int field2 = 3;
        private static final int field3 = 4;
        /*private static*/ int getResult(){ // <-- cant be private and static
            return (field1 + field2 + field3);
        }
    }
}

class Main12 {
    public static void main(String[] args) {
        System.out.println(new Outher().new Inner().getResult());
    }
    //result : compile error
}
