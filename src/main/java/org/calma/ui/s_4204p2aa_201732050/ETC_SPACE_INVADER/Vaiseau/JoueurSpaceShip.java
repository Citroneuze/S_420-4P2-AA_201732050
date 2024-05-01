package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Lazer.Lazer;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.OwnerId;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class JoueurSpaceShip extends Vaisseau {

    //private ObserverCollision observer;
    private Image imgAnimPersonnage = new Image("org/calma/ui/s_4204p2aa_201732050/ETC_SPACE_INVADER/sprite/Klaed-Battlecruiser-Base.png");
    private StrategieDeplacementVaiseau strategieDeplacementJoueur;
    public JoueurSpaceShip(int caseX, int caseY, StrategieDeplacementVaiseau strategie) throws SlickException {
        super("Joueur", caseX, caseY);
        this.strategieDeplacementJoueur = strategie;
        //déplacement spécifique au perso
        int rowX = 0;
        int rowY = 0;
        int height = 120;
        int width = 100;
        this.setAnim_LEFT(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setAnim_RIGHT(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setAnim_DOWN(getAnimation(imgAnimPersonnage,rowX,rowY+height,height,width));
        this.setEstJoueur(true);
    }
    public void render(GameContainer gc, Graphics grphcs, Direction direction) {
//        System.out.println("Joueur render");
        super.render(gc, grphcs, direction);
    }
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            fireProjectile();
        }
        strategieDeplacementJoueur.deplacer(this, gc);
    }

    private void fireProjectile() {
        int posX = this.getCaseX() * 32;
        int posY = this.getCaseY() * 32;
        Vector2f position = new Vector2f(posX, posY - 32); // Ajuster pour tirer depuis le haut du vaisseau
        Vector2f velocity = new Vector2f(0, -500); // Vitesse et direction du projectile
        Jeu_spaceInvader.getInstance().getLazers().add(new Lazer(position, velocity, Direction.DOWN, OwnerId.PLAYER));
    }

    @Override
    public void alarme() {

    }
}