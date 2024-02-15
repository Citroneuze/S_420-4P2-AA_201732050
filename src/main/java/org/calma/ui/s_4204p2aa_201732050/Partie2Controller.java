package org.calma.ui.s_4204p2aa_201732050;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Partie2Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}