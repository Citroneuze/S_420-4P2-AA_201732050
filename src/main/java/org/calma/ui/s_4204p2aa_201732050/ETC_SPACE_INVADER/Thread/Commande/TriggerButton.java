package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Commande;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Command;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.ThreadGestion;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.JoueurSpaceShip;
import org.newdawn.slick.SlickException;

import java.util.Objects;

public class TriggerButton implements Command {
    private String command;
    private JoueurSpaceShip personnage;
    private Jeu_spaceInvader jeu;

    public TriggerButton(String command) {
        this.command = command;
        this.personnage = Jeu_spaceInvader.getInstance().getJoueur();
    }

    @Override
    public void execute() throws SlickException {

        switch (command) {
            case "fire":
                // Assumes that the 'fireProjectile' method exists in JoueurSpaceShip
                personnage.fireProjectile();
                break;
            case "quit":
                // Assumes there's a method to quit the game
                System.exit(0);
                break;

        }
        ThreadGestion.focusSlickWindow();
        // Optional: refocus on the game window, useful if the game window could lose focus

    }
}
