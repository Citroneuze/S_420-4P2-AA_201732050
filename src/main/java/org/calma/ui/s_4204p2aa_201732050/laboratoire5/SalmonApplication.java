package org.calma.ui.s_4204p2aa_201732050.laboratoire5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SalmonApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SalmonApplication.class.getResource("/org/calma/ui/s_4204p2aa_201732050/laboratoire5/SalmonView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello World FXML!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
