package kovteba.dependencyinjectionbeans.anotationexamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MusicPlayer {

   @PostConstruct
   private void init(){
      System.out.println("Init Player");
   }

   @PreDestroy
   private void dest(){
      System.out.println("Destroy Player");
   }

   private Music music;

   @Value("${player.volume}")
   private int volume;

   // IoC
   @Autowired
   public MusicPlayer(@Qualifier("rockMusic") Music music) {
      this.music = music;
   }

   public String playMusic(){
      return "Playing: " + music.getSong();
   }

   public int getVolume() {
      return volume;
   }
}
