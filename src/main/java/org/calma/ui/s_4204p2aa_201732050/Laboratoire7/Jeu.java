package org.calma.ui.s_4204p2aa_201732050.Laboratoire7;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
enum Direction{
    UP, DOWN, RIGHT, LEFT
}
public class Jeu extends BasicGame {

    private int clicksRequired = 2;
    private TiledMap map;
    private int tileSize;
    private Image imgSmiley;
    private float imgSmiley_PosX;
    private float imgSmiley_PosY;
    private int image_size;
    private boolean wasKeyPressedLeft = false;
    private boolean wasKeyPressedRight = false;
    private boolean wasKeyPressedUp = false;
    private boolean wasKeyPressedDown = false;
    private Sound bounce;
    private Music musique;
    private Image professor;
    private Animation anim_UP;
    private Animation anim_DOWN;
    private Animation anim_RIGHT;
    private Animation anim_LEFT;
    private Direction lastDirection = Direction.DOWN;
    private boolean firstContact = true;
    private boolean[][] fogOfWar;
    private int mapWidth;
    private int mapHeight;
    private Animation anim_ennemi;
    private float ennemi_PosX;
    private float ennemi_PosY;
    private boolean ennemiVisible = true;
    private boolean ennemiMort = false;
    private LinkedList<Projectile> projectiles;
    private final int FIRERATE = 250;
    private int delta;
    public Jeu(String title) {
        super(title);
    }
    @Override
    public void init(GameContainer container) throws SlickException {

        tileSize = 32;
        image_size = tileSize;
        imgSmiley_PosX = 2 * tileSize;
        imgSmiley_PosY = 21 * tileSize;
        map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/laboratoire7/maps/maPremiereCarte.tmx");

        bounce = new Sound("org/calma/ui/s_4204p2aa_201732050/laboratoire7/Bounce.wav");
        musique = new Music("org/calma/ui/s_4204p2aa_201732050/laboratoire7/Music.wav");
        musique.play();
        musique.setVolume(0.2f);
        musique.loop();

        professor = new Image("org/calma/ui/s_4204p2aa_201732050/laboratoire7/sprites/professor_walk_cycle_32.png");
        anim_UP = getAnimation(0);
        anim_DOWN = getAnimation(2);
        anim_LEFT = getAnimation(1);
        anim_RIGHT = getAnimation(3);

        mapWidth = (container.getWidth() - 2 * tileSize) / tileSize;
        mapHeight = (container.getHeight() - 2 * tileSize) / tileSize;
        fogOfWar = new boolean[mapWidth][mapHeight];
        ennemi_PosX = 10 * tileSize;
        ennemi_PosY = 10 * tileSize;
        anim_ennemi = getEnnemiAnimation();
        projectiles = new LinkedList<>();
        delta = 0;
        fillFogOfWar();

        container.setTargetFrameRate(60);
    }
    private void fillFogOfWar() {
        System.out.println(mapHeight);
        System.out.println(mapWidth);
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                fogOfWar[x][y] = true; // Initialiser toutes les tuiles comme étant dans le brouillard
            }
        }
    }
    private void obscurcir(Graphics grphcs) {
        int mapWidth = fogOfWar.length;
        int mapHeight = fogOfWar[0].length;
        int posX = tileSize;
        int posY = tileSize;

        grphcs.setColor(Color.darkGray);
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (fogOfWar[i][j]) {
                    grphcs.fillRect(posX + i * tileSize, posY + j * tileSize, tileSize, tileSize);
                }
            }
        }
    }
    private Animation getAnimation(int rowY){
        Animation anim = new Animation(false);
        for (int x = 0; x < 9; x++){
            anim.addFrame(professor.getSubImage(x*32, rowY * 32, 32, 32), 50);
        }
        return anim;
    }
    private void updateFogOfWar(int playerTileX, int playerTileY, int range) {
        // Déterminez la portée de vision du joueur (range)
        int startX = Math.max(0, playerTileX - range);
        int endX = Math.min(mapWidth - 1, playerTileX + range);
        int startY = Math.max(0, playerTileY - range);
        int endY = Math.min(mapHeight - 1, playerTileY + range);

        // Mettez à jour le brouillard de guerre en fonction de la portée de vision du joueur
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                fogOfWar[x][y] = false;
            }
        }
    }
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        this.delta += delta;

        boolean isKeyPressedLeft = input.isKeyDown(Input.KEY_LEFT);
        boolean isKeyPressedRight = input.isKeyDown(Input.KEY_RIGHT);
        boolean isKeyPressedUp = input.isKeyDown(Input.KEY_UP);
        boolean isKeyPressedDown = input.isKeyDown(Input.KEY_DOWN);


        float deltaPosX = 0;
        float deltaPosY = 0;
        if (isKeyPressedLeft && !wasKeyPressedLeft) {
            deltaPosX -= tileSize;
            lastDirection = Direction.LEFT;
        }
        if (isKeyPressedRight && !wasKeyPressedRight) {
            deltaPosX += tileSize;
            lastDirection = Direction.RIGHT;
        }
        if (isKeyPressedUp && !wasKeyPressedUp) {
            deltaPosY -= tileSize;
            lastDirection = Direction.UP;
        }
        if (isKeyPressedDown && !wasKeyPressedDown) {
            deltaPosY += tileSize;
            lastDirection = Direction.DOWN;
        }

        int playerTileX = (int) (imgSmiley_PosX / tileSize);
        int playerTileY = (int) (imgSmiley_PosY / tileSize);
        int range = 3;

        updateFogOfWar(playerTileX, playerTileY, range);


        int tileX = (int) ((imgSmiley_PosX + deltaPosX) / tileSize);
        int tileY = (int) ((imgSmiley_PosY + deltaPosY) / tileSize);


        int layerMurIndex = map.getLayerIndex("mur");
        int layerBack = map.getLayerIndex("background");
        int layerRouteIndex = map.getLayerIndex("route");


        if (map.getTileId(tileX, tileY, layerMurIndex) == 0) {


            if (map.getTileId(tileX, tileY, layerRouteIndex) != 0 || map.getTileId(tileX, tileY, layerBack) != 0) {

                if (map.getTileId(tileX, tileY, layerBack) != 0) {

                    clicksRequired++;

                    if (clicksRequired % 2 != 0) {
                        deltaPosX = 0;
                        deltaPosY = 0;

                        if (firstContact) {
                            firstContact = false;
                            new Sound("org/calma/ui/s_4204p2aa_201732050/laboratoire7/Oh_no.wav").play();
                        }
                    }
                } else {

                    firstContact = true;
                }


                imgSmiley_PosX += deltaPosX;
                imgSmiley_PosY += deltaPosY;
            }
        } else {

            bounce.play();
        }


        int playerTilePosX = (int) (imgSmiley_PosX / tileSize);
        int playerTilePosY = (int) (imgSmiley_PosY / tileSize);
        int ennemiTilePosX = (int) (ennemi_PosX / tileSize);
        int ennemiTilePosY = (int) (ennemi_PosY / tileSize);

        // Vérification de la collision avec l'ennemi
        if (playerTilePosX == ennemiTilePosX && playerTilePosY == ennemiTilePosY && !ennemiMort) {
            // Gestion du passage après avoir tué l'ennemi
            switch (lastDirection) {
                case LEFT:
                    imgSmiley_PosX = ennemi_PosX + tileSize;
                    break;
                case RIGHT:
                    imgSmiley_PosX = ennemi_PosX - tileSize;
                    break;
                case UP:
                    imgSmiley_PosY = ennemi_PosY + tileSize;
                    break;
                case DOWN:
                    imgSmiley_PosY = ennemi_PosY - tileSize;
                    break;
                default:
                    break;
            }

        }


        wasKeyPressedLeft = isKeyPressedLeft;
        wasKeyPressedRight = isKeyPressedRight;
        wasKeyPressedUp = isKeyPressedUp;
        wasKeyPressedDown = isKeyPressedDown;



        this.delta += delta; // Incrémentez delta avec le temps écoulé

        boolean isSpacePressed = input.isKeyPressed(Input.KEY_SPACE);
        if (isSpacePressed && this.delta >= FIRERATE) { // Vérifiez le temps écoulé
            Vector2f projectileDirection = determineProjectileDirection();
            Vector2f projectileVelocity = projectileDirection.scale(500);
            projectiles.add(new Projectile(new Vector2f(imgSmiley_PosX + 10, imgSmiley_PosY + 10), projectileVelocity));
            this.delta = 0; // Réinitialisez delta après le tir
        }

        // Update existing projectiles
        Iterator<Projectile> iter = projectiles.iterator();
        while (iter.hasNext()) {
            Projectile projectile = iter.next();
            if (projectile.siActif()) {
                projectile.update(delta);

                // Vérifiez la collision avec l'ennemi si l'ennemi est visible et non mort
                if (ennemiVisible && !ennemiMort) {
                    Vector2f projectilePosition = projectile.getPosition();
                    float projectilePosX = projectilePosition.getX();
                    float projectilePosY = projectilePosition.getY();
                    float ennemiWidth = anim_ennemi.getWidth();
                    float ennemiHeight = anim_ennemi.getHeight();
                    float ennemiRight = ennemi_PosX + ennemiWidth;
                    float ennemiBottom = ennemi_PosY + ennemiHeight;
                    if (projectilePosX >= ennemi_PosX && projectilePosX <= ennemiRight &&
                            projectilePosY >= ennemi_PosY && projectilePosY <= ennemiBottom) {
                        // Gérez la collision
                        ennemiMort = true;
                        ennemiVisible = false;
                        System.out.println("L'ennemi est visible : " + ennemiVisible);
                        System.out.println("L'ennemi est mort : " + ennemiMort);
                        projectile.setActive(false);
                    }
                }
            } else {
                iter.remove(); // Supprimez les projectiles inactifs de la liste
            }
        }

    }
    private Vector2f determineProjectileDirection() {
        switch (lastDirection) {
            case LEFT:
                return new Vector2f(-1, 0);
            case RIGHT:
                return new Vector2f(1, 0);
            case UP:
                return new Vector2f(0, -1);
            case DOWN:
            default:
                return new Vector2f(0, 1);
        }
    }
    private Animation getEnnemiAnimation() throws SlickException {
        Animation anim = new Animation(false);
        Image ennemiSpriteSheet = new Image("org/calma/ui/s_4204p2aa_201732050/laboratoire7/sprites/Loki_32x32.png");

        anim.addFrame(ennemiSpriteSheet.getSubImage(2, 0, 32, 32), 200);


        return anim;
    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        map.render(0, 0);


        switch (lastDirection) {
            case LEFT:
                anim_LEFT.draw(imgSmiley_PosX, imgSmiley_PosY);
                break;
            case RIGHT:
                anim_RIGHT.draw(imgSmiley_PosX, imgSmiley_PosY);
                break;
            case UP:
                anim_UP.draw(imgSmiley_PosX, imgSmiley_PosY);
                break;
            case DOWN:
            default:
                anim_DOWN.draw(imgSmiley_PosX, imgSmiley_PosY);
                break;
        }

        if (ennemiVisible && !ennemiMort) {
            anim_ennemi.draw(ennemi_PosX, ennemi_PosY);
        }

        for(Projectile projectile : projectiles){
            projectile.render(container, g);
        }

        obscurcir(g);
    }


}
