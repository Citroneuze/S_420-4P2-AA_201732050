package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
//import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer.ObserverCollision;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer.ObserverCollision;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;

import org.newdawn.slick.*;

public abstract class Vaisseau implements ObserverCollision {
    private String nom;
    private int caseX;
    private int caseY;
    private int movingStep;
    private Animation anim_RIGHT;
    private Animation anim_LEFT;
    private Animation anim_DOWN;
    private Direction direction;
    private int keyPressed;
    private boolean estActif;
    private int fireRate = 2000;
    private boolean estJoueur;
    public int getFireRate() {
        return fireRate;
    }
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }
    public Vaisseau(String nom, int caseX, int caseY) throws SlickException {
        this.nom = nom;
        this.caseX = caseX;
        this.caseY = caseY;
        direction = Direction.DOWN;
        movingStep = 1;
        estActif = true;
        estJoueur = false;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public boolean isEstJoueur() {
        return estJoueur;
    }

    public void setEstJoueur(boolean estJoueur) {
        this.estJoueur = estJoueur;
    }

    public int getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int getMovingStep() {
        return movingStep;
    }

    public void setMovingStep(int movingStep) {
        this.movingStep = movingStep;
    }

    public Animation getAnim_RIGHT() {
        return anim_RIGHT;
    }

    public void setAnim_RIGHT(Animation anim_RIGHT) {
        this.anim_RIGHT = anim_RIGHT;
    }

    public Animation getAnim_LEFT() {
        return anim_LEFT;
    }

    public void setAnim_LEFT(Animation anim_LEFT) {
        this.anim_LEFT = anim_LEFT;
    }

    public Animation getAnim_DOWN() {
        return anim_DOWN;
    }

    public void setAnim_DOWN(Animation anim_DOWN) {
        this.anim_DOWN = anim_DOWN;
    }

    //Retourne l'�tat actif du personnage
    public boolean siActif() {
        return this.estActif;
    }

    //déplacement
    public void moveLeft() throws SlickException {
        this.caseX -= movingStep;
//        Jeu.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application_spaceInvader.getInstance().getJeu());
    }
    public void moveRight() throws SlickException {
        this.caseX += movingStep;
//        Application_spaceInvader.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application_spaceInvader.getInstance().getJeu());
    }

    //Animation du personnage
    public Animation getAnimation(Image imgAnimPersonnage, int rowX, int rowY, int height, int width) {

        Animation anim = new Animation(false);
        int colY = rowY;
        int colX = rowX;
        float scale = (float) 32 / height;
        anim.addFrame(
                imgAnimPersonnage.getSubImage(colX, colY, width, height).getScaledCopy(scale),
                50
        );
        return anim;
    }

    public void AnimationDraw(int tilesSize){
        int posX = this.caseX * tilesSize;
        int posY = this.caseY * tilesSize;

        switch (direction) {
            case RIGHT:
                anim_RIGHT.draw(posX, posY);
                break;
            case LEFT:
                anim_LEFT.draw(posX, posY);
                break;
            case DOWN:
                anim_DOWN.draw(posX, posY);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Personnage render");
        this.AnimationDraw(Jeu_spaceInvader.getInstance().getTilesSize());
    }
    public void update(GameContainer gc, int i) throws SlickException {
        System.out.println("Personnage update");
    }
}

