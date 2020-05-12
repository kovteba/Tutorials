package kovteba.concurrency;

class ThreadExampleJoin extends Thread {

   ThreadExampleJoin(String name) {
      super(name);
   }

   @Override
   public void run() {
      System.out.printf("%s started... \n", Thread.currentThread().getName());
      try {
         Thread.sleep(500);
      } catch (InterruptedException e) {
         System.out.println("Thread has been interrupted");
      }
      System.out.printf("%s fiished... \n", Thread.currentThread().getName());
   }


   static class Main {
      public static void main(String[] args) throws InterruptedException {
         System.out.println("Main thread started...");
         ThreadExampleJoin threadExampleJoin = new ThreadExampleJoin("ThreadExampleJoin");
         threadExampleJoin.start();
         threadExampleJoin.join();
         System.out.println("Main thread finished...");
      }
   }

}

/*
   RESULT:
      Main thread started...
      ThreadExampleJoin started...
      ThreadExampleJoin fiished...
      Main thread finished...

   EXPLAIN:
      Метод join заставляет ожидать main поток завершение потока на котором вызван этот метод
*/

class Accountant extends Thread {

   int x, y;
   static int result = 0;
   static Accountant accountant = null;

   public Accountant(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public static int sum(int x, int y) {
      accountant = new Accountant(x, y);
      accountant.start();
      System.out.println("Parallel evaluation: op1 " + x + ", op2 " + y);
      return result;
   }

   @Override
   public synchronized void start() {
      try {
         accountant.join();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      result = x + y;
   }

   static class Main {
      public static void main(String[] args) {
         System.out.println(Accountant.sum(3, 4));
      }
   }
}

