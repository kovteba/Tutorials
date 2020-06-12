package kovteba.dependencyinjectionbeans.xmlexamples.example3addlist;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class Example3App {
   public static void main(String[] args) {

      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusicList();

      context.close();
   }

//   @Bean
//   public static MusicPlayer getPlayer(){
//      return new MusicPlayer();
//   }
}
