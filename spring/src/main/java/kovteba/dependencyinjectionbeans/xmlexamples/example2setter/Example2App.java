package kovteba.dependencyinjectionbeans.xmlexamples.example2setter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Example2App {
   public static void main(String[] args) {

      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusic();

      System.out.println(musicPlayer.getVolume());

      context.close();
   }
}
