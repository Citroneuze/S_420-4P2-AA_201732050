package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Observer.ObserverCollision;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;
import org.newdawn.slick.*;

public abstract class Personnage implements ObserverCollision {
    private String nom;
    private int caseX;
    private int caseY;
    private int movingStep;
    private Animation anim_UP;
    private Animation anim_DOWN;
    private Animation anim_RIGHT;
    private Animation anim_LEFT;
    private Direction direction;
    private int keyPressed;
    private boolean estActif = true;
    private int fireRate;
    private boolean estJoueur;
    public int getFireRate() {
        return fireRate;
    }
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }
    public Personnage(String nom, int caseX, int caseY) throws SlickException {
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


    public Animation getAnim_UP() {
        return anim_UP;
    }

    public void setAnim_UP(Animation anim_UP) {
        this.anim_UP = anim_UP;
    }

    public Animation getAnim_DOWN() {
        return anim_DOWN;
    }

    public void setAnim_DOWN(Animation anim_DOWN) {
        this.anim_DOWN = anim_DOWN;
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

    //Retourne l'�tat actif du personnage
    public boolean siActif() {
        return this.estActif;
    }

    //déplacement
    public void moveLeft() throws SlickException {
        this.caseX -= movingStep;
        Application.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application.getInstance().getJeu());
    }
    public void moveRight() throws SlickException {
        this.caseX += movingStep;
        Application.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application.getInstance().getJeu());
    }
    public void moveUp() throws SlickException {
        this.caseY -= movingStep;
        Application.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application.getInstance().getJeu());
    }
    public void moveDown() throws SlickException {
        this.caseY += movingStep;
        Application.getInstance().getJeu().getCapteurs().observerCollisionPersonnage(Application.getInstance().getJeu());
    }

    //Animation du personnage
    public Animation getAnimation(Image imgAnimPersonnage,int rowX, int rowY, int height, int width) {

        Animation anim = new Animation(false);
        int colY = rowY;
        int colX = rowX;
        float scale = (float) Constante.getTilesSize() / height;
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
            case UP:
                anim_UP.draw(posX, posY);
                break;
            case DOWN:
                anim_DOWN.draw(posX, posY);
                break;
            case RIGHT:
                anim_RIGHT.draw(posX, posY);
                break;
            case LEFT:
                anim_LEFT.draw(posX, posY);
                break;
        }

    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Personnage render");
        this.AnimationDraw(Application.getInstance().getJeu().getTilesSize());
    }
    public void update(GameContainer gc, int i) throws SlickException {
//        System.out.println("Personnage update");
    }
}
