package org.calma.ui.s_4204p2aa_201732050.TP4.personnage;

import org.calma.ui.s_4204p2aa_201732050.TP4.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4.Jeu;
import org.calma.ui.s_4204p2aa_201732050.TP4.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

public class Joueur extends Personnage {
    private TiledMap map;
    private final int indexCalqueObstacles;

    public Joueur(String nom, int caseX, int caseY, TiledMap map, int indexCalqueObstacles) throws SlickException {
        super(nom, caseX, caseY, "org/calma/ui/s_4204p2aa_201732050/TP4/characters.png");

        this.indexCalqueObstacles = indexCalqueObstacles;
        this.map = map;

        this.setAnim_UP(getAnimation(10, 14, 44, 0.5F));
        this.setAnim_DOWN(getAnimation(10, 14, 44, 0.5F));
        this.setAnim_LEFT(getAnimation(10, 14, 44, 0.5F));
        this.setAnim_RIGHT(getAnimation(10, 14, 44, 0.5F));
    }

    @Override
    public void update(GameContainer container,int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if (map.getTileId(this.getCaseX() + 1, this.getCaseY(), indexCalqueObstacles) == 0) {
                this.setDirection(Direction.RIGHT);
                this.moveRight();
            }
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            if (map.getTileId(this.getCaseX() - 1, this.getCaseY(), indexCalqueObstacles) == 0) {
                this.setDirection(Direction.LEFT);
                this.moveLeft();
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (map.getTileId(this.getCaseX(), this.getCaseY() - 1, indexCalqueObstacles) == 0) {
                this.setDirection(Direction.UP);
                this.moveUp();
            }
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (map.getTileId(this.getCaseX(), this.getCaseY() + 1, indexCalqueObstacles) == 0) {
                this.setDirection(Direction.DOWN);
                this.moveDown();
            }
        }

    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {

        this.animationDraw(Constante.getTilesSize());
    }



}
