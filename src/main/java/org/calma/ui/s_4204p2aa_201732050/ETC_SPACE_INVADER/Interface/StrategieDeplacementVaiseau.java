package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public interface StrategieDeplacementVaiseau {
    void deplacer(JoueurSpaceShip joueur, GameContainer gc) throws SlickException;

}
