package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau;


import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.OwnerId;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.LinkedList;


public class EnnemiSpaceShip extends Vaisseau {
    private long lastMoveTime = 0;
    private int delta;
    private int delaisDeplacement;
    private int tilesSize = 32;
    private StrategieDeplacementEnnemi strategieDeplacement;
    protected boolean canFire = true;

    public EnnemiSpaceShip(int caseX, int caseY, StrategieDeplacementEnnemi strategie) throws SlickException {
        super("Ship Name", caseX, caseY); // Example initialization
        this.canFire = canFire;
        this.strategieDeplacement = strategie;
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
        super.render(gc,grphcs,direction);
    }
    public void update(GameContainer gc, int i) throws SlickException {
        int posX = this.getCaseX() * tilesSize;
        int posY = this.getCaseY() * tilesSize;
        LinkedList<Lazer> projectiles = Jeu_spaceInvader.getInstance().getLazers();
        delta += i;

        if (canFire && delta >= getFireRate()) {
            Vector2f positionProjectile = new Vector2f(posX, posY);
            Vector2f deplacementProjectile;
            int correctifCentreVertical = 8;

            switch (this.getDirection()) {
                case DOWN:
                    positionProjectile.add(new Vector2f(0, tilesSize));
                    deplacementProjectile = new Vector2f(0, 500);
                    break;
                default:
                    deplacementProjectile = new Vector2f(0, 0);
            }
            projectiles.add(new Lazer(positionProjectile, deplacementProjectile, Direction.DOWN, OwnerId.ENEMY));
            delta = 0;
            System.out.println(delta);  // Consider removing or using logging instead
        }

        strategieDeplacement.deplacer(this, gc);
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getDelaisDeplacement() {
        return delaisDeplacement;
    }

    public void setDelaisDeplacement(int delaisDeplacement) {
        this.delaisDeplacement = delaisDeplacement;
    }

    public int getTilesSize() {
        return tilesSize;
    }

    public void setTilesSize(int tilesSize) {
        this.tilesSize = tilesSize;
    }

    public StrategieDeplacementEnnemi getStrategieDeplacement() {
        return strategieDeplacement;
    }

    public void setStrategieDeplacement(StrategieDeplacementEnnemi strategieDeplacement) {
        this.strategieDeplacement = strategieDeplacement;
    }
    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }
    public boolean canFire() {
        return canFire;
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setFire(boolean canFire) {
        this.canFire = canFire;
    }

    @Override
    public void alarme() {

    }
}
