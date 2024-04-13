package org.calma.ui.s_4204p2aa_201732050.South_Park.personnage;

import org.calma.ui.s_4204p2aa_201732050.South_Park.*;
import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Pied;
import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Velo;
import org.calma.ui.s_4204p2aa_201732050.South_Park.friandises.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Personnage{
    private String nom;
    private int caseX;
    private int caseY;
    private int deplacementType;
    private int deplacementDelaisMarche;
    private int deplacementDelaisCourse;
    private Image deplacementImage = new Image("org/calma/ui/s_4204p2aa_201732050/res_South_Park/deplacement.png");
    private Animation anim_Walk;
    private Animation anim_RUN;
    private Animation anim_BIKE;
    private int movingStep;
    private Animation anim_UP;
    private Animation anim_DOWN;
    private Animation anim_RIGHT;
    private Animation anim_LEFT;
    private Direction direction;
    private int img_movingStep;
    private Image personnages = new Image("org/calma/ui/s_4204p2aa_201732050/res_South_Park/characteres.png");
    private Friandise[] inventaireFriandise = new Friandise[10];

    public Personnage(String nom, int caseX, int caseY) throws SlickException {
        this.nom = nom;
        this.caseX = caseX;
        this.caseY = caseY;
        direction = Direction.DOWN;
        movingStep = 1;
        deplacementType = 1;
        deplacementDelaisMarche = 1000;
        deplacementDelaisCourse = 0;
        this.anim_Walk = getAnimationDeplacement(0,0);
        this.anim_RUN = getAnimationDeplacement(1,0);
        this.anim_BIKE = getAnimationDeplacement(2,0);
    }



    public Animation getAnimation(int rowX, int rowY){
        Animation anim = new Animation(false);
        int height = 44;
        anim.addFrame(personnages.getSubImage(rowX, rowY, 32, height), 50);
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getImg_movingStep() {
        return img_movingStep;
    }
    public void setImg_movingStep(int img_movingStep) {
        this.img_movingStep = img_movingStep;
    }
    public Image getPersonnages() {
        return personnages;
    }

    public int getDeplacementType() {
        return deplacementType;
    }

    public void setDeplacementType(int deplacementType) {
        this.deplacementType = deplacementType;
    }

    public int getDeplacementDelaisMarche() {
        return deplacementDelaisMarche;
    }

    public void setDeplacementDelaisMarche(int deplacementDelaisMarche) {
        this.deplacementDelaisMarche = deplacementDelaisMarche;
    }

    public int getDeplacementDelaisCourse() {
        return deplacementDelaisCourse;
    }

    public void setDeplacementDelaisCourse(int deplacementDelaisCourse) {
        this.deplacementDelaisCourse = deplacementDelaisCourse;
    }


    public void MoveRight() {
        this.caseX += movingStep;
    }
    public void MoveLeft(){
        this.caseX -= movingStep;
    }
    public void MoveUp(){
        this.caseY -= movingStep;
    }
    public void MoveDown(){
        this.caseY += movingStep;
    }
    public void AnimationMenuDraw(int tilesSize){
        anim_DOWN.draw(66, 630);
    }
    public Friandise[] getInventaireFriandise() {
        return inventaireFriandise;
    }
    public void setInventaireFriandise(Friandise[] inventaireFriandise) {
        this.inventaireFriandise = inventaireFriandise;
    }
    public abstract void InventaireAjouter() throws SlickException;
    public abstract void InventaireSupprimer();
    public void InventaireDraw(int tilesSize){
        int posY = 630;
        int posX = 0;
        System.out.println(inventaireFriandise);
        for(int i = 0;i < inventaireFriandise.length; i++){
            posX = 90 + (i * tilesSize);
            if(inventaireFriandise[i] != null){
                inventaireFriandise[i].AnimationDraw(posX, posY);
            }
        }
    }
    public Animation getAnimationDeplacement(int rowX, int rowY) {
        Animation animation = new Animation(false);
        int width = 48;
        int height = 46;
        int colY = rowY * height;
        int colX = rowX * width;
        animation.addFrame(deplacementImage.getSubImage(colX, colY, width, height), 50);
        return animation;
    }

    public void deplacementDraw(){
        int posY = 630;
        int posX = 20;
        switch(deplacementType){
            case 1:
                anim_Walk.draw(posX, posY);
                break;
            case 2:
                anim_RUN.draw(posX, posY);
                break;
            case 3:
                anim_BIKE.draw(posX, posY);
                break;
        }
    }

    public void deplacement(){
        if(this instanceof Pied && this.deplacementType == 1){
            ((Pied) this).marcher();
        }
        if(this instanceof Pied && this.deplacementType == 2){
            ((Pied) this).courir();
        }
        if(this instanceof Velo && this.deplacementType == 3){
            ((Velo) this).rouler();
        }
    }

}
