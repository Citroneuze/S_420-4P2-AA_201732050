package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class DeplacementJoueur implements StrategieDeplacementVaiseau {

    @Override
    public void deplacer(JoueurSpaceShip joueur, GameContainer gc) throws SlickException {
        int indexCalqueObstacles = Application_spaceInvader.getInstance().getJeu().getIndexCalqueObstacles();
        TiledMap map = Application_spaceInvader.getInstance().getJeu().getMap();
        //Saisie de touches pour actions
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if (map.getTileId(joueur.getCaseX() + 1, joueur.getCaseY(), indexCalqueObstacles) == 0) {
                joueur.setDirection(Direction.RIGHT);
                joueur.moveRight();
            }
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            if (map.getTileId(joueur.getCaseX() - 1, joueur.getCaseY(), indexCalqueObstacles) == 0) {
                joueur.setDirection(Direction.LEFT);
                joueur.moveLeft();
            }
        }

    }
}
