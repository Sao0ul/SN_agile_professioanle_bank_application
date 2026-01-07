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

public class transferView extends StackPane {

    public transferView(Stage primaryStage) {
        this();
        this.prefWidthProperty().bind(primaryStage.widthProperty());
        this.prefHeightProperty().bind(primaryStage.heightProperty());
    }

    public transferView(Account acc) {
        // champ nom destinataire
        TextField nameAccountTF1 = new TextField("Entrez le nom du compte destinataire");
        nameAccountTF1.setAlignment(Pos.CENTER);
        nameAccountTF1.getStyleClass().add("ViewClassLabel");

        TextField montantTF = new TextField("Entrez le montant a transférer");
        montantTF.setAlignment(Pos.CENTER);
        montantTF.getStyleClass().add("ViewClassLabel");

        Button ValiderButton = new Button("Valider");
        ValiderButton.getStyleClass().add("ViewClassButton");
        ValiderButton.getStyleClass().add("validerButton");

        Button retourButton = new Button("Retour");
        retourButton.getStyleClass().add("ViewClassButton");
        retourButton.getStyleClass().add("retourButton");

        // positionement des elements
        nameAccountTF1.setTranslateY(-100);
        montantTF.setTranslateY(-25);
        ValiderButton.setTranslateY(100);
        retourButton.setTranslateY(200);

        // ajout des ecouteur d'evenements
        ValiderButton.addEventHandler(ActionEvent.ACTION, e -> {
            // code pour deposer de l'argent
            String nameAccount1 = nameAccountTF1.getText().trim(); // retire les espaces
            String montantTransferer = montantTF.getText().trim(); // retire les espaces
            int montant = 0;
            if (!databaseManagerLogged.isNameFound(nameAccount1)) {
                managerView.showErrorpuppup("erreur avec le nom du compte recever");
                return;
            }
            try {
                montant = Integer.parseInt(montantTransferer);
                databaseManagerLogged.transferTo(nameAccount1, montant);
                dataBaseManager.debit(acc.getName(), acc.getPassword(), montant);
                acc.debit(montant);
            } catch (NumberFormatException ex) {
                // sauvegarde de la scene pour y revenir
                managerView.setActualScene(new transferView(acc));
                // afficharge de la vue des erreurs
                managerView.showView(ValiderButton, new errorView());
            }
        });

        retourButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(retourButton, new loggedView(acc));
        });

        this.getChildren().addAll(nameAccountTF1, montantTF,
                ValiderButton, retourButton);
    }

    public transferView() {
        // declaration des elemts
        TextField nameAccountTF1 = new TextField("Entrez le nom du compte");
        TextField passwordAccountTF1 = new TextField("Entrez le mot de passe");
        TextField nameAccountTF2 = new TextField("Entrez le nom du compte destinataire");
        TextField passwordAccountTF2 = new TextField("Entrez le mot de passe du compte destinataire");
        TextField montantTF = new TextField("Entrez le montant a transférer");
        Button ValiderButton = new Button("Valider");
        Button retourButton = new Button("Retour");

        // centrer le texte dans les textField
        nameAccountTF1.setAlignment(Pos.CENTER);
        passwordAccountTF1.setAlignment(Pos.CENTER);
        nameAccountTF2.setAlignment(Pos.CENTER);
        passwordAccountTF2.setAlignment(Pos.CENTER);
        montantTF.setAlignment(Pos.CENTER);

        // positionement des elements
        nameAccountTF1.setTranslateY(-200);
        passwordAccountTF1.setTranslateY(-150);
        nameAccountTF2.setTranslateY(-100);
        passwordAccountTF2.setTranslateY(-50);
        montantTF.setTranslateY(0);
        ValiderButton.setTranslateY(100);
        retourButton.setTranslateY(200);

        // ajout de la classe
        nameAccountTF1.getStyleClass().add("ViewClassLabel");
        passwordAccountTF1.getStyleClass().add("ViewClassLabel");
        nameAccountTF2.getStyleClass().add("ViewClassLabel");
        passwordAccountTF2.getStyleClass().add("ViewClassLabel");
        montantTF.getStyleClass().add("ViewClassLabel");
        ValiderButton.getStyleClass().add("ViewClassButton");
        ValiderButton.getStyleClass().add("validerButton");
        retourButton.getStyleClass().add("ViewClassButton");
        retourButton.getStyleClass().add("retourButton");

        // ajout des ecouteur d'evenements
        ValiderButton.addEventHandler(ActionEvent.ACTION, e -> {
            // code pour deposer de l'argent
            String nameAccount1 = nameAccountTF1.getText().trim(); // retire les espaces
            String passwordAccount1 = passwordAccountTF1.getText().trim(); // retire les espaces
            String nameAccount2 = nameAccountTF2.getText().trim(); // retire les espaces
            String passwordAccount2 = passwordAccountTF2.getText().trim(); // retire les espaces
            String montantTransferer = montantTF.getText().trim(); // retire les espaces
            int montant = 0;
            try {
                montant = Integer.parseInt(montantTransferer);
                dataBaseManager.transferTo(nameAccount1, passwordAccount1, nameAccount2, passwordAccount2, montant);
                // nameAccountTF.setText("Vous venez de deposer avec success une somme de " +
                // montant + " sur votre compte");
            } catch (NumberFormatException ex) {
                // sauvegarde de la scene pour y revenir
                managerView.setActualScene(new transferView());
                // afficharge de la vue des erreurs
                managerView.showView(ValiderButton, new errorView());
            }
        });

        retourButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(retourButton, new mainView(managerView.getPrimaryStage(), 50));
        });

        this.getChildren().addAll(nameAccountTF1, passwordAccountTF1, nameAccountTF2, passwordAccountTF2, montantTF,
                ValiderButton, retourButton);

    }

}