package kovteba.enumexample;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Seasons {
    WINTER("1"){
        @Override
        String value() {
            return "Test abstract method1";
        }
    },

    SUMMER("2"){
        @Override
        String value() {
            return "Test abstract method2";
        }
    },

    SPRING("3"){
        @Override
        String value() {
            return "Test abstract method3";
        }
    },

    FALL("4"){
        @Override
        String value() {
            return "Test abstract method4";
        }
    };

    private String value;

    Seasons(String s) {
        this.value = s;
    }

    String getValue(){
        return value;
    }

    abstract String value();
}

class Program{
    public static void main(String[] args) {
        for (Seasons s : Seasons.values()){
            System.out.println(s);
            System.out.println(s.name());
            System.out.println(s.ordinal());
            System.out.println(s.getValue());
        }

        List<Seasons> list = Arrays.asList(Seasons.values());
        System.out.println(Seasons.SPRING.value());

        Set<Seasons> seasonsSet = EnumSet.allOf(Seasons.class);
        System.out.println(seasonsSet.toString());

    }
}
