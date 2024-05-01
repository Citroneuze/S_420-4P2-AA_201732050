package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.Vaisseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.OwnerId;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Lazer {
    private Vector2f position;
    private Vector2f deplacement;
    private Direction direction;
    private int caseX;
    private int caseY;
    private boolean estActif;
    private int age;
    private Image imageProjectile;
    private float correctifCentreX;
    private OwnerId ownerId;

    //Constructeur qui crée un projectile de déplacement
    public Lazer(Vector2f position, Vector2f deplacement, Direction direction, OwnerId ownerId) {
        this.position = position;
        this.deplacement = deplacement;
        this.direction = direction;
        this.ownerId = ownerId;
        this.estActif = true;
        init();  // S'assurer que cette méthode est appelée pour initialiser l'image
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
            imageProjectile = new Image("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/sprite/Laser-Beam-PNG-Picture.png").getSubImage(0, 0, 200, 600).getScaledCopy(20,20);

        } catch (SlickException ex) {
            Logger.getLogger(Lazer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void render(GameContainer gc, Graphics grphcs) {
        if (estActif) {
            imageProjectile.draw(position.x - correctifCentreX, position.y);
        }
    }

    public void update(int i) throws SlickException {
        this.caseX = Math.abs((int) this.getPosition().getX() / 32);
        this.caseY = Math.abs((int) this.getPosition().getY() / 32);
        if (estActif) {
            float uneSeconde = 2000.0f;
            Vector2f deplacementReel = deplacement.copy();
            deplacementReel.scale(i / uneSeconde);
            position.add(deplacementReel);
            age += i;
            if (age > 4000) {
                estActif = false;
                Jeu_spaceInvader.getInstance().getCapteurs().observerCollisionShip(Jeu_spaceInvader.getInstance());
            }
        }
        for (Vaisseau element : Jeu_spaceInvader.getInstance().getVaisseau()) {
            if (element.getCaseX() == this.getCaseX() &&
                    element.getCaseY() == this.getCaseY() &&
                    element.isEstActif() &&
                    this.estActif &&
                    ((this.ownerId == OwnerId.PLAYER && !element.isEstJoueur()) ||
                            (this.ownerId == OwnerId.ENEMY && element.isEstJoueur()))) {
                element.setEstActif(false);
                this.estActif = false;
                Jeu_spaceInvader.getInstance().getCapteurs().observerCollisionShip(Jeu_spaceInvader.getInstance());
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
