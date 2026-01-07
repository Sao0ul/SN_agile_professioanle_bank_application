package BankSystem;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// ==================== PROFILE VIEW ====================
public class ProfileView extends BorderPane {

    private Button backButton;
    private Button saveButton;
    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;
    private PasswordField currentPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmPasswordField;

    public ProfileView() {
        this("Jean Dupont", "jean.dupont@email.com", "+33 6 12 34 56 78");
    }

    public ProfileView(String name, String email, String phone) {
        this.getStyleClass().add("profile-view");

        // ===== TOP BAR =====
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(20, 30, 20, 30));
        topBar.setSpacing(20);
        topBar.getStyleClass().add("top-bar");

        backButton = new Button("← Retour");
        backButton.getStyleClass().addAll("ViewClassButton", "back-button");

        Label pageTitle = new Label("Mon Profil");
        pageTitle.getStyleClass().add("page-title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        saveButton = new Button("Enregistrer");
        saveButton.getStyleClass().addAll("ViewClassButton", "save-button");

        topBar.getChildren().addAll(backButton, pageTitle, spacer, saveButton);

        // ===== CENTER CONTENT =====
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setPadding(new Insets(40, 50, 40, 50));
        centerContent.setMaxWidth(800);

        // Section Avatar
        VBox avatarSection = new VBox(20);
        avatarSection.setAlignment(Pos.CENTER);

        StackPane avatarContainer = new StackPane();
        avatarContainer.setPrefSize(120, 120);
        avatarContainer.getStyleClass().add("avatar-container");

        Label avatarIcon = new Label("👤");
        avatarIcon.getStyleClass().add("avatar-icon");

        avatarContainer.getChildren().add(avatarIcon);

        Button changePhotoButton = new Button("Changer la photo");
        changePhotoButton.getStyleClass().addAll("ViewClassButton", "change-photo-button");

        avatarSection.getChildren().addAll(avatarContainer, changePhotoButton);

        // Section Informations personnelles
        VBox personalInfoSection = createSection("Informations personnelles");

        VBox nameBox = createFormField("Nom complet", name);
        nameField = (TextField) nameBox.getChildren().get(1);

        VBox emailBox = createFormField("Adresse e-mail", email);
        emailField = (TextField) emailBox.getChildren().get(1);

        VBox phoneBox = createFormField("Numéro de téléphone", phone);
        phoneField = (TextField) phoneBox.getChildren().get(1);

        personalInfoSection.getChildren().addAll(nameBox, emailBox, phoneBox);

        // Section Sécurité
        VBox securitySection = createSection("Sécurité et mot de passe");

        VBox currentPasswordBox = createPasswordField("Mot de passe actuel", "");
        currentPasswordField = (PasswordField) currentPasswordBox.getChildren().get(1);

        VBox newPasswordBox = createPasswordField("Nouveau mot de passe", "");
        newPasswordField = (PasswordField) newPasswordBox.getChildren().get(1);

        VBox confirmPasswordBox = createPasswordField("Confirmer le mot de passe", "");
        confirmPasswordField = (PasswordField) confirmPasswordBox.getChildren().get(1);

        Label passwordHint = new Label("💡 Le mot de passe doit contenir au moins 8 caractères");
        passwordHint.getStyleClass().add("password-hint");

        securitySection.getChildren().addAll(
                currentPasswordBox,
                newPasswordBox,
                confirmPasswordBox,
                passwordHint);

        // Section Préférences
        VBox preferencesSection = createSection("Préférences");

        HBox notificationRow = createToggleRow(
                "Notifications par e-mail",
                "Recevoir des alertes sur vos opérations");

        HBox smsRow = createToggleRow(
                "Notifications SMS",
                "Recevoir des codes de sécurité par SMS");

        HBox marketingRow = createToggleRow(
                "Offres commerciales",
                "Recevoir les promotions et offres spéciales");

        preferencesSection.getChildren().addAll(notificationRow, smsRow, marketingRow);

        // Section Actions
        VBox actionsSection = new VBox(15);
        actionsSection.setPadding(new Insets(20, 0, 0, 0));

        Button exportDataButton = new Button("📥 Télécharger mes données");
        exportDataButton.getStyleClass().addAll("ViewClassButton", "secondary-action-button");
        exportDataButton.setMaxWidth(Double.MAX_VALUE);

        Button deleteAccountButton = new Button("🗑️ Supprimer mon compte");
        deleteAccountButton.getStyleClass().addAll("ViewClassButton", "danger-button");
        deleteAccountButton.setMaxWidth(Double.MAX_VALUE);

        actionsSection.getChildren().addAll(exportDataButton, deleteAccountButton);

        centerContent.getChildren().addAll(
                avatarSection,
                personalInfoSection,
                securitySection,
                preferencesSection,
                actionsSection);

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("profile-scroll");

        this.setTop(topBar);
        this.setCenter(scrollPane);
    }

    private VBox createSection(String title) {
        VBox section = new VBox(20);
        section.setPadding(new Insets(30));
        section.getStyleClass().add("profile-section");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("section-title");

        section.getChildren().add(titleLabel);
        return section;
    }

    private VBox createFormField(String labelText, String value) {
        VBox fieldBox = new VBox(8);

        Label label = new Label(labelText);
        label.getStyleClass().add("field-label");

        TextField textField = new TextField(value);
        textField.getStyleClass().add("profile-field");
        textField.setPrefHeight(45);

        fieldBox.getChildren().addAll(label, textField);
        return fieldBox;
    }

    private VBox createPasswordField(String labelText, String value) {
        VBox fieldBox = new VBox(8);

        Label label = new Label(labelText);
        label.getStyleClass().add("field-label");

        PasswordField passwordField = new PasswordField();
        passwordField.setText(value);
        passwordField.setPromptText("••••••••");
        passwordField.getStyleClass().add("profile-field");
        passwordField.setPrefHeight(45);

        fieldBox.getChildren().addAll(label, passwordField);
        return fieldBox;
    }

    private HBox createToggleRow(String title, String subtitle) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(15, 0, 15, 0));
        row.getStyleClass().add("toggle-row");

        VBox textBox = new VBox(5);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("toggle-title");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.getStyleClass().add("toggle-subtitle");

        textBox.getChildren().addAll(titleLabel, subtitleLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        CheckBox toggleSwitch = new CheckBox();
        toggleSwitch.setSelected(true);
        toggleSwitch.getStyleClass().add("toggle-switch");

        row.getChildren().addAll(textBox, spacer, toggleSwitch);
        return row;
    }

    // Getters pour accéder aux composants
    public Button getBackButton() {
        return backButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public PasswordField getCurrentPasswordField() {
        return currentPasswordField;
    }

    public PasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }
}
