package kovteba.dependencyinjectionbeans.anotationexamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Computer {

   private int id;
   private MusicPlayer musicPlayer;

   @Autowired
   public Computer(MusicPlayer musicPlayer) {
      this.id = 1;
      this.musicPlayer = musicPlayer;
   }

   @Override
   public String toString() {
      return "Computer " + id + " " + musicPlayer.playMusic() + " ---> " + musicPlayer.getVolume();
   }
}
