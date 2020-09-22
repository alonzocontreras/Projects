import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class Music extends JFrame implements Runnable{
    private Clip moveSound, crushSound, buttonSound, squishedSound, gameSound,endgameSound;
    private String crush, move;
    public Music(){//String... files
        String endgame = "src/resources/endgamesound.wav";
        String game = "src/resources/Music1.wav";
        move = "src/resources/Move.wav";
        crush = "src/resources/Crush.wav";
        String button = "src/resources/Button.wav";
        String squish = "src/resources/Squished.wav";
        buttonSound = createSound(button);
        squishedSound = createSound(squish);
        gameSound = createSound(game);
        endgameSound = createSound(endgame);

    }

    private Clip createSound(String fileName){
        try{
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            return clip;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void playSound(Clip clip){
        clip.start();
    }

    public void playEndGameExp(){
        playSound(endgameSound);
    }

    public void playGameExp(){
        playSound(gameSound);
    }

    public void playSquishedExp(){
        playSound(squishedSound);
    }

    public void playMoveExp(){
        moveSound = createSound(move);
        playSound(moveSound);
    }

    public void playCrushExp(){
        crushSound = createSound(crush);
        playSound(crushSound);
    }

    public void playButtonExp(){
        playSound(buttonSound);
    }

    public void stopMainGame(){
        stopSound(gameSound);
    }

    private void stopSound(Clip clip){
        clip.stop();
    }

    @Override
    public void run() {
    }
}