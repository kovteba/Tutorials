package kovteba.concurrency;

class StoreWaitNotifyApp {

   public static void main(String[] args) {

      StoreWaitNotify storeWaitNotify =new StoreWaitNotify();
      ProducerStoreWaitNotify producerStoreWaitNotify = new ProducerStoreWaitNotify(storeWaitNotify);
      ConsumerStoreWaitNotify consumerStoreWaitNotify = new ConsumerStoreWaitNotify(storeWaitNotify);
      new Thread(producerStoreWaitNotify).start();
      new Thread(consumerStoreWaitNotify).start();
   }
}
// Класс Магазин, хранящий произведенные товары
class StoreWaitNotify {
   private int product=0;
   public synchronized void get() {
      while (product<1) {
         try {
            wait();
         }
         catch (InterruptedException e) {
         }
      }

      product--;
      System.out.println("Покупатель купил 1 товар");
      System.out.println("Товаров на складе: " + product);
      notify();
   }
   public synchronized void put() {
      while (product>=3) {
         try {
            wait();
         }
         catch (InterruptedException e) {
         }
      }
      product++;
      System.out.println("Производитель добавил 1 товар");
      System.out.println("Товаров на складе: " + product);
      notify();
   }
}
// класс Производитель
class ProducerStoreWaitNotify implements Runnable{

   StoreWaitNotify storeWaitNotify;
   ProducerStoreWaitNotify(StoreWaitNotify storeWaitNotify){
      this.storeWaitNotify = storeWaitNotify;
   }
   public void run(){
      for (int i = 1; i < 6; i++) {
         System.out.println(Thread.holdsLock(storeWaitNotify) + " LOCK <---");
         storeWaitNotify.put();
      }
   }
}
// Класс Потребитель
class ConsumerStoreWaitNotify implements Runnable{

   StoreWaitNotify storeWaitNotify;
   ConsumerStoreWaitNotify(StoreWaitNotify storeWaitNotify){
      this.storeWaitNotify = storeWaitNotify;
   }
   public void run(){
      for (int i = 1; i < 6; i++) {
         storeWaitNotify.get();
      }
   }
}
