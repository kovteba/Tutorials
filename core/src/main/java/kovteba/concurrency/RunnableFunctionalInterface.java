package kovteba.concurrency;

public class RunnableFunctionalInterface {

   public static void main(String[] args) {
      System.out.println("Main thread started...");
      Runnable r = ()->{
         System.out.printf("%s started... \n", Thread.currentThread().getName());
         try{
            Thread.sleep(500);
         }
         catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
         }
         System.out.printf("%s finished... \n", Thread.currentThread().getName());
      };
      Thread myThread = new Thread(r,"MyThread");
      myThread.start();
      System.out.println("Main thread finished...");
   }

}

/*
   RESULT:
      Main thread started...
      Main thread finished...
      MyThread started...
      MyThread finished...

   EXPLAIN:
      Метод join заставляет ожидать main поток завершение потока на котором вызван этот метод
*/
