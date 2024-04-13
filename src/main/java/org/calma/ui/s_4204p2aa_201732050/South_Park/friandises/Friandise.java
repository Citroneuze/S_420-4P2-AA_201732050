package org.calma.ui.s_4204p2aa_201732050.South_Park.friandises;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Friandise {
    private String nom;
    private Image friandiseImage = new Image("org/calma/ui/s_4204p2aa_201732050/res_South_Park/candy.png");
    private Animation animation;
    public Friandise(String bonbon) throws SlickException {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Image getFriandiseImage() {
        return friandiseImage;
    }

    public void setFriandiseImage(Image friandiseImage) {
        this.friandiseImage = friandiseImage;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getAnimation(int rowX, int rowY){
        animation = new Animation(false);
        int height = 46;
        animation.addFrame(friandiseImage.getSubImage(rowX, rowY, 32, height), 50);

        return animation;
    }

    public void AnimationDraw(int posX, int posY) {
        animation.draw(posX, posY);
    }

}
