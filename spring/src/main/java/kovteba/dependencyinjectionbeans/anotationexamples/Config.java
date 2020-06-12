package kovteba.dependencyinjectionbeans.anotationexamples;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("kovteba")
@PropertySource("classpath:musicPlayer.properties")
public class Config {

   @Bean
   public RockMusic rockBean() {
      return new RockMusic();
   }

   @Bean
   public MusicPlayer musicPlayer() {
      return new MusicPlayer(rockBean());
   }

}
