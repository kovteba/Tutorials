package kovteba.haffman;

import java.io.IOException;
import java.util.*;

public class MyHuffmanTree implements Comparable<MyHuffmanTree> {

    private Node root;

    //символ и его частота
    private static Map<Character, Integer> map = new HashMap<>();

    public MyHuffmanTree(Node root) {
        this.root = root;
    }

    //разбиваем текст на символы и посчитываем их
    public static Map<Character, Integer> characterCount(List<String> text) {
        ArrayList<Character> charList = new ArrayList<>();

        for (String str : text) {
            //разбиение на сомволы
            char[] fingStr = str.toCharArray();
            for (int i = 0; i < fingStr.length; i++) {
                charList.add(fingStr[i]);
            }
            //считаем количество поторений
            for (int i = 0; i < charList.size(); i++) {
                int count = 0;
                for (int j = 0; j < charList.size(); j++) {
                    if (charList.get(i).equals(charList.get(j))) {
                        count++;
                    }
                }
                map.put(charList.get(i), count);
            }
        }

        return map;
    }

    //Huffman tree
    public static MyHuffmanTree buildHuffmanTree(List<String> text) {
        Map<Character, Integer> map = characterCount(text);

        PriorityQueue<MyHuffmanTree> trees = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            trees.offer(new MyHuffmanTree(new Node(entry.getValue(), entry.getKey())));
        }
        while (trees.size() > 1) {
            // 4. - 5.
            MyHuffmanTree a = trees.poll();
            MyHuffmanTree b = trees.poll();
            trees.offer(new MyHuffmanTree(new Node(a, b)));
        }
        return trees.poll();
    }

    //save binary code
    public Map<Character, String> coding() {
        Map<Character, String> codeTable = new HashMap<>();
        coding(root, new StringBuilder(), codeTable);
        return codeTable;
    }

    private void coding(Node root, StringBuilder code, Map<Character, String> codeTable) {
        if (root.character != null) {
            codeTable.put(root.character, String.valueOf(code));
        } else {
            coding(root.leftChild, code.append('0'), codeTable);
            coding(root.rightChild, code.append('1'), codeTable);
        }
    }

    public static void deCoding(Map<Character, String> codeTable, List<String> text){

    }



    //
    public static void saveBinaryCode(Map<Character, String> codeTable){
        String outputFile = "tmp/output.txt";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Character, String> entry: codeTable.entrySet()){
            builder.append(entry.getKey() + " " + entry.getValue() + "\n");
        }

        ReadAndWriteClass.outputToFile(builder.toString(), outputFile, false);
    }

    //
    public static void createCompressFile(List<String> stringList, Map<Character, String> codeTable) throws IOException {
        String compressBinaryFile = "tmp/compressBinaryFile.txt";
        String compressByteFile = "tmp/compressByteFile.txt";
        StringBuilder builder = new StringBuilder();
        char[] chars = new char[0];
        for (String str : stringList){
            chars = str.toCharArray();
        }
        for (int i = 0; i < chars.length; i++) {
            for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                if (chars[i] == entry.getKey()){
                    builder.append(entry.getValue());
                }
            }
        }
        ReadAndWriteClass.writeToBinary(builder.toString(), compressBinaryFile);
        ReadAndWriteClass.outputToFile(builder.toString(), compressByteFile, false);
    }



    @Override
    public int compareTo(MyHuffmanTree tree) {
        return root.frequency - tree.root.frequency;
    }

    private static class Node {
        // Частота символа
        private Integer frequency;
        // Символ
        private Character character;
        // Левый потомок узла
        private Node leftChild;
        // Правый потомок узла
        private Node rightChild;

        public Node(Integer frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }

        public Node(MyHuffmanTree left, MyHuffmanTree right) {
            frequency = left.root.frequency + right.root.frequency;
            leftChild = left.root;
            rightChild = right.root;
        }

        @Override
        public String toString() {
            return "[id=" + frequency + ", data =" + character + "]";
        }
    }
}
