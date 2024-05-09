package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, Clip> clips = new HashMap<>();

    public void loadSound(String identifier, String filePath) {
        try {
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                System.err.println("File not found at path: " + soundFile.getAbsolutePath());
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clips.put(identifier, clip);
        } catch (Exception e) {
            System.err.println("Error loading sound " + identifier + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playSound(String identifier) {
        Clip clip = clips.get(identifier);
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);  // Rewind to the beginning
            clip.start();
        }
    }

    public void stopSound(String identifier) {
        Clip clip = clips.get(identifier);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loopSound(String identifier) {
        Clip clip = clips.get(identifier);
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopAllSounds() {
        clips.values().forEach(Clip::stop);
    }

    public void closeAllClips() {
        clips.values().forEach(Clip::close);
    }
}


