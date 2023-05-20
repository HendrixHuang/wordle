package musicPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class StartPanelPlayer {
    protected String path = "src/resources/music/rambling_pleat.wav";
    protected AudioFormat format = null;
    protected AudioInputStream aistream = null;
    protected float sampleRate = 0;
    protected float framelength = 0;
    protected DataLine.Info datalineinfo = null;
    protected SourceDataLine dataline = null;
    protected int played = 0;
    protected boolean _set = false;
    protected int length = 0;
    protected byte[] bytes = new byte[512];

    public StartPanelPlayer() {
        this.set(path);
    }

    public boolean set(String p)
    {
        if (_set)
        {
            boolean stop_playing_return = stop_playing();
            if (!stop_playing_return)
            {
                return false;
            }
        }
        path = p;
        try {
            aistream = AudioSystem.getAudioInputStream(new File(path));
            format = aistream.getFormat();
            sampleRate = format.getSampleRate();
            framelength = aistream.getFrameLength();
            datalineinfo = new DataLine.Info(SourceDataLine.class, format);
            dataline = (SourceDataLine) AudioSystem.getLine(datalineinfo);
        } catch (LineUnavailableException err) {
            System.out.println("LineUnavailableException");
            return false;
        } catch (UnsupportedAudioFileException err) {
            System.out.println("UnsupportedAudioFileException");
            return false;
        } catch (IOException err) {
            System.out.println("IOException");
            err.printStackTrace();
            return false;
        }
        try {
            // open
            bytes = new byte[512];
            length = 0;
            dataline.open(format);
            dataline.start();
            played = 0;
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            stop_playing();  // close current
            return false;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            stop_playing();  // close
            return false;
        }
        _set = true;  // init success
        return true;
    }

    /**
     * playFrom
     * @return int
     * -1：wrong
     * 0：ends
     * 1：normal
     */
    public int playFrom(int t)
    {
        if (!_set)
        {
            return -1;
        }
        // reset
        boolean stop_playing_return = stop_playing();
        if (!stop_playing_return) {
            return -1;
        }
        boolean set_return = set(path);
        if (!set_return) {
            return -1;
        }
        boolean audio_ended = false;
        try {
            while (played < t)
            {
                length = aistream.read(bytes);
                played++;
                if (length <= 0)
                {
                    audio_ended = true;
                    break;
                }
            }
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return -1;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return -1;
        }
        if (audio_ended) {
            return 0;
        }
        return 1;
    }

    public boolean stop_playing()  // stop play
    {
        try {
            aistream.close();
            dataline.drain();
            dataline.close();

            aistream = null;
            format = null;
            datalineinfo = null;
            dataline = null;
            _set = false;  // reset
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return false;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * playFrom
     * @return int
     * -1：wrong
     * 0：ends
     * 1：normal
     */
    public int play() {
        boolean audio_ended = false;  // is end?
        try {
            length = aistream.read(bytes);
            if (length <= 0)  // ends and quit
            {
                audio_ended = true;
            } else {
                dataline.write(bytes, 0, length);
                played++;
            }
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return -1;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return -1;
        }
        if (audio_ended) {
            return 0;
        }
        return 1;
    }

    /**
     * get audio path
     * @return audio path
     */
    public String getPath()
    {
        if (!_set) {
            return "Error";
        }
        return path;
    }

    /**
     * get play progress
     * @return progress
     */
    public int getPlayed()
    {
        if (!_set) {
            return -1;
        }
        return played;
    }

    /**
     * get play time
     * @return play time
     */
    public float getSecLength()
    {
        if (!_set) {
            return -1;
        }
        return framelength / sampleRate;
    }


}
