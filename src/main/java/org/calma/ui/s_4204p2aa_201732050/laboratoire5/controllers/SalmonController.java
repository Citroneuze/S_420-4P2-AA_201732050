package org.calma.ui.s_4204p2aa_201732050.laboratoire5.controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.calma.ui.s_4204p2aa_201732050.laboratoire5.imports.SoundPlayer;
import org.calma.ui.s_4204p2aa_201732050.laboratoire5.models.SalmonModel;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalmonController implements Initializable{
    public Rectangle greenBtn;
    public Rectangle redBtn;
    public Rectangle yellowBtn;
    public Rectangle blueBtn;

    @FXML
    private Button startBtn;
    private SalmonModel salmonModel = new SalmonModel();
    private SoundPlayer soundPlayer = new SoundPlayer();

    public SalmonController() {

    }
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == startBtn) {
            salmonModel.start();

            displayColors(salmonModel.getSequence());

        }
    }
    @FXML
    private void userInput(MouseEvent e){
        Rectangle[] rectangles = {greenBtn, redBtn, yellowBtn, blueBtn};
        Rectangle rectangle = (Rectangle) e.getSource();
        String colorClicked = rectangle.getId().substring(0, 1);

        System.out.println(colorClicked);
        if(salmonModel.isGameOn()){
            if(salmonModel.checkSequence(colorClicked)){
                Transition transition = generateTransition(colorClicked);
                transition.play();
                soundPlayer.setInstrument(1);
                soundPlayer.note_on(60);
                soundPlayer.note_off(60);
            }
            if(salmonModel.getCurrentIndex()==0){
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                pauseTransition.setOnFinished(actionEvent -> displayColors(salmonModel.getSequence()));
                pauseTransition.play();
            }
        }
        if(!salmonModel.isGameOn()){
            shake(rectangles);
            soundPlayer.setInstrument(12);
            soundPlayer.note_on(50);
            soundPlayer.note_off(50);
            showAlert("Salmon", "Game Over : Appuyer sur Start!");
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void displayColors(ArrayList<String> sequence){
        SequentialTransition seqTransition = new SequentialTransition();

        for (String color : sequence) {
            System.out.println(sequence);
            Transition transition = generateTransition(color);
            seqTransition.getChildren().add(transition);
        }

        seqTransition.play();
    }

    private Transition generateTransition(String color) {
        return switch (color) {
            case "g" -> generateGreenTransition();
            case "r" -> generateRedTransition();
            case "y" -> generateYellowTransition();
            case "b" -> generateBlueTransition();
            default -> new PauseTransition(Duration.seconds(1));
        };
    }


    private SequentialTransition generateScaleAndFillTransition(Rectangle rectangle, Color fromColor, Color toColor) {

        FillTransition fillTransition = new FillTransition(Duration.seconds(0.25), rectangle, fromColor, toColor);

        ScaleTransition scaleUpTransition = new ScaleTransition(Duration.seconds(0.25), rectangle);
        scaleUpTransition.setFromX(1.0);
        scaleUpTransition.setFromY(1.0);
        scaleUpTransition.setToX(1.2);
        scaleUpTransition.setToY(1.2);


        ScaleTransition scaleDownTransition = new ScaleTransition(Duration.seconds(0.25), rectangle);
        scaleDownTransition.setFromX(1.2);
        scaleDownTransition.setFromY(1.2);
        scaleDownTransition.setToX(1.0);
        scaleDownTransition.setToY(1.0);


        SequentialTransition scaleAndFillTransition = new SequentialTransition();
        scaleAndFillTransition.getChildren().addAll(fillTransition, scaleUpTransition, scaleDownTransition);


        scaleDownTransition.setOnFinished(event -> rectangle.setFill(fromColor));

        return scaleAndFillTransition;
    }

    private SequentialTransition generateGreenTransition() {
        return generateScaleAndFillTransition(greenBtn, Color.GREEN, Color.WHITE);
    }

    private SequentialTransition generateRedTransition() {
        return generateScaleAndFillTransition(redBtn, Color.RED, Color.WHITE);
    }

    private SequentialTransition generateYellowTransition() {
        return generateScaleAndFillTransition(yellowBtn, Color.YELLOW, Color.WHITE);
    }

    private SequentialTransition generateBlueTransition() {
        return generateScaleAndFillTransition(blueBtn, Color.BLUE, Color.WHITE);
    }


    private void shake(Rectangle[] rectangles) {
        for (Rectangle rectangle : rectangles) {

            TranslateTransition shakeTransitionX = new TranslateTransition(Duration.millis(200), rectangle);
            shakeTransitionX.setByX(Math.random() * 400 - 200); // Translation aléatoire sur l'axe X
            shakeTransitionX.setInterpolator(Interpolator.EASE_BOTH);

            TranslateTransition shakeTransitionY = new TranslateTransition(Duration.millis(200), rectangle);
            shakeTransitionY.setByY(Math.random() * 400 - 200); // Translation aléatoire sur l'axe Y
            shakeTransitionY.setInterpolator(Interpolator.EASE_BOTH);


            TranslateTransition returnTransitionX = new TranslateTransition(Duration.millis(200), rectangle);
            returnTransitionX.setToX(0);

            TranslateTransition returnTransitionY = new TranslateTransition(Duration.millis(200), rectangle);
            returnTransitionY.setToY(0);


            SequentialTransition shakeAndReturnTransition = new SequentialTransition(rectangle,
                    new ParallelTransition(shakeTransitionX, shakeTransitionY),
                    new ParallelTransition(returnTransitionX, returnTransitionY));
            shakeAndReturnTransition.setCycleCount(2);
            shakeAndReturnTransition.setAutoReverse(true);
            shakeAndReturnTransition.play();

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


























}

