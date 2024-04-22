package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.jeu.Jeu;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {


    private static Jeu jeu;
    private AppGameContainer app;
    public AppGameContainer getApp() {
        return app;
    }

    public void setApp(AppGameContainer app) {
        this.app = app;
    }
    public Jeu getJeu() {
        return jeu;
    }
    private static Application instance;
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
    public static void main(String[] args) throws SlickException {

        int largeurAffichage = Constante.getTilesSize()*Constante.getTilesNbX();//dimensions de la fenêtre
        int hauteurAffichage = Constante.getTilesSize()*Constante.getTilesNbY();	//dimensions de la fenêtre
        boolean siPleinEcran = false;

        jeu = new Jeu(Constante.getNomJeu());

        try {
            AppGameContainer app = new AppGameContainer(jeu);
            app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
            app.setShowFPS(false);	//Affiche les frames par seconde
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
