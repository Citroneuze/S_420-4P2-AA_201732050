package org.calma.ui.s_4204p2aa_201732050.laboratoire5.models;

import java.util.ArrayList;

public class SalmonModel {
    private int currentIndex;
    private final int STARTTURNS = 3;
    private ArrayList<String> sequence;
    private final String[] COLORS = {"g", "r", "y", "b"};
    private boolean gameOn;

    public SalmonModel(){
        currentIndex = 0;

        sequence = new ArrayList<String>();

        setGameOn(false);
    }

    public void start(){
        sequence.clear();

        generateRandomSequence();

        setGameOn(true);

        currentIndex = 0;
    }

    private void generateRandomSequence(){
        for(int i = 0; i < STARTTURNS; i++){
            sequence.add(randomColor());
        }
    }

    public boolean checkSequence(String color){
        if((sequence.get(currentIndex)).equals(color)){
            if(currentIndex == (sequence.size()-1)){
                addToSequence();

                currentIndex = 0;

                return true;
            }

            currentIndex++;

            return true;
        }
        else{
            endGame();
            System.out.println("fin de jeu");

            return false;
        }
    }

    public void endGame(){
        currentIndex = 0;

        sequence.clear();

        setGameOn(false);
    }

    public void addToSequence(){
        sequence.add(randomColor());
    }

    private String randomColor(){
        return COLORS[(int) (Math.random()*4)];
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public ArrayList<String> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

}