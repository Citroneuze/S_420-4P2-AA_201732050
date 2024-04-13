package org.calma.ui.s_4204p2aa_201732050.TP4.utils;

public class Constante {
    //LAPIN
    private static int LAPIN_NOMBRE_DEPART = 3;
    private static int LAPIN_DELAI_APPARITION = 10000;
    private static int LAPIN_NOMBRE_REGENERATION = 1;
    private static int LAPIN_DELAIS_DEPLACEMENT= 2000;
    private static int LAPIN_FIRERATE_MIN= 500;
    private static int LAPIN_FIRERATE_MAX= 1000;

    //FENETRE
    private static String NOM_JEU = "TP4 - La revenge des lapins ou l'attaque des cocos";

    //MAP
    private static int TILES_SIZE = 32;
    private static int TILES_NB_X = 30;
    private static int TILES_NB_Y = 30;

    //PROJECTILE
    private static float PROJECTILE_VITESSE = 2000.0f;
    private static int PROJECTILE_DUREE_VIE = 1000;



    public static String getNomJeu() {
        return NOM_JEU;
    }

    public static int getTilesSize() {
        return TILES_SIZE;
    }

    public static int getTilesNbX() {
        return TILES_NB_X;
    }

    public static int getTilesNbY() {
        return TILES_NB_Y;
    }

    public static float getProjectileVitesse() {
        return PROJECTILE_VITESSE;
    }

    public static int getProjectileDureeVie() {
        return PROJECTILE_DUREE_VIE;
    }

    public static int getLapinNombreDepart() {
        return LAPIN_NOMBRE_DEPART;
    }

    public static int getLapinDelaiApparition() {
        return LAPIN_DELAI_APPARITION;
    }

    public static int getLapinNombreRegeneration() {
        return LAPIN_NOMBRE_REGENERATION;
    }

    public static int getLapinDelaisDeplacement() {
        return LAPIN_DELAIS_DEPLACEMENT;
    }

    public static int getLapinFirerateMin() {
        return LAPIN_FIRERATE_MIN;
    }

    public static int getLapinFirerateMax() {
        return LAPIN_FIRERATE_MAX;
    }
}

