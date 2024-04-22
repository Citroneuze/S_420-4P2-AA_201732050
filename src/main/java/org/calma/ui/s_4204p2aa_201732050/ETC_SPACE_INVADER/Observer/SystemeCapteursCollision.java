//package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer;
//
//import org.newdawn.slick.SlickException;
//
//import java.util.ArrayList;
//
//public class SystemeCapteursCollision {
//    private ArrayList<ObserverCollision> ecouteurs;
//
//    public ArrayList<ObserverCollision> getEcouteurs() {
//        return ecouteurs;
//    }
//
//    public void setEcouteurs(ArrayList<ObserverCollision> ecouteurs) {
//        this.ecouteurs = ecouteurs;
//    }
//
//    public SystemeCapteursCollision() {
//        this.ecouteurs = new ArrayList<>();
//    }
//
//    //--------------------------------------------------------------------------
//    public void connecterObservateur (ObserverCollision obsrv) {
//        ecouteurs.add(obsrv); //Ajouté à l'ArrayList
//    }
//    public void observerCollisionPersonnage(Jeu jeu) throws SlickException {
//        for (ObserverCollision observerMort : jeu.getCapteurs().getEcouteurs()) {
//
//            if (observerMort instanceof Projectile && ((Projectile) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((Projectile) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
//                jeu.getPersonnageActif().setEstActif(false);
//            } else if (observerMort instanceof ennemi && ((ennemi) observerMort).getCaseX() == jeu.getPersonnageActif().getCaseX() && ((ennemi) observerMort).getCaseY() == jeu.getPersonnageActif().getCaseY()) {
//                ((ennemi) observerMort).setEstActif(false);
//            }
//        }
//    }
//}

