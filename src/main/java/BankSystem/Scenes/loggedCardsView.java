
package BankSystem;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// ==================== CARDS VIEW ====================
public class loggedCardsView extends BorderPane {

    private Button backButton;
    private Button orderNewCardButton;

    public loggedCardsView() {
        this("Jean Dupont");
    }

    public loggedCardsView(String cardholderName) {
        this.getStyleClass().add("cards-view");

        // ===== TOP BAR =====
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(20, 30, 20, 30));
        topBar.setSpacing(20);
        topBar.getStyleClass().add("top-bar");

        backButton = new Button("← Retour");
        backButton.getStyleClass().addAll("ViewClassButton", "back-button");

        backButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(backButton, new loggedView(dataBaseManager.getConnectedAccount()));
        });

        Label pageTitle = new Label("Mes Cartes");
        pageTitle.getStyleClass().add("page-title");

        topBar.getChildren().addAll(backButton, pageTitle);

        // ===== CENTER CONTENT =====
        VBox centerContent = new VBox(40);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(60, 50, 60, 50));

        // Carte bancaire
        StackPane cardContainer = new StackPane();
        cardContainer.setPrefSize(420, 260);
        cardContainer.setMaxSize(420, 260);
        cardContainer.getStyleClass().add("bank-card");

        VBox cardContent = new VBox(20);
        cardContent.setPadding(new Insets(30));
        cardContent.setAlignment(Pos.TOP_LEFT);

        // En-tête de la carte (logo banque + type)
        HBox cardHeader = new HBox();
        cardHeader.setAlignment(Pos.CENTER_LEFT);

        Label bankLogo = new Label("🏦 SecureBank");
        bankLogo.getStyleClass().add("card-bank-logo");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Label cardType = new Label("VISA");
        cardType.getStyleClass().add("card-type");

        cardHeader.getChildren().addAll(bankLogo, headerSpacer, cardType);

        // Puce de carte
        Label chip = new Label("💳");
        chip.getStyleClass().add("card-chip");

        // Numéro de carte
        Label cardNumber = new Label("•••• •••• •••• 4892");
        cardNumber.getStyleClass().add("card-number");

        // Bas de carte (nom + expiration)
        HBox cardFooter = new HBox(40);
        cardFooter.setAlignment(Pos.BOTTOM_LEFT);

        VBox nameBox = new VBox(5);
        Label nameLabel = new Label("TITULAIRE");
        nameLabel.getStyleClass().add("card-label");

        Label name = new Label(cardholderName.toUpperCase());
        name.getStyleClass().add("card-info");

        nameBox.getChildren().addAll(nameLabel, name);

        VBox expiryBox = new VBox(5);
        Label expiryLabel = new Label("EXPIRE FIN");
        expiryLabel.getStyleClass().add("card-label");

        Label expiry = new Label("12/28");
        expiry.getStyleClass().add("card-info");

        expiryBox.getChildren().addAll(expiryLabel, expiry);

        cardFooter.getChildren().addAll(nameBox, expiryBox);

        cardContent.getChildren().addAll(cardHeader, chip, cardNumber, cardFooter);
        cardContainer.getChildren().add(cardContent);

        // Informations de la carte
        VBox cardDetails = new VBox(15);
        cardDetails.setAlignment(Pos.CENTER);
        cardDetails.setPadding(new Insets(20));
        cardDetails.getStyleClass().add("card-details");
        cardDetails.setMaxWidth(420);

        Label statusLabel = new Label("✅ Carte active");
        statusLabel.getStyleClass().add("card-status-active");

        Label limitLabel = new Label("Plafond : 3 000 € / mois");
        limitLabel.getStyleClass().add("card-detail-text");

        cardDetails.getChildren().addAll(statusLabel, limitLabel);

        // Boutons d'actions
        HBox actionsBox = new HBox(15);
        actionsBox.setAlignment(Pos.CENTER);
        actionsBox.setMaxWidth(420);

        Button blockButton = new Button("🔒 Bloquer la carte");
        blockButton.getStyleClass().addAll("ViewClassButton", "block-card-button");
        blockButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(blockButton, Priority.ALWAYS);

        Button pinButton = new Button("🔢 Voir le code");
        pinButton.getStyleClass().addAll("ViewClassButton", "pin-button");
        pinButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(pinButton, Priority.ALWAYS);

        actionsBox.getChildren().addAll(blockButton, pinButton);

        // Bouton commander nouvelle carte
        orderNewCardButton = new Button("+ Commander une nouvelle carte");
        orderNewCardButton.getStyleClass().addAll("ViewClassButton", "order-card-button");
        orderNewCardButton.setMaxWidth(420);

        centerContent.getChildren().addAll(cardContainer);

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("cards-scroll");

        this.setTop(topBar);
        this.setCenter(scrollPane);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getOrderNewCardButton() {
        return orderNewCardButton;
    }
}
