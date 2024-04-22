package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.RabbitFactory;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementLapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Lapin;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LapinBlanc extends Lapin {
    private Image imgAnimPersonnage = new Image("org/calma/ui/s_4204p2aa_201732050/TP4/bunny.png");
    public LapinBlanc(int caseX, int caseY, StrategieDeplacementLapin strategieDeplacement) throws SlickException {
        super(caseX, caseY, strategieDeplacement);
        int rowX = 35;
        int rowY = 6;
        int height = 31;
        int width = 32;
        this.setAnim_DOWN(getAnimation(imgAnimPersonnage,rowX, rowY,height,width));
        this.setAnim_LEFT(getAnimation(imgAnimPersonnage,rowX,rowY + height,height,width));
        this.setAnim_RIGHT(getAnimation(imgAnimPersonnage,rowX,rowY + height * 2,height,width));
        this.setAnim_UP(getAnimation(imgAnimPersonnage,rowX,rowY + height * 3,height,width));
    }
}
