package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Observer;


import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Observer.ObserverCollision;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.jeu.Jeu;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Lapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.projectile.Projectile;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class SystemeCapteursCollision {
    private ArrayList<ObserverCollision> ecouteurs;

    public ArrayList<ObserverCollision> getEcouteurs() {
        return ecouteurs;
    }

    public void setEcouteurs(ArrayList<ObserverCollision> ecouteurs) {
        this.ecouteurs = ecouteurs;
    }

    public SystemeCapteursCollision() {
        this.ecouteurs = new ArrayList<>();
    }

    //--------------------------------------------------------------------------
    public void connecterObservateur (ObserverCollision obsrv) {
        ecouteurs.add(obsrv); //Ajouté à l'ArrayList
    }
    public void observerCollisionPersonnage(Jeu jeu) throws SlickException {
        for (ObserverCollision observerMort : jeu.getCapteurs().getEcouteurs()) {

            if (observerMort instanceof Projectile && ((Projectile) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((Projectile) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
                jeu.getPersonnageActif().setEstActif(false);
            } else if (observerMort instanceof Lapin && ((Lapin) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((Lapin) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
                ((Lapin) observerMort).setEstActif(false);
            }
        }
    }
}

