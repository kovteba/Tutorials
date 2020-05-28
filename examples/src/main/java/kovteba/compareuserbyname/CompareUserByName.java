package kovteba.compareuserbyname;

import java.util.*;

public class CompareUserByName {
   public static void main(String[] args) {
      List<User> userList = new ArrayList<>();
      User user1 = new User("ASD");
      User user2 = new User("DFGE");
      User user3 = new User("SDFSD");
      User user4 = new User("SDFSDE");
      User user5 = new User("SDFSDFDS");
      userList.add(user4);
      userList.add(user1);
      userList.add(user5);
      userList.add(user2);
      userList.add(user3);

      userList.stream().map(User::getName).forEach(System.out::println);
      System.out.println();
      Collections.sort(userList, new MyComparator());
      userList.stream().map(User::getName).forEach(System.out::println);
   }
}

class MyComparator implements Comparator<User> {
   @Override
   public int compare(User o1, User o2) {
      if (o1.getName().compareTo(o2.getName()) > 0)
         return 1;
      else
         return -1;
   }
}

class User {
   private String name;

   public User(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(name, user.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }
}
