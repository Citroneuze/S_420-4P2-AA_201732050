package org.calma.ui.s_4204p2aa_201732050.Laboratoire7;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class Projectile {
    private Vector2f position;
    private Vector2f velocity; // Ajoutez la vélocité pour le déplacement
    boolean estActif;
    private int age;
    private final int DUREEVIE = 750;


    public Projectile(Vector2f position, Vector2f velocity) {
        this.position = position;
        this.velocity = velocity; // Initialisez la vélocité
        estActif = true;
        age = 0;
    }

    public Projectile() {

    }

    public void update(int delta) {
        if (estActif) {
            float uneSeconde = 1000.0f;
            Vector2f deplacementReel = velocity.copy(); // Utilisez la vélocité pour le déplacement
            deplacementReel.scale(delta / uneSeconde);
            position.add(deplacementReel);
            age += delta;
            if (age > DUREEVIE) {
                estActif = false;
            }
        }
    }

    public void render(GameContainer container, Graphics g) {
        if (estActif) {
            g.setColor(Color.red);
            g.fillOval(position.getX(), position.getY(), 10, 10); // Dessiner un cercle rouge pour représenter la balle
        }
    }

    public boolean siActif() {
        return estActif;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setActive(boolean b) {
    }
}


