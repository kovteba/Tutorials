package kovteba.huffman;

public class Node {
   private int frequence;//частота
   private char letter;//буква
   private Node leftChild;//левый потомок
   private Node rightChild;//правый потомок



   public Node(char letter, int frequence) { //собственно, конструктор
      this.letter = letter;
      this.frequence = frequence;
   }

   public Node() {}//перегрузка конструтора для безымянных узлов(см. выше в разделе о построении дерева Хаффмана)
   public void addChild(Node newNode) {//добавить потомка
      if (leftChild == null)//если левый пустой=> правый тоже=> добавляем в левый
         leftChild = newNode;
      else {
         if (leftChild.getFrequence() <= newNode.getFrequence()) //в общем, левым потомком
            rightChild = newNode;//станет тот, у кого меньше частота
         else {
            rightChild = leftChild;
            leftChild = newNode;
         }
      }

      frequence += newNode.getFrequence();//итоговая частота
   }

   public Node getLeftChild() {
      return leftChild;
   }

   public Node getRightChild() {
      return rightChild;
   }

   public int getFrequence() {
      return frequence;
   }

   public char getLetter() {
      return letter;
   }

   public boolean isLeaf() {//проверка на лист
      return leftChild == null && rightChild == null;
   }
}
