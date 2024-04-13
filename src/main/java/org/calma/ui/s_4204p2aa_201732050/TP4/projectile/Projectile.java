package org.calma.ui.s_4204p2aa_201732050.TP4.projectile;

import org.calma.ui.s_4204p2aa_201732050.TP4.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4.personnage.Joueur;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Direction;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Projectile {
    private Vector2f position;
    private Vector2f deplacement;
    private boolean estActif;
    private int age;
    private final int DUREEVIE = Constante.getProjectileDureeVie();
    private Image oeuf;
    public Projectile(Vector2f position, Vector2f deplacement, Direction direction) {
        this.position = position;
        this.deplacement = deplacement;
        this.estActif = true;
        init();
        switch (direction) {
            case RIGHT:
                oeuf.setRotation(90.0f);

                break;
            case LEFT:
                oeuf.setRotation(270.0f);

                break;
            case UP:
                oeuf.setRotation(0.0f);

                break;
            case DOWN:
                oeuf.setRotation(180.0f);

                break;
            default:

                break;
        }
    }
    private void init() {
        this.age = 0;
        try {
            oeuf = new Image("org/calma/ui/s_4204p2aa_201732050/TP4/easter-easter-egg-sprite.png").getSubImage(240, 20, 100, 140).getScaledCopy(0.25f);
        } catch (SlickException ex) {
            Logger.getLogger(Projectile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(int i) {

        if (estActif) {
            Joueur joueur = Application.getInstance().getJeu().getJoueur();
            if (siToucheJoueur(joueur.getDirection())) {
                joueur.setAlive(false);
            }

            float uneSeconde = 1000.0f;
            Vector2f deplacementReel = deplacement.copy();
            deplacementReel.scale(i / uneSeconde);
            position.add(deplacementReel);
            age += i;
            if (age > DUREEVIE) {
                estActif = false;
            }
        }
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {

        if (estActif) {
            oeuf.draw(position.x, position.y-5);
        }
//        this.dessinerRectangles(grphcs, direction);
    }

    public Rectangle getRectangJoueur() {
        float joueurPosX = Application.getInstance().getJeu().getJoueurPositionX() * Constante.getTilesSize();
        float joueurPosY = Application.getInstance().getJeu().getJoueurPositionY() * Constante.getTilesSize();

        return new Rectangle(joueurPosX, joueurPosY, 30, 30);
    }

    public Rectangle getRectangLapin() {
        float lapinPosX = Application.getInstance().getJeu().getLapinPositionX() * Constante.getTilesSize(); // Supposons que getCaseX() renvoie la position X du lapin
        float lapinPosY = Application.getInstance().getJeu().getLapinPositionY() * Constante.getTilesSize(); // Supposons que getCaseY() renvoie la position Y du lapin

        return new Rectangle(lapinPosX, lapinPosY, 30, 30);
    }
    private Rectangle getRectangleProjectile(Direction direction) {
        float projPosX = position.x;
        float projPosY = position.y;
        float projLargeur = 30;
        float projHauteur = 30;

        // Ajuster les positions et tailles en fonction de la direction du projectile
        if (direction == Direction.UP) {
            projPosY -= projHauteur / 5.0f; // Déplacer vers le haut (centré verticalement)
        } else if (direction == Direction.DOWN) {
            projPosY += projHauteur / 10.0f; // Déplacer vers le bas (centré verticalement)
        } else if (direction == Direction.LEFT) {
            projPosX -= projLargeur / 10.0f; // Déplacer vers la gauche (centré horizontalement)
        } else if (direction == Direction.RIGHT) {
            projPosX += projLargeur / 15.0f; // Déplacer vers la droite (centré horizontalement)
        }

        return new Rectangle(projPosX, projPosY, projLargeur, projHauteur);
    }


//    private void dessinerRectangles(Graphics grphcs, Direction direction) {
//        grphcs.setColor(Color.white);
//        Rectangle rectangleJoueur = getRectangJoueur();
//        Rectangle rectangleProjectile = getRectangleProjectile(direction);
//        Rectangle rectangleLapin = getRectangLapin();
//        grphcs.draw(rectangleLapin);
//        grphcs.draw(rectangleJoueur);
//        grphcs.draw(rectangleProjectile);
//    }

    public boolean siToucheJoueur(Direction direction) {
        boolean siTouche = false;

        Rectangle rectangleJoueur = getRectangJoueur();
        Rectangle rectangleProjectile = getRectangleProjectile(direction);

        if (rectangleJoueur.intersects(rectangleProjectile)) {
            siTouche = true;
        }

        return siTouche;
    }



    public boolean siActif() {
        return this.estActif;
    }



}
