package org.calma.ui.s_4204p2aa_201732050.South_Park;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JeuApplication_SouthPark {
    public static void main(String[] args){
        int largeurAffichage = 960;
        int hauteurAffichage = 700;
        boolean siPleinEcran = false;
        try{
            AppGameContainer app = new AppGameContainer(new Jeu_SouthPark("South_Park"));
            app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);

            app.start();

        } catch (SlickException ex) {

            Logger.getLogger(JeuApplication_SouthPark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
