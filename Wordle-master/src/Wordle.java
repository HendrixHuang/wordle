import containers.WordleFrame;
import musicPlayer.StartPanelPlayer;


public class Wordle {

    public static void main(String[] args) {
        Runnable r1 = () -> {
            WordleFrame frame = new WordleFrame();
        };

        Runnable r2 = () -> {
            StartPanelPlayer player = new StartPanelPlayer();
            int au_return;
            while(true)
            {
                au_return = player.play();  // play 1s
                if(au_return==0)  // if ends, replay
                {
                    System.out.println("audio ended");
                    player.playFrom(0);
                    break;
                }
                else if(au_return==-1)
                {
                    System.out.println("Error");
                    break;
                }
            }
            player.stop_playing();
        };

        r1.run();
//        r2.run();
    }
}
