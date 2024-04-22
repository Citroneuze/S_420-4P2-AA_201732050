package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement.DeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
//import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer.SystemeCapteursCollision;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.Vaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Jeu_spaceInvader extends BasicGame {

    private JoueurSpaceShip joueur;
    private TiledMap map;
    private int indexCalqueObstacles;
    //private SystemeCapteursCollision capteurs = new SystemeCapteursCollision();
    private StrategieDeplacementVaiseau strategieDeplacementJoueur = new DeplacementJoueur();
    public Jeu_spaceInvader(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/map/nebulaR.tmx");
            indexCalqueObstacles = map.getLayerIndex("wall");
            joueur = new JoueurSpaceShip(10, 10, strategieDeplacementJoueur);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        joueur.update(gameContainer, i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.render(0, 0);
        joueur.render(gameContainer, graphics, Direction.RIGHT);
    }

    public TiledMap getMap() {
        return map;
    }

    public int getIndexCalqueObstacles() {
        return indexCalqueObstacles;
    }
//    public SystemeCapteursCollision getCapteurs() {
//        return capteurs;
//    }

    public int getTilesSize() {
        return 32;
    }
}

