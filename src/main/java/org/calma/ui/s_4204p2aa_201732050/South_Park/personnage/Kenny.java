package org.calma.ui.s_4204p2aa_201732050.South_Park.personnage;

import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Deplacement;
import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Pied;
import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Velo;
import org.newdawn.slick.SlickException;

public class Kenny extends Personnage implements Pied, Velo {
    public Kenny(int caseX, int caseY) throws SlickException {
        super("Kenny", caseX, caseY);
        //
        //deplacement specifique au perso
        int rowY = 78;
        this.setAnim_UP(getAnimation(360, rowY));
        this.setAnim_DOWN(getAnimation(3, rowY));
        this.setAnim_LEFT(getAnimation(478, rowY));
        this.setAnim_RIGHT(getAnimation(141, rowY));
    }

    @Override
    public void InventaireAjouter() {

    }

    @Override
    public void InventaireSupprimer() {

    }

    @Override
    public void marcher() {
        System.out.println(this.getNom() + "marche");
        this.setMovingStep(1);
        Deplacement.deplacementDelais(this.getDeplacementDelaisMarche());
    }

    @Override
    public void courir() {
        System.out.println(this.getNom() + "Court");
        this.setMovingStep(1);
        Deplacement.deplacementDelais(this.getDeplacementDelaisCourse());
    }

    @Override
    public void rouler() {
        System.out.println(this.getNom() + "Velo");
        this.setMovingStep(2);

    }
}
