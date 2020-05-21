package kovteba.huffman;

import java.io.*;
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
      try (BufferedReader br = new BufferedReader(new FileReader(new File("algorithms/files/" + pathName)))) {
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

   public static String readFromFile(String fileNme){
      File file = new File("algorithms/files/" + fileNme);
      StringBuilder builder = new StringBuilder();
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         String readLine = br.readLine();
         while (readLine != null){
            builder.append(readLine);
            readLine = br.readLine();
            if (readLine != null){
               builder.append("\n");
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return builder.toString();
   }
}