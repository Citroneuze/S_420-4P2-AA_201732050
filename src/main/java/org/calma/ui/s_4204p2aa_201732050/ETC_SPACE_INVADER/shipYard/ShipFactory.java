package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.shipYard;


import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.newdawn.slick.SlickException;

public class ShipFactory {
    public static EnnemiSpaceShip getEnnemi(String couleur, int caseX, int caseY, StrategieDeplacementEnnemi strategieDeplacement) throws SlickException {
        System.out.println(couleur);
        switch (couleur) {
            case "battleCruiser":
                return new BattleCruiser(caseX, caseY, strategieDeplacement);
            case "bomber":
                return new Bomber(caseX, caseY, strategieDeplacement);
            case "dreadnought":
                return new Dreadnought(caseX, caseY, strategieDeplacement);
            case "asteroid":
                return new Asteroid(caseX, caseY, strategieDeplacement);
            default:
                throw new IllegalArgumentException("Erreur mauvais nom");
        }
    }
}
