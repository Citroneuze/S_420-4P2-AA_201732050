package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Thread.Commande.PlayerMoveCommand;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App_javafx extends Application {
    private Jeu_spaceInvader jeu; // Instance du jeu
    private static String titre = "Space_invader_Remix";
    public static String getTitre() {
        return titre;
    }

    @Override
    public void start(Stage primaryStage) throws SlickException {
        jeu = Jeu_spaceInvader.init(titre); // Initialiser le jeu avec le singleton ou méthode appropriée
        launchGame();

        // Créer le VBox et configurer les propriétés
        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_LEFT);

        // Créer un menu
        MenuBar menuBar = new MenuBar();
        Menu menuGame = new Menu("Game");
        MenuItem startGame = new MenuItem("Start Game");
        MenuItem exitItem = new MenuItem("Exit");

        startGame.setOnAction(event -> launchGame());
        exitItem.setOnAction(e -> System.exit(0));

        menuGame.getItems().addAll(startGame,exitItem);
        menuBar.getMenus().add(menuGame);
        root.getChildren().add(menuBar);

        Label leftArrow = new Label("\u2190");
        Label rightArrow = new Label("\u2192");

        //---------- INTÉGRATION DU THREAD - DEBUT -------------------------//
        leftArrow.setOnMouseClicked(event -> jeu.getJeuThread().sendCommandToGame(new PlayerMoveCommand("LEFT")));
        rightArrow.setOnMouseClicked(event -> jeu.getJeuThread().sendCommandToGame(new PlayerMoveCommand("RIGHT")));
        //---------- INTÉGRATION DU THREAD - FIN -------------------------//

        // Ajouter des flèches à la VBox
        VBox arrowsBox = new VBox(5, leftArrow, rightArrow);
        arrowsBox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(arrowsBox);


        // Configurer la scène et le stage
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle(titre+" - Controls");
        primaryStage.setScene(scene);
        primaryStage.show();

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

