package org.calma.ui.s_4204p2aa_201732050.TP4;

import org.calma.ui.s_4204p2aa_201732050.TP4.personnage.*;
import org.calma.ui.s_4204p2aa_201732050.TP4.utils.Constante;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu extends BasicGame {
    private int tempsEcouleApresVictoire = 0;
    private static final int TEMPS_ATTENTE_APRES_VICTOIRE = 3000; // Temps en millisecondes (10 secondes)

    private TiledMap map;
    private ArrayList<Personnage> lapins = new ArrayList<>();
    private Personnage lapin;
    private Joueur joueur;
    Iterator<Personnage> iter;
    private int lapinTimer = 0;
    private int  compteurKill = 0;
    public Jeu(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/TP4/map/easter_map.tmx");

        int indexCalqueObstacles = map.getLayerIndex("Obstacles");


        joueur = new Joueur("joueur", 11, 11, map, indexCalqueObstacles);
        lapins.add(new Lapin("1", 10, 10, map, indexCalqueObstacles));
        lapin = lapins.get(0);

        System.out.printf(String.valueOf(lapins));

        container.setTargetFrameRate(30);
    }
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        iter = lapins.iterator();
        while (iter.hasNext()) {
            Personnage personnage = iter.next();
            if (personnage.isAlive()) {
                personnage.update(container, delta);
            }
        }
        joueur.update(container, delta);

        for (Personnage lapin : new ArrayList<>(lapins)) { // Utilisation d'une nouvelle liste pour éviter ConcurrentModificationException
            if (joueur.getCaseX() == lapin.getCaseX() && joueur.getCaseY() == lapin.getCaseY()) {
                System.out.println("Le joueur a marché sur un lapin !");
                if (lapin instanceof Lapin) {
                    ((Lapin) lapin).setAlive(false, (Lapin) lapin); // Appeler la méthode pour faire mourir le lapin
                    compteurKill++;
                    lapins.remove(lapin); // Supprimer le lapin de la liste
                }
            }
        }

        lapinTimer += delta;

        // Si le compteur atteint 5000 millisecondes (5 secondes), créer un nouveau lapin
        if (lapinTimer >= Constante.getLapinDelaiApparition()) {
            ajouterNouveauLapin();
            lapinTimer = 0; // Réinitialiser le compteur
        }

        if (compteurKill >= 10) {
            System.out.println("Le joueur a atteint 2 kills, jeu terminé !");
            tempsEcouleApresVictoire += delta;
            if (tempsEcouleApresVictoire >= TEMPS_ATTENTE_APRES_VICTOIRE) {
                container.exit(); // Fermer le jeu après le délai spécifié
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        map.render(0, 0);
        for (Personnage personnage : lapins) {
            if (personnage.isAlive()){
                personnage.render(container, g);
            }

        }
        if(joueur.isAlive()){
            joueur.render(container, g);
        }
    }

    public int getJoueurPositionX() {
        return joueur.getCaseX();
    }

    public int getJoueurPositionY() {
        return joueur.getCaseY();
    }

    public int getLapinPositionX() {
        if (lapin != null) {
            return lapin.getCaseX(); // Supposons que getCaseX() renvoie la position X du lapin
        }
        return -1; // Valeur par défaut si le lapin n'est pas présent
    }

    public int getLapinPositionY() {
        if (lapin != null) {
            return lapin.getCaseY(); // Supposons que getCaseY() renvoie la position Y du lapin
        }
        return -1; // Valeur par défaut si le lapin n'est pas présent
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Personnage> getLapins() {
        return lapins;
    }

    private void ajouterNouveauLapin() throws SlickException {
        int indexCalqueObstacles = map.getLayerIndex("Obstacles");
        int indexCalqueSol = map.getLayerIndex("Sol");
        Random random = new Random();
        int randomX = getRandomPositionX();
        int randomY = getRandomPositionY();

        // Vérifier que la position aléatoire n'est pas sur un obstacle et sur le sol
        if (map.getTileId(randomX, randomY, indexCalqueObstacles) == 0 && map.getTileId(randomX, randomY, indexCalqueSol) != 0) {
            lapins.add(new Lapin("Nouveau Lapin", randomX, randomY, map, indexCalqueObstacles));
        }
    }

    private int getRandomPositionX() {
        Random random = new Random();
        return random.nextInt(map.getWidth());
    }

    private int getRandomPositionY() {
        Random random = new Random();
        return random.nextInt(map.getHeight());
    }

    public void setKill() {
        this.compteurKill += 1;
        System.out.println(compteurKill);
    }

    public void removeLapin(Lapin lapin) {
        lapins.remove(lapin);
    }
}
