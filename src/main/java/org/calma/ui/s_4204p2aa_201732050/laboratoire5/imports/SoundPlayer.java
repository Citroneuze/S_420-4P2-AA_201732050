package org.calma.ui.s_4204p2aa_201732050.laboratoire5.imports;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

// Référence des notes MIDI
// https://soundprogramming.net/file-formats/midi-note-frequencies/
// Référence des instruments MIDI
// https://soundprogramming.net/file-formats/general-midi-instrument-list/

public class SoundPlayer {
    private int         volume;
    private MidiChannel channel;

    public SoundPlayer(){
        this.volume = 200;

        try {
            Synthesizer synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
            channel = synthetiseur.getChannels()[0];

            //Set instrument to piano (0)
            channel.programChange(128);

        }
        catch (MidiUnavailableException ex) {
            System.out.println(ex.toString());
        }
    }

    //Joue la note dont le numéro est en paramètre
    public void note_on(int note){
        channel.noteOn(note, volume);
    }
    public void note_on(int note, int intrument){
        // This selects a specific instrument from the currently selected bank of instruments.
        channel.programChange(intrument);
        channel.noteOn(note, volume);
    }

    //Arrête de jouer la note dont le numéro est en paramètre
    public void note_off(int note){
        channel.noteOff(note);
    }
    public void note_off(int note, int intrument){
        // This selects a specific instrument from the currently selected bank of instruments.
        channel.programChange(intrument);
        channel.noteOff(note);
    }

    public void setInstrument(int instrument){
        channel.programChange(instrument);
    }
}

