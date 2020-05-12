package kovteba.concurrency;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class StoreConditionApp {
   public static void main(String[] args) {
      StoreCondition storeCondition =new StoreCondition();
      ProducerStoreCondition producerStoreCondition = new ProducerStoreCondition(storeCondition);
      ConsumerStoreCondition consumerStoreCondition = new ConsumerStoreCondition(storeCondition);
      new Thread(producerStoreCondition).start();
      new Thread(consumerStoreCondition).start();
   }
}
// Класс Магазин, хранящий произведенные товары
class StoreCondition {
   private int product=0;
   ReentrantLock locker;
   Condition condition;

   StoreCondition(){
      locker = new ReentrantLock(); // создаем блокировку
      condition = locker.newCondition(); // получаем условие, связанное с блокировкой
   }

   public void get() {

      locker.lock();
      try{
         // пока нет доступных товаров на складе, ожидаем
         while (product<1)
            condition.await();

         product--;
         System.out.println("Покупатель купил 1 товар");
         System.out.println("Товаров на складе: " + product);

         // сигнализируем
         condition.signalAll();
      }
      catch (InterruptedException e){
         System.out.println(e.getMessage());
      }
      finally{
         locker.unlock();
      }
   }
   public void put() {

      locker.lock();
      try{
         // пока на складе 3 товара, ждем освобождения места
         while (product>=3)
            condition.await();

         product++;
         System.out.println("Производитель добавил 1 товар");
         System.out.println("Товаров на складе: " + product);
         // сигнализируем
         condition.signalAll();
      }
      catch (InterruptedException e){
         System.out.println(e.getMessage());
      }
      finally{
         locker.unlock();
      }
   }
}
// класс Производитель
class ProducerStoreCondition implements Runnable{

   StoreCondition storeCondition;
   ProducerStoreCondition(StoreCondition storeCondition){
      this.storeCondition = storeCondition;
   }
   public void run(){
      for (int i = 1; i < 6; i++) {
         storeCondition.put();
      }
   }
}
// Класс Потребитель
class ConsumerStoreCondition implements Runnable{

   StoreCondition storeCondition;
   ConsumerStoreCondition(StoreCondition storeCondition){
      this.storeCondition = storeCondition;
   }
   public void run(){
      for (int i = 1; i < 6; i++) {
         storeCondition.get();
      }
   }
}