/**
 * Q5: Adapter Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Adapter Pattern acts as a bridge between two incompatible interfaces.
 * It wraps an existing class with a new interface so that it becomes compatible
 * with the client's expected interface.
 * 
 * Real-world analogy: A power adapter plug that allows a US device (flat pins) 
 * to plug into an Indian wall outlet (round pins).
 */

// Main class to demonstrate the Adapter Pattern (must be first for direct Java runner)
public class Q5_AdapterPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q5: Adapter Design Pattern Demo ===");
        
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_away.vlc");
        audioPlayer.play("avi", "mind_games.avi");
    }
}

// Target interface that the client expects
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee interface (incompatible interface that needs adaptation)
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete classes implementing the Adaptee interface
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter class implementing Target interface to bridge the two
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

// Concrete client class implementing MediaPlayer using Adapter
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        }
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } 
        else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
