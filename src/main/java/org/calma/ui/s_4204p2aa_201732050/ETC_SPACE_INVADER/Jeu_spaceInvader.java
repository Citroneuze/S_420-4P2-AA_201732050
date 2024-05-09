package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement.DeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement.DeplacementJoueur;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Observer.SystemeCapteursCollisionShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Command;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.ThreadGestion;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.Vaisseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.shipYard.ShipFactory;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.*;

public class Jeu_spaceInvader extends BasicGame {
    private ArrayList<Vaisseau> vaisseaux = new ArrayList<>();
    Random random = new Random();
    private int shipNumber = 5;
    private JoueurSpaceShip joueur;
    private TiledMap map;
    private int indexCalqueObstacles;
    private SystemeCapteursCollisionShip capteurs = new SystemeCapteursCollisionShip();
    private StrategieDeplacementVaiseau strategieDeplacementJoueur = new DeplacementJoueur();
    private StrategieDeplacementEnnemi strategieDeplacementEnnemi = new DeplacementEnnemi();
    private LinkedList<Lazer> lazers;
    Iterator<Lazer> iterLazers;
    private int score = 0;
    private int currentLevel = 1;
    private static final int MAX_LEVEL = 2;  // Vous avez 2 niveaux dans le jeu
    private boolean gameOver = false;
    private static Jeu_spaceInvader INSTANCE = null;
    private ThreadGestion jeuThread = new ThreadGestion();
    private SoundManager soundManager;
    public static Jeu_spaceInvader getInstance() {
        return INSTANCE;
    }
    public ThreadGestion getJeuThread() {
        return jeuThread;
    }
    public void setJeuThread(ThreadGestion jeuThread) {
        this.jeuThread = jeuThread;
    }
    public static Jeu_spaceInvader init(String title) throws SlickException {
        if(INSTANCE != null){
            return INSTANCE;
        }
        else{
            INSTANCE = new Jeu_spaceInvader(title);
            return INSTANCE;
        }
    }
    public Jeu_spaceInvader(String title) {
        super(title);
        lazers = new LinkedList<>();
    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        soundManager = new SoundManager();
        loadLevel(currentLevel);
    }
    public void loadLevel(int level) throws SlickException {
        soundManager.loadSound("background", "src/main/resources/org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/music/music.wav");
        soundManager.loadSound("laser", "src/main/resources/org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/music/laser.wav");
        soundManager.loopSound("background");

        lazers.clear();
        map = new TiledMap("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/map/nebula" + level + ".tmx");
        indexCalqueObstacles = map.getLayerIndex("wall");
        joueur = new JoueurSpaceShip(9, 27, strategieDeplacementJoueur);
        vaisseaux.clear();
        vaisseaux.add(joueur);
        if (level == 2) {
            // Pour le niveau 2, générer deux vagues d'ennemis
            spawnWave(shipNumber, strategieDeplacementEnnemi, 3, 5);  // Première vague à la ligne 5
            spawnWave(shipNumber, strategieDeplacementEnnemi, 3, 10); // Deuxième vague à la ligne 10
        } else {
            // Pour les autres niveaux, une seule vague
            newEnnemiShipsOnMap(shipNumber * level, strategieDeplacementEnnemi);
        }
    }
    public void changeMap() throws SlickException {
        if (currentLevel < MAX_LEVEL) {
            currentLevel++;
            loadLevel(currentLevel);
        } else {
            gameOver = true; // Ou redémarrer le jeu, ou montrer un écran de victoire, etc.
        }
    }
    private void spawnWave(int shipNumber, StrategieDeplacementEnnemi strategieDeplacement, int startX, int startY) throws SlickException {
        int shipWidthInTiles = 2;  // Largeur du vaisseau en nombre de tuiles

        for (int i = 0; i < shipNumber; i++) {
            int caseX = startX + i * shipWidthInTiles;
            int caseY = startY;

            // Vérification des limites de la carte pour éviter de placer des vaisseaux hors de la carte
            if (caseX + shipWidthInTiles <= map.getWidth() && isCaseEmpty(caseX, caseY) && map.getTileId(caseX, caseY, indexCalqueObstacles) == 0) {
                Vaisseau vaisseau = ShipFactory.getEnnemi("battleCruiser", caseX, caseY, strategieDeplacement);
                capteurs.connecterObservateur(vaisseau);
                vaisseaux.add(vaisseau);
            }
        }
    }
    public void playsound(){
        soundManager.playSound("laser");
        soundManager.stopSound("laser");
    }
   // Déclarer une variable pour suivre si le jeu est terminé
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (!gameOver) {  // Vérifier si le jeu n'est pas déjà terminé
            //---------- INTÉGRATION DU THREAD - DEBUT -------------------------//
            while (!jeuThread.getCommandQueue().isEmpty()) {
                try {
                    //traiter la commande
                    Command command = jeuThread.getCommandQueue().take();
                    command.execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //---------- INTÉGRATION DU THREAD - FIN -------------------------//
            Iterator<Vaisseau> iterVaisseau = vaisseaux.iterator();
            while (iterVaisseau.hasNext()) {
                Vaisseau vaisseau = iterVaisseau.next();
                if (vaisseau.siActif()) {
                    if (isPositionValid(vaisseau.getCaseX(), vaisseau.getCaseY())) {
                        vaisseau.update(gameContainer, i);
                    } else {
                        iterVaisseau.remove(); // Supprimer le vaisseau s'il est en dehors des limites
                    }
                } else {
                    if (vaisseau instanceof EnnemiSpaceShip) {
                        score += 10; // Augmenter le score de 10 points par ennemi tué
                    }
                    iterVaisseau.remove();
                }
            }
            iterLazers = lazers.iterator();
            while (iterLazers.hasNext()) {
                Lazer lazer = iterLazers.next();
                if (lazer.siActif()) {
                    lazer.update(i);
                    if (!isPositionValid(lazer.getCaseX(), lazer.getCaseY())) {
                        iterLazers.remove();
                    }
                } else {
                    iterLazers.remove();
                }
            }
            if (allEnemiesDefeated()) {
                score += 100;
                 // Marquer le jeu comme terminé
                if (currentLevel < MAX_LEVEL) {
                    currentLevel++;
                    loadLevel(currentLevel);
                } else {
                    gameOver = true;  // Marquer le jeu comme terminé
                    showEndGameOptions();  // Afficher les options de fin de jeu
                }
            }
            if(!joueur.isEstActif()){
                gameOver = true;
            }
        }
        handleInput(gameContainer);
    }
    private void showEndGameOptions() {
        System.out.println("Fin du jeu. Score final : " + score);
        // Vous pouvez ici définir la logique pour afficher les options de redémarrage ou de sortie du jeu.
    }
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Render the map background first
        map.render(0, 0);
        // Display the current level and score in the top-left corner
        graphics.drawString("Niveau " + currentLevel + " - Score: " + score, 10, 30);
        // Render all enemy ships
        for (Vaisseau ennemi : vaisseaux) {
            ennemi.render(gameContainer, graphics, Direction.RIGHT);
        }
        // Render all lasers
        for (Lazer lazer : lazers) {
            lazer.render(gameContainer, graphics);
        }
        // If the game is over, display the game over screen
        if (gameOver) {
            graphics.setColor(org.newdawn.slick.Color.red);  // Set the color for the game over text
            graphics.drawString("Appuyez sur 'R' pour recommencer ou 'Q' pour quitter.", 100, 130);
            graphics.setColor(org.newdawn.slick.Color.white); // Reset the color to default
        }
    }

    private boolean allEnemiesDefeated() {
        for (Vaisseau vaisseau : vaisseaux) {
            if (vaisseau instanceof EnnemiSpaceShip && vaisseau.isEstActif()) {
                return false; // Il reste au moins un ennemi actif
            }
        }
        return true; // Tous les ennemis sont vaincus
    }

    private boolean isPositionValid(int caseX, int caseY) {
        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();
        return caseX >= 0 && caseX < mapWidth && caseY >= 0 && caseY < mapHeight;
    }

    private boolean gameOver() {
        // Ajoutez ici votre logique pour déterminer si le jeu est terminé
        return false; // Modifier cette condition selon les règles de fin de partie
    }


    private void spawnShips(int shipNumber, String shipType, StrategieDeplacementEnnemi strategieDeplacement) throws SlickException {
        int startX = 5;  // Position initiale en tuiles (supposons que le bord gauche est libre)
        int startY = 3;  // Position verticale initiale en tuiles
        int shipWidthInTiles = 2;  // Largeur du vaisseau en nombre de tuiles

        for (int i = 0; i < shipNumber; i++) {
            int caseX = startX + i * shipWidthInTiles;
            int caseY = startY;

            // Vérification des limites de la carte pour éviter de placer des vaisseaux hors de la carte
            if (caseX + shipWidthInTiles <= 20 && isCaseEmpty(caseX, caseY) && map.getTileId(caseX, caseY, indexCalqueObstacles) == 0) {
                Vaisseau vaisseau = ShipFactory.getEnnemi(shipType, caseX, caseY, strategieDeplacement);
                capteurs.connecterObservateur(vaisseau);
                vaisseaux.add(vaisseau);
            }
        }
    }

    public void newEnnemiShipsOnMap(int shipNumber, StrategieDeplacementEnnemi strategieDeplacement) throws SlickException {
        String[] choixVaisseaus = {"battleCruiser", "bomber", "dreadnought", "asteroid"};
        int choixVaisseau = random.nextInt(choixVaisseaus.length);

        if (choixVaisseaus[choixVaisseau].equals("asteroid")) {
            spawnShips(20, choixVaisseaus[choixVaisseau], strategieDeplacement);  // Spawn 10 asteroids
        } else {
            spawnShips(shipNumber, choixVaisseaus[choixVaisseau], strategieDeplacement);  // Spawn the specified number of ships

        }
    }

    public boolean isOccupiedByShip(int x, int y) {
        for (Vaisseau vaisseau : vaisseaux) {
            if (vaisseau.getCaseX() == x && vaisseau.getCaseY() == y && vaisseau.isEstActif()) {
                return true;  // Position is occupied by an active ship
            }
        }
        return false;  // No ships at the given position
    }

    public void resetGame() {
        score = 0;
        currentLevel = 1;
        System.out.println(currentLevel+"javafx");
        vaisseaux.clear();
        lazers.clear();
        gameOver = false;
        try {
            loadLevel(currentLevel);  // Recharge le premier niveau
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void handleInput(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (gameOver) {
            if (input.isKeyPressed(Input.KEY_R)) {
                resetGame();
            } else if (input.isKeyPressed(Input.KEY_Q)) {
                System.exit(0);  // Exit the game
            }
        }
    }

    public TiledMap getMap() {
        return map;
    }

    public int getIndexCalqueObstacles() {
        return indexCalqueObstacles;
    }


    public int getTilesSize() {
        return 32;
    }

    public boolean isCaseEmpty(int i, int caseY) {
        return true;
    }


    public ArrayList<Vaisseau> getVaisseau() {
        return vaisseaux;
    }

    public LinkedList<Lazer> getLazers() {
        return lazers;
    }

    public void setLazers(LinkedList<Lazer> lazers) {
        this.lazers = lazers;
    }

    public Iterator<Lazer> getIterLazers() {
        return iterLazers;
    }

    public void setIterLazers(Iterator<Lazer> iterLazers) {
        this.iterLazers = iterLazers;
    }

    public SystemeCapteursCollisionShip getCapteurs() {
        return capteurs;
    }

    public JoueurSpaceShip getPersonnageActif() {
        return joueur;
    }

    public JoueurSpaceShip getJoueur() {
        return joueur;
    }
}