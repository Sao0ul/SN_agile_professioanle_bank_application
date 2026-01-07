package BankSystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class noErrorView extends StackPane {

    public noErrorView() {
        this("TOUT C'EST BIEN PASSÉ");
    }

    public noErrorView(String message) {
        // declaration des elemts
        Button comprisButton = new Button("Continuer");
        if (message == null || message.isEmpty()) {
            message = "Success";
        }
        Label label = new Label(message);

        // positionement des elements
        comprisButton.setTranslateY(200);
        label.setTranslateY(-100);

        // ajout de la classe
        comprisButton.getStyleClass().add("ViewClassButton");
        comprisButton.getStyleClass().add("comprisNoErrorButton");
        label.getStyleClass().add("noErrorViewLabel");

        // ajout des ecouteur d'evenements
        comprisButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (dataBaseManager.getConnectedAccount() == null) {
                managerView.showView(comprisButton, new mainView(managerView.getPrimaryStage(), 50));
            } else {
                managerView.showView(comprisButton, new loggedView(dataBaseManager.getConnectedAccount()));
            }
        });

        this.getChildren().addAll(label, comprisButton);

    }
}
