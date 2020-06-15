package kovteba.concurrency.examples;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
   public static void main(String[] args) {
      Semaphore sem = new Semaphore(1); // 1 разрешение
      CommonResourceSemaphoreExample res = new CommonResourceSemaphoreExample();
      new Thread(new CountThreadSemaphoreExample(res, sem, "CountThread 1")).start();
      new Thread(new CountThreadSemaphoreExample(res, sem, "CountThread 2")).start();
      new Thread(new CountThreadSemaphoreExample(res, sem, "CountThread 3")).start();
   }
}
class CommonResourceSemaphoreExample {
   int x=0;
}
class CountThreadSemaphoreExample implements Runnable{
   CommonResourceSemaphoreExample res;
   Semaphore sem;
   String name;
   CountThreadSemaphoreExample(CommonResourceSemaphoreExample res, Semaphore sem, String name){
      this.res=res;
      this.sem=sem;
      this.name=name;
   }
   public void run(){
      try{
         System.out.println(name + " ожидает разрешение");
         sem.acquire();
         res.x=1;
         for (int i = 1; i < 5; i++){
            System.out.println(this.name + ": " + res.x);
            res.x++;
            Thread.sleep(100);
         }
      }
      catch(InterruptedException e){System.out.println(e.getMessage());}
      System.out.println(name + " освобождает разрешение");
      sem.release();
   }
}