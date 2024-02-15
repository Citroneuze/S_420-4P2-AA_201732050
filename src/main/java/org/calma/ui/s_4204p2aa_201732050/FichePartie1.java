package org.calma.ui.s_4204p2aa_201732050;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class FichePartie1 extends Application {

    private DatePicker checkInDatePicker;

    @Override
    public void start(Stage stage) throws Exception {
        // Base
        VBox root = new VBox();

        // MenuBAr
        MenuBar menubar = new MenuBar();
        menubar.prefWidthProperty().bind(stage.widthProperty());
        Menu FileMenu = new Menu("File");
        Menu EditMenu = new Menu("Edit");
        Menu Help = new Menu("Help");
        root.getChildren().add(menubar);
        menubar.getMenus().addAll(FileMenu, EditMenu, Help);

        GridPane gridPane = new GridPane();
        root.getChildren().add(gridPane);

        // Chargement de l'image depuis le chemin spécifié
        ImageView logo = new ImageView(new Image(new FileInputStream("src/main/resources/images/ocp.png")));
        logo.setFitHeight(50);
        logo.setFitWidth(50);

        // Création du label pour le titre
        Label titleLabel = new Label("Titre de votre application");
        titleLabel.setStyle("-fx-font-size: 18px;");

        // Ajouter l'image et le titre dans le GridPane
        gridPane.add(logo, 0, 0); // Image dans la première colonne, première ligne
        gridPane.add(titleLabel, 1, 0); // Titre dans la deuxième colonne, première ligne

        Label identification = new Label("Identification");
        Label prenom = new Label("Prenom");
        Label nom = new Label("Nom");
        Label date = new Label("Date de Naissance");
        Label courriel = new Label("Courriel");

        TextField tfIdentification = new TextField();
        TextField tfPrenom = new TextField();
        TextField tfNom = new TextField();
        checkInDatePicker = new DatePicker();
        ImageView imgCourriel = new ImageView(new Image(new FileInputStream("src/main/resources/images/email_invalid.png")));
        TextField tfCourriel = new TextField();

        gridPane.addRow(1, identification, tfIdentification);
        gridPane.addRow(2, prenom, tfPrenom);
        gridPane.addRow(3, nom, tfNom);
        gridPane.addRow(4, date, checkInDatePicker);
        gridPane.addRow(5, courriel, tfCourriel, imgCourriel);


        GridPane.setColumnSpan(tfIdentification, 2);
        GridPane.setColumnSpan(tfPrenom, 2);
        GridPane.setColumnSpan(tfNom, 2);
        GridPane.setColumnSpan(checkInDatePicker, 2);


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(), columnConstraints);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));


        Text labels = new Text("Département(s)");
        Text label1 = new Text("Inclu(s)");
        Text label2 = new Text("Exclu(s)");

        TextArea area1 = new TextArea();
        TextArea area2 = new TextArea();

        Button gauche = new Button("<");
        Button droite = new Button(">");

        gauche.setMinWidth(40);
        droite.setMinWidth(40);

        VBox v1 = new VBox();
        VBox vB = new VBox();
        VBox v2 = new VBox();

        vB.setAlignment(Pos.CENTER_LEFT);
        vB.setPadding(new Insets(10, 40, 10, 40));

        v2.setPadding(new Insets(0, 40, 0, 0));

        v1.getChildren().addAll(label1, area1);
        vB.getChildren().addAll(gauche, droite);
        v2.getChildren().addAll(label2, area2);

        HBox departement = new HBox();

        departement.getChildren().addAll(labels, v1, vB, v2);

        HBox.setMargin(labels, new Insets(0, 100, 0, 0));
        departement.setAlignment(Pos.CENTER_LEFT);
        departement.setPadding(new Insets(10, 10, 10, 10));

        root.getChildren().add(departement);

        Button validerButton = new Button("Valider");

        HBox validerBox = new HBox(validerButton);
        validerBox.setAlignment(Pos.CENTER_RIGHT);
        validerBox.setPadding(new Insets(10));

        root.getChildren().add(validerBox);

        Scene scene = new Scene(root, 640, 480);

        stage.setTitle("OCP");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

