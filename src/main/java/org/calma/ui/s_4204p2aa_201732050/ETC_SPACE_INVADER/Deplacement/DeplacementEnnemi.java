package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.Random;

public class DeplacementEnnemi implements StrategieDeplacementEnnemi {
    private long lastMoveTime; // Temps du dernier déplacement
    private int delay = 2000; // Délai en millisecondes (1 seconde) // 1 second
    private int tilesSize = 32;
    @Override
    public void deplacer(EnnemiSpaceShip ennemi, GameContainer gc) {
        long currentTime = System.currentTimeMillis(); // Temps actuel en millisecondes

        // Vérifie si le délai est écoulé depuis le dernier mouvement
        if (currentTime - ennemi.getLastMoveTime() >= delay) {
            ennemi.setLastMoveTime(currentTime);
            Random random = new Random();
            Direction direction = Direction.getRandomDirection(); // Direction aléatoire
            System.out.println("deplacement");
            int posX = ennemi.getCaseX() * tilesSize;
            int posY = ennemi.getCaseY() * tilesSize;
            int indexCalqueObstacles = Application_spaceInvader.getInstance().getJeu().getIndexCalqueObstacles();

            // Vérification des limites de la carte
            int maxX = Application_spaceInvader.getInstance().getJeu().getMap().getWidth() - 1;
            int maxY = Application_spaceInvader.getInstance().getJeu().getMap().getHeight() - 1;

            if (direction == Direction.RIGHT && ennemi.getCaseX() < maxX) {
                if (Application_spaceInvader.getInstance().getJeu().isCaseEmpty(ennemi.getCaseX() + 1, ennemi.getCaseY()) &&
                        Application_spaceInvader.getInstance().getJeu().getMap().getTileId(ennemi.getCaseX() + 1, ennemi.getCaseY(), indexCalqueObstacles) == 0) {
                    ennemi.setCaseX(ennemi.getCaseX() + 1);
                }
            } else if (direction == Direction.LEFT && ennemi.getCaseX() > 0) {
                if (Application_spaceInvader.getInstance().getJeu().isCaseEmpty(ennemi.getCaseX() - 1, ennemi.getCaseY()) &&
                        Application_spaceInvader.getInstance().getJeu().getMap().getTileId(ennemi.getCaseX() - 1, ennemi.getCaseY(), indexCalqueObstacles) == 0) {
                    ennemi.setCaseX(ennemi.getCaseX() - 1);
                }
            } else if (direction == Direction.DOWN && ennemi.getCaseY() < maxY) {
                if (Application_spaceInvader.getInstance().getJeu().isCaseEmpty(ennemi.getCaseX(), ennemi.getCaseY() + 1) &&
                        Application_spaceInvader.getInstance().getJeu().getMap().getTileId(ennemi.getCaseX(), ennemi.getCaseY() + 1, indexCalqueObstacles) == 0) {
                    ennemi.setCaseY(ennemi.getCaseY() + 1);
                }
            }

            lastMoveTime = currentTime; // Met à jour le temps du dernier mouvement
        }
    }

}
