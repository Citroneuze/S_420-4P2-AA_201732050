package org.calma.ui.s_4204p2aa_201732050.Laboratoire7;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JeuApplication {
    public static void main(String[] args){
        int largeurAffichage = 1440;
        int hauteurAffichage = 768;
        boolean siPleinEcran = false;

        try{
            AppGameContainer app = new AppGameContainer(new Jeu("Mon gentil premier jeu"));
            app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
            app.start();

        } catch (SlickException ex) {

            Logger.getLogger(JeuApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}