package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils;

import java.util.Random;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;
    public static Direction getRandomDirection() {
        Random random = new Random();
        // Obtient toutes les valeurs de l'énumération Direction
        Direction[] directions = Direction.values();
        // Sélectionne une direction au hasard
        return directions[random.nextInt(directions.length)];
    }
}
