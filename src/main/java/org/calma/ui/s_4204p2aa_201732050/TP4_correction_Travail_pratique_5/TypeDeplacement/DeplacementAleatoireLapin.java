package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.TypeDeplacement;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementLapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Lapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;

import java.util.Random;

public class DeplacementAleatoireLapin implements StrategieDeplacementLapin {

    private int tilesSize = Constante.getTilesSize();

    @Override
    public void deplacer(Lapin lapin) {
        Random random = new Random();
        Direction direction = Direction.getRandomDirection(); // Direction aléatoire

        int posX = lapin.getCaseX() * tilesSize;
        int posY = lapin.getCaseY() * tilesSize;
        int indexCalqueObstacles = Application.getInstance().getJeu().getIndexCalqueObstacles();
        if (direction == Direction.RIGHT) {
            if (Application.getInstance().getJeu().isCaseEmpty(lapin.getCaseX() + 1, lapin.getCaseY()) &&
                    Application.getInstance().getJeu().getMap().getTileId(lapin.getCaseX() + 1, lapin.getCaseY(), indexCalqueObstacles) == 0) {
                lapin.setCaseX(lapin.getCaseX() + 1);
            }
        } else if (direction == Direction.LEFT) {
            if (Application.getInstance().getJeu().isCaseEmpty(lapin.getCaseX() - 1, lapin.getCaseY()) &&
                    Application.getInstance().getJeu().getMap().getTileId(lapin.getCaseX() - 1, lapin.getCaseY(), indexCalqueObstacles) == 0) {
                lapin.setCaseX(lapin.getCaseX() - 1);
            }
        } else if (direction == Direction.UP) {
            if (Application.getInstance().getJeu().isCaseEmpty(lapin.getCaseX(), lapin.getCaseY() - 1) &&
                    Application.getInstance().getJeu().getMap().getTileId(lapin.getCaseX(), lapin.getCaseY() - 1, indexCalqueObstacles) == 0) {
                lapin.setCaseY(lapin.getCaseY() - 1);
            }
        } else if (direction == Direction.DOWN) {
            if (Application.getInstance().getJeu().isCaseEmpty(lapin.getCaseX(), lapin.getCaseY() + 1) &&
                    Application.getInstance().getJeu().getMap().getTileId(lapin.getCaseX(), lapin.getCaseY() + 1, indexCalqueObstacles) == 0) {
                lapin.setCaseY(lapin.getCaseY() + 1);
            }
        }
    }
}
