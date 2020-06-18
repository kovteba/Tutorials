package kovteba.anotationforinstallvalue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;


public class AddValue {

    public void installAllValue(Person person) throws IllegalAccessException, NoSuchFieldException {

        Class<?> clazz = person.getClass();

        Field[] fields = clazz.getDeclaredFields();

        String value = null;

        for (Field field : fields) {
            TestAnnotation testAnnotation = field.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                if (field.get(person) == null || field.get(person).equals(0)) {
                    if (field.getType().equals(int.class)) {
                        field.set(person, Integer.parseInt(findValue(testAnnotation.value())));
                    } else {
                        field.set(person, findValue(testAnnotation.value()));
                    }
                }
            }
        }

    }

    private String findValue(String name) {

//        InputStream in = AddValue.class.getClassLoader().getResourceAsStream("/app.properties");

        String value = null;

        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(new File("src/main/resources/app.properties"))) {
            props.load(in);
            value = props.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

}
