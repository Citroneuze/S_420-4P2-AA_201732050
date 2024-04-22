package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.RabbitFactory;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementLapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Lapin;
import org.newdawn.slick.SlickException;

public class FabriqueLapins {

    public static Lapin getLapin(String couleur, int caseX, int caseY, StrategieDeplacementLapin strategieDeplacement) throws SlickException {
        System.out.println(couleur);
        switch (couleur) {
            case "blanc":
                return new LapinBlanc(caseX, caseY, strategieDeplacement);
            case "noir":
                return new LapinNoir(caseX, caseY, strategieDeplacement);
            case "gris":
                return new LapinGris(caseX, caseY, strategieDeplacement);
            case "beige":
                return new LapinBeige(caseX, caseY, strategieDeplacement);
            case "blancbleu":
                return new LapinBlancBleu(caseX, caseY, strategieDeplacement);
            case "rose":
                return new LapinRose(caseX, caseY, strategieDeplacement);
            case "beigefonce":
                return new LapinBeigeFonce(caseX, caseY, strategieDeplacement);
            case "brun":
                return new LapinBrun(caseX, caseY, strategieDeplacement);
            default:
                throw new IllegalArgumentException("Couleur de lapin inconnue : " + couleur);
        }
    }
}


