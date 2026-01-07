package BankSystem;

//import Scenes.*;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;

public class managerView {

    static StackPane actualScene;
    static String cssPath;
    static Stage primaryStage;
    static Scene mainScene;
    static public boolean success = true;

    public managerView() {
        //
    }

    static public void showView(Parent nouvelleVue) {
        nouvelleVue.getStyleClass().add("mainSceneCss");
        mainScene.setRoot(nouvelleVue);
    }

    static public void showView(Button btn, Parent nouvelleVue) {
        nouvelleVue.getStyleClass().add("mainSceneCss");
        mainScene.setRoot(nouvelleVue);
    }

    static public void showView(Button btn, Scene scen) {
        scen.getStylesheets().add(cssPath);
        Stage stage = (Stage) btn.getScene().getWindow(); // récupère le Stage
        primaryStage = stage;
        stage.setScene(scen);
    }

    static public void setActualScene(StackPane actualStack) {
        actualScene = actualStack;
    }

    static public void setCssPath(String cssString) {
        cssPath = cssString;
    }

    static public void setMainScene(Scene scen) {
        mainScene = scen;
    }

    static public void viderCash() {
        actualScene = null;
    }

    static public void setPrimaryStage(Stage primaryStag) {
        primaryStage = primaryStag;
    }

    static public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void showErrorpuppup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERRO-R");
        alert.setHeaderText(null); // ou un texte plus explicite
        alert.setContentText(message);
        alert.showAndWait(); // bloque jusqu’à ce que l’utilisateur ferme la fenêtre
    }

}
