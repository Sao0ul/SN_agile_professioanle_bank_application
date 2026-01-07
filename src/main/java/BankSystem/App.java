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
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class App extends Application {
    private final String WINDOWS_TITLE = "BANK";
    private final double WINDOWS_WIDTH = 660;
    private final double WINDOWS_HEIGHT = 560;

    @Override
    public void start(Stage primaryStage) {

        // initialisation de la base de donne
        dataBaseManager.initialiseDataBase();

        // sauvegarde du stage principal
        managerView.setPrimaryStage(primaryStage);

        // creation du conteneur principal
        mainView root = new mainView(primaryStage, 50);

        // Crée une scène avec le conteneur et les dimensions
        Scene mainScene = new Scene(root, WINDOWS_WIDTH, WINDOWS_HEIGHT);
        managerView.setMainScene(mainScene);

        // ajout des styles css
        String cssPath = getClass().getResource("/styles.css").toExternalForm();
        mainScene.getStylesheets().add(cssPath);
        managerView.setCssPath(cssPath);

        // Configure la fenêtre principale
        primaryStage.setTitle(WINDOWS_TITLE);
        primaryStage.setScene(mainScene);
        // primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

// crer un projet rfx :mvn archetype:generate -D
// archetypeArtifactId=maven-archetype-quickstart

/*
 * javac -d name src/paiement/*.java
 * envoyer un les ficheirs .class dans un dossier nommé <name> qui sera cree par
 * javac
 */