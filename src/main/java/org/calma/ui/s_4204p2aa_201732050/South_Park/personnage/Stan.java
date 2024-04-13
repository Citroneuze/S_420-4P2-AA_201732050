package org.calma.ui.s_4204p2aa_201732050.South_Park.personnage;

import org.newdawn.slick.SlickException;

public class Stan extends Personnage{
    public Stan(int caseX, int caseY) throws SlickException {
        super("Stan", caseX, caseY);
        //
        //deplacement specifique au perso
        int rowY = 119;
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
}
