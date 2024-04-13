package org.calma.ui.s_4204p2aa_201732050.South_Park;

import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.Pied;
import org.calma.ui.s_4204p2aa_201732050.South_Park.personnage.*;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

import static org.newdawn.slick.Input.*;

public class Jeu_SouthPark extends BasicGame {
    private TiledMap map;
    private int tilesSize;

//    private Kyle kyle;
//    private Stan stan;
//    private Kenny kenny;
//    private Cartman cartma5n;
//   private Butters butters;
    private Personnage personnage;
    private ArrayList<Personnage> personnages = new ArrayList<>();
    public Jeu_SouthPark(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //map
        map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/res_South_Park/maps/minimap.tmx");
        tilesSize = 32;

        personnages.add(new Butters(13, 8));
        personnages.add(new Cartman(22, 6));
        personnages.add(new Kenny(22, 12));
        personnages.add(new Kyle(4, 8));
        personnages.add(new Stan(22, 8));

        personnage = personnages.get(3);


        container.setTargetFrameRate(30);
    }
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        int indexCalqueRoute = map.getLayerIndex("Route");
        int indexCalqueMaison = map.getLayerIndex("Maison");
        int indexCalqueMaisonPorte = map.getLayerIndex("MaisonPorte");
        int indexCalqueArbre = map.getLayerIndex("Arbre");
        int indexCalqueSol = map.getLayerIndex("Sol");
        int posX = personnage.getCaseX() * tilesSize;
        int posY = personnage.getCaseY() * tilesSize;


        ////////////////////////////////////////////////////////////////////////

        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            if (map.getTileId(personnage.getCaseX() - 1, personnage.getCaseY(), indexCalqueSol) == 0) {
                personnage.setDirection(Direction.LEFT);
                personnage.deplacement();
                personnage.MoveLeft();
            }
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if (map.getTileId(personnage.getCaseX() + 1, personnage.getCaseY(), indexCalqueSol) == 0) {
                personnage.setDirection(Direction.RIGHT);
                personnage.deplacement();
                personnage.MoveRight();
            }
        }

        if (input.isKeyPressed(Input.KEY_UP)) {
            if (map.getTileId(personnage.getCaseX(), personnage.getCaseY() - 1, indexCalqueSol) == 0) {
                personnage.setDirection(Direction.UP);
                personnage.deplacement();
                personnage.MoveUp();
            }
        }

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (map.getTileId(personnage.getCaseX(), personnage.getCaseY() + 1, indexCalqueSol) == 0) {
                personnage.setDirection(Direction.DOWN);
                personnage.deplacement();
                personnage.MoveDown();
            }
        }

        ////////////////////////////////////////////////////////////////////////

        if (input.isKeyPressed(Input.KEY_1)) {
            personnage = personnages.get(3);
        }
        else if (input.isKeyPressed(Input.KEY_2)) {
            personnage = personnages.get(0);
        }
        else if (input.isKeyPressed(Input.KEY_3)) {
            personnage = personnages.get(1);
        }
        else if (input.isKeyPressed(Input.KEY_4)) {
            personnage = personnages.get(2);
        }
        else if (input.isKeyPressed(Input.KEY_5)) {
            personnage = personnages.get(4);
        }


        if(input.isKeyPressed(KEY_EQUALS)){
            personnage.InventaireAjouter();
        }
        if(input.isKeyPressed(KEY_MINUS)){
            personnage.InventaireSupprimer();
        }


        if (input.isKeyPressed(Input.KEY_Q) && (personnage instanceof Pied)) {
            System.out.println("Marcher");
            personnage.setDeplacementType(1);
        }
        if (input.isKeyPressed(Input.KEY_W) && (personnage instanceof Pied)) {
            System.out.println("Courrir");
            personnage.setDeplacementType(2);
        }
        if (input.isKeyPressed(Input.KEY_E) && (personnage instanceof Pied)) {
            System.out.println("Velo");
            personnage.setDeplacementType(3);
        }
    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        map.render(0,0);

        for(Personnage element : personnages){
            element.animationDraw(tilesSize);
        }

        //Gestion Personnage
        personnage.AnimationMenuDraw(tilesSize);
        personnage.InventaireDraw(tilesSize);
        personnage.deplacementDraw();
    }

}
