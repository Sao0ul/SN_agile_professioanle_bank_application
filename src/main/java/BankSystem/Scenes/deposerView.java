package BankSystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class deposerView extends StackPane {

    public deposerView(Stage primaryStage) {
        this();
        this.prefWidthProperty().bind(primaryStage.widthProperty());
        this.prefHeightProperty().bind(primaryStage.heightProperty());

    }

    public deposerView() {

        // declaration des elemts
        TextField nameAccountTF = new TextField("Entrez le nom du compte");
        TextField passwordAccountTF = new TextField("Entrez le mot de passe");
        TextField montantTF = new TextField("Entrez le montant a déposer");
        Button ValiderButton = new Button("Valider");
        Button retourButton = new Button("Retour");

        // centrer le texte dans les textField
        nameAccountTF.setAlignment(Pos.CENTER);
        passwordAccountTF.setAlignment(Pos.CENTER);
        montantTF.setAlignment(Pos.CENTER);

        // positionement des elements
        nameAccountTF.setTranslateY(-200);
        passwordAccountTF.setTranslateY(-150);
        montantTF.setTranslateY(-100);
        retourButton.setTranslateY(100);

        // ajout de la classe
        nameAccountTF.getStyleClass().add("ViewClassLabel");
        passwordAccountTF.getStyleClass().add("ViewClassLabel");
        montantTF.getStyleClass().add("ViewClassLabel");
        ValiderButton.getStyleClass().add("ViewClassButton");
        ValiderButton.getStyleClass().add("validerButton");
        retourButton.getStyleClass().add("ViewClassButton");
        retourButton.getStyleClass().add("retourButton");

        // ajout des ecouteur d'evenements
        ValiderButton.addEventHandler(ActionEvent.ACTION, e -> {
            // code pour deposer de l'argent
            String nameAccount = nameAccountTF.getText().trim(); // retire les espaces
            String passwordAccount = passwordAccountTF.getText().trim(); // retire les espaces
            String montantDeposer = montantTF.getText().trim(); // retire les espaces
            int montant = 0;
            try {
                montant = Integer.parseInt(montantDeposer);
                dataBaseManager.deposit(nameAccount, passwordAccount, montant);
                // nameAccountTF.setText("Vous venez de deposer avec success une somme de " +
                // montant + " sur votre compte");
            } catch (NumberFormatException ex) {
                // sauvegarde de la scene pour y revenir
                managerView.setActualScene(new deposerView(managerView.getPrimaryStage()));
                // afficharge de la vue des erreurs
                managerView.showView(ValiderButton, new errorView());
            }
        });

        retourButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(retourButton, new mainView(managerView.getPrimaryStage(), 50));
        });

        this.getChildren().addAll(nameAccountTF, passwordAccountTF, montantTF, ValiderButton, retourButton);

    }

    // ajouter les comptes de depot avec les validations
}