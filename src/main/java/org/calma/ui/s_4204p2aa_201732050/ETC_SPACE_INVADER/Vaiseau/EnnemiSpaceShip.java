package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau;


import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.Vaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.util.LinkedList;
import java.util.Random;



public class EnnemiSpaceShip extends Vaiseau {
    private long lastMoveTime = 0;
    private int delta;
    private int delaisDeplacement;
    private int tilesSize = 32;
    private StrategieDeplacementEnnemi strategieDeplacement;



    public <strategie> EnnemiSpaceShip(int caseX, int caseY, StrategieDeplacementEnnemi strategie) throws SlickException {
        super("Lapin", caseX, caseY);
        this.strategieDeplacement = strategie;
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
        super.render(gc,grphcs,direction);
    }
    public void update(GameContainer gc, int i) throws SlickException {
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
    //    @Override
//    public void alarme() {
//
//    }
}
