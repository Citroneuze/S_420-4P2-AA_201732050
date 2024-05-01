package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.shipYard;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroid extends EnnemiSpaceShip {
    private Image imgAnimPersonnage = new Image("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/sprite/Asteroid01-Base.png");
    public Asteroid(int caseX, int caseY, StrategieDeplacementEnnemi strategieDeplacement) throws SlickException {
        super(caseX, caseY, strategieDeplacement);
        this.setStrategieDeplacement(strategieDeplacement);
        this.setNom("Asteroid");
        this.setFire(false);
        int rowX = 0;
        int rowY = 0;
        int height = 100;
        int width = 120;
        this.setAnim_LEFT(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setAnim_RIGHT(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setAnim_DOWN(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
    }

}
