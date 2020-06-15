package kovteba.concurrency.examples;

import java.util.concurrent.locks.ReentrantLock;

class StoreReentrantLockApp {
   public static void main(String[] args) {
      CommonResourceStoreReentrantLock commonResource= new CommonResourceStoreReentrantLock();
      ReentrantLock locker = new ReentrantLock(); // создаем заглушку
      for (int i = 1; i < 6; i++){

         Thread t = new Thread(new CountThreadStoreReentrantLock(commonResource, locker));
         t.setName("Thread "+ i);
         t.start();
      }
   }
}
class CommonResourceStoreReentrantLock {
   int x=0;
}
class CountThreadStoreReentrantLock implements Runnable{
   CommonResourceStoreReentrantLock res;
   ReentrantLock locker;
   CountThreadStoreReentrantLock(CommonResourceStoreReentrantLock res, ReentrantLock lock){
      this.res=res;
      locker = lock;
   }
   public void run(){
      locker.lock(); // устанавливаем блокировку
      try{
         res.x=1;
         for (int i = 1; i < 5; i++){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            res.x++;
            Thread.sleep(100);
         }
      }
      catch(InterruptedException e){
         System.out.println(e.getMessage());
      }
      finally{
         locker.unlock(); // снимаем блокировку
      }
   }
}
