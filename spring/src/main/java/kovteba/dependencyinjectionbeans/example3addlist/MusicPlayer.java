package kovteba.dependencyinjectionbeans.example3addlist;


import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {

   private List<Music> musicList = new ArrayList<>();

   public void playMusicList(){
      for (Music s : musicList){
         System.out.println("Playing: " + s.getSong());
      }
   }

   public List<Music> getMusicList() {
      return musicList;
   }

   public void setMusicList(List<Music> musicList) {
      this.musicList = musicList;
   }
}