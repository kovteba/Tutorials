package kovteba.dependencyinjectionbeans.anotationexamples;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
   public static void main(String[] args) {

//      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

      AnnotationConfigApplicationContext context =
          new AnnotationConfigApplicationContext(Config.class);

/*      Music rockMusic = context.getBean("someRockMusic", Music.class);
      MusicPlayer rockPlayer = new MusicPlayer(rockMusic);
      rockPlayer.playMusic();

      Music clasicalMusic = context.getBean("classicalMusic", Music.class);
      MusicPlayer classicalPlayer = new MusicPlayer(clasicalMusic);
      classicalPlayer.playMusic();*/


//      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//      musicPlayer.playMusic();

      Computer computer = context.getBean("computer", Computer.class);



      System.out.println(computer.toString());


      context.close();

   }
}
