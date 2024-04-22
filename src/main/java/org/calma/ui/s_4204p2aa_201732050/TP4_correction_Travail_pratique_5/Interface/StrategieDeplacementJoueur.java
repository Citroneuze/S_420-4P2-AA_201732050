package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Joueur;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public interface StrategieDeplacementJoueur {
    void deplacer(Joueur joueur, GameContainer gc) throws SlickException;
}
