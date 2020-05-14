package kovteba.dependencyinjectionbeans.example3addlist;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Example3App {
   public static void main(String[] args) {

      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusicList();

      context.close();
   }
}
