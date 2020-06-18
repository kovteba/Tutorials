package kovteba.haffman;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {

        String inputFile = "/input.txt";

        List<String> text = ReadAndWriteClass.inputFromFile(inputFile);

        MyHuffmanTree tree = MyHuffmanTree.buildHuffmanTree(text);

        Map<Character, String> codeTable = tree.coding();

        MyHuffmanTree.saveBinaryCode(codeTable);

        MyHuffmanTree.createCompressFile(text,codeTable);



//        String inputCompressedFile = "tmp/compressBinaryFile.txt";
//
//        List<String> textout = ReadAndWriteClass.inputFromFile(inputCompressedFile);

        String inputOutPutFile = "tmp/output.txt";

        List<String> textout = ReadAndWriteClass.inputFromFile(inputOutPutFile);
        Map<Character, char[]> newCodeTable = new HashMap<>();
        for (String str : textout){
            char[] chars = null;
            chars = str.toCharArray();

        }

        for (String str : textout){
            System.out.println(str);
        }

        MyHuffmanTree.deCoding(codeTable, text);


    }
}
