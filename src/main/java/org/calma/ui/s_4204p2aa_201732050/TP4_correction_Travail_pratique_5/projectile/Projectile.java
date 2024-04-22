package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.projectile;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Personnage;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Projectile {
    private Vector2f position;
    private Vector2f deplacement;
    private Direction direction;
    private int caseX;
    private int caseY;
    private boolean estActif;
    private int age;
    private Image imageProjectile;
    private float correctifCentreX;

    public Projectile() {
        this.estActif = false;
        init();
    }
    //Constructeur qui crée un projectile de déplacement
    public Projectile(Vector2f position, Vector2f deplacement, Direction direction) {
        this.position = position;
        this.deplacement = deplacement;
        this.estActif = true;
        this.direction = direction;
        init();
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCaseX() {
        return caseX;
    }

    public void setCaseX(int caseX) {
        this.caseX = caseX;
    }

    public int getCaseY() {
        return caseY;
    }

    public void setCaseY(int caseY) {
        this.caseY = caseY;
    }

    //initialise l'�clair 
    private void init() {
        this.age = 0;
        try {
            imageProjectile = new Image("org/calma/ui/s_4204p2aa_201732050/TP4/easter-easter-egg-sprite.png").getSubImage(35, 15, 112, 146).getScaledCopy(50,50);
//            System.out.println("Création du projectile");
        } catch (SlickException ex) {
            Logger.getLogger(Projectile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Génération du projectile
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Projectile render");
    	if (estActif) {
	        imageProjectile.draw(position.x - correctifCentreX, position.y);
	    }
    }
    //Mise � jour de l'�tat du projectile
    public void update(int i) {
//        System.out.println("Projectile update");

        this.caseX = Math.abs((int)this.getPosition().getX() / Constante.getTilesSize());
        this.caseY = Math.abs((int)this.getPosition().getY() / Constante.getTilesSize());
        if (estActif) {
            float uneSeconde = Constante.getProjectileVitesse();
            Vector2f deplacementReel = deplacement.copy();
            deplacementReel.scale(i / uneSeconde);
            position.add(deplacementReel);
            age += i;
            if (age > Constante.getProjectileDureeVie()) {
                estActif = false;
            }
        }
        //Tuons le personnage
        for (Personnage element : Application.getInstance().getJeu().getPersonnages()) {
            if(
                    element.getCaseX() == this.getCaseX() &&
                    element.getCaseY() == this.getCaseY() &&
                    element.isEstJoueur()
            ){
                element.setEstActif(false);
            }
        }
    }

    public Vector2f getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(Vector2f deplacement) {
        this.deplacement = deplacement;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Image getImageProjectile() {
        return imageProjectile;
    }

    public void setImageProjectile(Image imageProjectile) {
        this.imageProjectile = imageProjectile;
    }

    public float getCorrectifCentreX() {
        return correctifCentreX;
    }

    public void setCorrectifCentreX(float correctifCentreX) {
        this.correctifCentreX = correctifCentreX;
    }

    //Retourne l'�tat actif du projectile
    public boolean siActif() {
        return this.estActif;
    }

}
