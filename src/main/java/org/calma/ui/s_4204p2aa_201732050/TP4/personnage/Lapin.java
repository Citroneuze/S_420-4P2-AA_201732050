package org.calma.ui.s_4204p2aa_201732050.TP4.personnage;
import org.calma.ui.s_4204p2aa_201732050.TP4.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Lapin extends Personnage {
    private final int indexCalqueObstacles;
    private TiledMap map;
    private int i;
    private int y;
    Iterator<Projectile> iter;
    private LinkedList<Projectile> projectiles;

    public Lapin(String nom, int caseX, int caseY, TiledMap map, int indexCalqueObstacles) throws SlickException {
        super(nom, caseX, caseY, "org/calma/ui/s_4204p2aa_201732050/TP4/bunny.png");
        this.indexCalqueObstacles = indexCalqueObstacles;
        this.map = map;
        projectiles = new LinkedList<>();

        this.setAnim_UP(getAnimation(35, 6, 31, 1.0F));
        this.setAnim_DOWN(getAnimation(35, 6, 31, 1.0F));
        this.setAnim_LEFT(getAnimation(35, 6, 31, 1.0F));
        this.setAnim_RIGHT(getAnimation(35, 6, 31, 1.0F));
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

        i += delta;
        y += delta;
        if (i >= 2000) {
            i=0;
            int randomDirection = (int) (Math.random() * 4);
            switch (randomDirection) {
                case 0:
                    if (map.getTileId(this.getCaseX() + 1, this.getCaseY(), indexCalqueObstacles) == 0) {
                        moveRight();
                        this.setDirection(Direction.RIGHT);
                    }
                    break;
                case 1:
                    if (map.getTileId(this.getCaseX() - 1, this.getCaseY(), indexCalqueObstacles) == 0) {
                        moveLeft();
                        this.setDirection(Direction.LEFT);
                    }
                    break;
                case 2:
                    if (map.getTileId(this.getCaseX(), this.getCaseY() - 1, indexCalqueObstacles) == 0) {
                        moveUp();
                        this.setDirection(Direction.UP);
                    }
                    break;
                case 3:
                    if (map.getTileId(this.getCaseX(), this.getCaseY() + 1, indexCalqueObstacles) == 0) {
                        moveDown();
                        this.setDirection(Direction.DOWN);
                    }
                    break;
            }
        }

        if (y >= Constante.getLapinFirerateMax()) {
            Vector2f positionProjectile = new Vector2f(this.getCaseX() * Constante.getTilesSize(), this.getCaseY() * Constante.getTilesSize());
            Vector2f deplacementProjectile;
            int correctifCentreVertical = 8;
            switch (this.getDirection()) {
                case RIGHT:
                    positionProjectile.add(new Vector2f(Constante.getTilesSize(), correctifCentreVertical));
                    deplacementProjectile = new Vector2f(500, 0);
                    break;
                case LEFT:
                    positionProjectile.add(new Vector2f(-Constante.getTilesSize(), correctifCentreVertical));
                    deplacementProjectile = new Vector2f(-500, 0);
                    break;
                case UP:
                    positionProjectile.add(new Vector2f(0, -Constante.getTilesSize()));
                    deplacementProjectile = new Vector2f(0, -500);
                    break;
                case DOWN:
                    positionProjectile.add(new Vector2f(0, Constante.getTilesSize()));
                    deplacementProjectile = new Vector2f(0, 500);
                    break;
                default:
                    deplacementProjectile = new Vector2f(0, 0);
            }
            y = 0;

            projectiles.add(new Projectile(positionProjectile, deplacementProjectile, this.getDirection()));


        }

        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.update(delta);
            if (!projectile.siActif()) {
                iterator.remove();
            }
        }
    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        animationDraw(Constante.getTilesSize());

        for (Projectile projectile : projectiles) {
            projectile.render(container, g, this.getDirection());
        }
    }
}
