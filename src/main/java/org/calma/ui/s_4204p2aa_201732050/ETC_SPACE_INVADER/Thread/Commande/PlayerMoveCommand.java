package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Commande;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Command;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.ThreadGestion;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.Objects;


public class PlayerMoveCommand implements Command {
    private String direction;

    public PlayerMoveCommand(String direction) {
        this.direction = direction;
    }

    public void execute() throws SlickException {
        System.out.println("PlayerMoveCommand: " + direction);
        JoueurSpaceShip personnage = Jeu_spaceInvader.getInstance().getJoueur();
        TiledMap currentTiledMap = Jeu_spaceInvader.getInstance().getMap();
        int indexCalqueMur = currentTiledMap.getLayerIndex("wall");

        switch (direction.toUpperCase()) {
            case "RIGHT":
                if (currentTiledMap.getTileId(personnage.getCaseX() + 1, personnage.getCaseY(), indexCalqueMur) == 0) {
                    personnage.setDirection(Direction.RIGHT);
                    personnage.moveRight();
                }
                break;
            case "LEFT":
                if (currentTiledMap.getTileId(personnage.getCaseX() - 1, personnage.getCaseY(), indexCalqueMur) == 0) {
                    personnage.setDirection(Direction.LEFT);
                    personnage.moveLeft();
                }
                break;
        }
        ThreadGestion.focusSlickWindow(); // Focaliser sur la fenêtre Slick2D
    }
}