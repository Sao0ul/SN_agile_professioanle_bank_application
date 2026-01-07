package BankSystem;

import java.net.URL;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class mainView extends VBox {

    public mainView(Stage primaryStage, double spacing) {
        super(spacing);
        this.prefWidthProperty().bind(primaryStage.widthProperty());
        this.prefHeightProperty().bind(primaryStage.heightProperty());
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("mainSceneCss");
        this.setSpacing(20);

        // ===== CONTENEUR PRINCIPAL =====
        VBox mainContainer = new VBox(15);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setMaxWidth(500);

        // ===== LOGO ET TITRE =====
        VBox headerSection = new VBox(5);
        headerSection.setAlignment(Pos.CENTER);

        // Logo agrandi et stylisé
        Label logoLabel = new Label("🏦");
        logoLabel.setStyle(
                "-fx-font-size: 70px;" +
                        "-fx-effect: dropshadow(gaussian, rgba(102, 126, 234, 0.4), 25, 0.5, 0, 4);");
        logoLabel.setAlignment(Pos.CENTER);

        // Logo + Titre sur la même ligne
        HBox logoTitleBox = new HBox(15);
        logoTitleBox.setAlignment(Pos.CENTER);

        // Titre principal
        Label titleLabel = new Label("SecureBank");
        titleLabel.getStyleClass().add("main-title");

        logoTitleBox.getChildren().addAll(titleLabel);

        headerSection.getChildren().addAll(logoLabel, logoTitleBox);

        // ===== SECTION DES BOUTONS PRINCIPAUX =====
        VBox buttonsSection = new VBox(12);
        buttonsSection.setAlignment(Pos.CENTER);
        buttonsSection.setPadding(new Insets(15, 0, 10, 0));
        buttonsSection.setMaxWidth(400);

        // Boutons principaux (si mainButtonView existe)
        mainButtonView root = new mainButtonView(primaryStage);
        root.setMaxWidth(400);

        buttonsSection.getChildren().add(root);

        // ===== SECTION FOOTER =====
        VBox footerSection = new VBox(12);
        footerSection.setAlignment(Pos.CENTER);
        footerSection.setPadding(new Insets(10, 0, 0, 0));

        // Bouton Login (avant-dernier bouton)
        Button loginButton = new Button("Se connecter à mon espace");
        loginButton.getStyleClass().addAll("my-button", "main-login-button");
        loginButton.setPrefWidth(350);
        loginButton.setPrefHeight(60);

        // Icône pour le bouton login
        Label loginIcon = new Label("🔐");
        loginIcon.setStyle("-fx-font-size: 24px;");
        loginButton.setGraphic(loginIcon);
        loginButton.setContentDisplay(ContentDisplay.LEFT);

        // Action du bouton login
        loginButton.setOnAction(e -> {
            managerView.showView(loginButton, new loginView());
        });

        // Effet hover sur login
        loginButton.setOnMouseEntered(e -> {
            loginButton.setEffect(new DropShadow(20, Color.rgb(102, 126, 234, 0.6)));
        });

        loginButton.setOnMouseExited(e -> {
            loginButton.setEffect(null);
        });

        // Bouton Quitter (dernier bouton)
        Button quitButton = new Button("Quitter l'application");
        quitButton.getStyleClass().addAll("my-button", "quit-button");
        quitButton.setPrefWidth(250);
        quitButton.setPrefHeight(45);

        // Image initiale
        URL imageUrlQuit = getClass().getClassLoader().getResource("images/shutdown.png");
        if (imageUrlQuit != null) {
            Image quitImg = new Image(imageUrlQuit.toExternalForm());
            ImageView quitImgView = new ImageView(quitImg);
            quitImgView.setFitWidth(20);
            quitImgView.setFitHeight(20);
            quitButton.setGraphic(quitImgView);
            quitButton.setContentDisplay(ContentDisplay.LEFT);

            // Nouvelle image pour le survol
            URL newImageUrl = getClass().getClassLoader().getResource("images/new-shutdown.png");
            if (newImageUrl != null) {
                Image newImg = new Image(newImageUrl.toExternalForm());
                ImageView newImgView = new ImageView(newImg);
                newImgView.setFitWidth(30);
                newImgView.setFitHeight(30);

                // État initial pour restauration
                final String initialText = quitButton.getText();
                final ImageView initialImageView = quitImgView;
                final ContentDisplay initialContent = quitButton.getContentDisplay();

                // Survol
                quitButton.setOnMouseEntered(e -> {
                    quitButton.setText("");
                    quitButton.setGraphic(newImgView);
                    quitButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    quitButton.setEffect(new DropShadow(15, Color.DARKRED));
                });

                // Fin du survol
                quitButton.setOnMouseExited(e -> {
                    quitButton.setText(initialText);
                    quitButton.setGraphic(initialImageView);
                    quitButton.setContentDisplay(initialContent);
                    quitButton.setEffect(null);
                });
            }
        }

        // Action fermeture
        quitButton.setOnAction(e -> {
            dataBaseManager.closeConnection();
            primaryStage.close();
        });

        footerSection.getChildren().addAll(loginButton, quitButton);

        // ===== ASSEMBLY =====
        mainContainer.getChildren().addAll(headerSection, buttonsSection, footerSection);

        this.getChildren().add(mainContainer);
    }
}
// crer un projet rfx :mvn archetype:generate -D
// archetypeArtifactId=maven-archetype-quickstart

/*
 * javac -d name src/paiement/*.java
 * envoyer un les ficheirs .class dans un dossier nommé <name> qui sera cree par
 * javac
 */
