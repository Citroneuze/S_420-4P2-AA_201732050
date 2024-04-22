package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement.DeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement.DeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
//import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer.SystemeCapteursCollision;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.Vaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.shipYard.ShipFactory;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;


import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Personnage;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu_spaceInvader extends BasicGame {
    private ArrayList<Vaiseau> vaisseauEnnemi = new ArrayList<>();
    private JoueurSpaceShip joueur;
    Random random = new Random();
    private int shipNumber = 5;

    private TiledMap map;
    private int indexCalqueObstacles;
    //private SystemeCapteursCollision capteurs = new SystemeCapteursCollision();
    private StrategieDeplacementVaiseau strategieDeplacementJoueur = new DeplacementJoueur();
    private StrategieDeplacementEnnemi strategieDeplacementEnnemi = new DeplacementEnnemi();
    public Jeu_spaceInvader(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/map/nebulaR.tmx");
            indexCalqueObstacles = map.getLayerIndex("wall");
            joueur = new JoueurSpaceShip(9, 27, strategieDeplacementJoueur);
            newEnnemiShipsOnMap(shipNumber, strategieDeplacementEnnemi);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        joueur.setDirection(Direction.RIGHT);
        joueur.update(gameContainer, i);
        Iterator<Vaiseau> iterPersonnages = vaisseauEnnemi.iterator();
        while (iterPersonnages.hasNext()) {
            Vaiseau personnage = iterPersonnages.next();
            System.out.println("Vaisseau avant mise à jour: Position X=" + personnage.getCaseX() + ", Y=" + personnage.getCaseY()); // Debug
            if (personnage.siActif()) {
                personnage.update(gameContainer, i);
                System.out.println("Vaisseau après mise à jour: Position X=" + personnage.getCaseX() + ", Y=" + personnage.getCaseY()); // Debug
            } else {
                iterPersonnages.remove();
            }
        }
    }


    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.render(0, 0);
        joueur.render(gameContainer, graphics, Direction.RIGHT);

        // Rendre tous les vaisseaux ennemis
        for (Vaiseau ennemi : vaisseauEnnemi) {
            ennemi.render(gameContainer, graphics, Direction.RIGHT); // Vous pouvez utiliser la direction appropriée ici
        }
    }

    public void newEnnemiShipsOnMap(int shipNumber, StrategieDeplacementEnnemi strategieDeplacement) throws SlickException {
        int caseX, caseY;

        String[] choixVaisseaus = {
                "battleCruiser",
                "bomber",
                "dreadnought",
                "asteroid"
        };
        int choixVaisseau = random.nextInt(0 , choixVaisseaus.length);
        for (int i = 0; i < shipNumber; i++) {
            caseX = random.nextInt(0, 20);  // Assume the map width of 20
            caseY = random.nextInt(0, 10);  // Limit to the first 10 tiles for height

            if (isCaseEmpty(caseX, caseY) && map.getTileId(caseX, caseY, indexCalqueObstacles) == 0) {
                EnnemiSpaceShip ennemi = new EnnemiSpaceShip(caseX, caseY, strategieDeplacementEnnemi) {
                }; // Pass the movement strategy to the constructor
                System.out.println(choixVaisseau);
                Vaiseau vaisseau = ShipFactory.getEnnemi(choixVaisseaus[choixVaisseau], caseX, caseY, strategieDeplacement);
                vaisseauEnnemi.add(vaisseau);
            } else {
                i--;  // Decrement to retry a new position if the case is not empty or valid
            }
        }
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

    public boolean isCaseEmpty(int i, int caseY) {
        return true;
    }
}

