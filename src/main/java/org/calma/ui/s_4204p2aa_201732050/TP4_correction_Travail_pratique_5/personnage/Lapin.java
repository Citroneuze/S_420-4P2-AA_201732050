package org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.personnage;

import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Application;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.RabbitFactory.FabriqueLapins;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.projectile.Projectile;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Constante;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.utils.Direction;
import org.calma.ui.s_4204p2aa_201732050.TP4_correction_Travail_pratique_5.Interface.StrategieDeplacementLapin;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.util.LinkedList;
import java.util.Random;



public class Lapin extends Personnage {

    private int delta;
    private int delaisDeplacement;
    private int tilesSize = Constante.getTilesSize();
    private StrategieDeplacementLapin strategieDeplacementLapin;
    private Image imgAnimPersonnage = new Image("org/calma/ui/s_4204p2aa_201732050/TP4/bunny.png");

    public <strategie> Lapin(int caseX, int caseY, StrategieDeplacementLapin strategie) throws SlickException {
        super("Lapin", caseX, caseY);
        this.strategieDeplacementLapin = strategie;
        Random random = new Random();
        this.setFireRate(random.nextInt(Constante.getLapinFirerateMin(),Constante.getLapinFirerateMax()));
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Lapinous render");
        super.render(gc,grphcs,direction);
    }
    public void update(GameContainer gc, int i) throws SlickException {
//        System.out.println("LAPINOU UPDATE !!!");
        //SHOOT
        int posX = this.getCaseX() * tilesSize;
        int posY = this.getCaseY() * tilesSize;
        LinkedList<Projectile> projectiles = Application.getInstance().getJeu().getProjectiles();
        delta += i;
        if (delta >= getFireRate()) {
//            this.setDirection(Direction.RIGHT);
            Vector2f positionProjectile = new Vector2f(posX, posY);
            Vector2f deplacementProjectile;
            int correctifCentreVertical = 8;
            this.setDirection(Direction.getRandomDirection());      //Direction du personnage au hasard

            switch (this.getDirection()) {
                case RIGHT:
                    positionProjectile.add(new Vector2f(tilesSize, correctifCentreVertical));
                    deplacementProjectile = new Vector2f(500, 0);
                    break;
                case LEFT:
                    positionProjectile.add(new Vector2f(-tilesSize, correctifCentreVertical));
                    deplacementProjectile = new Vector2f(-500, 0);
                    break;
                case UP:
                    positionProjectile.add(new Vector2f(0, -tilesSize));
                    deplacementProjectile = new Vector2f(0, -500);
                    break;
                case DOWN:
                    positionProjectile.add(new Vector2f(0, tilesSize));
                    deplacementProjectile = new Vector2f(0, 500);
                    break;
                default:
                    deplacementProjectile = new Vector2f(0, 0);
            }
            projectiles.add(new Projectile(positionProjectile, deplacementProjectile, this.getDirection()));
            delta = 0;
        }
        //MOVE
        delaisDeplacement +=i;
        TiledMap map = Application.getInstance().getJeu().getMap();
        int indexCalqueObstacles = Application.getInstance().getJeu().getIndexCalqueObstacles();
        if(delaisDeplacement >= Constante.getLapinDelaisDeplacement()){
//            System.out.println("Move the lapinou:");
            delaisDeplacement = 0;
            strategieDeplacementLapin.deplacer(this);

        }
    }

    @Override
    public void alarme() {

    }
}
