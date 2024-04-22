package org.calma.ui.s_4204p2aa_201732050.TP4.personnage;
import org.calma.ui.s_4204p2aa_201732050.TP4.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Direction;
import org.newdawn.slick.*;

public abstract class Personnage {
    private String nom;
    private int caseX;
    private int caseY;
    private int movingStep;
    private Animation anim_UP;
    private Animation anim_DOWN;
    private Animation anim_RIGHT;
    private Animation anim_LEFT;
    private Direction direction;
    private Image personnages;
    private boolean isAlive;

    public Personnage(String nom, int caseX, int caseY, String s) throws SlickException {
        this.nom = nom;
        this.caseX = caseX;
        this.caseY = caseY;
        direction = Direction.DOWN;
        movingStep = 1;
        this.personnages = new Image(s);
        isAlive = true;

    }
    public Animation getAnimation(int rowX, int rowY, int s, float scale){
        Animation anim = new Animation(false);
        int height = s;
        anim.addFrame(personnages.getSubImage(rowX, rowY, 32, height).getScaledCopy(scale), 50);
        return anim;
    }
    public void animationDraw(int tilesSize) {
        int posX = this.caseX * tilesSize;
        int posY = this.caseY * tilesSize;

        switch (direction) {
            case UP -> anim_UP.draw(posX, posY);
            case DOWN -> anim_DOWN.draw(posX, posY);
            case RIGHT -> anim_RIGHT.draw(posX, posY);
            case LEFT -> anim_LEFT.draw(posX, posY);
        }
    }

    public abstract void render(GameContainer container, Graphics g) throws SlickException;

    public abstract void update(GameContainer container, int delta) throws SlickException;

    public void moveRight() {
        this.caseX += movingStep;
    }
    public void moveLeft(){
        this.caseX -= movingStep;
    }
    public void moveUp(){
        this.caseY -= movingStep;
    }
    public void moveDown(){
        this.caseY += movingStep;
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
    public Image getPersonnages() {
        return personnages;
    }
    public void setPersonnages(Image personnages) {
        this.personnages = personnages;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive, Lapin lapin) {
        isAlive = alive;
        Application.getInstance().getJeu().setKill();
        Application.getInstance().getJeu().removeLapin(lapin);
    }


    public void setAliveJoueur(boolean b) {
        isAlive = b;
    }
}
