package kovteba.anotationforinstallvalue;


public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        Person person = new Person();
        person.setName("Dima");

        AddValue addValue = new AddValue();
        addValue.installAllValue(person);

        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getGender());



    }


}
