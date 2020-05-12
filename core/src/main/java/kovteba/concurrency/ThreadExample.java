package kovteba.concurrency;

class ThreadExample  extends Thread {

   ThreadExample(String name){
      super(name);
   }

   @Override
   public void run(){
      System.out.printf("%s started... \n", Thread.currentThread().getName());
      try{
         Thread.sleep(500);
      }
      catch(InterruptedException e){
         System.out.println("Thread has been interrupted");
      }
      System.out.printf("%s fiished... \n", Thread.currentThread().getName());
   }

   static class Main {
      public static void main(String[] args) throws InterruptedException {
         System.out.println("Main thread started...");
         new ThreadExample("ThreadExample").start();
         System.out.println("Main thread finished...");
      }
   }

}

/*
   RESULT:
   Main thread started...
   Main thread finished...
   ThreadExample started...
   ThreadExample fiished...

   EXPLAIN:

*/





