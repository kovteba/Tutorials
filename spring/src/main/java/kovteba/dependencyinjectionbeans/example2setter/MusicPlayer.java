package kovteba.dependencyinjectionbeans.example2setter;

public class MusicPlayer {

   private Music music;

   private int volume;

   public void playMusic(){
      System.out.println("Playing: " + music.getSong());
   }

   public Music getMusic() {
      return music;
   }

   public void setMusic(Music music) {
      this.music = music;
   }

   public int getVolume() {
      return volume;
   }

   public void setVolume(int volume) {
      this.volume = volume;
   }
}
