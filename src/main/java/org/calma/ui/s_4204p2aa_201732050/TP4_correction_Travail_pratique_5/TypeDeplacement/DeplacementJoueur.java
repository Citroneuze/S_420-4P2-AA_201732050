package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.TypeDeplacement;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Joueur;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class DeplacementJoueur implements StrategieDeplacementJoueur {

    @Override
    public void deplacer(Joueur joueur, GameContainer gc) throws SlickException {
        int indexCalqueObstacles = Application.getInstance().getJeu().getIndexCalqueObstacles();
        TiledMap map = Application.getInstance().getJeu().getMap();
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
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (map.getTileId(joueur.getCaseX(), joueur.getCaseY() - 1, indexCalqueObstacles) == 0) {
                joueur.setDirection(Direction.UP);
                joueur.moveUp();
            }
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (map.getTileId(joueur.getCaseX(), joueur.getCaseY() + 1, indexCalqueObstacles) == 0) {
                joueur.setDirection(Direction.DOWN);
                joueur.moveDown();
            }
        }
    }
}


