package BankSystem;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// ==================== DASHBOARD VIEW (LOGGED PAGE) ====================
public class loggedView extends BorderPane {

    private Label balanceLabel;
    private Button transferButton;
    private Button historyButton;
    private Button cardsButton;
    private Button logoutButton;

    public loggedView() {
        this(null);
    }

    public loggedView(Account connectedAcc) {
        this.getStyleClass().add("dashboard-view");

        // ===== TOP BAR =====
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(20, 30, 20, 30));
        topBar.setSpacing(20);
        topBar.getStyleClass().add("top-bar");

        Label bankName = new Label("🏦 BankApp");
        bankName.getStyleClass().add("bank-name-header");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label welcomeLabel = new Label("Bonjour, " + connectedAcc.getName());
        welcomeLabel.getStyleClass().add("welcome-label");

        logoutButton = new Button("Déconnexion");
        logoutButton.getStyleClass().addAll("ViewClassButton", "logout-button");
        logoutButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(logoutButton, new mainView(managerView.getPrimaryStage(), 50));
            dataBaseManager.setConnectedAccount(null);
        });

        topBar.getChildren().addAll(bankName, spacer, welcomeLabel, logoutButton);

        // ===== CENTER CONTENT =====
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setPadding(new Insets(40, 50, 40, 50));

        // Carte de solde principal
        VBox balanceCard = new VBox(15);
        balanceCard.setAlignment(Pos.CENTER);
        balanceCard.setPadding(new Insets(40));
        balanceCard.getStyleClass().add("balance-card");
        balanceCard.setMaxWidth(600);

        Label balanceTitle = new Label("Solde disponible");
        balanceTitle.getStyleClass().add("balance-title");

        balanceLabel = new Label(String.valueOf(connectedAcc.getBalance()) + "€");
        balanceLabel.getStyleClass().add("balance-amount");

        Label accountNumber = new Label("Compte Courant • •• 4892");
        accountNumber.getStyleClass().add("account-number");

        balanceCard.getChildren().addAll(balanceTitle, balanceLabel, accountNumber);

        // Grille d'actions rapides
        GridPane actionsGrid = new GridPane();
        actionsGrid.setHgap(20);
        actionsGrid.setVgap(20);
        actionsGrid.setAlignment(Pos.CENTER);
        actionsGrid.setMaxWidth(700);

        // Bouton Virement
        VBox transferBox = createActionCard("💸", "Virement", "Effectuer un virement");
        transferButton = new Button();
        transferButton.setGraphic(transferBox);
        transferButton.getStyleClass().add("action-card-button");

        transferButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(transferButton, new transferView(connectedAcc));
        });

        // Bouton Historique
        VBox historyBox = createActionCard("📊", "Historique", "Consulter les opérations");
        historyButton = new Button();
        historyButton.setGraphic(historyBox);
        historyButton.getStyleClass().add("action-card-button");

        historyButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(historyButton, new loggedHistoryView());
        });

        // Bouton Cartes
        VBox cardsBox = createActionCard("💳", "Mes Cartes", "Gérer mes cartes");
        cardsButton = new Button();
        cardsButton.setGraphic(cardsBox);
        cardsButton.getStyleClass().add("action-card-button");

        cardsButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(cardsButton, new loggedCardsView(dataBaseManager.getConnectedAccount().getName()));
        });

        // Bouton Profil
        VBox profileBox = createActionCard("👤", "Mon Profil", "Paramètres du compte");
        Button profileButton = new Button();
        profileButton.setGraphic(profileBox);
        profileButton.getStyleClass().add("action-card-button");

        profileButton.addEventHandler(ActionEvent.ACTION, e -> {
            managerView.showView(profileButton, new loggedProfileView());
        });

        actionsGrid.add(transferButton, 0, 0);
        actionsGrid.add(historyButton, 1, 0);
        actionsGrid.add(cardsButton, 0, 1);
        actionsGrid.add(profileButton, 1, 1);

        // Dernières transactions
        VBox transactionsSection = new VBox(15);
        transactionsSection.setMaxWidth(700);

        Label transactionsTitle = new Label("Dernières opérations");
        transactionsTitle.getStyleClass().add("section-title");

        VBox transactionsList = new VBox(10);
        transactionsList.getStyleClass().add("transactions-list");

        transactionsList.getChildren().addAll(
                createTransactionItem("Supermarché Carrefour", "-45,30 €", "Aujourd'hui"),
                createTransactionItem("Salaire - Entreprise XYZ", "+2 500,00 €", "01/01/2026"),
                createTransactionItem("Netflix Abonnement", "-13,99 €", "30/12/2025"));

        transactionsSection.getChildren().addAll(transactionsTitle, transactionsList);

        centerContent.getChildren().addAll(balanceCard, actionsGrid, transactionsSection);

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("dashboard-scroll");

        this.setTop(topBar);
        this.setCenter(scrollPane);
    }

    private VBox createActionCard(String icon, String title, String subtitle) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30, 40, 30, 40));
        card.getStyleClass().add("action-card");
        card.setPrefSize(300, 150);

        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("action-icon");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("action-title");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.getStyleClass().add("action-subtitle");

        card.getChildren().addAll(iconLabel, titleLabel, subtitleLabel);
        return card;
    }

    private HBox createTransactionItem(String description, String amount, String date) {
        HBox item = new HBox(15);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(15, 20, 15, 20));
        item.getStyleClass().add("transaction-item");

        VBox leftInfo = new VBox(5);
        Label descLabel = new Label(description);
        descLabel.getStyleClass().add("transaction-description");

        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("transaction-date");

        leftInfo.getChildren().addAll(descLabel, dateLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label amountLabel = new Label(amount);
        amountLabel.getStyleClass()
                .add(amount.startsWith("+") ? "transaction-amount-positive" : "transaction-amount-negative");

        item.getChildren().addAll(leftInfo, spacer, amountLabel);
        return item;
    }

    // Getters pour accéder aux composants
    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public Button getTransferButton() {
        return transferButton;
    }

    public Button getHistoryButton() {
        return historyButton;
    }

    public Button getCardsButton() {
        return cardsButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
