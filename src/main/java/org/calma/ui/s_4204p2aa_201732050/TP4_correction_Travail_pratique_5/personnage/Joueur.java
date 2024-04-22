package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Observer.ObserverCollision;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;
import org.newdawn.slick.*;

public class Joueur extends Personnage {

    private ObserverCollision observer;
    private Image imgAnimPersonnage = new Image("org/calma/ui/s_4204p2aa_201732050/TP4/characters.png");
    private StrategieDeplacementJoueur strategieDeplacementJoueur;
    public Joueur(int caseX, int caseY, StrategieDeplacementJoueur strategie) throws SlickException {
        super("Joueur", caseX, caseY);
        this.strategieDeplacementJoueur = strategie;
        //déplacement spécifique au perso
        int rowX = 373;
        int rowY = 295;
        int height = 70;
        int width = 47;
        this.setAnim_DOWN(getAnimation(imgAnimPersonnage,rowX, rowY,height,width));
        this.setAnim_LEFT(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setAnim_RIGHT(getAnimation(imgAnimPersonnage,rowX,rowY+height*2,height,width));
        this.setAnim_UP(getAnimation(imgAnimPersonnage,rowX,rowY+height*3,height,width));
        this.setEstJoueur(true);
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Joueur render");
        super.render(gc,grphcs,direction);
    }
    public void update(GameContainer gc, int i) throws SlickException {
        //System.out.println("Joueur update");

        strategieDeplacementJoueur.deplacer(this, gc);


    }


    @Override
    public void alarme() {

    }
}
