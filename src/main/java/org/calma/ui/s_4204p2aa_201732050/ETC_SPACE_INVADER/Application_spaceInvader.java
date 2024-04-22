package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Application_spaceInvader {


    private static Jeu_spaceInvader jeu;
    private AppGameContainer app;
    public AppGameContainer getApp() {
        return app;
    }

    public void setApp(AppGameContainer app) {
        this.app = app;
    }
    public Jeu_spaceInvader getJeu() {
        return jeu;
    }
    private static Application_spaceInvader instance;
    public static Application_spaceInvader getInstance() {
        if (instance == null) {
            instance = new Application_spaceInvader();
        }
        return instance;
    }
    public static void main(String[] args) {
        int largeurAffichage = 640;
        int hauteurAffichage = 960;
        boolean siPleinEcran = false; // Changer à true pour activer le plein écran

        jeu = new Jeu_spaceInvader("Space Invader");

        try {
            AppGameContainer app = new AppGameContainer(jeu);
            app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(Application_spaceInvader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

