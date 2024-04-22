package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementVaiseau;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.newdawn.slick.*;

public class JoueurSpaceShip extends Vaiseau {

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
    public void update(GameContainer gc, int i) throws SlickException {
        //System.out.println("Joueur update");
        strategieDeplacementJoueur.deplacer(this, gc);


    }
//    @Override
//    public void alarme() {
//
//    }
}