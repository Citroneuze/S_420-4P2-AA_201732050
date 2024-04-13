package org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement;

import java.util.concurrent.TimeUnit;

public abstract class Deplacement {
    public static void deplacementDelais(int timeout){
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
