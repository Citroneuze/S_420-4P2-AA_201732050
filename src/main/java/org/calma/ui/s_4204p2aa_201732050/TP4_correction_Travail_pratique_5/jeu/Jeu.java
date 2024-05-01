package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.jeu;

import javafx.application.Platform;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementLapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Observer.SystemeCapteursCollision;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.RabbitFactory.FabriqueLapins;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.TypeDeplacement.DeplacementAleatoireLapin;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.TypeDeplacement.DeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.Personnage;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage.*;
import java.util.*;

//Classe gérant le jeu
public class Jeu extends BasicGame {
    Random random = new Random();
    private boolean gameOver;
    private TiledMap map;
    private int indexCalqueObstacles;
    private int indexCalqueSol;
    private int tilesSize = Constante.getTilesSize();
    private Personnage personnageActif;
    private ArrayList<Personnage> personnages = new ArrayList<>();
    private Iterator<Personnage> iterPersonnages;
    private int lapinDelaiApparitionCompteur;
    private int lapinDelaiApparition;
    private int lapinNombreDepart;
    private int lapinNombreRegeneration;
    StrategieDeplacementLapin strategieDeplacementLapin = new DeplacementAleatoireLapin(); // Replace this with your actual strategy object creation
    StrategieDeplacementJoueur strategieDeplacementJoueur = new DeplacementJoueur();
    int lapinNombre = 10; // Example value for the number of bunnies
    private LinkedList<Projectile> projectiles;
    Iterator<Projectile> iterProjectiles;
    private Direction direction;
    private SystemeCapteursCollision capteurs = new SystemeCapteursCollision();
    public Jeu(String title) throws SlickException {
        super(title);
        gameOver=false;
        //Projectiles
        projectiles = new LinkedList<>();
        lapinNombreDepart = Constante.getLapinNombreDepart();
        lapinDelaiApparition = Constante.getLapinDelaiApparition();
        lapinNombreRegeneration = Constante.getLapinNombreRegeneration();

    }
    public Jeu(String title, TiledMap map) {
        super(title);
        this.map = map;
    }
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }
    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public TiledMap getMap() {
        return map;
    }
    public int getTilesSize() {
        return tilesSize;
    }
    private static Jeu instance;
    public static Jeu getInstance() throws SlickException {
        if (instance == null) {
            instance = new Jeu("Shit");
        }
        return instance;
    }
    public int getIndexCalqueObstacles() {
        return indexCalqueObstacles;
    }
    public boolean isCaseEmpty(int caseX, int caseY){

        for (Personnage element : personnages) {
            if(element.getCaseX() == caseX && element.getCaseY() == caseY) return false;
        }
        return true;
    }
    public void newBunnysOnMap(int lapinNombre, StrategieDeplacementLapin strategieDeplacement) throws SlickException {
        int caseX, caseY;

        String[] choixLapins = {
                "blanc",
                "noir",
                "gris",
                "beige",
                "blancbleu",
                "rose",
                "beigefonce",
                "brun"
        };
        int choixLapin = random.nextInt(0,choixLapins.length);
        for (int i = 0; i < lapinNombre; i++) {
            caseX = random.nextInt(0, Constante.getTilesNbX());
            caseY = random.nextInt(0, Constante.getTilesNbY());

            if (isCaseEmpty(caseX, caseY) && map.getTileId(caseX, caseY, indexCalqueObstacles) == 0) {
                Lapin lapin = new Lapin(caseX, caseY, strategieDeplacement) {
                }; // Passer la stratégie de déplacement au constructeur
                System.out.println(choixLapin);
                Personnage personnage = FabriqueLapins.getLapin(choixLapins[choixLapin], caseX, caseY, strategieDeplacement);
                capteurs.connecterObservateur(personnage);
                personnages.add(personnage);
            } else {
                i--;
            }
        }
    }
    public Personnage getPersonnageActif() {
        return personnageActif;
    }
    public void setPersonnageActif(Personnage personnageActif) {
        this.personnageActif = personnageActif;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public SystemeCapteursCollision getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(SystemeCapteursCollision capteurs) {
        this.capteurs = capteurs;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //carte
        map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/TP4/map/easter_map.tmx");
        //type de sol
        indexCalqueObstacles = map.getLayerIndex("Obstacles");
        indexCalqueSol = map.getLayerIndex("Sol");
        //personnages
        int caseX,caseY;
        boolean caseValide=false;
        do {
            caseX = random.nextInt(0, Constante.getTilesNbX());
            caseY = random.nextInt(0, Constante.getTilesNbY());
            if(map.getTileId(caseX,caseY, indexCalqueObstacles) == 0){
                personnageActif = new Joueur(caseX,caseY, strategieDeplacementJoueur);
                personnages.add(personnageActif);
                caseValide=true;
            }
        }
        while(!caseValide);
        //lapins
        newBunnysOnMap(lapinNombre, strategieDeplacementLapin);
    }

    @Override
    //Méthode de génération du jeu
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        //Carte
        map.render(0, 0);
        //Personnages
        for (Personnage personnage : personnages) {
//            personnage.AnimationDraw(tilesSize);
            personnage.render(gc, grphcs, direction);
        }
        //Projectiles
        for (Projectile projectile : projectiles) {
            projectile.render(gc, grphcs, direction);
        }
    }
    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        //Personnages
        iterPersonnages = personnages.iterator();
        while (iterPersonnages.hasNext()) {
            Personnage personnage = iterPersonnages.next();
            if (personnage.siActif()) {
                personnage.update(gc,i);

            }
            else {
                iterPersonnages.remove();
            }
        }
        if (!personnageActif.isEstActif()) {
            // Mettre fin au jeu si le personnage actif est mort
            gameOver = true;
            if (gameOver) {
                Platform.exit(); // Arrête l'application JavaFX
            }
        }

        //Lapins
        lapinDelaiApparitionCompteur+=i;
        if(lapinDelaiApparitionCompteur >= lapinDelaiApparition){
            lapinDelaiApparitionCompteur=0;
            newBunnysOnMap(lapinNombre, strategieDeplacementLapin);
        }

        //Projectiles
        iterProjectiles = projectiles.iterator();
        while (iterProjectiles.hasNext()) {
            Projectile projectile = iterProjectiles.next();

            if (projectile.siActif()) {
                projectile.update(i);
                //test si le projectile rencontre un obstacle
                if (map.getTileId(projectile.getCaseX() , projectile.getCaseY(), indexCalqueObstacles) > 0)
                {
                    iterProjectiles.remove();
                }
            }
            else {
                iterProjectiles.remove();
            }
        }

        if(!personnageActif.isEstActif()){
//            System.out.println("DÉFAITE JOUEUR !!!!");
            gameOver=true;
        }
        else if(personnages.size()==1){
//            System.out.println("VICTOIRE JOUEUR !!!!");
            gameOver=true;
        }
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE) ) {//&& gameOver
            System.out.println("Restart");
//            Application.getInstance().getApp().start();
//            Application.main(new String[] arg);
        }
    }
}
