package BankSystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class errorView extends StackPane {

    public errorView() {
        this("VOUS AVEZ SAISI UNE DONNÉE INCORRECT VEUILLEZ RÉESSAYER");
    }

    public errorView(String message) {
        // declaration des elemts
        Button comprisButton = new Button("J'ai compris");
        if (message == null || message.isEmpty()) {
            message = "VOUS AVEZ SAISI UNE DONNÉE INCORRECT VEUILLEZ RÉESSAYER";
        }
        Label label = new Label(message);

        // positionement des elements
        comprisButton.setTranslateY(200);
        label.setTranslateY(-100);

        // ajout de la classe
        comprisButton.getStyleClass().add("ViewClassButton");
        comprisButton.getStyleClass().add("comprisErrorButton");
        label.getStyleClass().add("errorViewLabel");

        // ajout des ecouteur d'evenements
        comprisButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(comprisButton, managerView.actualScene);
        });

        this.getChildren().addAll(label, comprisButton);

    }
}
