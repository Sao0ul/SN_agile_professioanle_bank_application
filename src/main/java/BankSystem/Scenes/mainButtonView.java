package BankSystem;

import java.net.URL;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainButtonView extends VBox {

    public mainButtonView(Stage primaryStage) {
        this();
        this.prefWidthProperty().bind(primaryStage.widthProperty());
        this.prefHeightProperty().bind(primaryStage.heightProperty());
    }

    public mainButtonView() {

        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.TOP_CENTER);

        HBox hbox1 = new HBox(70);
        HBox hbox2 = new HBox(70);
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        Button btn1 = new Button("Deposer de l'argent");
        Button btn2 = new Button("Retirer de l'argent");
        Button btn3 = new Button("Consulter le solde");
        Button btn4 = new Button("Faire un virement");

        // Images
        URL imageUrlDeposer = getClass().getClassLoader().getResource("images/payment.png");
        URL imageUrlRetirer = getClass().getClassLoader().getResource("images/money.png");
        URL imageUrlConsulter = getClass().getClassLoader().getResource("images/visibility.png");
        URL imageUrlvirement = getClass().getClassLoader().getResource("images/transfer.png");

        Image deposerImg = new Image(imageUrlDeposer.toExternalForm());
        Image retirerImg = new Image(imageUrlRetirer.toExternalForm());
        Image consulterImg = new Image(imageUrlConsulter.toExternalForm());
        Image virementImg = new Image(imageUrlvirement.toExternalForm());

        ImageView deposerImgView = new ImageView(deposerImg);
        ImageView retirerImgView = new ImageView(retirerImg);
        ImageView consulterImgView = new ImageView(consulterImg);
        ImageView virementImgView = new ImageView(virementImg);

        // Tailles initiales
        int baseSize = 32;
        deposerImgView.setFitWidth(baseSize);
        deposerImgView.setFitHeight(baseSize);
        retirerImgView.setFitWidth(baseSize);
        retirerImgView.setFitHeight(baseSize);
        consulterImgView.setFitWidth(baseSize);
        consulterImgView.setFitHeight(baseSize);
        virementImgView.setFitWidth(baseSize);
        virementImgView.setFitHeight(baseSize);

        // config bouton
        btn1.setGraphic(deposerImgView);
        btn2.setGraphic(retirerImgView);
        btn3.setGraphic(consulterImgView);
        btn4.setGraphic(virementImgView);
        btn1.setContentDisplay(ContentDisplay.BOTTOM);
        btn2.setContentDisplay(ContentDisplay.BOTTOM);
        btn3.setContentDisplay(ContentDisplay.BOTTOM);
        btn4.setContentDisplay(ContentDisplay.BOTTOM);

        // style CSS commun
        btn1.getStyleClass().add("my-button");
        btn2.getStyleClass().add("my-button");
        btn3.getStyleClass().add("my-button");
        btn4.getStyleClass().add("my-button");

        // Animation fluide (Scale + Translate)
        addHoverAnimation(btn1, deposerImgView);
        addHoverAnimation(btn2, retirerImgView);
        addHoverAnimation(btn3, consulterImgView);
        addHoverAnimation(btn4, virementImgView);

        // Actions
        btn1.setOnAction(e -> managerView.showView(btn1, new deposerView(managerView.getPrimaryStage())));
        btn2.setOnAction(e -> managerView.showView(btn2, new retirerView(managerView.getPrimaryStage())));
        btn3.setOnAction(e -> managerView.showView(btn3, new consulterView()));
        btn4.setOnAction(e -> managerView.showView(btn4, new transferView(managerView.getPrimaryStage())));

        hbox1.getChildren().addAll(btn1, btn2);
        hbox2.getChildren().addAll(btn3, btn4);
        vbox.getChildren().addAll(hbox1, hbox2);

        this.getChildren().add(vbox);
    }

    // Méthode utilitaire pour animations fluides
    private void addHoverAnimation(Button button, ImageView imgView) {
        int animationTime = 90;// en milisecondes
        // Animation Scale
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(animationTime), imgView);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(animationTime), imgView);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        // Animation Translate Image
        TranslateTransition translateUp = new TranslateTransition(Duration.millis(animationTime), imgView);
        translateUp.setToY(-5);

        TranslateTransition translateDown = new TranslateTransition(Duration.millis(animationTime), imgView);
        translateDown.setToY(0);

        // Animation Translate Button (pour texte)
        TranslateTransition textDown = new TranslateTransition(Duration.millis(animationTime), button);
        textDown.setToY(3);

        TranslateTransition textUp = new TranslateTransition(Duration.millis(animationTime), button);
        textUp.setToY(0);

        // Effets au survol
        button.setOnMouseEntered(e -> {
            scaleIn.playFromStart();
            translateUp.playFromStart();
            textDown.playFromStart();
        });

        button.setOnMouseExited(e -> {
            scaleOut.playFromStart();
            translateDown.playFromStart();
            textUp.playFromStart();
        });
    }
}
