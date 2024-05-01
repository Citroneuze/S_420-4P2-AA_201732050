package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Commande.PlayerMoveCommand;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Commande.TriggerButton;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App_javafx extends Application {
    private Jeu_spaceInvader jeu; // Instance du jeu
    private static final String TITRE = "Space_invader_Remix";

    public static String getTitre() {
        return TITRE;
    }

    @Override
    public void start(Stage primaryStage) throws SlickException {
        jeu = Jeu_spaceInvader.init(TITRE); // Initialize the game
        launchGame();

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(15, 20, 15, 20));
        buttonBox.setAlignment(Pos.CENTER);

        // Create buttons
        Button btnRestart = createTriggerButton("Restart", "restart");
        Button btnQuit = createTriggerButton("Quit", "quit");
        Button btnLeft = createPlayerMoveButton("\u2190", "LEFT");
        Button btnRight = createPlayerMoveButton("\u2192", "RIGHT");
        Button btnFire = createTriggerButton("Fire Laser", "fire");

        // Add buttons to the HBox
        buttonBox.getChildren().addAll(btnRestart, btnQuit, btnLeft, btnRight, btnFire);

        Scene scene = new Scene(buttonBox, 500, 100);
        primaryStage.setTitle(TITRE + " - Controls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createTriggerButton(String text, String command) {
        Button button = new Button(text);
        button.setMinWidth(90);
        button.setOnAction(e -> {
            TriggerButton commandButton = new TriggerButton(command);
            try {
                commandButton.execute();
            } catch (SlickException ex) {
                Logger.getLogger(App_javafx.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return button;
    }

    private Button createPlayerMoveButton(String text, String direction) {
        Button button = new Button(text);
        button.setMinWidth(90);
        button.setOnAction(e -> {
            PlayerMoveCommand moveCommand = new PlayerMoveCommand(direction);
            try {
                jeu.getJeuThread().sendCommandToGame(moveCommand);
            } catch (Exception ex) {
                Logger.getLogger(App_javafx.class.getName()).log(Level.SEVERE, "Failed to send move command", ex);
            }
        });
        return button;
    }

    private void launchGame() {
        int largeurAffichage = 640;
        int hauteurAffichage = 960;
        boolean siPleinEcran = false; // Changer à true pour activer le plein écran

        new Thread(() -> {
            try {
                AppGameContainer app = new AppGameContainer(jeu);
                app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
                app.setShowFPS(false);
                app.start();
            } catch (SlickException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
