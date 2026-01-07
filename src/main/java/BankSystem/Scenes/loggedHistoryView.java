
package BankSystem;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// ==================== HISTORY VIEW ====================
public class loggedHistoryView extends BorderPane {

    private Button backButton;

    public loggedHistoryView() {
        this.getStyleClass().add("history-view");

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

        Label pageTitle = new Label("Historique des opérations");
        pageTitle.getStyleClass().add("page-title");

        topBar.getChildren().addAll(backButton, pageTitle);

        // ===== CENTER CONTENT =====
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setPadding(new Insets(40, 50, 40, 50));
        centerContent.setMaxWidth(900);

        // Liste des transactions
        VBox transactionsList = new VBox(15);
        transactionsList.getStyleClass().add("history-list");

        // Transactions d'exemple
        transactionsList.getChildren().addAll(
                createHistoryItem("Supermarché Carrefour", "Achat par carte", "-45,30 €", "07/01/2026 14:32"),
                createHistoryItem("Virement - Pierre Martin", "Virement reçu", "+150,00 €", "06/01/2026 09:15"),
                createHistoryItem("Netflix", "Prélèvement automatique", "-13,99 €", "05/01/2026 00:01"),
                createHistoryItem("Salaire - Entreprise XYZ", "Virement reçu", "+2 500,00 €", "01/01/2026 08:00"),
                createHistoryItem("Restaurant Le Gourmet", "Achat par carte", "-67,50 €", "31/12/2025 20:45"),
                createHistoryItem("Retrait DAB", "Retrait espèces", "-100,00 €", "30/12/2025 16:20"),
                createHistoryItem("EDF - Facture électricité", "Prélèvement", "-89,20 €", "28/12/2025 10:00"),
                createHistoryItem("Amazon", "Achat en ligne", "-34,99 €", "27/12/2025 15:30"),
                createHistoryItem("Remboursement Sécurité Sociale", "Virement reçu", "+45,80 €", "26/12/2025 11:10"),
                createHistoryItem("Station service Total", "Achat par carte", "-65,00 €", "25/12/2025 13:45"));

        centerContent.getChildren().add(transactionsList);

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("history-scroll");

        this.setTop(topBar);
        this.setCenter(scrollPane);
    }

    private VBox createHistoryItem(String title, String category, String amount, String dateTime) {
        VBox item = new VBox(8);
        item.setPadding(new Insets(20));
        item.getStyleClass().add("history-item");

        HBox topRow = new HBox(15);
        topRow.setAlignment(Pos.CENTER_LEFT);

        VBox leftInfo = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("history-title");

        Label categoryLabel = new Label(category);
        categoryLabel.getStyleClass().add("history-category");

        leftInfo.getChildren().addAll(titleLabel, categoryLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label amountLabel = new Label(amount);
        amountLabel.getStyleClass().add(amount.startsWith("+") ? "history-amount-positive" : "history-amount-negative");

        topRow.getChildren().addAll(leftInfo, spacer, amountLabel);

        Label dateLabel = new Label("📅 " + dateTime);
        dateLabel.getStyleClass().add("history-date");

        item.getChildren().addAll(topRow, dateLabel);
        return item;
    }

    public Button getBackButton() {
        return backButton;
    }
}
