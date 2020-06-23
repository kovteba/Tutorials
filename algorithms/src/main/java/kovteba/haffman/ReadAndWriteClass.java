package kovteba.haffman;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteClass {

    public static void outputToFile(String outputString, String pathName, boolean appendOrNot) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathName), appendOrNot))) {
            bw.write(outputString + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file!");
        }
    }

    public static void outputToFile(byte[] outputString, String pathName, boolean appendOrNot) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathName), appendOrNot))) {
            bw.write(outputString + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file!");
        }
    }

    public static List<String> inputFromFile(String pathName) {
        List<String> hasRead = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(pathName)))) {
            String tmp;
            while ((tmp = br.readLine()) != null) {
                hasRead.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading from file!");
        }
        return hasRead;
    }


    public static void writeToBinary(String outputString, String pathName) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(pathName))) {
            byte[] bytes = new BigInteger(outputString, 2).toByteArray();
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file!");
        }
    }


}
