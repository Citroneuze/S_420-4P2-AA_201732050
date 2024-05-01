package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class SystemeCapteursCollisionShip {
    private ArrayList<ObserverCollision> ecouteurs;

    public ArrayList<ObserverCollision> getEcouteurs() {
        return ecouteurs;
    }

    public void setEcouteurs(ArrayList<ObserverCollision> ecouteurs) {
        this.ecouteurs = ecouteurs;
    }

    public SystemeCapteursCollisionShip() {
        this.ecouteurs = new ArrayList<>();
    }

    //--------------------------------------------------------------------------
    public void connecterObservateur (ObserverCollision obsrv) {
        ecouteurs.add(obsrv); //Ajouté à l'ArrayList
    }
    public void observerCollisionShip(Jeu_spaceInvader jeu) throws SlickException {
        for (ObserverCollision observerMort : jeu.getCapteurs().getEcouteurs()) {

            if (observerMort instanceof Lazer && ((Lazer) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((Lazer) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
                jeu.getPersonnageActif().setEstActif(false);
            } else if (observerMort instanceof EnnemiSpaceShip && ((EnnemiSpaceShip) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((EnnemiSpaceShip) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
                ((EnnemiSpaceShip) observerMort).setEstActif(false);
            }
        }
    }
}

