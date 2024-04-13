package org.calma.ui.s_4204p2aa_201732050.South_Park.personnage;

import org.calma.ui.s_4204p2aa_201732050.South_Park.deplacement.*;
import org.calma.ui.s_4204p2aa_201732050.South_Park.friandises.Bonbon;
import org.calma.ui.s_4204p2aa_201732050.South_Park.friandises.Friandise;
import org.newdawn.slick.SlickException;

public class Kyle extends Personnage implements Pied {
    public Kyle(int caseX, int caseY) throws SlickException {
        super("Kyle", caseX, caseY);
        //
        //deplacement specifique au perso
        int rowY = 30;
        this.setAnim_UP(getAnimation(360, rowY));
        this.setAnim_DOWN(getAnimation(3, rowY));
        this.setAnim_LEFT(getAnimation(478, rowY));
        this.setAnim_RIGHT(getAnimation(141, rowY));
    }

    @Override
    public void InventaireAjouter() throws SlickException {
        System.out.println("ajout d'un bonbon");
        Friandise inventaireTemp[] = super.getInventaireFriandise();

        Bonbon bonbontest = new Bonbon();
        for(int i = 0;i < inventaireTemp.length; i++){
            if(inventaireTemp[i]==null){
                inventaireTemp[i] = bonbontest;
                break;
            }
        }
        super.setInventaireFriandise(inventaireTemp);
    }

    @Override
    public void InventaireSupprimer() {
        Friandise inventaireTemp[] = super.getInventaireFriandise();
        for (int i = 0;i < inventaireTemp.length; i++){
            if(inventaireTemp[i] != null){
                inventaireTemp[i] = null;
                break;
            }
        }
        super.setInventaireFriandise(inventaireTemp);
    }


    @Override
    public void marcher() {
        System.out.println(this.getNom() + "marche");
        this.setMovingStep(1);
        Deplacement.deplacementDelais(this.getDeplacementDelaisMarche());
    }

    @Override
    public void courir() {
        System.out.println(this.getNom() + "Court");
        this.setMovingStep(1);
        Deplacement.deplacementDelais(this.getDeplacementDelaisCourse());
    }
}
