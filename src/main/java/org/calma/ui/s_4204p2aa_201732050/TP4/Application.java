package org.calma.ui.s_4204p2aa_201732050.TP4;
import org.calma.ui.s_4204p2aa_201732050.TP4.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.calma.ui.s_4204p2aa_201732050.TP4.utils.Direction.LEFT;

public class Application {
    private static Application instance;
    private Jeu jeu;

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public static void main(String[] args) {
        // Créez une instance de Jeu
        Application app = Application.getInstance();
        app.setJeu(new Jeu("NomDuJeu")); // Remplacez "NomDuJeu" par le nom réel de votre jeu

        int largeurAffichage = 960;
        int hauteurAffichage = 960;
        boolean siPleinEcran = false;

        try {
            // Démarrage du jeu
            AppGameContainer appContainer = new AppGameContainer(app.getJeu());
            appContainer.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
            appContainer.setShowFPS(false);
            appContainer.start();
        } catch (SlickException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

}

